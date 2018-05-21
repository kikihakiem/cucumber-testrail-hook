package com.icehousecorp.testing.testrail.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.*;

@Builder
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class TestRun {
    private Integer id;
    private String name;
    @JSONField(name = "plan_id")
    private Integer planId;
    @JSONField(name = "project_id")
    private Integer projectId;
    @JSONField(name = "suite_id")
    private Integer suiteId;
}
