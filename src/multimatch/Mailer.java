package multimatch;


import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.*;
import javax.swing.JOptionPane;


/**
 *
 * @author Ethan
 */
public class Mailer {
    String results;
    int random;
    
    public Mailer(String r) {
        results = r;
        random = (int)(Math.random()*10000);
        
        sendResults();
    }
    
    private void sendResults() {
        String address = "multimatch.results@gmail.com";
        String password = "multimatch";
        
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com"); 
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.fallback", "false");

        Session session = Session.getInstance(props,
          new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(address, password);
            }
          });

        
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(address));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(address));
            message.setSubject("Results #"+random);
            message.setText(results);
            Transport.send(message);
        } catch (MessagingException me) {
            me.printStackTrace();
            Writer writer = null;
            try {
                writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream("multimatch_results_"+random+".txt"),"utf-8"));
                writer.write(results);
                JOptionPane.showMessageDialog(null, "Email failed; results written to file.");
            } catch (Exception ex) {
            } finally {
                try {writer.close();} catch (Exception e) {};
            }
        
        }
    }
}
