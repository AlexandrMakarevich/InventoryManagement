package com;

import org.junit.Test;

public class SomeTest extends BaseTest {

    @Test
    public void failTest() {
        throw new IllegalArgumentException("FailTest");
    }
}
