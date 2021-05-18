package com.spotify.oauth.uitls;

import java.util.Properties;

public class DataLoader {

        private final Properties properties;
        private static  DataLoader dataloader;

    private DataLoader()
        {
            properties = PropertyUtils.getProperty("src/test/resources/data.properties");
        }

        public static DataLoader getInstance()
        {
            if(dataloader==null)
            {
                dataloader = new DataLoader();
            }
            return  dataloader;
        }

        public String playlistid()
        {
            String playlistId = properties.getProperty("get_playlist_id");
            if(playlistId!=null)
            {
                return  playlistId;
            }
            else
            {
                throw new RuntimeException("property get_playlist_id is not specified in the config.properties file");
            }
        }

        public String updatePlaylistId()
        {
            String updatePlaylistId = properties.getProperty("get_updateplaylist_id");
            if(updatePlaylistId!=null)
            {
                return  updatePlaylistId;
            }
            else
            {
                throw new RuntimeException("property get_updateplaylist_id is not specified in the config.properties file");
            }
        }


    }
