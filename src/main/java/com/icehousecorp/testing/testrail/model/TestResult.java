package com.icehousecorp.testing.testrail.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class TestResult {
    @JSONField(name = "status_id")
    private Integer statusId;
    private String comment;
    @JSONField(name = "custom_defect_grade")
    private Integer customDefectGrade;

    public TestResult() {
        this.customDefectGrade = 3;
    }
}
