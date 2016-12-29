package com.intesys.trial.journals.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.intesys.trial.journals.model.Journal;
import com.intesys.trial.journals.service.EmailService;
import com.intesys.trial.journals.service.JournalService;
import com.intesys.trial.journals.service.UserService;

@Configuration
@EnableScheduling
public class MvcConfig extends WebMvcConfigurerAdapter {
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private JournalService journalService;
	
	@Autowired
	private UserService userService;
	
    @Value("${email.host}")
    private String host;

    @Value("${email.port}")
    private Integer port;

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("home");
        registry.addViewController("/journals").setViewName("journals");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/publisher/browse").setViewName("publisher/browse");
        registry.addViewController("/403").setViewName("403");
    }
    
    @Bean
    public JavaMailSender javaMailService() {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost(host);
        javaMailSender.setPort(port);
        return javaMailSender;
    }
    
    @Scheduled(cron="00:00 * * * MON-FRI")
    public void doSomething() {
    	List<Journal> journals = journalService.getNew();
    	emailService.sendEmail(users, journal);
    }

}