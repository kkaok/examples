package eblo.example.rest.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import eblo.example.rest.util.RestTemplateUtil;
import lombok.extern.slf4j.Slf4j;

/*
 * https://stackoverflow.com/questions/19112121/check-available-connections-in-poolingclientconnectionmanager
 * https://tech.asimio.net/2016/12/27/Troubleshooting-Spring-RestTemplate-Requests-Timeout.html
 * 
 */
@RestController
@Slf4j
public class DelegatorController {

    @Autowired    
    private RestTemplate restTemplate;

    @Autowired    
    private RestTemplateUtil restTemplateUtil;
    
    @GetMapping("/delegate/demo")
    public String getDemoDelegate() {
        // 대기 수 체크 
        if(!restTemplateUtil.checkPending()) {
            return "대기 요청이 많아서 처리가 지연되고 있습니다. 잠시 후 다시 이용해 주세요.";
        }
        // 화면에 모니터링 로그 남기기 
        log.info(restTemplateUtil.createHttpInfo());
        try {
            return this.restTemplate.getForObject("http://localhost:8080/demo", String.class);
        }catch(Exception e) {
            // 화면에 모니터링 로그 남기기 
            log.error(restTemplateUtil.createHttpInfo());
            return e.getMessage();
        }
    }
}
