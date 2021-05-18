package com.spotify.oauth.test;

import com.spotify.oauth.api.StatusCode;
import com.spotify.oauth.api.applicationapi.SpotifyApi;
import com.spotify.oauth.pojo.Error;
import com.spotify.oauth.pojo.Playlist;
import com.spotify.oauth.uitls.DataLoader;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@Epic("Playlist Api")
@Feature("Playlist Api")
public class PlayListApis{

    @Story("Story : it will create a api")
    @Description("It will create a playlist")
    @Test(description = "Should be able to create a playlist")
    public void shouldBeAbleToCreatePlaylist()
    {
       Playlist playlist = playlistBuilder("New Playlist 2",
               "New playlist 2 description",false);
       Response response =  SpotifyApi.post(playlist);
       assertstatuscode(response.statusCode(),StatusCode.CODE_201);
       Playlist responsePlaylist =  response.as(Playlist.class);
       assertPlaylist(responsePlaylist,playlist);

    }
    @Story("Story : it will get a api")
    @Description("It will get a playlist")
    @Test(description = "Should be able to get a playlist")
    public void shouldBeAbleToGetPlaylist()
    {

       Response resposne =  SpotifyApi.get(DataLoader.getInstance().playlistid());
       assertstatuscode(resposne.statusCode(),StatusCode.CODE_200);
       Playlist respPlay =  resposne.as(Playlist.class);
       assertPlaylist(respPlay,"Updated Playlist Name","Updated playlist description",false);

    }
    @Story("Story : it will update a api")
    @Description("It will update a playlist")
    @Test(description = "Should be able to update a playlist")
    public void shouldBeAbleToUpdatePlaylist()
    {
        Playlist playlist = playlistBuilder("Updated Playlist 2",
                "Updated playlist 2 description",false);
        Response resp = SpotifyApi.put(DataLoader.getInstance().updatePlaylistId(), playlist);
        assertstatuscode(resp.statusCode(),StatusCode.CODE_200);
    }
    @Story("Story : it will not create a api")
    @Description("It will Not create a playlist")
    @Test(description = "Should Not be able to create a playlist")
    public void shouldNotBeAbleToCreatePlaylistWithoutName()
    {
        Playlist playlist = playlistBuilder("",
                "New playlist 2 description",false);
        Response response = SpotifyApi.post(playlist);
        assertstatuscode(response.statusCode(),StatusCode.CODE_400);
        Error respError = response.as(Error.class);
        assertError(respError,StatusCode.CODE_400);
    }
    @Story("Story : it will not update a api")
    @Description("It will Not update a playlist")
    @Test(description = "Should Not be able to update a playlist")
    public void shouldNotBeAbleToUpdatePlaylist()
    {
        String token = "1235456";
        Playlist playlist = playlistBuilder("New Playlist",
                "New playlist description",false);
        Response resp = SpotifyApi.post(token,playlist);
        assertstatuscode(resp.statusCode(),StatusCode.CODE_401);
        Error errorresp = resp.as(Error.class);
        assertError(errorresp,StatusCode.CODE_401);

    }

    @Step
    public Playlist playlistBuilder(String name,String description,boolean _public)
    {
        Playlist playlist = new Playlist();
        playlist.setName(name);
        playlist.setDescription(description);
        playlist.setPublic(_public);
        return  playlist;
    }
    @Step
    public void assertPlaylist(Playlist responsePlaylist,Playlist requestPlaylist)
    {
        assertThat(requestPlaylist.getName(),equalTo(responsePlaylist.getName()));
        assertThat(requestPlaylist.getDescription(),equalTo(responsePlaylist.getDescription()));
        assertThat(requestPlaylist.getPublic(),equalTo(responsePlaylist.getPublic()));
    }
    @Step
    public void assertPlaylist(Playlist responsePlaylist,String name,String description,boolean _public)
    {
        assertThat(responsePlaylist.getName(),equalTo(name));
        assertThat(responsePlaylist.getDescription(),equalTo(description));
        assertThat(responsePlaylist.getPublic(),equalTo(_public));
    }
    @Step
    public void assertstatuscode(int actualCode, StatusCode statusCode)
    {
        assertThat(actualCode,equalTo(statusCode.code));
    }
    @Step
    public void assertError(Error errorresp,StatusCode statusCode)
    {
        assertThat(errorresp.getError().getStatus(),equalTo(statusCode.code));
        assertThat(errorresp.getError().getMessage(),equalTo(statusCode.message));
    }

}
