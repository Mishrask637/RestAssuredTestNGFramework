package com.spotify.oauth.api;

import com.spotify.oauth.pojo.Playlist;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.HashMap;

import static com.spotify.oauth.api.Route.API;
import static com.spotify.oauth.api.Route.TOKEN;
import static com.spotify.oauth.api.Specification.*;
import static io.restassured.RestAssured.given;

public class RestResource {

    public static Response post(String path,String token,Object playlist)
    {
        return given(getRequestSpec()).
                header("Authorization", "Bearer " + token)
                .body(playlist).
                when().post(path).
                then().spec(getResponseSpec()).extract().response();
    }

    public static Response postAccount(HashMap<String,String> paramss)
    {
        return given(getAccountSpec()).
                formParams(paramss).
                when().post(API+TOKEN).then().spec(getResponseSpec())
                .extract().response();
    }

    public static Response get(String path,String token)
    {
        return given(getRequestSpec()).
                header("Authorization", "Bearer " + token).
                when().get(path).
                then().spec(getResponseSpec()).
                extract().response();
    }


    public static Response put(String path,String token,Object playlist)
    {
        return given(getRequestSpec()).body(playlist).
                header("Authorization", "Bearer " + token).
                when().put(path).
                then().spec(getResponseSpec()).extract().response();
    }

}
