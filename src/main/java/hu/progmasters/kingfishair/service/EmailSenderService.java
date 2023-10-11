package hu.progmasters.kingfishair.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;

@Service
public class EmailSenderService {


    private JavaMailSender mailSender;


    private final TemplateEngine templateEngine;

    @Autowired
    public EmailSenderService(JavaMailSender mailSender, TemplateEngine templateEngine) {
        this.mailSender = mailSender;
        this.templateEngine = templateEngine;
    }

    public void sendEmail(String toEmail, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("kingfishairservice@gmail.com");
        message.setTo(toEmail);
        message.setSubject(subject);
        message.setText(body);

        mailSender.send(message);


    }

    public void sendSimpleWelcomeEmail(String recipient, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(recipient);
        message.setSubject("Simple Welcome");
        message.setText(body);
        mailSender.send(message);
    }



    public void sendWelcomeMailFromTemplateForRegistration(String email, String name, String confirmationLink) throws MessagingException {
        Context context = createContext(name,confirmationLink);

        String process = templateEngine.process("welcome", context);
        javax.mail.internet.MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
        helper.setSubject("Welcome from KingfishAir Ltd.");
        helper.setText(process, true);
        helper.setTo(email);
        helper.setFrom("kingfishairservice@gmail.com");
        mailSender.send(mimeMessage);
    }


    public void sendWelcomeMailFromTemplate(String email, String name,String confirmationLink) throws MessagingException {
        Context context = createContext(name, confirmationLink);

        String process = templateEngine.process("emails/welcome", context);
        javax.mail.internet.MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
        helper.setSubject("Welcome from Template");
        helper.setText(process, true);
        helper.setTo(email);
        mailSender.send(mimeMessage);
    }

    private void createHtml(){}


    private Context createContext( String name, String confirmationLink) {
        Context context = new Context();
        context.setVariable("name", name);
        context.setVariable("confirmationLink",confirmationLink);
        return context;
    }


}
