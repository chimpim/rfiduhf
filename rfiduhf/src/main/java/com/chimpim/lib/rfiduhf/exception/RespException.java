package com.chimpim.lib.rfiduhf.exception;

public abstract class RespException extends Exception {

    private static final long serialVersionUID = -8680590818321104289L;
    private byte[] originalResp;

    RespException(byte[] originalResp, String message) {
        super(message);
        this.originalResp = originalResp;
    }

    public byte[] getOriginalResp() {
        return originalResp;
    }

}
