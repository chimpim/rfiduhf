package com.chimpim.rfiduhf.exception;

public abstract class RespException extends Exception {

    private static final long serialVersionUID = -8680590818321104289L;
    private byte[] resp;

    RespException(byte[] resp, String message) {
        super(message);
        this.resp = resp;
    }

    public byte[] getResp() {
        return resp;
    }
}
