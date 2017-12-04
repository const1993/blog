package by.owm.domain.mail;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

@Service
public class AbstractEMailService {
	/** Logger. */
	protected static final Logger logger = Logger
			.getLogger(AbstractEMailService.class);
	/** Synch lock. */
	private static String synchLock = "synchronize";
	/** Subject. */
	private String subject;
	/** Body template. */
	private MessageFormat bodyTemplate;
	/** Properties. */
	private Properties properties;

	private String body = "";
	
	private String recipient;

	/**
	 * Get subject.
	 * 
	 * @return the subject
	 */
	public String getSubject() {
		if(subject == null){
			subject = " ";
		}
		return subject;
	}

	/**
	 * Set subject.
	 * 
	 * @param subject
	 *            the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * Get bodyTemplate.
	 * 
	 * @return the bodyTemplate
	 */
	public MessageFormat getBodyTemplate() {
		return bodyTemplate;
	}

	/**
	 * Set bodyTemplate.
	 * 
	 * @param bodyTemplate
	 *            the bodyTemplate to set
	 */
	public void setBodyTemplate(MessageFormat bodyTemplate) {
		this.bodyTemplate = bodyTemplate;
	}

	/**
	 * Get properties.
	 * 
	 * @return the properties
	 */
	public Properties getProperties() {
		return properties;
	}

	/**
	 * Set properties.
	 * 
	 * @param properties
	 *            the properties to set
	 */
	public void setProperties(Properties properties) {
		this.properties = properties;
	}
	
	public String getRecipient() {
		return recipient;
	}

	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}

	public String getBody() {
		if(body== null)
		{
			body ="";
		}
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	};

	public void sendEmail() {
		synchronized (synchLock) {

			final Properties props = getProperties();
			Session mailSession = Session.getDefaultInstance(props);

			Transport transport;
			try {
				MimeMessage message = new MimeMessage(mailSession);
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(getRecipient()));
				message.setSubject("no-response");
				message.setContent(getBody(), "text/html; charset=utf-8");
				transport = mailSession.getTransport("smtps");
				transport.connect(props.getProperty("mail.host"), Integer.valueOf(props.getProperty("mail.port")),
						props.getProperty("mail.username"), props.getProperty("mail.password"));
				transport.sendMessage(message,message.getAllRecipients());
				transport.close();
			} catch (MessagingException e) {
				logger.error("Error in sending e-mail.", e);
			}
		}
	}
	
	public List<Message> getMailList(String box, Integer start, Integer end) throws MessagingException 
	{
		Folder folder = getFolder(box);
		
		List<Message> messages = new ArrayList<Message>();
		if(start!= null && end != null)
		{
			messages.addAll(Arrays.asList(folder.getMessages(start, end)));
		}
		else
		{
			messages.addAll(Arrays.asList(folder.getMessages()));
		}
		
		return messages;
	}
	
	public int getMailCount(String box) throws MessagingException 
	{
		Folder folder = getFolder(box);
		return folder.getMessageCount();
	}
	
	private Folder getFolder(String box) throws MessagingException
	{
		final Properties props = getProperties();
		Session mailSession = Session.getDefaultInstance(props);
		
		Folder folder = null;
		try {
			Store store = mailSession.getStore("imaps");
			store.connect("smtp.gmail.com", props.getProperty("mail.username"), props.getProperty("mail.password"));
			folder = store.getFolder(box);
			folder.open(Folder.READ_ONLY);
		} 
		catch (NoSuchProviderException e) {
			e.printStackTrace();
		}
		return folder;
	}
	
	protected MimeMessage getMessage(Session mailSession)
			throws MessagingException
	{
		return new MimeMessage(mailSession);
	}

}
