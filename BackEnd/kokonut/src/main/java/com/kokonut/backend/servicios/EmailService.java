package com.kokonut.backend.servicios;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

@Service
public class EmailService {
	
	@Autowired
    private JavaMailSender mailSender;

    public void simpleMessage(String toEmail, String messageBody, String messageSubject) throws MessagingException {
    	final MimeMessage mimeMessage = this.mailSender.createMimeMessage();
    	final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, "UTF-8");
    	message.setFrom("pruebacdod@Prueba.com");
    	message.setTo(toEmail);
    	message.setSubject(messageSubject);
    	message.setText(messageBody);
    	this.mailSender.send(mimeMessage);
    }
    
    @Autowired
    @Qualifier("SpringTemplateEngineConfig")
    private SpringTemplateEngine templateEngine;
    
    public void templateMessageParam(String toEmail, String messageBody, String messageSubject, String template, Map<String, Object> model) throws MessagingException {
    	System.out.println("Enviando Correo...");
    	final MimeMessage mimeMessage = this.mailSender.createMimeMessage();
    	final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, "UTF-8");
    	Context context = new Context();
        context.setVariables(model);
        String html = templateEngine.process(template, context);
        message.setFrom("pruebacdod@Prueba.com");
        String to[] = {"pruebacdod@gmail.com", toEmail};
    	message.setTo(to);
    	message.setSubject(messageSubject);
    	message.setText(html, true);
    	this.mailSender.send(mimeMessage);
        
	}

    
    public void templateMessageParamMultiple(String toEmail[], String messageBody, String messageSubject, String template, Map<String, Object> model) throws MessagingException {
    	SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"); 
        model.put("fecha", dt.format(new Date()));
    	final MimeMessage mimeMessage = this.mailSender.createMimeMessage();
    	final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, "UTF-8");
    	Context context = new Context();
        context.setVariables(model);
        String html = templateEngine.process(template, context);
        message.setFrom("pruebacdod@Prueba.com");
        message.setTo(toEmail);
    	message.setSubject(messageSubject);
    	message.setText(html, true);
    	this.mailSender.send(mimeMessage);
        
	}
}
