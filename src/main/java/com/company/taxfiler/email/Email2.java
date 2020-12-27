package com.company.taxfiler.email;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class Email2 {

	private static final String SMTP_HOST_NAME = "smtpout.asia.secureserver.net"; // smtp URL
	private static final int SMTP_HOST_PORT = 465; // port number
	private static String SMTP_AUTH_USER = "admin@serenetax.in"; // email_id of sender
	private static String SMTP_AUTH_PWD = "Gurukuntla"; // password of sender email_id
	

	public static void sendImageEmail(String toEmail, String subject, String body) {
		try {
			
			Session mailSession = getSession();
			Transport transport = mailSession.getTransport();
			MimeMessage message = new MimeMessage(mailSession);
			message.setSubject(subject);
			message.setContent(body, "text/html");
			Address[] from = InternetAddress.parse(SMTP_AUTH_USER);// Your domain email
			message.addFrom(from);
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));

			// Create the message body part
			BodyPart messageBodyPart = new MimeBodyPart();

			messageBodyPart.setText(body);

			// Create a multipart message for attachment
			Multipart multipart = new MimeMultipart();

			// Set text message part
			multipart.addBodyPart(messageBodyPart);

			// Second part is image attachment
			messageBodyPart = new MimeBodyPart();
			String filename = "/home/raja/taxfiler/src/main/resources/serenetax.jpg";
			DataSource source = new FileDataSource(filename);
			messageBodyPart.setDataHandler(new DataHandler(source));
			messageBodyPart.setFileName(filename);
			// Trick is to add the content-id header here
			messageBodyPart.setHeader("Content-ID", "image_id");
			multipart.addBodyPart(messageBodyPart);

			// third part for displaying image in the email body
			messageBodyPart = new MimeBodyPart();
			messageBodyPart.setContent("<h1>Attached Image</h1>" + "<img src='cid:image_id'>", "text/html");
			multipart.addBodyPart(messageBodyPart);

			// Set the multipart message to the email message
			message.setContent(multipart);

			// Send message
			transport.connect(SMTP_HOST_NAME, SMTP_HOST_PORT, SMTP_AUTH_USER, SMTP_AUTH_PWD);
			transport.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
			transport.close();
			System.out.println("EMail Sent Successfully with image!!");
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	public static void sendEmail(String toEmail, String subject, String body) {
		try {

			Session mailSession = getSession();
			Transport transport = mailSession.getTransport();
			MimeMessage message = new MimeMessage(mailSession);
			message.setSubject(subject);
			message.setContent(body, "text/html");
			Address[] from = InternetAddress.parse(SMTP_AUTH_USER);// Your domain email
			message.addFrom(from);
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
			transport.connect(SMTP_HOST_NAME, SMTP_HOST_PORT, SMTP_AUTH_USER, SMTP_AUTH_PWD);
			transport.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
			transport.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Session getSession() {
		Properties props = new Properties();
		props.put("mail.transport.protocol", "smtps");
		props.put("mail.smtps.host", SMTP_HOST_NAME);
		props.put("mail.smtps.auth", "true");
		//props.put("mail.mime.allowutf8", "true");

		Session mailSession = Session.getDefaultInstance(props);
		mailSession.setDebug(true);
		return mailSession;
	}

}
