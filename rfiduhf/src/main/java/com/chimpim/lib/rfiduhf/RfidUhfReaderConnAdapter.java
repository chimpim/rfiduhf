package com.chimpim.lib.rfiduhf;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface RfidUhfReaderConnAdapter {

    void connect() throws Exception;

    boolean isConnected();

    @NotNull
    OutputStream getOutputStream() throws Exception;

    @NotNull
    InputStream getInputStream() throws Exception;

    void disconnect();

}
