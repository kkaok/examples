package eblo.example.rabbitmq.hello;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmailMsgSenderTest {

    @Autowired
    private EmailMsgSender emailMsgSender;
    
    @Test
    public void testSendMsg() {
        
        Email email = new Email("info@example.com", "Hello");
        emailMsgSender.sendEmail("test.0001", email);
    }
    
}
