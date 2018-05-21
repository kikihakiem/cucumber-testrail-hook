package com.icehousecorp.testing.formatter;


import com.icehousecorp.common.Util;
import com.icehousecorp.testing.testrail.API;
import com.icehousecorp.testing.testrail.HTTPClient;
import com.icehousecorp.testing.testrail.model.TestCase;
import com.icehousecorp.testing.testrail.model.TestResult;
import com.icehousecorp.testing.testrail.model.TestRun;
import com.icehousecorp.testing.testrail.model.TestStatus;
import cucumber.api.event.*;
import cucumber.api.formatter.Formatter;

import java.io.IOException;

@SuppressWarnings("unused")
public class TestRail implements Formatter {
    private TestRun testRun;
    private EventHandler<TestRunFinished> runFinishedHandler1;

    @Override
    public void setEventPublisher(EventPublisher publisher) {
        publisher.registerHandlerFor(TestCaseStarted.class, caseStartedHandler);
        publisher.registerHandlerFor(TestCaseFinished.class, caseFinishedHandler);
    }

    private EventHandler<TestCaseStarted> caseStartedHandler = this::handleTestCaseStarted;

    private void handleTestCaseStarted(TestCaseStarted event) {
        try {
            testRun = API.client().createTestRun("Whatever");
        } catch (IOException | HTTPClient.APIException e) {
            e.printStackTrace();
        }
    }

    private EventHandler<TestCaseFinished> caseFinishedHandler = this::handleTestCaseFinished;

    private void handleTestCaseFinished(TestCaseFinished event) {
        int statusId;
        switch(event.result.getStatus()) {
            case PASSED:
                statusId = TestStatus.PASSED.code();
                break;
            case FAILED:
                statusId = TestStatus.FAILED.code();
                break;
            default:
                statusId = TestStatus.UNTESTED.code();

        }

        TestResult testResult = new TestResult(statusId);
        TestCase testCase = new TestCase();
        testCase.setId(Util.getCallerCaseId());

        try {
            API.client().submitTestResult(testRun, testCase, testResult);
        } catch (IOException | HTTPClient.APIException e) {
            e.printStackTrace();
        }
    }


}
