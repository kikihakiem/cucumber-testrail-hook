package com.icehousecorp.testing.testrail.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class TestResult {
    private Integer statusId;
    private String comment;
    private Integer customDefectGrade;

    public TestResult(int statusId) {
        this.statusId = statusId;
        this.customDefectGrade = 3;
    }
}
