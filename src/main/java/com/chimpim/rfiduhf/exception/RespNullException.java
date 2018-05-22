package com.chimpim.rfiduhf.exception;

public class RespNullException extends RespException {
    private static final long serialVersionUID = 8390373978645168670L;

    public RespNullException(byte[] resp, String message) {
        super(resp, message);
    }
}
