package com.icehousecorp.testing.hook;

import com.icehousecorp.testing.testrail.model.TestCase;
import com.icehousecorp.testing.testrail.model.TestResult;
import com.icehousecorp.testing.testrail.model.TestRun;
import com.icehousecorp.testing.testrail.service.TestCaseService;
import com.icehousecorp.testing.testrail.service.TestResultService;
import com.icehousecorp.testing.testrail.service.TestRunService;
import cucumber.api.Scenario;
import cucumber.api.java.After;

public class TestRail {
    @After
    public void sendTestResult(Scenario scenario) {
        TestResult testResult = TestResultService.create(scenario.getStatus());
        TestCase testCase = TestCaseService.create(scenario.getSourceTagNames());
        TestRun testRun = TestRunService.create();
        TestResultService.submit(testRun, testCase, testResult);
    }
}
