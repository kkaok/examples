package eblo.example.logback;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class LombokTestController {

    @GetMapping("/lombok-test")
    public String test() {
        log.info("test");
        log.warn("test");
        log.error("test");
        
        return "test";
    }
    
}
