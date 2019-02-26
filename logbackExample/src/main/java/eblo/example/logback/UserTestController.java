package eblo.example.logback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserTestController {

    private static final Logger logger = LoggerFactory.getLogger(UserTestController.class);
    
    @GetMapping("/test")
    public String test() {
        logger.info("test");
        logger.warn("test");
        logger.error("test");
        
        return "test";
    }
    
}
