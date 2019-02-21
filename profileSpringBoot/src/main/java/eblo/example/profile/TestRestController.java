package eblo.example.profile;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestRestController {

    @Value("${app.title}")
    private String appTitle;
    
    @GetMapping("/test")
    public String test() {
        
        return appTitle;
    }
}
