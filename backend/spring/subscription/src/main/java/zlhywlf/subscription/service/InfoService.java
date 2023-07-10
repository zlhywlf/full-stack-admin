package zlhywlf.subscription.service;

import zlhywlf.subscription.model.Info;

import java.util.List;
import java.util.Map;

public interface InfoService {

    String getAuthorityPath();

    String updateInfo(String code);

    void queryToken(Info info);

    boolean queryData();

    void refreshToken();

    List<Map<String, String>> getData();

}
