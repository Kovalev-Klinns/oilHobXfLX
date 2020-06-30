package com.epam.github.models;

public class Issue {
    private String title;
    private String body = "I'm having a problem with this";

    public Issue(String title) {
        this.title = title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }
}
