package com.smartjava.demo;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.smartjava.demo.scheduler.EmailScheduler;

import freemarker.template.TemplateException;
import jakarta.mail.MessagingException;

@SpringBootApplication
@EnableScheduling
public class EmailSchedulerApplication {

	public static void main(String[] args) throws MessagingException, IOException, TemplateException {
		SpringApplication.run(EmailSchedulerApplication.class, args);
		
		ConfigurableApplicationContext context = SpringApplication.run(EmailSchedulerApplication.class, args);
		
		 EmailScheduler job = context.getBean(EmailScheduler.class);
		 job.emailScheduler();
	}

}
