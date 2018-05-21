package com.icehousecorp.testing.testrail.model;

import cucumber.api.Result;

@SuppressWarnings("unused")
public enum TestStatus {
    PASSED(1), BLOCKED(2), UNTESTED(3), RETEST(4), FAILED(5);
    private final int code;

    TestStatus(int code) {
        this.code = code;
    }

    public static int fromCucumberResultType(Result.Type result) {
        int statusId;
        switch (result) {
            case FAILED:
                statusId = FAILED.code;
                break;
            case PASSED:
                statusId = PASSED.code;
                break;
            default:
                statusId = UNTESTED.code;
        }
        return statusId;
    }

    public int code() {
        return this.code;
    }
}

