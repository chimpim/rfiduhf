package com.chimpim.rfiduhf.data;

import com.chimpim.rfiduhf.util.HexStringUtil;

import java.util.Arrays;

public class UhfTag {
    public static final byte TYPE_ISO_6B = (byte) 0x01;
    public static final byte TYPE_GEN2_6C = (byte) 0x04;

    private byte type;

    private byte ant;

    private byte[] data;

    public static UhfTag of14bytes(byte[] bytes) {
        byte[] data = new byte[12];
        System.arraycopy(bytes, 2, data, 0, 12);
        return new UhfTag(bytes[0], bytes[1], data);
    }

    public UhfTag(byte type, byte ant, byte[] data) {
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
        return "UhfTag{" +
                "type=" + (0xFF & type) +
                ", typeName=" + getTypeName() +
                ", ant=" + (0xFF & ant) +
                ", data=" + Arrays.toString(data) +
                ", hexData=" + HexStringUtil.bytesToHexString(data) +
                '}';
    }
}
