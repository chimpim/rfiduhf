package com.chimpim.rfiduhf;

import com.chimpim.rfiduhf.util.TimeUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

class RfidUhfReader {
    private RfidUhfReaderConnAdapter mConnectionAdapter;
    private @Nullable InputStream mInputStream;
    private @Nullable OutputStream mOutputStream;

    RfidUhfReader(@NotNull RfidUhfReaderConnAdapter adapter) {
        this.mConnectionAdapter = adapter;
    }

    void connect() throws Exception {
        mConnectionAdapter.connect();
        mInputStream = mConnectionAdapter.getInputStream();
        mOutputStream = mConnectionAdapter.getOutputStream();
    }

    boolean hasConnected() {
        return mConnectionAdapter.hasConnected();
    }

    void disconnect() {
        mConnectionAdapter.disconnect();
    }

    void write(@NotNull byte[] cmd) throws IOException {
        writeAndFlush(cmd);
    }

    @Nullable byte[] readResponse() throws IOException {
        return readResponse(10, 50);
    }

    private synchronized void writeAndFlush(@NotNull byte[] cmd) throws IOException {
        if (mOutputStream == null) throw new IOException("mOutputStream == null");
        mOutputStream.write(cmd);
        mOutputStream.flush();
    }

    private synchronized @Nullable byte[] readResponse(final int interval, final int count) throws IOException {
        if (mInputStream == null) throw new IOException("mInputStream == null");
        int counter = 0;
        int available = mInputStream.available();
        // 最多循环500毫秒
        while (available < 3) {
            available = mInputStream.available();
            // 时间超时
            if (counter > count) return null;
            else TimeUtil.sleep(interval);
            counter++;
        }
        // 读取前三个字节
        byte[] buf = new byte[3];
        int len = mInputStream.read(buf);
        if (len != 3) return null;
        byte length = buf[2];
        if (length <= 0) return null;
        available = mInputStream.available();
        while (available < length) {
            available = mInputStream.available();
        }
        //
        byte[] buf2 = new byte[length];
        len = mInputStream.read(buf2);
        byte[] returnBytes = new byte[3 + length];
        System.arraycopy(buf, 0, returnBytes, 0, 3);
        System.arraycopy(buf2, 0, returnBytes, 3, length);
        return returnBytes;
    }
}
