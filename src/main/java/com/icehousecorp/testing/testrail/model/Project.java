package com.icehousecorp.testing.testrail.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Project {
    private Integer id;

    private Project() {
        Properties prop = new Properties();
        InputStream input;

        try {

            input = new FileInputStream("testrail.properties");

            // load a properties file
            prop.load(input);

            // get the property value
            this.id = Integer.parseInt(prop.getProperty("project.id"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class Holder {
        private static final Project project = new Project();
    }

    public Project instance() {
        return Holder.project;
    }
}
