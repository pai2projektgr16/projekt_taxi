package auth;

import java.util.Date;
import java.util.Properties;
import javax.ejb.Stateless;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 * DO wysyłania maili
 *
 * @author Dominik Zawadzki && Adrian Dorota
 */
@Stateless
public class EmailSessionBean {

    private int port = 465; //tls 587
    private String host = "smtp.gmail.com";
    private String from = "taxiprojectjavaee@gmail.com";
    private boolean auth = true;
    private String username = "taxiprojectjavaee@gmail.com";
    private String password = "asdwsx1qaz";
    private boolean debug = true;

    public boolean sendEmail(String to, String subject, String htmlContent) {
	Properties props = new Properties();
	props.put("mail.smtp.host", host);
	props.put("mail.smtp.port", port);
	props.put("mail.smtp.ssl.enable", true);

	Authenticator authenticator = null;
	if (auth) {
	    props.put("mail.smtp.auth", true);
	    authenticator = new Authenticator() {
		private PasswordAuthentication pa = new PasswordAuthentication(username, password);

		@Override
		public PasswordAuthentication getPasswordAuthentication() {
		    return pa;
		}
	    };
	}

	Session session = Session.getInstance(props, authenticator);
	session.setDebug(debug);

	MimeMessage message = new MimeMessage(session);

	try {
	 
	    message.setFrom(new InternetAddress(from));
	    InternetAddress[] address = {new InternetAddress(to)};
	    message.setRecipients(Message.RecipientType.TO, address);
	    message.setSubject(subject);
	    message.setSentDate(new Date());
	    //message.setText(body);

	    Multipart multipart = new MimeMultipart("alternative");
	    MimeBodyPart htmlPart = new MimeBodyPart();
	    htmlPart.setContent(htmlContent, "text/html; charset=UTF-8");
	    multipart.addBodyPart(htmlPart);

	    message.setContent(multipart, "text/html; charset=UTF-8");
	    Transport.send(message);
	    return true;
	} catch (MessagingException ex) {
	    ex.printStackTrace();
	}

	return false;
    }

}
