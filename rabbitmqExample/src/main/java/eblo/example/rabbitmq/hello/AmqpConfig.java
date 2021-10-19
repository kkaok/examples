package eblo.example.rabbitmq.hello;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmqpConfig {

	private final String routingKey;
	
	private final String serviceId;
	
	public AmqpConfig(@Value("${app.service.id}") String serviceId) {
		this.serviceId = serviceId;
		routingKey = serviceId+"."+UUIDGenerator.generateUniqueKeysWithUUIDAndMessageDigest();
	}

	public String getRoutingKey() {
        return routingKey;
    }

	public String getServiceId() {
		return this.serviceId;
	}

}
