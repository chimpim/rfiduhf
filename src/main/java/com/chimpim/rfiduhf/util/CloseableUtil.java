package com.chimpim.rfiduhf.util;

import java.io.Closeable;
import java.io.IOException;

public class CloseableUtil {
    // 安全的关闭可关闭的对象
    public static void close(Closeable... closeables) {
        for (Closeable closeable : closeables) {
            if (closeable == null) continue;
            try {
                closeable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
