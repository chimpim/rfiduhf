package com.chimpim.rfiduhf.internal;

import com.chimpim.rfiduhf.RFIDUHFReader;
import com.chimpim.rfiduhf.RFIDUHFReaderConnAdapter;
import com.chimpim.rfiduhf.util.TimeUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public abstract class AbstractRFIDUHFReader implements RFIDUHFReader {
    private RFIDUHFReaderConnAdapter mConnectionAdapter;
    @Nullable
    private InputStream mInputStream;
    @Nullable
    private OutputStream mOutputStream;
    private final int readResponseInterval;
    private final int readResponseCount;

    public AbstractRFIDUHFReader(@NotNull RFIDUHFReaderConnAdapter adapter,
                                 int readResponseInterval,
                                 int readResponseCount) {
        this.mConnectionAdapter = adapter;
        this.readResponseInterval = readResponseInterval;
        this.readResponseCount = readResponseCount;
    }

    public void connect() throws Exception {
        mConnectionAdapter.connect();
        mInputStream = mConnectionAdapter.getInputStream();
        mOutputStream = mConnectionAdapter.getOutputStream();
    }

    public boolean hasConnected() {
        return mConnectionAdapter.hasConnected();
    }

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
            else TimeUtils.sleep(interval);
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

}
