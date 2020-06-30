package com.epam.github.util;

import com.epam.github.service.DataFromProperty;
import com.epam.github.service.PropertyReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.mail.*;
import java.io.IOException;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MailUtil {
    private final static String MESSAGE_TYPE = "INBOX";
    private final static String MAIL_HOST = "mail.pop3.host";
    private final static String MAIL_PORT = "mail.pop3.port";
    private final static String MAIL_ENABLE = "mail.pop3.starttls.enable";
    private static Logger log = LogManager.getRootLogger();
    public static Store store;
    public static Folder emailFolder;

    public static String getTextFromMailByRegex(String regex, String author) {
        try {
            Message message = getNewMail(author);
            String bodyOfMessage = message.getContent().toString();
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(bodyOfMessage);
            while (matcher.find()) {
                return matcher.group();
            }
            emailFolder.close(false);
            store.close();
        } catch (MessagingException | IOException e) {
            log.error("Failed to get text from mail by regex: " + e.getLocalizedMessage());
            e.printStackTrace();
        }
        return null;
    }

    private static Message getNewMail(String author) {
        int numberOfExpectations = 10;
        try {
            store = connect(PropertyReader.getMailSettings(DataFromProperty.MAIL_POP_HOST.getKey()),
                    PropertyReader.getMailSettings(DataFromProperty.MAIL_USER_NAME.getKey()),
                    PropertyReader.getMailSettings(DataFromProperty.MAIL_USER_PASSWORD.getKey()));
            emailFolder = store.getFolder(MESSAGE_TYPE);
            emailFolder.open(Folder.READ_ONLY);
            Message[] messages = emailFolder.getMessages();
            while (numberOfExpectations > 0) {
                Message message = messages[messages.length - 1];
                if (message.getFrom()[0].toString().equals(author)) {
                    return message;
                } else {
                    Thread.sleep(2000);
                    numberOfExpectations--;
                }
            }
        } catch (MessagingException | InterruptedException e) {
            log.error("Failed to get message: " + e.getLocalizedMessage());
            e.printStackTrace();
        }
        return null;
    }

    private static Store connect(String host, String username, String password) {
        try {
            Session emailSession = Session.getDefaultInstance(getPropertiesToConnect());
            Store store = emailSession.getStore(PropertyReader.getMailSettings(DataFromProperty.MAIL_POP_PROTOCOL.getKey()));
            store.connect(host, username, password);
            return store;
        } catch (MessagingException e) {
            log.error("Email connection error: " + e.getLocalizedMessage());
            e.printStackTrace();
            return null;
        }
    }

    private static Properties getPropertiesToConnect() {
        Properties properties = new Properties();
        properties.put(MAIL_HOST, PropertyReader.getMailSettings(DataFromProperty.MAIL_POP_HOST.getKey()));
        properties.put(MAIL_PORT, PropertyReader.getMailSettings(DataFromProperty.MAIL_POP_PORT.getKey()));
        properties.put(MAIL_ENABLE, "true");
        return properties;
    }
}
