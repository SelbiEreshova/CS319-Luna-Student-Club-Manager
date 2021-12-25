package luna.clubverse.backend.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Properties;

public class MailManager {

    //@Autowired
    private JavaMailSender mailSender;

    private static MailManager mailManager;

    private MailManager() {}

    public static MailManager getMailManager() {
        if(mailManager == null) {

            mailManager = new MailManager();

            JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

            mailSender.setHost("smtp.gmail.com");
            mailSender.setPort(587);
            

            Properties props = new Properties();

            props.put("mail.transport.protocol", "smtp");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.debug", "true");

            mailSender.setJavaMailProperties(props);

            mailManager.setMailSender(mailSender);
        }
        return mailManager;
    }

    public void setMailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendSimpleMessage(String to, String subject, String text) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("noreply@baeldung.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);

        mailSender.send(message);
    }
}
