package com.icehousecorp.testing.testrail.model;

public enum TestStatus {
    PASSED(1), BLOCKED(2), UNTESTED(3), RETEST(4), FAILED(5);
    private final int code;

    TestStatus(int code) {
        this.code = code;
    }

    public int code() {
        return this.code;
    }
}

