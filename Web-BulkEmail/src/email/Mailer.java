package email;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.mail.MessagingException;

import com.opensymphony.xwork2.ActionSupport;

import email.MailSender;

public class Mailer extends ActionSupport {
	private static final long serialVersionUID = -1905974197186620917L;
	private String to;
	private String subject;
	private String message;
	private String msg;

// 	execute() method to extract information from the user interface such as recipients(to), subject and message.
	public String execute() throws Exception {
		
		List<String> recipients_confirm = new ArrayList<String>();
		
//		Functionality to extract the recipient list from the HTML form
	    String[] recipients = to.split(";");
	    for(int i = 0; i < recipients.length; i++) {
			int j = 0;
			if(isValid(recipients[i])) {
				System.out.println(recipients[i] + " " +"valid");
				recipients_confirm.add(recipients[j]);
			}
	    }				
		MailSender mailSender = new MailSender();
		
		try {
			mailSender.sendSSLMessage(recipients_confirm, message, subject);
			this.msg = "Email successfully sent";
						
		} catch (MessagingException e) {
			e.printStackTrace();
			this.msg = "Email not sent";
		}
		
		return "SENDS";
					
	}

//	Functionality to validate that the recipients email are of the correct format
	public static boolean isValid(String email) {
				
		String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
		return email.matches(regex);
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
