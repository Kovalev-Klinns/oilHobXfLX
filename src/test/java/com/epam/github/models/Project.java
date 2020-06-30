package com.epam.github.models;

import com.fasterxml.jackson.annotation.JsonGetter;

public class Project {
    private String nameProject;
    private String body = "Developer documentation project for the developer site.";

    public Project(String nameProject) {
        this.nameProject = nameProject;
    }

    @JsonGetter("name")
    public String getNameProject() {
        return nameProject;
    }

    public String getBody() {
        return body;
    }

    public void setNameProject(String nameProject) {
        this.nameProject = nameProject;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
