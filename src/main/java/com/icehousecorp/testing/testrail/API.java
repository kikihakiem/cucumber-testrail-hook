package com.icehousecorp.testing.testrail;

import com.alibaba.fastjson.JSON;
import com.icehousecorp.testing.testrail.model.TestCase;
import com.icehousecorp.testing.testrail.model.TestResult;
import com.icehousecorp.testing.testrail.model.TestRun;

import java.io.IOException;

public class API {
    private HTTPClient client;

    private API() {
        Config config = Config.instance();
        this.client = new HTTPClient(config.getBaseUrl());
        client.setUser(config.getUser());
        client.setPassword(config.getPassword());
    }

    private static class Holder {
        private static final API api = new API();
    }

    public static API client() {
        return Holder.api;
    }

    public TestRun createTestRun(TestRun testRun) throws IOException, HTTPClient.APIException {
        String json = client.sendPost("add_run/" + testRun.getProjectId(), testRun);
        return JSON.parseObject(json, TestRun.class);
    }

    public void addTestResult(TestRun testRun, TestCase testCase, TestResult testResult) throws IOException, HTTPClient.APIException {
        client.sendPost("add_result_for_case/" + testRun.getId() + "/" + testCase.getId(), testResult);
    }
}
