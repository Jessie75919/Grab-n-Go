package _08_mail.controller;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMail {
	public static void main(String[] args) {
		MailBean mb = new MailBean();
		mb.sendMail();
	}
}

class MailBean {
	String fromEmail = "jessie75919@gmail.com";
	String toEmail = "crazy75919@hotmail.com";
	String message = "hihihihi";
	String subject = "JAVA MAIL TEST";
	String uername = "jessie75919";
	String password = "";

	public void sendMail() {
		try {
			String host = "smtp.gmail.com";
			int port = 587;
			final String username = "jessie75919@gmail.com";
			final String password = "";

			Properties props = new Properties();
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.starttis.enable", "true");
			props.put("mail.smtp.port", "587");
			props.put("mail.smtp.starttis.required", "true");

			// props.put("mail.smtp.socketFactory.class",
			// "javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.auth", "true");

			Session session = Session.getInstance(props, null);

			Message mailMessage = new MimeMessage(session);
			mailMessage.setFrom(new InternetAddress(fromEmail));
			mailMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
			mailMessage.setContent(message, "text/html");
			mailMessage.setSubject(subject);
			mailMessage.setText("Dear Mail Crawler,\n\n No spam to my email, please!");

			Transport transport = session.getTransport("smtp");
			transport.connect("smtp.gamil.com", uername, password);

			transport.sendMessage(mailMessage, mailMessage.getAllRecipients());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
