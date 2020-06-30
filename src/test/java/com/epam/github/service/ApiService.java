package com.epam.github.service;

import com.epam.github.models.Issue;
import com.epam.github.models.Project;
import com.epam.github.models.Repository;
import com.epam.github.models.User;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class ApiService {
    private final String baseApiUrl = PropertyReader.getBaseApiUrl();
    private User testUser = PropertyReader.getUserWithCredentialsFromProperty();

    public Response createEmptyRepository(Repository repository) {
        return getApiUserAuthentification()
                .body(repository)
                .post(getRequestUrl(DataFromProperty.API_CREATE_REPOSITORY, repository));
    }

    public Response deleteRepository(Repository repository) {
        return getApiUserAuthentification()
                .delete(getRequestUrl(DataFromProperty.API_DELETE_REPOSITORY, repository));
    }

    public Response createProjectInRepository(Repository repository, Project project) {
        return getApiUserAuthentification()
                .body(project)
                .header("Accept", "application/vnd.github.inertia-preview+json")
                .post(getRequestUrl(DataFromProperty.API_CREATE_PROJECT_IN_REPOSITORY, repository));
    }

    public Response createIssueInRepository(Repository repository, Issue issue) {
        return getApiUserAuthentification()
                .body(issue)
                .post(getRequestUrl(DataFromProperty.API_CREATE_ISSUE_IN_REPOSITORY, repository));
    }

    private RequestSpecification getApiUserAuthentification() {
        return given().auth()
                .preemptive()
                .basic(testUser.getUserName(), testUser.getUserPassword());
    }

    private String getRequestUrl(DataFromProperty endPoint, Repository repository) {
        return String.format(baseApiUrl + PropertyReader.getApiEndpoint(endPoint),
                testUser.getUserName(), repository.getNameRepository());
    }
}
