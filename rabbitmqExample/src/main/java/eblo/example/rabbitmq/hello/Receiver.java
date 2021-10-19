package eblo.example.rabbitmq.hello;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class Receiver {

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue,
            exchange = @Exchange(value = "amq.topic", type = "topic", durable = "true"), //
            key = "testAll"))
    public void handleMsg1(Email in) {
        System.out.println(in.toString());
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue,
            exchange = @Exchange(value = "amq.topic", type = "topic", durable = "true"), //
            key = "test.0001"))
    public void handleMsgKey(Email in) {
        System.out.println(in.toString());
    }

}