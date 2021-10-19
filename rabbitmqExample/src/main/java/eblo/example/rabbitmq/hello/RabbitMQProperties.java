package eblo.example.rabbitmq.hello;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import lombok.Getter;
import lombok.Setter;

@Configuration
@PropertySource({ "classpath:conf/${spring.profiles.active}/rabbitmq-settings.properties" })
@ConfigurationProperties(prefix = "spring.rabbitmq")
@Getter
@Setter
public class RabbitMQProperties {

	private static final long serialVersionUID = -8213601232007786579L;

	private String host;
	private int port;
	private String username;
	private String password;

}
