package com.icehousecorp.common;

import com.icehousecorp.testing.annotation.Case;
import sun.reflect.Reflection;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Util {
    private static final Pattern testCase = Pattern.compile("^@testrail.case-(?<id>\\d+)");
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

    public static int getCaseId(String tag) {
        Matcher m = testCase.matcher(tag);
        if (m.find()) {
            return Integer.parseInt(m.group("id"));
        }

        return 0;
    }

}
