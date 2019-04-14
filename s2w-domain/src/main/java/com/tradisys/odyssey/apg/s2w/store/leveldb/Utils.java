package com.tradisys.odyssey.apg.s2w.store.leveldb;

import org.iq80.leveldb.DB;
import org.iq80.leveldb.DBIterator;
import java.io.IOException;
import java.util.Map;
import java.util.function.Consumer;

public class Utils {
    public static boolean startsWith(byte[] arr, byte[] prefix) {
        if (arr.length < prefix.length) {
            return false;
        } else {
            boolean result = true;

            for (int i = 0; i < prefix.length; i++) {
                result = result && (arr[i] == prefix[i]);
            }
            return result;
        }
    }

    public static void iterateOverPrefix(DB db, byte[] prefix, Consumer<Map.Entry<byte[], byte[]>> f) throws IOException {
        try (DBIterator iter = db.iterator()) {
            iter.seek(prefix);
            while (iter.hasNext() && Utils.startsWith(iter.peekNext().getKey(), prefix)) {
                f.accept(iter.next());
            }
        }
    }
}
