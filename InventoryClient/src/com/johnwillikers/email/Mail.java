package com.johnwillikers.email;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.json.JSONException;
import org.json.JSONObject;

import com.johnwillikers.Core;
import com.johnwillikers.io.In;

public class Mail {

	public String email;
	public String subject;
	public String msg;
	public String authEmail;
	public String authPassword;
	
	public Mail(String email, String subject, String msg){
		this.email = email;
		this.subject = subject;
		this.msg = msg;
	}
	
	//TODO enable this function to auto-load details from a .json file.
	public void getEmailSettings(){
		try {
			JSONObject settings = In.readItem(Core.settingsFile);
			this.authEmail = settings.getString("authEmail");
			this.authPassword = settings.getString("authPassword");
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void sendMail(){
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");
		
		Session session = Session.getDefaultInstance(props, new Authenticator(){
			protected PasswordAuthentication getPasswordAuthentication(){
				return new PasswordAuthentication("lonelyretardxd@gmail.com", "labeasts2016");
			}
		});
		
		try{
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("lonelyretardxd@gmail.com"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(this.email));
			message.setSubject(this.subject);
			message.setText(this.msg);
			Transport.send(message);
			System.out.print("Message has been Sent");
		}catch(MessagingException e){
			e.printStackTrace();
		}
	}
	
}
