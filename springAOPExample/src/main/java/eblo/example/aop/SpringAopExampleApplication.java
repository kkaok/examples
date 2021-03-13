package eblo.example.aop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ComponentScan("eblo.example")
public class SpringAopExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringAopExampleApplication.class, args);
	}

}
