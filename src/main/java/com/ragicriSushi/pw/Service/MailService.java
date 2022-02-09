package com.ragicriSushi.pw.Service;

import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
public class MailService {

    public boolean sendEmail(String email, int mex) {

        // Recipient's email ID needs to be mentioned.
        String to = email;

        // Sender's email ID needs to be mentioned
        String from = "ragicri.sushi@gmail.com";

        // Assuming you are sending email from through gmails smtp
        String host = "smtp.gmail.com";

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        // Get the Session object.// and pass username and password
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication("ragicri.sushi@gmail.com", "Ragicri1234");

            }

        });

        // Used to debug SMTP issues
        session.setDebug(true);

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            if(mex==1) {
                // Set Subject: header field
                message.setSubject("Congratulazioni, ordine effettuato!");

                message.setContent(
                        "<h1>La ringraziamo di aver scelto Ragicri-sushi!</h1>",
                        "text/html");
            }

            if(mex==2) {
                // Set Subject: header field
                message.setSubject("Registrazione completata!");

                message.setContent(
                        "<h1>Benvenuto,</h1>" +
                                "<p>la ringraziamo per essersi iscritto a Ragicri-sushi,</p>" +
                                "<p>per eventuali domande o di assistenza tecnica non esitare a scriverci!</p>" +
                                "<h5>La aspettiamo per soddisfare la sua voglia di sushi!</h5>",
                        "text/html");
            }

            System.out.println("sending...");
            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return false;
    }
}
