package eblo.example.activemq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@EnableJms
@ComponentScan("eblo.example.activemq")  // 스캔 범위 지정
public class ActiveMqExampleApplication {

    public static void main(String[] args) {
      SpringApplication.run(ActiveMqExampleApplication.class, args);
//      ConfigurableApplicationContext context = SpringApplication.run(ActiveMqExampleApplication.class, args);
//      Producer producer = context.getBean(Producer.class);
//      producer.sendMsg("info@example.com", "Hello");
    }

}