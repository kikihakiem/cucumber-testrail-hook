package com.icehousecorp.testing.testrail.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter @Setter
public class TestRun {
    private Integer id;
    private String name;
    private Integer planId;
    private Integer projectId;
    private Integer suiteId;
}
