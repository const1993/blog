package by.owm.domain.mail;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static java.util.Arrays.asList;
import static javax.mail.Session.getDefaultInstance;

@Service
public class EMailService implements MailService {

    private static final Logger logger = Logger.getLogger(EMailService.class);

    private Properties properties;
    private Session mailSession;
    private Store store;

    @PostConstruct
    public void init() throws NoSuchProviderException {
        //TODO add init properties
        this.mailSession = getDefaultInstance(properties);
        store = mailSession.getStore("imaps");
    }

    @Override
    public void sendMail(final Mail mail) {
        try {

            MimeMessage message = new MimeMessage(mailSession);
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(mail.getRecipient()));
            message.setSubject("no-response");
            message.setContent(mail.getBody(), "text/html; charset=utf-8");

            Transport transport = mailSession.getTransport("smtps");
            transport.connect(
                    properties.getProperty("mail.host"),
                    Integer.valueOf(properties.getProperty("mail.port")),
                    properties.getProperty("mail.username"),
                    properties.getProperty("mail.password"));
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();

        } catch (MessagingException e) {
            logger.error("Error in sending e-mail.", e);
        }
    }

    public List<Message> getMailList(String box, Integer start, Integer end) throws MessagingException {
        Folder folder = getFolder(box);

        List<Message> messages = new ArrayList<>();
        if (start != null && end != null) {
            messages.addAll(asList(folder.getMessages(start, end)));
        } else {
            messages.addAll(asList(folder.getMessages()));
        }

        return messages;
    }

    public int getMailCount(String box) throws MessagingException {
        Folder folder = getFolder(box);
        return folder.getMessageCount();
    }

    private Folder getFolder(String box) {
        try {
            store.connect("smtp.gmail.com", properties.getProperty("mail.username"), properties.getProperty("mail.password"));

            Folder folder = store.getFolder(box);
            folder.open(Folder.READ_ONLY);
            return folder;

        } catch (MessagingException e) {
            logger.error("Error in sending e-mail.", e);
            throw new IllegalStateException("", e);
        }
    }

    protected MimeMessage getMessage(Session mailSession) throws MessagingException {
        return new MimeMessage(mailSession);
    }
}
