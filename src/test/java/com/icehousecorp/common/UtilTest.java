package com.icehousecorp.common;

import com.icehousecorp.testing.annotation.Case;
import org.junit.Test;

import static com.icehousecorp.common.Util.getCaseId;
import static org.junit.Assert.assertEquals;

public class UtilTest {
    @Case(id = 123)
    private class Dummy {
    }

    @Test
    public void getCaseId_fromClass() {
        int caseId = getCaseId(Dummy.class);
        assertEquals(123, caseId);
    }
}