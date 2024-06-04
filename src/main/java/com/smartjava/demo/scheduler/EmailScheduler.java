package com.smartjava.demo.scheduler;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.smartjava.demo.service.EmailService;

import freemarker.template.TemplateException;
import jakarta.mail.MessagingException;

@Component
public class EmailScheduler {
	
	@Autowired
	private EmailService emailService;
	
	@Scheduled(cron = "0 0/5 * * * ?") 
	public void emailScheduler() throws MessagingException, IOException, TemplateException {
		String toemail="smartjava20@gmail.com";
		String subject="Scheduled Email";
		String text="This is a scheduled email sent every 5 minutes.";
		
		String image="C:\\doc\\image.jpeg";
		String pdf="C:\\doc\\data.pdf";
		
		emailService.sendEmail(toemail,subject,text,image,pdf);
		System.out.println("Scheduled email sent successfully.");
	}
	
	@Scheduled(cron = "0 0/5 * * * ?") 
	public void emailSchedulerMul() throws MessagingException {
		String[] toemail={"smartjava20@gmail.com","sweetypatil9144@gmail.com"};
		String subject="Scheduled Email Multiple Email Id";
		String text="This is a scheduled email sent every 5 minutes multiple email id.";
		String[] cc= {"smartjava20@gmail.com"};
		String[] bcc= {"smartjava20@gmail.com"};
		
		emailService.sendEmailMul(toemail,subject,text,cc,bcc);
		System.out.println("Scheduled email sent successfully For multiple email id.");
	
	}
	

}
