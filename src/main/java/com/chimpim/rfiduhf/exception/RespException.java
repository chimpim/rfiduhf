package com.chimpim.rfiduhf.exception;

public class RespException extends Exception {

    private byte[] resp;

    RespException(byte[] resp, String message) {
        super(message);
        this.resp = resp;
    }

    public byte[] getResp() {
        return resp;
    }
}
