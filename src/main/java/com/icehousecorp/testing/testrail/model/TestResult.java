package com.icehousecorp.testing.testrail.model;

public class TestResult {
    private Integer statusId;
    private String comment;
    private Integer customDefectGrade;

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getCustomDefectGrade() {
        return customDefectGrade;
    }

    public void setCustomDefectGrade(Integer customDefectGrade) {
        this.customDefectGrade = customDefectGrade;
    }
}
