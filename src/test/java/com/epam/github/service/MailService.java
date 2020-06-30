package com.epam.github.service;

import com.epam.github.util.MailUtil;

public class MailService {

    public static String getVerificationCode() {
        return MailUtil.getTextFromMailByRegex(PropertyReader.getMailSettings(DataFromProperty.MAIL_NUMBER_REGEX.getKey()),
                PropertyReader.getMailSettings(DataFromProperty.MAIL_AUTHOR.getKey()));
    }
}
