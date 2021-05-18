package com.spotify.oauth.uitls;

import java.util.Properties;

public class ConfigLoader {

    private final Properties properties;
    private static  ConfigLoader configloader;

    private ConfigLoader()
    {
        properties = PropertyUtils.getProperty("src/test/resources/config.properties");
    }

    public static ConfigLoader getInstance()
    {
        if(configloader==null)
        {
            configloader = new ConfigLoader();
        }
        return  configloader;
    }

    public String clientId()
    {
        String cliendid = properties.getProperty("client_id");
        if(cliendid!=null)
        {
            return  cliendid;
        }
        else
        {
            throw new RuntimeException("property client_id is not specified in the config.properties file");
        }
    }

    public String client_secret()
    {
        String client_secret = properties.getProperty("client_secret");
        if(client_secret!=null)
        {
            return  client_secret;
        }
        else
        {
            throw new RuntimeException("property client_secret is not specified in the config.properties file");
        }
    }

    public String refresh_token()
    {
        String refresh_token = properties.getProperty("refresh_token");
        if(refresh_token!=null)
        {
            return  refresh_token;
        }
        else
        {
            throw new RuntimeException("property refresh_token is not specified in the config.properties file");
        }
    }

    public String grant_type()
    {
        String grant_type = properties.getProperty("grant_type");
        if(grant_type!=null)
        {
            return  grant_type;
        }
        else
        {
            throw new RuntimeException("property grant_type is not specified in the config.properties file");
        }
    }

    public String user_Id()
    {
        String user_Id = properties.getProperty("user_Id");
        if(user_Id!=null)
        {
            return  user_Id;
        }
        else
        {
            throw new RuntimeException("property user_Id is not specified in the config.properties file");
        }
    }
}
