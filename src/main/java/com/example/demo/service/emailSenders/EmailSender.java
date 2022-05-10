package com.example.demo.service.emailSenders;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailSender extends Thread {
    private String from;
    private String to;
    protected String messageStr;
    protected String subject;
    private final String HOST = "smtp.teol.net";

    public EmailSender(String from, String to, String subject, String messageStr) {
        this.from = "pop.s@blic.net";
        this.to = to;
        this.subject = subject;
        this.messageStr = messageStr;
    }

    @Override
    public void run() {
        Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host", HOST);
        Session session = Session.getDefaultInstance(properties);
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject(subject);
            message.setText(messageStr, "utf-8");
            Transport.send(message);
            System.out.println("Sent message successfully");
        } catch (MessagingException mex) {
            System.out.println("Message sending failed");
        }
    }
}
