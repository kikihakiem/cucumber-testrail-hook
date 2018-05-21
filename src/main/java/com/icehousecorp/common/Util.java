package com.icehousecorp.common;

import com.icehousecorp.testing.annotation.Case;
import sun.reflect.Reflection;

public class Util {
    static int getCaseId(Class clazz) {
        Case c = (Case) clazz.getAnnotation(Case.class);
        return c == null ? 0 : c.id();
    }

    public static int getCallerCaseId() {
        for (int i = 2; i < 10; i++) {
            @SuppressWarnings("deprecation")
            Class clazz = Reflection.getCallerClass(i);
            int caseId = getCaseId(clazz);
            if (caseId != 0) return caseId;
        }

        return 0;
    }

}
