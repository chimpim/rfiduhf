package com.chimpim.rfiduhf;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface RfidUhfReaderConnAdapter {

    void connect() throws Exception;

    boolean hasConnected();

    OutputStream getOutputStream() throws IOException;

    InputStream getInputStream() throws IOException;

    void disconnect();
}
