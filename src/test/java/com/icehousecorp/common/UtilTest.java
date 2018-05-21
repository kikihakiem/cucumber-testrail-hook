package com.icehousecorp.common;

import com.icehousecorp.testing.annotation.Case;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UtilTest {
    @Case(id = 123)
    private class Dummy {
        void foo(Runnable runnable) {
            runnable.run();
        }
    }

    @Test
    public void getCaseId() {
        int caseId = Util.getCaseId(Dummy.class);
        assertEquals(123, caseId);
    }

    @Test
    public void getCallerCaseId() {
        Dummy dummy = new Dummy();
        dummy.foo(() -> {
            int caseId = Util.getCallerCaseId();
            assertEquals(123, caseId);
        });
    }

    @Test
    public void getCaseId_fromTag() {
        int caseId = Util.getCaseId("@testrail.case-234");
        assertEquals(234, caseId);
    }
}