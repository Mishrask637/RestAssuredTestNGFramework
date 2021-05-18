package com.spotify.oauth.api.applicationapi;

import com.spotify.oauth.api.RestResource;
import com.spotify.oauth.pojo.Playlist;
import com.spotify.oauth.uitls.ConfigLoader;
import io.restassured.response.Response;

import static com.spotify.oauth.api.Route.PLAYLISTS;
import static com.spotify.oauth.api.Route.USERS;
import static com.spotify.oauth.api.TokenManager.getToken;

public class SpotifyApi {

    public static Response post(Playlist playlist)
    {
        return RestResource.post(USERS+"/"+ConfigLoader.getInstance().user_Id()+PLAYLISTS,getToken(),playlist);
    }

    public static Response post(String tokend,Playlist playlist)
    {
        return RestResource.post(USERS+"/"+ConfigLoader.getInstance().user_Id()+PLAYLISTS,tokend,playlist);
    }

    public static Response get(String playlistId)
    {
        return RestResource.get(PLAYLISTS+"/"+playlistId,getToken());
    }

    public static Response put(String playlistId,Playlist playlist)
    {
        return RestResource.put(PLAYLISTS+"/"+playlistId,getToken(),playlist);
    }

}
