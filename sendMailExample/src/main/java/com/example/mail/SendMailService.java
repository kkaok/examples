package com.example.mail;

import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class SendMailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private TemplateEngine emailTemplateEngine;
    
    /**
     * @param to 받는 사람 
     * @param emailTitle 이메일 제목 
     * @param params 템플릿 바인딩 값들  
     * @param templateHtml 템플릿 주소 "templates/mail 아래에 html 파일을 넣는다. ".html" 은 생략 
     * @throws MessagingException
     */
    public void sendEmail(String to, String emailTitle , Map<String, Object> params, String templateHtml) throws MessagingException {
        sendEmail( new String[] {to}, emailTitle, params, templateHtml );
    }

    /**
     * @param to 받는 사람 여러명  
     * @param emailTitle 이메일 제목 
     * @param params 템플릿 바인딩 값들  
     * @param templateHtml 템플릿 주소 "templates/mail 아래에 html 파일을 넣는다. ".html" 은 생략 
     * @throws MessagingException
     */
    public void sendEmail(String[] to, String emailTitle , Map<String, Object> params, String templateHtml) throws MessagingException {
        final Context ctx = new Context();
        for (String key : params.keySet()) { 
            ctx.setVariable(key, params.get(key));
        }
        final String htmlContent = this.emailTemplateEngine.process(templateHtml, ctx);
        sendEmailCommon(ctx,  to, emailTitle, htmlContent );
    }

    private void sendEmailCommon(final Context ctx, String[] to, String emailTitle , String emailBody) throws MessagingException {
        final MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8"); // true = multipart
        message.setTo(to);
        message.setSubject(emailTitle);
        message.setText(emailBody, true); // true = isHtml
        javaMailSender.send(mimeMessage);
    }
}

