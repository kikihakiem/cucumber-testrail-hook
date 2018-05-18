package com.icehousecorp.testing.testrail;

import lombok.Getter;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Getter
public class Config {
    private Integer projectId;
    private String baseUrl;
    private String user;
    private String password;

    private Config() {
        Properties prop = new Properties();
        InputStream input;

        try {

            input = new FileInputStream("testrail.properties");

            // load a properties file
            prop.load(input);

            // get the property value
            this.projectId = Integer.parseInt(prop.getProperty("project.id"));
            this.baseUrl = prop.getProperty("api.url");
            this.user = prop.getProperty("api.user");
            this.password = prop.getProperty("api.password");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class Holder {
        private static final Config config = new Config();
    }

    public static Config instance() {
        return Holder.config;
    }
}
