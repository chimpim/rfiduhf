package com.chimpim.rfiduhf.internal;

import org.jetbrains.annotations.NotNull;

interface ReqCmd {

    // =========================== 系统设置命令 =========================== //
    @NotNull
    byte[] setBaudRate(byte readerAddr, byte baudRate);

    @NotNull
    byte[] resetReader(byte readerAddr);

    @NotNull
    byte[] getFirmwareVersion(byte readerAddr);

    @NotNull
    byte[] setWorkAntenna(byte readerAddr, byte antCfg);

    @NotNull
    byte[] getWorkAntenna(byte readerAddr);

    @NotNull
    byte[] setRf(byte readerAddr, byte power, byte freq);

    @NotNull
    byte[] getRf(byte readerAddr);

    @NotNull
    byte[] setWorkMode(byte readerAddr, byte workMode);

    @NotNull
    byte[] getWorkMode(byte readerAddr);

    @NotNull
    byte[] setAddr(byte readerAddr, byte newReaderAddr);

    @NotNull
    byte[] getAddr(byte readerAddr);

    // =========================== 6B标签操作命令 =========================== //
    @NotNull
    byte[] multiTagIdentify6b(byte readerAddr);

    @NotNull
    byte[] multiTagRead6b(byte readerAddr, byte startAddr);

    @NotNull
    byte[] write6b(byte readerAddr, byte addr, byte value);

    @NotNull
    byte[] readWithUid6b(byte readerAddr, byte[] uid, byte addr);

    @NotNull
    byte[] writeWithUid6b(byte readerAddr, byte[] uid, byte addr, byte value);

    @NotNull
    byte[] lock6b(byte readerAddr, byte addr);

    @NotNull
    byte[] queryLock6b(byte readerAddr, byte addr);

    @NotNull
    byte[] blockWrite6b(byte readerAddr, byte addr, byte[] value);

    @NotNull
    byte[] singleTagRead6b(byte readerAddr, byte addr);

    // =========================== 6C标签操作命令 =========================== //
    @NotNull
    byte[] multiTagIdentify6c(byte readerAddr);

    @NotNull
    byte[] epcWrite6c(byte readerAddr, byte addr, byte[] value);

    @NotNull
    byte[] lock6c(byte readerAddr, byte memBank, byte control);

    @NotNull
    byte[] kill6c(byte readerAddr, byte[] password);

    @NotNull
    byte[] init6c(byte readerAddr, byte bitCount);

    @NotNull
    byte[] read6c(byte readerAddr, byte memBank, byte addr, byte len);

    @NotNull
    byte[] write6c(byte readerAddr, byte memBank, byte addr, byte[] value);

    // =========================== 缓存管理命令 =========================== //
    @NotNull
    byte[] getIdAndDelete(byte readerAddr, byte tagCount);

    @NotNull
    byte[] getId(byte readerAddr);

    @NotNull
    byte[] getIdAck(byte readerAddr);

    @NotNull
    byte[] queryIdCount(byte readerAddr);

    @NotNull
    byte[] clearIdBuffer(byte readerAddr);
}
