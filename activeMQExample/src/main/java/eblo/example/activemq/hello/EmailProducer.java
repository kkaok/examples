package eblo.example.activemq.hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class EmailProducer {

    @Autowired
    private JmsTemplate jmsTemplate;
    
    @Value("${destination.topic}")
    private String topic;
    
    public void sendMsg(String email, String msg) {
        jmsTemplate.setPubSubDomain(false);
        jmsTemplate.convertAndSend("mailbox", new Email(email, msg));
//        System.out.println("email : "+email);
//        System.out.println("message : "+msg);
        System.out.println("Sending an email message.");
    }

    public void sendTopicMsg(String topicStr, String email, String msg) {
        jmsTemplate.setPubSubDomain(true);
        jmsTemplate.convertAndSend(topicStr, new Email(email, msg));
//        System.out.println("email : "+email);
//        System.out.println("message : "+msg);
        System.out.println("Sending an topic email message.");
    }
}
