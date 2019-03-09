package com.chimpim.lib.rfiduhf;

import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import org.jetbrains.annotations.NotNull;

import java.io.InputStream;
import java.io.OutputStream;

class RFIDUHFReaderConnAdapterImpl implements RfidUhfReaderConnAdapter {
    private String port;
    private SerialPort serialPort;


    RFIDUHFReaderConnAdapterImpl(@NotNull String port) {
        this.port = port;
    }

    @Override
    public void connect() throws Exception {
        CommPortIdentifier commPortIdentifier = CommPortIdentifier.getPortIdentifier(port);
        this.serialPort = (SerialPort) commPortIdentifier.open("RFIDUHFReaderConnAdapterImpl", 0);
    }

    @Override
    public boolean isConnected() {
        return serialPort != null;
    }

    @NotNull
    @Override
    public OutputStream getOutputStream() throws Exception {
        return serialPort.getOutputStream();
    }

    @NotNull
    @Override
    public InputStream getInputStream() throws Exception {
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
