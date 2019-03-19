package eblo.example.rest.test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import eblo.example.rest.domain.RestParams;
import eblo.example.rest.service.RestClientCUDService;
import eblo.example.rest.service.RestClientGetService;
import lombok.extern.slf4j.Slf4j;
 
@RestController
@Slf4j
public class DelegatorController {

    @Autowired
    @Qualifier("restClientGetService")
    private RestClientGetService restClientGetService;
    
    @Autowired
    @Qualifier("restClientCUDService")
    private RestClientCUDService restClientCUDService;

    @GetMapping(value={"/delegate/demo"}, produces="text/plain;charset=UTF-8")
    public String getService(HttpServletRequest request) throws IOException  {
        RestParams pRestParams  = new RestParams();
        // 파라미터 설정 
        Map<String, String[]> requestMap = request.getParameterMap();
        for (Map.Entry<String, String[]> entry : requestMap.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue()[0];
            pRestParams.addParameter(key, value);
        }
        // http method 설정 
        pRestParams.setHttpMethod(HttpMethod.GET);
        // url 설정
        String resourceServerUrl = "http://localhost:8084/services/{serviceId}";
        pRestParams.setUrlInfo(resourceServerUrl);
        
        // url pathvariable 설정 
        Map<String, String> pathInfo = new HashMap<>();
        pathInfo.put("serviceId", "1234");
        pRestParams.setPathInfo(pathInfo);
        // token 설정 
        pRestParams.setTokenInfo("tokenInfo");
        return restClientGetService.getService(pRestParams);
    }

    @PostMapping(value={"/delegate/demo"}, produces="text/plain;charset=UTF-8")
    public String postService(HttpServletRequest request) throws IOException  {
        RestParams pRestParams  = new RestParams();
        // 파라미터 설정 
        String jsonFormatRequestBody = "{'userId':'testId', 'userPwd':'1234'}";
        pRestParams.setRequestBody(jsonFormatRequestBody);
        // http method 설정 
        pRestParams.setHttpMethod(HttpMethod.POST);
        // url 설정
        String resourceServerUrl = "http://localhost:8084/services/{serviceId}";
        pRestParams.setUrlInfo(resourceServerUrl);
        
        // url pathvariable 설정 
        Map<String, String> pathInfo = new HashMap<>();
        pathInfo.put("serviceId", "1234");
        pRestParams.setPathInfo(pathInfo);
        // token 설정 
        pRestParams.setTokenInfo("tokenInfo");
        return restClientCUDService.getService(pRestParams);
    }

}
