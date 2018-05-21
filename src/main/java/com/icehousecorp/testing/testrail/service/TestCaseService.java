package com.icehousecorp.testing.testrail.service;

import com.icehousecorp.testing.testrail.model.TestCase;

import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestCaseService {
    private static final Pattern regex = Pattern.compile("^@case.id-(?<id>\\d+)");
    static int getIdFromTags(Collection<String> tags) {
        int id = 0;
        for (String tag : tags) {
            id = getIdFromTag(tag);
            if (id != 0) return id;
        }

        return 0;
    }

    static int getIdFromTag(String tag) {
        Matcher m = regex.matcher(tag);
        if (m.find()) {
            return Integer.parseInt(m.group("id"));
        }

        return 0;
    }

    public static TestCase create(Collection<String> tags) {
        int id = getIdFromTags(tags);
        TestCase testCase = new TestCase();
        testCase.setId(id);
        return testCase;
    }

}
