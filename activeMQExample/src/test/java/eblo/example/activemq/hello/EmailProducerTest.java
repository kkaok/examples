package eblo.example.activemq.hello;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmailProducerTest {

    @Autowired
    private EmailProducer emailProducer;
    
    @Test
    public void testSendMsg() {
        emailProducer.sendMsg("info@example.com", "Hello");
    }

}
