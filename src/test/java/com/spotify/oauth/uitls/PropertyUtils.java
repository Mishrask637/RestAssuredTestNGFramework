package com.spotify.oauth.uitls;

import java.io.*;
import java.util.Properties;

public class PropertyUtils {

    public static Properties getProperty(String path) {
        Properties properties = new Properties();
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(path));
            try {
                properties.load(reader);
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException("Failed to load properties file " + path);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("File not found " + path);
        }
        return properties;
    }
}
