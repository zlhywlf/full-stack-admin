package zlhywlf.subscription.util;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Slf4j
public class HttpUtil {

    private static HttpURLConnection createConnection(String urlStr, String method) throws IOException {
        URL url = new URL(urlStr);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setConnectTimeout(10 * 1000);
        con.setReadTimeout(15 * 1000);
        con.setRequestMethod(method);
        return con;
    }

    private static void setHeader(HttpURLConnection con, Map<String, String> headerMap) {
        if (headerMap != null) {
            for (String key : headerMap.keySet()) {
                con.setRequestProperty(key, headerMap.get(key));
            }
        }
    }

    public static <R> R get(String urlStr, Map<String, String> headerMap, Function<HttpURLConnection, R> handle) {
        HttpURLConnection conn = null;
        try {
            conn = createConnection(urlStr, "GET");
            conn.setDoInput(true);
            if (headerMap != null) {
                setHeader(conn, headerMap);
            }
        } catch (Exception e) {
            log.error("", e);
        }
        return handle.apply(conn);
    }

    public static <R> R get(String urlStr, Function<HttpURLConnection, R> handle) {
        return get(urlStr, null, handle);
    }

    public static <R> R post(String urlStr, String body, String bodyType, Map<String, String> headerMap,
                             Function<HttpURLConnection, R> handle) {
        HttpURLConnection conn = null;
        try {
            conn = createConnection(urlStr, "POST");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            if (bodyType != null && bodyType.length() != 0) {
                conn.setRequestProperty("Content-Type", bodyType);
            }
            if (headerMap != null) {
                setHeader(conn, headerMap);
            }

            if (body != null && body.length() != 0) {
                OutputStream out = conn.getOutputStream();
                out.write(body.getBytes(StandardCharsets.UTF_8));
                out.close();
            }
        } catch (Exception e) {
            log.error("", e);
        }
        return handle.apply(conn);
    }

    public static <R> R post(String urlStr, String body, Function<HttpURLConnection, R> handle) {
        return post(urlStr, body, "application/x-www-form-urlencoded", null, handle);
    }

    public static String getResponseStringFromConn(HttpURLConnection conn) {
        if (conn == null) {
            return "conn==null";
        }
        BufferedReader reader;
        StringBuilder stringBuilder = new StringBuilder();
        try {
            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            } else {
                reader = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
            }
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            stringBuilder.append(e.getMessage());
        }
        return stringBuilder.toString();
    }

    public static Map<String, String> getResponseMapFromConn(HttpURLConnection conn) {
        Map<String, String> responseJson = new HashMap<>();
        if (conn == null) {
            responseJson.put("responseMsg", "conn==null");
            return responseJson;
        }
        try {
            responseJson.put("responseCode", String.valueOf(conn.getResponseCode()));
            String response = getResponseStringFromConn(conn);
            if ("".equalsIgnoreCase(response)) {
                responseJson.put("responseMsg", "");
            } else {
                responseJson.put("responseMsg", response);
            }
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return responseJson;
    }

}
