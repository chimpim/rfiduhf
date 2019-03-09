package com.chimpim.lib.rfiduhf.exception;

public class RespScException extends RespException {
    private static final long serialVersionUID = 6860278472730433489L;

    public RespScException(byte[] resp, String message) {
        super(resp, message);
    }
}
