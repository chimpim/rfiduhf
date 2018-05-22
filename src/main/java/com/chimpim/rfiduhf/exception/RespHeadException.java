package com.chimpim.rfiduhf.exception;

public class RespHeadException extends RespException {

    private static final long serialVersionUID = 5571883753880007563L;

    public RespHeadException(byte[] resp, String message) {
        super(resp, message);
    }
}
