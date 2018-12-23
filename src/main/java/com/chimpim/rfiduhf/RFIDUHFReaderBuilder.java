package com.chimpim.rfiduhf;

import com.chimpim.rfiduhf.internal.DefaultRFIDUHFProtocol;
import com.chimpim.rfiduhf.internal.RFIDUHFProtocol;
import org.jetbrains.annotations.NotNull;

public class RFIDUHFReaderBuilder {
    RFIDUHFReaderConnAdapter adapter;
    byte readerAddress = RFIDUHFConstants.ADDRESS_COMMUNAL;
    int readResponseInterval = 10;
    int readResponseCount = 50;
    RFIDUHFProtocol protocol;
    Logger logger;

    public RFIDUHFReaderBuilder(@NotNull RFIDUHFReaderConnAdapter adapter) {
        this.adapter = adapter;
    }

    @NotNull
    public RFIDUHFReaderBuilder setAdapter(@NotNull RFIDUHFReaderConnAdapter adapter) {
        this.adapter = adapter;
        return this;
    }

    @NotNull
    public RFIDUHFReaderBuilder setReaderAddress(byte readerAddress) {
        this.readerAddress = readerAddress;
        return this;
    }

    @NotNull
    public RFIDUHFReaderBuilder setReadResponseInterval(int readResponseInterval) {
        this.readResponseInterval = readResponseInterval;
        return this;
    }

    @NotNull
    public RFIDUHFReaderBuilder setReadResponseCount(int readResponseCount) {
        this.readResponseCount = readResponseCount;
        return this;
    }

    public RFIDUHFReaderBuilder setProtocol(@NotNull RFIDUHFProtocol protocol) {
        this.protocol = protocol;
        return this;
    }

    @NotNull
    public RFIDUHFReaderBuilder setLogger(@NotNull Logger logger) {
        this.logger = logger;
        return this;
    }


    @NotNull
    public RFIDUHFReader build() {
        if (adapter == null) {
            throw new NullPointerException("adapter == null");
        }
        if (protocol == null) {
            protocol = new DefaultRFIDUHFProtocol();
        }
        if (logger == null) {
            logger = new Logger() {
                @Override
                public void log(@NotNull String msg) {
                    System.err.println(msg);
                }
            };
        }
        return new DefaultRFIDUHFReader(this);
    }
}
