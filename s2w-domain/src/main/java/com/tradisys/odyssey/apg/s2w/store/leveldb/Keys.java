package com.tradisys.odyssey.apg.s2w.store.leveldb;

import com.google.common.primitives.Bytes;
import com.google.common.primitives.Ints;
import com.google.common.primitives.Shorts;

public class Keys {
    public static short CustomerIdPrefix = 100;
    public static short CustomerPrefix = 101;
    public static short CustomerBSNPrefix = 102;
    public static short OrganizationIdPrefix = 200;
    public static short OrganizationPrefix = 201;
    public static short TaskIdPrefix = 300;
    public static short TaskPrefix = 301;
    public static short TaskAssignmentPrefix = 302;

    public static byte[] fromPrefix(short prefix) {
        return Shorts.toByteArray(prefix);
    }

    public static byte[] fromPrefixAndId(short prefix, int id) {
        byte[] idBytes = Ints.toByteArray(id);

        return fromPrefixAndBytes(prefix, idBytes);
    }

    public static byte[] fromPrefixAndBytes(short prefix, byte[] keyBytes) {
        byte[] prefixBytes = Shorts.toByteArray(prefix);
        return Bytes.concat(prefixBytes, keyBytes);
    }
}
