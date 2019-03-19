package eblo.example.rest.service;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

import eblo.example.rest.domain.RestParams;
import eblo.example.rest.exception.UnknownException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public abstract class RestClientCommonService {

    @Autowired    
    protected RestTemplate restTemplate;

    protected String postHandler(String url, HttpMethod httpMethod, HttpEntity<?> requestEntity) {
        try {
            ResponseEntity<String> resultEntity = restTemplate.exchange(url, httpMethod, requestEntity, String.class);
            if (resultEntity.getStatusCode() == HttpStatus.OK) {
                return resultEntity.getBody();
            } else {
                throw new UnknownException(resultEntity.getStatusCode().getReasonPhrase());
            }                
        }catch(ResourceAccessException e) {
            log.error(e.getMessage());
            throw new UnknownException("해당 서비스에 접근 할 수 없습니다.");  
        }catch(HttpStatusCodeException e) {
            log.error(e.getMessage());
            throw new UnknownException(e.getStatusCode().toString());  
        }
    }

    protected HttpHeaders getHeader(RestParams pRestParams) throws JsonProcessingException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.set(HttpHeaders.AUTHORIZATION, "Bearer "+pRestParams.getTokenInfo());
        return headers;
    }
    
    @HystrixCommand(fallbackMethod = "defaultService", groupKey = "RestClientCommonService", commandKey = "RestClientCommonService", threadPoolKey = "RestClientCommonService", commandProperties = {
//          @HystrixProperty(name="execution.isolation.strategy", value="SEMAPHORE")
          @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "30000"),
          @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "4"),
          @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "60000"),
          @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "180000") 
      }, threadPoolProperties = {
          @HystrixProperty(name = "coreSize", value = "30"),
          @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "180000") 
      })
    public String getService(RestParams pRestParams) throws IOException  {
        String forwardingUrl = getForwardingUrl(pRestParams);
        HttpEntity<?> requestEntity = getHttpEntity(pRestParams, getHeader(pRestParams));
        return postHandler(forwardingUrl, pRestParams.getHttpMethod(), requestEntity); 
    }
    public String defaultService(RestParams pRestParams) throws IOException  {
        throw new UnknownException("서비스가 지연되고 있습니다. 잠시 후 다시 이용해 주세요.");
    }
    
    protected abstract String getForwardingUrl(RestParams pRestParams);
    protected abstract HttpEntity<?> getHttpEntity(RestParams pRestParams, HttpHeaders headers);

    protected String makeUrl(String purl, Map<String, String> parameters) {
        //if(parameters == null || parameters.isEmpty()) return purl;
        String rUrl = purl;
        Matcher m = Pattern.compile("\\{(.*?)\\}").matcher(rUrl);
        while(m.find()) { 
            String pathvariable = m.group(1);
            if(parameters == null || !parameters.containsKey(pathvariable)) {
                throw new IllegalArgumentException(pathvariable+" 값은 필수입니다.");
            }
            String value = parameters.get(pathvariable);
            rUrl = rUrl.replaceAll("\\{"+pathvariable+"\\}", value);
        } 
        return rUrl;
    }
}
