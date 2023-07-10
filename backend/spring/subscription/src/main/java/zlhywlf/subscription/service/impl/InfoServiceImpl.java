package zlhywlf.subscription.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import zlhywlf.subscription.config.InfoConfig;
import zlhywlf.subscription.model.Info;
import zlhywlf.subscription.repository.InfoRepository;
import zlhywlf.subscription.service.InfoService;
import zlhywlf.subscription.util.HttpUtil;

import java.net.HttpURLConnection;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Function;

@Slf4j
@RequiredArgsConstructor
@Service
public class InfoServiceImpl implements InfoService {

    private final InfoConfig config;
    private final InfoRepository repository;
    private final Function<InfoRepository, Info> func = r -> {
        Info info = r.findById(1).orElse(null);
        if (info == null) {
            throw new RuntimeException("info is null.");
        }
        return info;
    };
    private final ObjectMapper mapper = new ObjectMapper();
    @Getter
    private final List<Map<String, String>> data = new CopyOnWriteArrayList<>();

    @Override
    public String getAuthorityPath() {
        return config.authorityUrl +
                "?" +
                "client_id=" +
                config.clientId +
                "&response_type=code" +
                "&redirect_uri=" + config.redirectUrl +
                "&response_mode=query" +
                "&scope=" + config.scope +
                "&state=" + UUID.randomUUID();
    }

    @Override
    public String updateInfo(String code) {
        Info info = repository.findById(1).orElse(null);
        if (info == null) {
            info = new Info();
            info.setId(1);
        }
        info.setCode(code);
        repository.save(info);
        return "/getInfo";
    }

    @Override
    public void queryToken(Info info) {
        String tokenBody = "client_id=" + config.clientId +
                "&grant_type=authorization_code" +
                "&client_secret=" + config.secret +
                "&redirect_uri=" + config.redirectUrl +
                "&code=" + info.getCode();
        Map<String, String> jsonObject = HttpUtil.post(config.tokenUrl, tokenBody, HttpUtil::getResponseMapFromConn);
        int responseCode = Integer.parseInt(jsonObject.get("responseCode"));
        if (responseCode == HttpURLConnection.HTTP_OK) {
            try {
                var responseMsg = mapper.readValue(jsonObject.get("responseMsg"), Map.class);
                info.setAccessToken(String.valueOf(responseMsg.get("access_token")));
                info.setRefreshToken(String.valueOf(responseMsg.get("refresh_token")));
                repository.save(info);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
            return;
        }
        info.setRefreshToken(null);
        info.setAccessToken(null);
        repository.save(info);
        log.warn("获取 token 失败, 尝试重新授权!");
    }

    @Override
    public boolean queryData() {
        if (this.data.size() > 12) {
            this.data.clear();
        }
        Info info = func.apply(repository);
        String accessToken = info.getAccessToken();
        if (accessToken == null || accessToken.length() == 0) {
            queryToken(info);
        }
        HashMap<String, String> header = new HashMap<>(2);
        header.put("Authorization", "Bearer " + info.getAccessToken());
        header.put("Accept", "application/json");
        Map<String, String> result = HttpUtil.get(
                "https://graph.microsoft.com/v1.0/me/messages?$select=sender,subject",
                header, HttpUtil::getResponseMapFromConn);
        int responseCode = Integer.parseInt(result.get("responseCode"));
        if (responseCode == HttpURLConnection.HTTP_OK) {
            Map<String, String> data = new HashMap<>();
            data.put("||" + LocalDate.now() + "||" + LocalTime.now() + "||",
                    String.valueOf(result.get("responseMsg").length()));
            this.data.add(data);
            return true;
        }
        return false;
    }

    @Override
    public void refreshToken() {
        log.info("尝试刷新token");
        Info info = func.apply(repository);
        String refreshBody = "client_id=" + config.clientId +
                "&scope=" + config.scope +
                "&client_secret=" + config.secret +
                "&grant_type=refresh_token" +
                "&refresh_token=" + info.getRefreshToken();
        Map<String, String> result = HttpUtil.post(config.tokenUrl,
                refreshBody, HttpUtil::getResponseMapFromConn);
        int responseCode = Integer.parseInt(result.get("responseCode"));
        if (responseCode == HttpURLConnection.HTTP_OK) {
            try {
                var responseMsg = mapper.readValue(result.get("responseMsg"), Map.class);
                info.setAccessToken(String.valueOf(responseMsg.get("access_token")));
                repository.save(info);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        } else {
            queryToken(info);
        }
    }

}
