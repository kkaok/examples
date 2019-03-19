package eblo.example.rest.domain;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpMethod;

import lombok.Data;

@Data
public class RestParams extends BaseObject{

    private static final long serialVersionUID = 1L;

    private Map<String, String> pathInfo;   // url에 pathvariable로 이루어 지는 경우 urlInfo와 pathInfo를 이용해서 url를 만든다. 
    private Map<String, String> parameters;
    private HttpMethod httpMethod;
    private String requestBody;
    private String urlInfo;
    private String tokenInfo;
    
    public RestParams() {
        this.pathInfo = new HashMap<>();
        this.parameters = new HashMap<>();
    }
    
    public void addParameter(String key, String value) {
        this.parameters.put(key, value);
    }
    
}
