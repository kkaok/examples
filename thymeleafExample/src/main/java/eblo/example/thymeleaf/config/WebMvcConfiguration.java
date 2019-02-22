package eblo.example.thymeleaf.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class WebMvcConfiguration extends WebMvcConfigurationSupport {

    private static final String CLASSPATH_RESOURCE_LOCATIONS = "classpath:/static/";

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/assets/**").addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS+"assets/").setCachePeriod(31536000);
        registry.addResourceHandler("/vendor/**").addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS+"vendor/").setCachePeriod(31536000);
        registry.addResourceHandler("/html/**").addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS+"html/").setCachePeriod(31536000);
    }
    
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        /*공통*/
        registry.addViewController("/exampleLayout").setViewName("exampleLayout");                            
    }
}
