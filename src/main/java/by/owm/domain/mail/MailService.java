package by.owm.domain.mail;

import by.owm.domain.model.Mail;

import javax.mail.Message;
import javax.mail.MessagingException;
import java.util.List;

public interface MailService {

    void sendMail(Mail mail);

    List<Message> getMailList(String box, Integer start, Integer end) throws MessagingException;

    int getMailCount(String box) throws MessagingException;
}
