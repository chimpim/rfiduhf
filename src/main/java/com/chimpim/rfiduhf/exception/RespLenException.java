package com.chimpim.rfiduhf.exception;

public class RespLenException extends RespException {
    public RespLenException(byte[] resp, String message) {
        super(resp, message);
    }
}
