package eblo.example.activemq.hello;

import java.util.concurrent.CountDownLatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Receiver {

    @JmsListener(destination = "mailbox", containerFactory = "myFactory")
    public void receiveMessage(Email email) {
        System.out.println("Received <" + email + ">");
    }

    @JmsListener(destination = "test1", containerFactory = "pubsubFactory")
    public void receiveMessage1(Email email) {
        System.out.println("TOPIC Received1 <" + email + ">");
    }
    
    @JmsListener(destination = "test2", containerFactory = "pubsubFactory")
    public void receiveMessage2(Email email) {
        System.out.println("TOPIC Received2 <" + email + ">");
    }
    
}