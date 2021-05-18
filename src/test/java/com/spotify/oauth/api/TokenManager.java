package com.spotify.oauth.api;

import com.spotify.oauth.uitls.ConfigLoader;
import io.restassured.response.Response;
import java.time.Instant;
import java.util.HashMap;


public class TokenManager {

    private static String access_Token;
    private static Instant expiryTime;

    public synchronized static String getToken()
    {

                try {
                    if(access_Token==null || Instant.now().isAfter(expiryTime)) {
                        System.out.println("Renewing Token");
                    Response resp = renewToken();
                    access_Token = resp.path("access_token");
                    int expiryInSeconds = resp.path("expires_in");
                    expiryTime = Instant.now().plusSeconds(expiryInSeconds - 300);
                }
                    else
                    {
                        System.out.println("Token is valid");
                    }
                } catch (Exception e) {
                    throw new RuntimeException("Abort!!! Failed to renew token");
                }

            return access_Token;
    }


    private static Response renewToken() {

        HashMap<String, String> paramss = new HashMap<>();

        paramss.put("client_id", ConfigLoader.getInstance().clientId());
        paramss.put("client_secret",ConfigLoader.getInstance().client_secret());
        paramss.put("refresh_token",ConfigLoader.getInstance().refresh_token());
        paramss.put("grant_type",ConfigLoader.getInstance().grant_type());

       Response resp= RestResource.postAccount(paramss);

        if(resp.statusCode()!=200)
        {
            throw new RuntimeException("Abort!!! Renew Token Failed");
        }
        else
        {
            return resp;
        }

    }
}
