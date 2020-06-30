package com.epam.github.models;

import com.epam.github.service.PropertyReader;
import com.fasterxml.jackson.annotation.JsonGetter;

public class Repository {

    private String nameRepository;
    private String description = "This is your first repository";
    private String homepage = PropertyReader.getBaseUrl();
    private boolean privateChangeName = false;
    private boolean has_issues = true;
    private boolean has_projects = true;
    private boolean has_wiki = true;

    public Repository(String nameRepository) {
        this.nameRepository = nameRepository;
    }

    public void setNameRepository(String nameRepository) {
        this.nameRepository = nameRepository;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public void setPrivateChangeName(boolean privateChangeName) {
        this.privateChangeName = privateChangeName;
    }

    public void setHas_issues(boolean has_issues) {
        this.has_issues = has_issues;
    }

    public void setHas_projects(boolean has_projects) {
        this.has_projects = has_projects;
    }

    public void setHas_wiki(boolean has_wiki) {
        this.has_wiki = has_wiki;
    }

    @JsonGetter("name")
    public String getNameRepository() {
        return nameRepository;
    }

    public String getDescription() {
        return description;
    }

    public String getHomepage() {
        return homepage;
    }

    @JsonGetter("private")
    public boolean getPrivateChangeName() {
        return privateChangeName;
    }

    public boolean getHas_issues() {
        return has_issues;
    }

    public boolean getHas_projects() {
        return has_projects;
    }

    public boolean getHas_wiki() {
        return has_wiki;
    }
}