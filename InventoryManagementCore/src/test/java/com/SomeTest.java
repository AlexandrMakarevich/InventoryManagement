package com;

import org.junit.Test;

public class SomeTest extends BaseTest {

    @Test
    public void failTest() {
        System.out.println("new changes for jenkins");
        throw new IllegalArgumentException("FailTest");
    }
}
