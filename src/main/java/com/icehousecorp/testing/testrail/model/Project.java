package com.icehousecorp.testing.testrail.model;

import com.icehousecorp.testing.testrail.Config;

@SuppressWarnings("unused")
public class Project {
    private Integer id;

    private Project() {
        this.id = Config.instance().getProjectId();
    }

    private static class Holder {
        private static final Project project = new Project();
    }

    public static Project instance() {
        return Holder.project;
    }

    public Integer getId() {
        return id;
    }
}
