package com.epam.github.service;

import com.epam.github.models.User;
import com.epam.github.util.Cryptor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ResourceBundle;

public class PropertyReader {

    private static ResourceBundle resourceBundleUser = ResourceBundle.getBundle("stage_user_data");
    private static ResourceBundle resourceBundleBrowser = ResourceBundle.getBundle("browser");

    private static ResourceBundle resourceBundleMail = ResourceBundle.getBundle("mail");

    private static ResourceBundle resourceBundleEndpointsApi = ResourceBundle.getBundle("endpoints_api");

    private static Logger log = LogManager.getRootLogger();

    public static String getTestDataApi(String key) {
        return resourceBundleEndpointsApi.getString(key);
    }

    public static String getTestData(String key) {
        return resourceBundleUser.getString(key);
    }

    public static String getBrowserSettings(String key) {
        return resourceBundleBrowser.getString(key);
    }

    public static String getMailSettings(String key) {
        return resourceBundleMail.getString(key);
    }

    public static User getUserWithCredentialsFromProperty() {
        return new User(getTestData(DataFromProperty.TEST_USER_NAME.getKey()),
                Cryptor.decrypt(getTestData(DataFromProperty.TEST_USER_PASSWORD.getKey())));
    }

    public static String getBaseUrl() {
        return getBrowserSettings(DataFromProperty.BASE_URL.getKey());
    }

    public static String getBaseApiUrl() {
        return getBrowserSettings(DataFromProperty.BASE_API_URL.getKey());
    }

    public static int getWaitTime() {
        return Integer.parseInt(getBrowserSettings(DataFromProperty.WAITING_TIMEOUT_SECONDS.getKey()));
    }

    public static String getBaseBrowser() {
        return getBrowserSettings(DataFromProperty.BASE_BROWSER.getKey());
    }

    public static String getApiEndpoint(DataFromProperty endpoint) {
        return getTestDataApi(endpoint.getKey());
    }
}