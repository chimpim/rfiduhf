package com.chimpim.rfiduhf.internal;

import com.chimpim.rfiduhf.data.PowerAndFreq;
import com.chimpim.rfiduhf.data.Result;
import com.chimpim.rfiduhf.data.UhfTag;
import com.chimpim.rfiduhf.data.Version;
import com.chimpim.rfiduhf.exception.RespException;
import org.jetbrains.annotations.NotNull;

interface RespParser {

    // =========================== 系统设置命令 =========================== //
    @NotNull
    Result<Void> setBaudRate(byte[] resp) throws RespException;

    @NotNull
    Result<Void> resetReader(byte[] resp) throws RespException;

    @NotNull
    Result<Version> getFirmwareVersion(byte[] resp) throws RespException;

    @NotNull
    Result<Void> setRf(byte[] resp) throws RespException;

    @NotNull
    Result<PowerAndFreq> getRf(byte[] resp) throws RespException;

    @NotNull
    Result<Void> setWorkAntenna(byte[] resp) throws RespException;

    @NotNull
    Result<Byte> getWorkAntenna(byte[] resp) throws RespException;

    @NotNull
    Result<Void> setWorkMode(byte[] resp) throws RespException;

    @NotNull
    Result<Byte> getWorkMode(byte[] resp) throws RespException;

    // =========================== 6B标签操作命令 =========================== //
    @NotNull
    Result<Byte> multiTagIdentify6b(byte[] resp) throws RespException;

    @NotNull
    Result<Byte> multiTagRead6b(byte[] resp) throws RespException;

    @NotNull
    Result<Void> write6b(byte[] resp) throws RespException;

    @NotNull
    Result<UhfTag> readWithUid6b(byte[] resp) throws RespException;

    @NotNull
    Result<Void> writeWithUid6b(byte[] resp) throws RespException;

    @NotNull
    Result<Void> lock6b(byte[] resp) throws RespException;

    @NotNull
    Result<Boolean> queryLock6b(byte[] resp) throws RespException;

    @NotNull
    Result<Void> blockWrite6b(byte[] resp) throws RespException;

    @NotNull
    Result<UhfTag> singleTagRead6b(byte[] resp) throws RespException;

    // =========================== 6C标签操作命令 =========================== //
    @NotNull
    Result<Byte> multiTagIdentify6c(byte[] resp) throws RespException;

    @NotNull
    Result<Void> epcWrite6c(byte[] resp) throws RespException;

    @NotNull
    Result<Void> lock6c(byte[] resp) throws RespException;

    @NotNull
    Result<Void> kill6c(byte[] resp) throws RespException;

    @NotNull
    Result<Void> init6c(byte[] resp) throws RespException;

    @NotNull
    Result<UhfTag> read6c(byte[] resp) throws RespException;

    @NotNull
    Result<Void> write6c(byte[] resp) throws RespException;

    // =========================== 缓存管理命令 =========================== //
    @NotNull
    Result<UhfTag[]> getIdAndDelete(byte[] resp) throws RespException;

    @NotNull
    Result<UhfTag> getId(byte[] resp) throws RespException;

    @NotNull
    Result getIdAck(byte[] resp) throws RespException;

    @NotNull
    Result<Byte> queryIdCount(byte[] resp) throws RespException;

    @NotNull
    Result<Void> clearIdBuffer(byte[] resp) throws RespException;
}
