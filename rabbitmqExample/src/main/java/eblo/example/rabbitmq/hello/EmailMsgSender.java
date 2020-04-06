package eblo.example.rabbitmq.hello;

import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailMsgSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private TopicExchange topic;

    public void sendEmail(String routingKey, Email email){
        rabbitTemplate.convertAndSend(topic.getName(), routingKey, email);
    }

}
