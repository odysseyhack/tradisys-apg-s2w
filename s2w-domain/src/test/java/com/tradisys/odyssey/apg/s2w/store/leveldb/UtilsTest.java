package com.tradisys.odyssey.apg.s2w.store.leveldb;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class UtilsTest {

    @Test
    public void startsWithTest() {
        byte[] b1 = { 1, 2, 3, 4, 5, 6 };
        byte[] b2 = { 1, 2, 3 };
        byte[] b3 = { 2, 2, 3 };

        Assert.assertTrue(Utils.startsWith(b1, b2));
        Assert.assertFalse(Utils.startsWith(b1, b3));
    }
}
