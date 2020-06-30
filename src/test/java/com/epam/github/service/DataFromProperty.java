package com.epam.github.service;

public enum DataFromProperty {
    TEST_USER_NAME("test.user.name"),
    TEST_USER_PASSWORD("test.user.password"),
    TEST_USER_KEY("test.user.key"),
    BASE_URL("base.url"),
    BASE_API_URL("base.api.url"),
    WAITING_TIMEOUT_SECONDS("waiting.timeout.seconds"),
    BASE_BROWSER("default.browser"),
    MAIL_USER_NAME("mail.user.name"),
    MAIL_USER_PASSWORD("mail.user.password"),
    MAIL_POP_PROTOCOL("mail.pop.protocol"),
    MAIL_POP_HOST("mail.pop.host"),
    MAIL_POP_PORT("mail.pop.port"),
    MAIL_NUMBER_REGEX("mail.number.regex"),
    MAIL_AUTHOR("mail.author"),
    API_CREATE_REPOSITORY("api.create.repository"),
    API_DELETE_REPOSITORY("api.delete.repository"),
    API_CREATE_PROJECT_IN_REPOSITORY("api.create.project.in.repository"),
    API_CREATE_ISSUE_IN_REPOSITORY("api.create.issue.in.repository");

    private final String key;

    DataFromProperty(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
