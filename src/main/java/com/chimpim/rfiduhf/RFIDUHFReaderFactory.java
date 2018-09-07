package com.chimpim.rfiduhf;

import com.chimpim.rfiduhf.internal.RFIDUHFProtocolImpl;
import org.jetbrains.annotations.NotNull;

public final class RFIDUHFReaderFactory {

    @NotNull
    public static RFIDUHFReader createRFIDUHFReader(@NotNull RFIDUHFReaderConnAdapter adapter) {
        return createRFIDUHFReader(adapter, RFIDUHFConstants.ADDRESS_COMMUNAL);
    }

    @NotNull
    public static RFIDUHFReader createRFIDUHFReader(@NotNull RFIDUHFReaderConnAdapter adapter,
                                                    byte address) {
        return createRFIDUHFReader(adapter, address, 10, 50);
    }

    @NotNull
    public static RFIDUHFReader createRFIDUHFReader(@NotNull RFIDUHFReaderConnAdapter adapter,
                                                    byte readerAddress,
                                                    int readResponseInterval,
                                                    int readResponseCount) {
        return new RFIDUHFReaderImpl(adapter, readerAddress,
                readResponseInterval,
                readResponseCount,
                new RFIDUHFProtocolImpl());
    }

}
