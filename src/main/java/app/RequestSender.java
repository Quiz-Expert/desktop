package app;

import com.google.api.client.http.*;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.jayway.jsonpath.JsonPath;
import java.io.IOException;
import java.util.List;

public class RequestSender {

    private static String token;
    public HttpResponse response;
    public HttpRequest request;

    public RequestSender(String method, String urlPart, String data) throws IOException {
        GenericUrl genericUrl = new GenericUrl("http://localhost:80/api/" + urlPart);
        HttpRequestFactory requestFactory = new NetHttpTransport().createRequestFactory();
        request = requestFactory.buildRequest(method, genericUrl, ByteArrayContent.fromString("application/json", data));
        request.setHeaders(new HttpHeaders().setAuthorization("Bearer " + token));
        response = request.execute();
        if (urlPart.equals("auth/login") || urlPart.equals("auth/register")){
            token = JsonPath.read(response.parseAsString(), "$.data");
        } else if (urlPart.equals("auth/logout")){
            token = "";
        }
    }

    public List<String> getStringListResponseValues(String query){
        List<String> values = null;
        try {
            values = JsonPath.parse(response.parseAsString()).read(query);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return values;
    }

    public int getIntResponseValue(String query){
        int value = 0;
        try {
            value = JsonPath.parse(response.parseAsString()).read(query, int.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return value;
    }

    public String getStringResponseValue(String query){
        String value = null;
        try {
            value = JsonPath.parse(response.parseAsString()).read(query);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return value;
    }
}
