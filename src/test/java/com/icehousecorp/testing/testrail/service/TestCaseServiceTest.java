package com.icehousecorp.testing.testrail.service;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestCaseServiceTest {

    @Test
    public void getIdFromTag() {
        int id = TestCaseService.getIdFromTag("@case.id-234");
        assertEquals(234, id);
    }
}