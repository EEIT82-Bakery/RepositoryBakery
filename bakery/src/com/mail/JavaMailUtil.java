package com.mail;
// https://www.google.com/settings/security/lesssecureapps


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class JavaMailUtil {
	private static String userid;
	private static String password;

	MimeMessage message;
	String from;
	String subject;
	String text;
	String to;
	List<String> attachment;
	
	public JavaMailUtil(String from, String to, String subject,  String text, List<String> attachment
			) {

		this.from = from;
		this.to=to;
		
		if (subject == null) {
		   this.subject = "";
		} else {
		   this.subject = subject;
		}
		if (text == null) {
			   this.text = "";
			} else {
			   this.text = text;
			}
		if (attachment == null){
			this.attachment = new ArrayList<>();
		} else {
			this.attachment = attachment;
		}
	}

	public boolean send() {
		try {
			createSession();
			setAddresses();
			setSubject();
			addContent();
			Transport.send(message);
			return true;
		} catch (MessagingException ex) {
			String msg= ex.getMessage();
			if (msg.indexOf("Please log in via your web browser") > 0){
			} else {
        		System.out.println(ex.getMessage());
			}
			return false;
		} catch (IOException ex) {
			System.out.println(ex.getMessage());
			return false;
		}
	}

	public void addContent() throws MessagingException, IOException {
		Multipart multipart = new MimeMultipart();
		MimeBodyPart bodyPart0 = new MimeBodyPart();
		int idx1 = tokenCount(text, "<");
		int idx2 = tokenCount(text, ">");
		System.out.println(text);
		System.out.println(idx1);
		System.out.println(idx2);
		if (idx1 == idx2 && idx1 != -1) {
			bodyPart0.setText(text, "UTF-8", "html");
		} else {
			bodyPart0.setText(text, "UTF-8", "plain");
		}
		multipart.addBodyPart(bodyPart0);
		for (String file : attachment) {
			MimeBodyPart bodyPart2 = new MimeBodyPart();
			bodyPart2.attachFile(new File(file));
			bodyPart2.setFileName(getFileName(file));
			multipart.addBodyPart(bodyPart2);
		}
		message.setContent(multipart);

	}


	public void setSubject() throws MessagingException {
		message.setSubject(subject);
	}

	public void setAddresses() throws AddressException, MessagingException {
		
		message.setFrom(new InternetAddress(from));
		message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
//	
//		List<Address> listTO = new ArrayList<>();
//		for (String s : to) {
//			listTO.add(new InternetAddress(s));
//		}
//		message.addRecipients(Message.RecipientType.TO,
//				listTO.toArray(new Address[0]));

	}

	public void createSession() {
		Session session;
		Properties props = new Properties();
//		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.host", "smtp.sendgrid.net");
//		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.port", "587");		
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
	
		props.put("mail.smtp.auth", "true");
		Authenticator au = new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
//				return new PasswordAuthentication("pyeongjing228@gmail.com", "kimbumsoo0130");
				return new PasswordAuthentication("azure_c034c6e7f96f5a805f2e81d0faf5bb46@azure.com", "VEnoH433ERrgNBl");
			}
		};

		session = Session.getDefaultInstance(props, au);
		message = new MimeMessage(session);
	}

	private static String getFileName(String path) {
		return path.substring(path.lastIndexOf("\\") + 1);
	}
	
	private static int tokenCount(String target, String token) {
		int lastIndex = 0;
		int count = 0;

		while(lastIndex != -1){

		    lastIndex = target.indexOf(token,lastIndex);

		    if(lastIndex != -1){
		        count ++;
		        lastIndex += token.length();
		    }
		}
		return count;
	}

	static {
		userid = System.getProperty("JavaMailUserId");
		password = System.getProperty("JavaMailPassword");
		
	}
}
