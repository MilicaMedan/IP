package beans;

import java.util.Properties;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import dto.User;

@ManagedBean(name = "mailBean")
@SessionScoped
public class MailBean {

	/*
	 * public static void sendMail(User user) { try { String host = "localhost";
	 * Properties properties=new Properties();
	 * properties.setProperty("mail.smtp.host", host); Session
	 * session=Session.getDefaultInstance(properties,null); MimeMessage message=new
	 * MimeMessage(session); message.setFrom(new
	 * InternetAddress("sonoojaiswal@sssit.org"));
	 * message.addRecipient(Message.RecipientType.TO, new
	 * InternetAddress("milica.medan@yahoo.com"));
	 * message.setHeader("Hi, everyone","");
	 * message.setText("Hi, This mail is to inform you...");
	 * Transport.send(message); }catch(Exception e){ e.printStackTrace(); } }
	 */

	String host, port, emailid, username, password;
	Properties props = System.getProperties();
	Session l_session = null;

	public MailBean(String mailTo, String text) {
		System.out.println("1");
		host = "smtp.gmail.com";
		port = "567";
		String to = mailTo;
		emailid = "milica.medan1811@gmail.com";
		username = "milica.medan1811";
		password = "studentica8";
		Properties props = new Properties();
		props.put("mail.smtp.starttls.enable", "true");

		props.put("mail.smtp.ssl.trust", host);
		props.put("mail.smtp.user", username);
		props.put("mail.smtp.password", password);
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.auth", "true");


		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});
		
		try {
			Message message = new MimeMessage(session);

			message.setFrom(new InternetAddress(emailid));

			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));

			message.setSubject("Message");

			message.setText(text);

			Transport transport = session.getTransport("smtp");


	        transport.connect(host, username, password);
	        transport.sendMessage(message, message.getAllRecipients());
	        transport.close();

			System.out.println("Sent message successfully....");
			

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}


}
