package email;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.Map.Entry;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailSender {
	String message;

	public void sendSSLMessage(List<String> recipients,String message, String subject) throws MessagingException, UnsupportedEncodingException 
	{
		boolean debug = true;
		Properties prop = new Properties();
		InputStream input = null;
		Set<Entry<Object, Object>> value=null;

		try {
			String filename = "properties_en.properties";
			input = getClass().getClassLoader().getResourceAsStream( "email/properties_en.properties");
		// load a properties file
			prop.load(input);
 
		// get the property value and print it out
			value=prop.entrySet();
 
	} catch (IOException ex) {
		ex.printStackTrace();
	} 
	
	String SMTP_HOST_NAME = prop.getProperty("SMTP_HOST_NAME");
	String SMTP_PORT= prop.getProperty("SMTP_PORT");
	final String emailFromAddress= prop.getProperty("emailFromAddress");
	final String emailPassword= prop.getProperty("emailPassword");
	
	Properties props = new Properties();
	props.put("mail.smtp.host", SMTP_HOST_NAME);
	props.put("mail.smtp.auth", "true");
	props.put("mail.smtp.starttls.enable", "true");
	props.put("mail.debug", "true");
	props.put("mail.smtp.port", SMTP_PORT);
	props.put("mail.smtp.socketFactory.port", SMTP_PORT);
	/*props.put("mail.smtp.socketFactory.class", SSL_FACTORY);*/
	/*props.put("mail.smtp.socketFactory.fallback", "false");*/

	Session session = Session.getDefaultInstance(props,
	new javax.mail.Authenticator() {

	protected PasswordAuthentication getPasswordAuthentication() {
	return new PasswordAuthentication(emailFromAddress,emailPassword);
	}
	});
	
	session.setDebug(debug);
	
	try {
		Message msg = new MimeMessage(session);
		Address addressFrom = new InternetAddress(emailFromAddress, "ABC Pte Ltd (Administrator)");
		msg.setFrom(addressFrom);
	
		InternetAddress[] addressTo = new InternetAddress[recipients.size()];
		for (int i = 0; i < recipients.size(); i++) {
		addressTo[i] = new InternetAddress(recipients.get(i));
		}
		msg.setRecipients(Message.RecipientType.TO, addressTo);
	
		// Setting the Subject and Content Type
		msg.setSubject(subject);
	
		//setting message mime type to text/plain
		msg.setContent(messages, "text/plain");
		Transport.send(msg);
		
		this.message = "Email successfully sent";
		
	}catch (Exception e) {
		this.message = "Messaging Exception, Error:" + e.getMessage();

    }
	System.out.println(this.message);
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
