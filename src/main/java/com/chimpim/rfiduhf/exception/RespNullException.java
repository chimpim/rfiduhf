package com.chimpim.rfiduhf.exception;

public class RespNullException extends RespException {
    public RespNullException(byte[] resp, String message) {
        super(resp, message);
    }
}
