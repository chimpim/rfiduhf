package com.chimpim.rfiduhf.exception;

public class RespHeadException extends RespException {

    public RespHeadException(byte[] resp, String message) {
        super(resp, message);
    }
}
