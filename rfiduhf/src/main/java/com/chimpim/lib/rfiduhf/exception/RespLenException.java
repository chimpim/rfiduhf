package com.chimpim.lib.rfiduhf.exception;

public class RespLenException extends RespException {
    private static final long serialVersionUID = 8030017996215417866L;

    public RespLenException(byte[] resp, String message) {
        super(resp, message);
    }
}
