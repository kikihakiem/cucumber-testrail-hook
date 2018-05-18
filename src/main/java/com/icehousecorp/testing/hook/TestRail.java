package com.icehousecorp.testing.hook;

import com.icehousecorp.testing.testrail.API;
import com.icehousecorp.testing.testrail.HTTPClient;
import com.icehousecorp.testing.testrail.model.*;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

import java.io.IOException;

public class TestRail {
    private TestRun testRun;
    @Before
    public void createTestRun() {
        try {
            testRun = API.client().createTestRun("Whatever");
        } catch (IOException | HTTPClient.APIException e) {
            e.printStackTrace();
        }
    }

    @After
    public void sendTestResult(Scenario scenario) {
        int statusId = TestStatus.PASSED.code();
        if (scenario.isFailed()) {
            statusId = TestStatus.FAILED.code();
        }

        TestResult testResult = new TestResult(statusId);
        TestCase testCase = new TestCase();
        try {
            API.client().submitTestResult(testRun, testCase, testResult);
        } catch (IOException | HTTPClient.APIException e) {
            e.printStackTrace();
        }
    }
}
