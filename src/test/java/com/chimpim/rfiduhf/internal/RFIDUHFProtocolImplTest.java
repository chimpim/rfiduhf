package com.chimpim.rfiduhf.internal;

import com.chimpim.rfiduhf.RFIDUHFConstants;
import com.chimpim.rfiduhf.internal.util.HexStringUtils;
import org.junit.Test;

import java.util.Arrays;

public class RFIDUHFProtocolImplTest {

    @Test
    public void getFirmwareVersion() {
        RFIDUHFProtocolImpl rfidUhfProtocol = new RFIDUHFProtocolImpl();
        byte[] command = rfidUhfProtocol.getFirmwareVersion((byte) 0xFF);
        System.out.println(HexStringUtils.bytesToHexString(command));
    }

    @Test
    public void setWorkMode() {
        RFIDUHFProtocolImpl rfidUhfProtocol = new RFIDUHFProtocolImpl();
        byte[] command = rfidUhfProtocol.setWorkMode((byte) 0xFF, RFIDUHFConstants.WORK_MODE_COMMAND);
        System.out.println(HexStringUtils.bytesToHexString(command));
        System.out.println(Arrays.toString(HexStringUtils.hexStringToBytes(HexStringUtils.bytesToHexString(command))));
    }
}