package by.owm.domain.mail;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import static java.util.Arrays.asList;
import static javax.mail.Session.getDefaultInstance;

@Service
public class AbstractEMailService {


    private static String synchLock = "synchronize";





}
