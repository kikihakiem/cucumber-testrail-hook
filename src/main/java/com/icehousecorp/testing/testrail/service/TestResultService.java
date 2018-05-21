package com.icehousecorp.testing.testrail.service;

import com.icehousecorp.testing.testrail.API;
import com.icehousecorp.testing.testrail.HTTPClient;
import com.icehousecorp.testing.testrail.model.TestCase;
import com.icehousecorp.testing.testrail.model.TestResult;
import com.icehousecorp.testing.testrail.model.TestRun;
import com.icehousecorp.testing.testrail.model.TestStatus;
import cucumber.api.Result;

import java.io.IOException;

public class TestResultService {
    public static TestResult create(Result.Type result) {
        int statusId = TestStatus.fromCucumberResultType(result);
        TestResult testResult = new TestResult();
        testResult.setStatusId(statusId);
        return testResult;
    }

    public static void submit(TestRun testRun, TestCase testCase, TestResult testResult) {
        try {
            API.client().addTestResult(testRun, testCase, testResult);
        } catch (IOException | HTTPClient.APIException e) {
            e.printStackTrace();
        }
    }
}
