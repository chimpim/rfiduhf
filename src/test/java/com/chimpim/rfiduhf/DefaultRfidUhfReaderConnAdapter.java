package com.chimpim.rfiduhf;

import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DefaultRfidUhfReaderConnAdapter implements RfidUhfReaderConnAdapter {
    private String port;
    private SerialPort serialPort;


    DefaultRfidUhfReaderConnAdapter(@NotNull String port) {
        this.port = port;
    }

    @Override
    public void connect() throws Exception {
        CommPortIdentifier commPortIdentifier = CommPortIdentifier.getPortIdentifier(port);
        this.serialPort = (SerialPort) commPortIdentifier.open("DefaultRfidUhfReaderConnAdapter", 0);
    }

    @Override
    public boolean hasConnected() {
        return serialPort != null;
    }

    @Override
    public OutputStream getOutputStream() throws IOException {
        return serialPort.getOutputStream();
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return serialPort.getInputStream();
    }

    @Override
    public void disconnect() {
        if (serialPort != null) {
            serialPort.close();
        }
        serialPort = null;
    }
}
