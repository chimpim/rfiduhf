package com.chimpim.lib.rfiduhf.internal;

import com.chimpim.lib.rfiduhf.RfidUhfReader;
import com.chimpim.lib.rfiduhf.RfidUhfReaderConnAdapter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.TimeUnit;

public abstract class AbstractRfidUhfReader implements RfidUhfReader {
    private RfidUhfReaderConnAdapter mConnectionAdapter;
    @Nullable
    private InputStream mInputStream;
    @Nullable
    private OutputStream mOutputStream;
    private final int readResponseInterval;
    private final int readResponseCount;

    public AbstractRfidUhfReader(
            @NotNull RfidUhfReaderConnAdapter adapter,
            int readResponseInterval,
            int readResponseCount
    ) {
        this.mConnectionAdapter = adapter;
        this.readResponseInterval = readResponseInterval;
        this.readResponseCount = readResponseCount;
    }

    @Override
    public void connect() throws Exception {
        mConnectionAdapter.connect();
        mInputStream = mConnectionAdapter.getInputStream();
        mOutputStream = mConnectionAdapter.getOutputStream();
    }

    @Override
    public boolean isConnected() {
        return mConnectionAdapter.isConnected();
    }

    @Override
    public void disconnect() {
        mConnectionAdapter.disconnect();
    }

    protected void write(@NotNull byte[] cmd) throws IOException {
        writeAndFlush(cmd);
    }

    @Nullable
    protected byte[] readResponse() throws IOException {
        return readResponse(readResponseInterval, readResponseCount);
    }

    private synchronized void writeAndFlush(@NotNull byte[] cmd) throws IOException {
        if (mOutputStream == null) throw new IOException("mOutputStream == null");
        mOutputStream.write(cmd);
        mOutputStream.flush();
    }

    @Nullable
    private synchronized byte[] readResponse(final int interval, final int count) throws IOException {
        if (mInputStream == null) throw new IOException("mInputStream == null");
        int counter = 0;
        int available = mInputStream.available();
        // 最多循环interval * count毫秒
        while (available < 3) {
            available = mInputStream.available();
            // 时间超时
            if (counter > count) return null;
            else delay(interval);
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
        // 合并buf 和 buf2
        byte[] returnBytes = new byte[buf.length + buf2.length];
        System.arraycopy(buf, 0, returnBytes, 0, buf.length);
        System.arraycopy(buf2, 0, returnBytes, buf.length, buf2.length);
        return returnBytes;
    }

    private static void delay(int ms) {
        try {
            TimeUnit.MILLISECONDS.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
