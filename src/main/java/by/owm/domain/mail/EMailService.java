package by.owm.domain.mail;

import by.owm.domain.model.Mail;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import java.util.List;
import java.util.Properties;

import static java.util.Arrays.asList;
import static javax.mail.Message.RecipientType.TO;
import static javax.mail.Session.getDefaultInstance;

@Service
public class EMailService implements MailService {

    private static final Logger LOGGER = Logger.getLogger(EMailService.class);

    private final String host;
    private final Integer port;
    private final String username;
    private final String password;

    private Session mailSession;

    @Autowired
    public EMailService(@Value("${mail.host}") final String host,
                        @Value("${mail.port}") final Integer port,
                        @Value("${mail.username}") final String username,
                        @Value("${mail.password}") final String password) {
        this.host = host;
        this.port = port;
        this.username = username;
        this.password = password;
    }

    @PostConstruct
    public void init() throws NoSuchProviderException {
        final Properties properties = new Properties();
        //TODO: add properties, which meail session need

        this.mailSession = getDefaultInstance(properties);
    }

    @Override
    public void sendMail(final Mail mail) {
        try {

            final MimeMessage message = new MimeMessage(mailSession);
            message.addRecipient(TO, new InternetAddress(mail.getRecipient()));
            message.setSubject("no-response");
            message.setContent(mail.getBody(), "text/html; charset=utf-8");

            Transport transport = mailSession.getTransport("smtps");
            transport.connect(host, port, username, password);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();

        } catch (MessagingException e) {
            LOGGER.error("Error in sending e-mail.", e);
            throw new IllegalStateException("Error in sending e-mail.", e);
        }
    }

    @Override
    public List<Message> getMailList(final String box, final Integer start, final Integer end) {
        try {
            final Folder folder = getFolder(box);


            final List<Message> messages;

            if (start == null && end == null) {
                messages = asList(folder.getMessages());
            } else if (start == null) {
                messages = asList(folder.getMessages(0, end));
            } else if (end == null) {
                messages = asList(folder.getMessages(start, folder.getMessageCount()));
            } else {
                messages = asList(folder.getMessages(start, end));
            }

            folder.close(false);

            return messages;
        } catch (MessagingException e) {
            LOGGER.error("Error. Get mail list failed.", e);
            throw new IllegalStateException("Error. Get mail list failed.", e);
        }
    }

    @Override
    public int getMailCount(final String box) {
        try {
            final Folder folder = getFolder(box);
            final int count = folder.getMessageCount();
            folder.close(false);
            return count;
        } catch (MessagingException e) {
            LOGGER.error("Error. Get mail count failed.", e);
            throw new IllegalStateException("Error. Get mail count failed.", e);
        }
    }

    private Folder getFolder(final String box) throws MessagingException {
        final Store store = mailSession.getStore("imaps");
        store.connect(host, username, password);

        final Folder folder = store.getFolder(box);
        folder.open(Folder.READ_ONLY);

        return folder;
    }
}
