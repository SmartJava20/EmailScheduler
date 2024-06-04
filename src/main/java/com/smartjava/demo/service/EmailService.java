package com.smartjava.demo.service;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {
	
	@Autowired 
	private JavaMailSender javaMailSender;
	
	@Autowired
    private Configuration freemarkerConfig;

	public void sendEmail(String toemail, String subject, String text, String image, String pdf) throws MessagingException, IOException, TemplateException {
		MimeMessage message=javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		helper.setTo(toemail);
	 	helper.setSubject(subject);
			
		   Map<String, Object> model = new HashMap<>();
	       model.put("text", text);
	       String htmlText = FreeMarkerTemplateUtils.processTemplateIntoString(loadTemplate("email-template.ftl"), model);
           helper.setText(htmlText, true);

         if(image != null) {
        	    File imageFile = new File(image);
        	    helper.addInline("Image", imageFile);
         }
           
         if(pdf !=null) {
        	 File pdfFile = new File(pdf);
     	    helper.addAttachment("Document.pdf", pdfFile);
         }
		
		javaMailSender.send(message);
	}

	private Template loadTemplate(String templateName) throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException {
		
		return freemarkerConfig.getTemplate(templateName);
	}

	public void sendEmailMul(String[] toemail, String subject, String text, String[] cc, String[] bcc) throws MessagingException {
		MimeMessage message=javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		helper.setTo(toemail);
	 	helper.setSubject(subject);
		helper.setText(text);
		
		 if (cc != null && cc.length > 0) {
	            helper.setCc(cc);
	        }
	        if (bcc != null && bcc.length > 0) {
	            helper.setBcc(bcc);
	        }
		javaMailSender.send(message);
	}

}
