package com.chimpim.lib.rfiduhf.model;

import com.chimpim.lib.rfiduhf.RFIDUHFConstants;

public class Result<T> {
    private byte sc;
    private byte address;
    private T payload;

    public Result(byte sc, byte address, T payload) {
        this.sc = sc;
        this.address = address;
        this.payload = payload;
    }

    public byte getSc() {
        return sc;
    }

    public byte getAddress() {
        return address;
    }

    public T getPayload() {
        return payload;
    }

    public boolean isError() {
        return sc != RFIDUHFConstants.SC_ERR_NONE;
    }

    public String getScName() {
        switch (sc) {
            case RFIDUHFConstants.SC_ERR_NONE:
                return "SC_ERR_NONE";
            case RFIDUHFConstants.SC_ERR_GENERAL_ERR:
                return "SC_ERR_GENERAL_ERR";
            case RFIDUHFConstants.SC_ERR_PAR_SET_FAILED:
                return "SC_ERR_PAR_SET_FAILED";
            case RFIDUHFConstants.SC_ERR_PAR_GET_FAILED:
                return "SC_ERR_PAR_GET_FAILED";
            case RFIDUHFConstants.SC_ERR_NO_TAG:
                return "SC_ERR_NO_TAG";
            case RFIDUHFConstants.SC_ERR_READ_FAILED:
                return "SC_ERR_READ_FAILED";
            case RFIDUHFConstants.SC_ERR_WRITE_FAILED:
                return "SC_ERR_WRITE_FAILED";
            case RFIDUHFConstants.SC_ERR_LOCK_FAILED:
                return "SC_ERR_LOCK_FAILED";
            case RFIDUHFConstants.SC_ERR_ERASE_FAILED:
                return "SC_ERR_ERASE_FAILED";
            case RFIDUHFConstants.SC_ERR_CMD_ERR:
                return "SC_ERR_CMD_ERR";
            case RFIDUHFConstants.SC_ERR_UNDEFINED:
                return "SC_ERR_UNDEFINED";
            default:
                return "UNKNOWN";
        }
    }

    @Override
    public String toString() {
        return "Result{" +
                "sc=" + getScName() +
                ", address=" + address +
                ", payload=" + payload +
                '}';
    }
}
