package com.intesys.trial.journals.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;
import org.springframework.ui.velocity.VelocityEngineUtils;

import com.intesys.trial.journals.model.Journal;
import com.intesys.trial.journals.model.User;

@Component
public class EmailService {
	
	@Autowired
    private JavaMailSender mailSender;
	
	@Autowired    
    private VelocityEngine velocityEngine;
	
	@Value("${email.sender}")
	private String sender;
	
    public void sendEmail(final List<User> users, Journal journal) {
    	for (User user : users) {
	        MimeMessagePreparator preparator = new MimeMessagePreparator() {
	            public void prepare(MimeMessage mimeMessage) throws Exception {
	                MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
	                message.setTo(user.getEmail());
	                message.setFrom(sender);
	                Map<String, Object> model = new HashMap<String, Object>();
	                model.put("userName", user);
	                model.put("journal", journal.getName());
	                String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "new-journal.vm", "UTF-8", model);
	                message.setText(text, true);
	            }
	        };
	        mailSender.send(preparator);
    	}
    }

}
