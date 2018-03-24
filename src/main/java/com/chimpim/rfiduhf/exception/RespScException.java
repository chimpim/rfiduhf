package com.chimpim.rfiduhf.exception;

public class RespScException extends RespException {
    public RespScException(byte[] resp, String message) {
        super(resp, message);
    }
}
