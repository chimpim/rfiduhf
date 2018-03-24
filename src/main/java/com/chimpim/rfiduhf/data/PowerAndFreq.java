package com.chimpim.rfiduhf.data;

public class PowerAndFreq {
    private byte power;
    private byte freq;

    public PowerAndFreq(byte power, byte freq) {
        this.power = power;
        this.freq = freq;
    }

    public byte getPower() {
        return power;
    }

    public byte getFreq() {
        return freq;
    }

    @Override
    public String toString() {
        return "PowerAndFreq{" +
                "power=" + power +
                ", freq=" + freq +
                '}';
    }
}
