package com.spotify.oauth.api;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static com.spotify.oauth.api.Route.BASE_PATH;

public class Specification {

    public static RequestSpecification getRequestSpec()
    {

        return new RequestSpecBuilder().
        setBaseUri("https://api.spotify.com/").
        setBasePath(BASE_PATH).
        addFilter(new AllureRestAssured()).
        setContentType(ContentType.JSON).
        log(LogDetail.ALL).build();
    }

    public static RequestSpecification getAccountSpec()
    {
                return new RequestSpecBuilder().
                setBaseUri("https://accounts.spotify.com").
                addFilter(new AllureRestAssured()).
                setContentType(ContentType.URLENC).
                log(LogDetail.ALL).build();
    }

    public static ResponseSpecification getResponseSpec()
    {

       return new ResponseSpecBuilder().
        log(LogDetail.ALL).build();

    }

}
