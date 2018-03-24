package com.chimpim.rfiduhf.data;

public class Version {
    private byte major;
    private byte minor;

    public Version(byte major, byte minor) {
        this.major = major;
        this.minor = minor;
    }

    public byte getMajor() {
        return major;
    }

    public byte getMinor() {
        return minor;
    }

    @Override
    public String toString() {
        return "Version{" +
                "major=" + major +
                ", minor=" + minor +
                '}';
    }
}
