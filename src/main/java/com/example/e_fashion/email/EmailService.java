package com.example.e_fashion.email;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static java.nio.charset.StandardCharsets.UTF_8;

@Service
@RequiredArgsConstructor
@Async
public class EmailService {
    private final JavaMailSender mailSender;
    private final SpringTemplateEngine templateEngine;

    public void sendEmail(
            String to,
            String user,
            String subject,
            EmailTemplateName emailTemplateName,
            String activateCode,
            String confirmationUrl
    ) throws MessagingException {
        String templateName = emailTemplateName == null ? "confirm-email" : emailTemplateName.name();
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, MimeMessageHelper.MULTIPART_MODE_MIXED, UTF_8.name());

        Map<String, Object> properties = new HashMap<>();
        properties.put("username", user);
        properties.put("activation_code", activateCode);
        properties.put("confirmation_url", confirmationUrl);

        Context context = new Context();
        context.setVariables(properties);

        mimeMessageHelper.setFrom("contact@e-fashion.com");
        mimeMessageHelper.setTo(to);
        mimeMessageHelper.setSubject(subject);

        String template = templateEngine.process(templateName, context);
        mimeMessageHelper.setText(template, true);
        mailSender.send(mimeMessage);
    }
}
