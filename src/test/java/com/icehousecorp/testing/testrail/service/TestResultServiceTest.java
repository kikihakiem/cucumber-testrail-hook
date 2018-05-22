package com.icehousecorp.testing.testrail.service;

import com.alibaba.fastjson.JSON;
import com.icehousecorp.testing.testrail.model.TestResult;
import com.icehousecorp.testing.testrail.model.TestStatus;
import cucumber.api.Result;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestResultServiceTest {

    @Test
    public void create() {
        TestResult testResult = TestResultService.create(Result.Type.FAILED);
        assertEquals(TestStatus.FAILED.code(), testResult.getStatusId().intValue());
        testResult = TestResultService.create(Result.Type.PASSED);
        assertEquals(TestStatus.PASSED.code(), testResult.getStatusId().intValue());
        testResult = TestResultService.create(Result.Type.UNDEFINED);
        assertEquals(TestStatus.UNTESTED.code(), testResult.getStatusId().intValue());
    }

    @Test
    public void marshall() {
        TestResult testResult = TestResultService.create(Result.Type.FAILED);
        String json = JSON.toJSONString(testResult);
        assertEquals("{\"custom_defect_grade\":3,\"status_id\":5}", json);
    }
}