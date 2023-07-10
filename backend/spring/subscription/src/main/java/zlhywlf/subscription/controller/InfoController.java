package zlhywlf.subscription.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;
import zlhywlf.subscription.service.InfoService;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@RestController
@Slf4j
public class InfoController {

    InfoService service;

    @GetMapping("/")
    public String home() {
        return "<button><a href=\"" + service.getAuthorityPath() + "\">认证</a></button>";
    }

    @GetMapping("/registered")
    public RedirectView registered(String code, String state, @RequestParam("session_state") String sessionState) {
        log.info("state: {}, sessionState: {}", state, sessionState);
        return new RedirectView(service.updateInfo(code));
    }

    @GetMapping("/getInfo")
    public List<Map<String, String>> getInfo() {
        return service.getData();
    }

    @GetMapping("/queryData")
    public RedirectView queryData() {
        return new RedirectView(service.queryData() ? "/getInfo" : "/refresh");
    }

    @GetMapping("/refresh")
    public RedirectView refresh() {
        service.refreshToken();
        if (!service.queryData()) {
            return new RedirectView("/error");
        }
        return new RedirectView("/getInfo");
    }

    @GetMapping("/error")
    public String error() {
        return "请重新授权";
    }

}
