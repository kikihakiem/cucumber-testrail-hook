package com.icehousecorp.testing.testrail.service;

import com.icehousecorp.testing.testrail.API;
import com.icehousecorp.testing.testrail.Config;
import com.icehousecorp.testing.testrail.HTTPClient;
import com.icehousecorp.testing.testrail.model.TestRun;

import java.io.IOException;

public class TestRunService {
    public static TestRun create() {
        int projectId = Config.instance().getProjectId();
        TestRun testRun = TestRun.builder().name(getName()).projectId(projectId).build();
        try {
            testRun = API.client().createTestRun(testRun);
        } catch (IOException | HTTPClient.APIException e) {
            e.printStackTrace();
        }

        return testRun;
    }

    private static String getName() {
        return System.getProperty("testrun.name", "Default Test Run");
    }
}
