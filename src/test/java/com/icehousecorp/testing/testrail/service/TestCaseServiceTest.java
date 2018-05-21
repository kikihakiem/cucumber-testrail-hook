package com.icehousecorp.testing.testrail.service;

import com.icehousecorp.testing.testrail.service.TestCaseService;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestCaseServiceTest {

    @Test
    public void getIdFromTag() {
        int id = TestCaseService.getIdFromTag("@case.id-234");
        assertEquals(234, id);
    }
}