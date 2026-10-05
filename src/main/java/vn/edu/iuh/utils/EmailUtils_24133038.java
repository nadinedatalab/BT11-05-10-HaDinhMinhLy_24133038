package vn.edu.iuh.utils;

import java.util.Properties;
import java.util.Random;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailUtils_24133038 {
    public static String sendOTP(String toEmail) {
        String otp = generateOTP();
        
        String from = "aligned.withmly@gmail.com"; 
        String password = "cipl ldwz jgvw obys"; 

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject("Ma OTP Dang Ky Tai Khoan");
            message.setText("Ma OTP cua ban la: " + otp);
            Transport.send(message);
        } catch (Exception e) {
            e.printStackTrace();
            return null; // For exam purpose, we might just bypass real email sending or let it fail gracefully
        }
        return otp;
    }

    private static String generateOTP() {
        Random rnd = new Random();
        int number = rnd.nextInt(999999);
        return String.format("%06d", number);
    }
}
