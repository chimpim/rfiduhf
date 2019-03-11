package com.chimpim.lib.rfiduhf;

import com.chimpim.lib.rfiduhf.internal.DefaultRfidUhfProtocol;
import com.chimpim.lib.rfiduhf.internal.RFIDUHFProtocol;
import org.jetbrains.annotations.NotNull;

public class RfidUhfReaderBuilder {
    RfidUhfReaderConnAdapter adapter;
    byte readerAddress = RfidUhfConstants.ADDRESS_COMMUNAL;
    int readResponseInterval = 40;
    int readResponseCount = 25;
    RFIDUHFProtocol protocol;
    Logger logger;

    public RfidUhfReaderBuilder(@NotNull RfidUhfReaderConnAdapter adapter) {
        this.adapter = adapter;
    }

    @NotNull
    public RfidUhfReaderBuilder setAdapter(@NotNull RfidUhfReaderConnAdapter adapter) {
        this.adapter = adapter;
        return this;
    }

    @NotNull
    public RfidUhfReaderBuilder setReaderAddress(byte readerAddress) {
        this.readerAddress = readerAddress;
        return this;
    }

    @NotNull
    public RfidUhfReaderBuilder setReadResponseInterval(int readResponseInterval) {
        this.readResponseInterval = readResponseInterval;
        return this;
    }

    @NotNull
    public RfidUhfReaderBuilder setReadResponseCount(int readResponseCount) {
        this.readResponseCount = readResponseCount;
        return this;
    }

    public RfidUhfReaderBuilder setProtocol(@NotNull RFIDUHFProtocol protocol) {
        this.protocol = protocol;
        return this;
    }

    @NotNull
    public RfidUhfReaderBuilder setLogger(@NotNull Logger logger) {
        this.logger = logger;
        return this;
    }

    @NotNull
    public RfidUhfReader build() {
        if (adapter == null) {
            throw new NullPointerException("RfidUhfReaderConnAdapter is null!");
        }
        if (protocol == null) {
            protocol = new DefaultRfidUhfProtocol();
        }
        if (logger == null) {
            logger = new Logger() {
                @Override
                public void log(@NotNull String msg) {
                    System.out.println(msg);
                }
            };
        }
        return new DefaultRfidUhfReader(this);
    }
}
