package com.chimpim.rfiduhf.data;

import com.chimpim.rfiduhf.util.HexStringUtils;

import java.util.Arrays;

/**
 * UHF标签
 */
public class UHFTag {
    public static final byte TYPE_ISO_6B = (byte) 0x01;
    public static final byte TYPE_GEN2_6C = (byte) 0x04;

    private byte type;

    private byte ant;

    private byte[] data;

    public UHFTag(byte type, byte ant, byte[] data) {
        this.type = type;
        this.ant = ant;
        this.data = data;
    }

    public byte getType() {
        return type;
    }

    public byte getAnt() {
        return ant;
    }

    public byte[] getData() {
        return data;
    }

    public String getTypeName() {
        switch (type) {
            case TYPE_GEN2_6C:
                return "TYPE_GEN2_6C";
            case TYPE_ISO_6B:
                return "TYPE_ISO_6B";
            default:
                return "UNKNOWN";
        }
    }

    @Override
    public String toString() {
        return "UHFTag{" +
                "type=" + (0xFF & type) +
                ", typeName=" + getTypeName() +
                ", ant=" + (0xFF & ant) +
                ", data=" + Arrays.toString(data) +
                ", hexData=" + HexStringUtils.bytesToHexString(data) +
                '}';
    }
}
