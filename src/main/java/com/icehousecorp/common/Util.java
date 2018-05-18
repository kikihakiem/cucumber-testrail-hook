package com.icehousecorp.common;

import com.icehousecorp.testing.annotation.Case;

public class Util {
    private int projectId;
    public static int getCaseId(Class clazz) {
        Case c = (Case) clazz.getAnnotation(Case.class);
        return c.id();
    }
}
