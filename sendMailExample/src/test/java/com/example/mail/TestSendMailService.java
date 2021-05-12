package com.example.mail;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {SendMailExampleApplication.class})
@ActiveProfiles({"dev"})
@SpringBootTest
public class TestSendMailService {

    @Autowired
    private SendMailService sendMailService;
    
    @Test
    public void testSendMail() {
            Map<String, Object> ctxMap = new HashMap<>();
            ctxMap.put("name", "test");
            ctxMap.put("subscriptionDate", new Date());
            ctxMap.put("hobbies", Arrays.asList("Cinema", "Sports", "Music"));
            //String[] toArr = new String[] {"hkim@foodtechkorea.com"};
            try {
                sendMailService.sendEmail( "테스트 메일주소입력", "test email send2222 ~~~", ctxMap , "emailTest") ;
            }catch(Exception e) {
                throw new RuntimeException("이메일 발송에 실패했습니다. 지속족인 오류 발송 시 관리자에게 문의해 주세요.");
            }
    }
}
