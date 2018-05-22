package com.chimpim.rfiduhf;

import com.chimpim.rfiduhf.internal.RfidUhfProtocolImpl;
import com.chimpim.rfiduhf.internal.RfidUhfReader;
import org.jetbrains.annotations.NotNull;

public final class RfidUhfReaderApiFactory {

    public static RfidUhfReaderApi create(@NotNull RfidUhfReaderConnAdapter adapter) {
        return create(adapter, RfidUhfConstant.ADDRESS_COMMUNAL);
    }

    public static RfidUhfReaderApi create(@NotNull RfidUhfReaderConnAdapter adapter,
                                          byte address) {
        return create(adapter, address, 10, 50);
    }

    public static RfidUhfReaderApi create(@NotNull RfidUhfReaderConnAdapter adapter,
                                          byte address,
                                          int readResponseInterval,
                                          int readResponseCount) {
        RfidUhfReader reader = new RfidUhfReader(adapter, readResponseInterval, readResponseCount);
        return new RfidUhfReaderApiImpl(reader, address, RfidUhfProtocolImpl.INSTANCE);
    }

}
