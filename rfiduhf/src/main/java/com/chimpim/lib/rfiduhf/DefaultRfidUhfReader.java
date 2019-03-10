package com.chimpim.lib.rfiduhf;

import com.chimpim.lib.rfiduhf.exception.RespException;
import com.chimpim.lib.rfiduhf.internal.AbstractRfidUhfReader;
import com.chimpim.lib.rfiduhf.internal.RFIDUHFProtocol;
import com.chimpim.lib.rfiduhf.model.PowerAndFreq;
import com.chimpim.lib.rfiduhf.model.Result;
import com.chimpim.lib.rfiduhf.model.UhfTag;
import com.chimpim.lib.rfiduhf.model.Version;
import com.chimpim.lib.rfiduhf.util.HexStringUtils;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class DefaultRfidUhfReader extends AbstractRfidUhfReader implements RfidUhfReader {

    private static final UhfTag[] EMPTY_UHF_TAG = new UhfTag[0];

    private final Logger mLogger;
    private final byte mReaderAddr;
    private final RFIDUHFProtocol mProtocol;

    public DefaultRfidUhfReader(@NotNull RfidUhfReaderBuilder builder) {
        super(builder.adapter, builder.readResponseInterval, builder.readResponseCount);
        mReaderAddr = builder.readerAddress;
        mProtocol = builder.protocol;
        this.mLogger = builder.logger;
    }

    @Override
    public Result<Void> setBaudRate(byte baudRate) throws IOException, RespException {
        mLogger.log("");
        byte[] cmd = mProtocol.setBaudRate(mReaderAddr, baudRate);
        mLogger.log("setBaudRate#cmd: " + HexStringUtils.bytesToHexString(cmd));
        write(cmd);
        byte[] resp = readResponse();
        mLogger.log("setBaudRate#resp: " + HexStringUtils.bytesToHexString(resp));
        return mProtocol.setBaudRate(resp);
    }

    @Override
    public Result<Void> resetReader() throws IOException, RespException {
        byte[] cmd = mProtocol.resetReader(mReaderAddr);
        mLogger.log("resetReader#cmd: " + HexStringUtils.bytesToHexString(cmd));
        write(cmd);
        byte[] resp = readResponse();
        mLogger.log("resetReader#resp: " + HexStringUtils.bytesToHexString(resp));
        return mProtocol.resetReader(resp);
    }

    @Override
    public Result<Version> getFirmwareVersion() throws IOException, RespException {
        byte[] cmd = mProtocol.getFirmwareVersion(mReaderAddr);
        mLogger.log("getFirmwareVersion#cmd: " + HexStringUtils.bytesToHexString(cmd));
        write(cmd);
        byte[] resp = readResponse();
        mLogger.log("getFirmwareVersion#resp: " + HexStringUtils.bytesToHexString(resp));
        return mProtocol.getFirmwareVersion(resp);
    }

    @Override
    public Result<Void> setWorkAntenna(byte antCfg) throws IOException, RespException {
        byte[] cmd = mProtocol.setWorkAntenna(mReaderAddr, antCfg);
        mLogger.log("setWorkAntenna#cmd: " + HexStringUtils.bytesToHexString(cmd));
        write(cmd);
        byte[] resp = readResponse();
        mLogger.log("setWorkAntenna#resp: " + HexStringUtils.bytesToHexString(resp));
        return mProtocol.setWorkAntenna(resp);
    }

    @Override
    public Result<Byte> getWorkAntenna() throws IOException, RespException {
        byte[] cmd = mProtocol.getWorkAntenna(mReaderAddr);
        mLogger.log("getWorkAntenna#cmd: " + HexStringUtils.bytesToHexString(cmd));
        write(cmd);
        byte[] resp = readResponse();
        mLogger.log("getWorkAntenna#resp: " + HexStringUtils.bytesToHexString(resp));
        return mProtocol.getWorkAntenna(resp);
    }

    @Override
    public Result<Byte> getWorkMode() throws IOException, RespException {
        byte[] cmd = mProtocol.getWorkMode(mReaderAddr);
        mLogger.log("getWorkMode#cmd: " + HexStringUtils.bytesToHexString(cmd));
        write(cmd);
        byte[] resp = readResponse();
        mLogger.log("getWorkMode#resp: " + HexStringUtils.bytesToHexString(resp));
        return mProtocol.getWorkMode(resp);
    }

    @Override
    public Result<Void> setWorkMode(byte workMode) throws IOException, RespException {
        byte[] cmd = mProtocol.setWorkMode(mReaderAddr, workMode);
        mLogger.log("setWorkMode#cmd: " + HexStringUtils.bytesToHexString(cmd));
        write(cmd);
        byte[] resp = readResponse();
        mLogger.log("setWorkMode#resp: " + HexStringUtils.bytesToHexString(resp));
        return mProtocol.setWorkMode(resp);
    }

    @Override
    public Result<Byte> getAddr() throws IOException, RespException {
        byte[] cmd = mProtocol.getAddr(mReaderAddr);
        mLogger.log("getAddr#cmd: " + HexStringUtils.bytesToHexString(cmd));
        write(cmd);
        byte[] resp = readResponse();
        mLogger.log("getAddr#resp: " + HexStringUtils.bytesToHexString(resp));
        return mProtocol.getAddr(resp);
    }

    @Override
    public Result<Void> setAddr(byte newReaderAddr) throws IOException, RespException {
        byte[] cmd = mProtocol.setAddr(mReaderAddr, newReaderAddr);
        mLogger.log("setAddr#cmd: " + HexStringUtils.bytesToHexString(cmd));
        write(cmd);
        byte[] resp = readResponse();
        mLogger.log("setAddr#resp: " + HexStringUtils.bytesToHexString(resp));
        return mProtocol.setAddr(resp);
    }


    @Override
    public Result<Void> setRf(byte power, byte freq) throws IOException, RespException {
        byte[] cmd = mProtocol.setRf(mReaderAddr, power, freq);
        mLogger.log("setRf#cmd: " + HexStringUtils.bytesToHexString(cmd));
        write(cmd);
        byte[] resp = readResponse();
        mLogger.log("setRf#resp: " + HexStringUtils.bytesToHexString(resp));
        return mProtocol.setRf(resp);
    }

    @Override
    public Result<PowerAndFreq> getRf() throws IOException, RespException {
        byte[] cmd = mProtocol.getRf(mReaderAddr);
        mLogger.log("getRf#cmd: " + HexStringUtils.bytesToHexString(cmd));
        write(cmd);
        byte[] resp = readResponse();
        mLogger.log("getRf#resp: " + HexStringUtils.bytesToHexString(resp));
        return mProtocol.getRf(resp);
    }


    @Override
    public Result<Byte> multiTagIdentify6b() throws IOException, RespException {
        byte[] cmd = mProtocol.multiTagIdentify6b(mReaderAddr);
        mLogger.log("multiTagIdentify6b#cmd: " + HexStringUtils.bytesToHexString(cmd));
        write(cmd);
        byte[] resp = readResponse();
        mLogger.log("multiTagIdentify6b#resp: " + HexStringUtils.bytesToHexString(resp));
        return mProtocol.multiTagIdentify6b(resp);
    }

    @Override
    public Result<Byte> multiTagRead6b(byte startAddr) throws IOException, RespException {
        byte[] cmd = mProtocol.multiTagRead6b(mReaderAddr, startAddr);
        mLogger.log("multiTagRead6b#cmd: " + HexStringUtils.bytesToHexString(cmd));
        write(cmd);
        byte[] resp = readResponse();
        mLogger.log("multiTagRead6b#resp: " + HexStringUtils.bytesToHexString(resp));
        return mProtocol.multiTagRead6b(resp);
    }


    @Override
    public Result<Void> write6b(byte addr, byte value) throws IOException, RespException {
        byte[] cmd = mProtocol.write6b(mReaderAddr, addr, value);
        mLogger.log("write6b#cmd: " + HexStringUtils.bytesToHexString(cmd));
        write(cmd);
        byte[] resp = readResponse();
        mLogger.log("write6b#resp: " + HexStringUtils.bytesToHexString(resp));
        return mProtocol.write6b(resp);
    }

    @Override
    public Result<UhfTag> readWithUid6b(byte[] uid, byte addr) throws IOException, RespException {
        byte[] cmd = mProtocol.readWithUid6b(mReaderAddr, uid, addr);
        mLogger.log("readWithUid6b#cmd: " + HexStringUtils.bytesToHexString(cmd));
        write(cmd);
        byte[] resp = readResponse();
        mLogger.log("readWithUid6b#resp: " + HexStringUtils.bytesToHexString(resp));
        return mProtocol.readWithUid6b(resp);
    }

    @Override
    public Result<Void> writeWithUid6b(byte[] uid, byte addr, byte value) throws IOException, RespException {
        byte[] cmd = mProtocol.writeWithUid6b(mReaderAddr, uid, addr, value);
        mLogger.log("writeWithUid6b#cmd: " + HexStringUtils.bytesToHexString(cmd));
        write(cmd);
        byte[] resp = readResponse();
        mLogger.log("writeWithUid6b#resp: " + HexStringUtils.bytesToHexString(resp));
        return mProtocol.writeWithUid6b(resp);
    }

    @Override
    public Result<Void> lock6b(byte addr) throws IOException, RespException {
        byte[] cmd = mProtocol.lock6b(mReaderAddr, addr);
        mLogger.log("lock6b#cmd: " + HexStringUtils.bytesToHexString(cmd));
        write(cmd);
        byte[] resp = readResponse();
        mLogger.log("lock6b#resp: " + HexStringUtils.bytesToHexString(resp));
        return mProtocol.lock6b(resp);
    }

    @Override
    public Result<Boolean> queryLock6b(byte addr) throws IOException, RespException {
        byte[] cmd = mProtocol.queryLock6b(mReaderAddr, addr);
        mLogger.log("queryLock6b#cmd: " + HexStringUtils.bytesToHexString(cmd));
        write(cmd);
        byte[] resp = readResponse();
        mLogger.log("queryLock6b#resp: " + HexStringUtils.bytesToHexString(resp));
        return mProtocol.queryLock6b(resp);
    }

    @Override
    public Result<Void> blockWrite6b(byte addr, byte[] value) throws IOException, RespException {
        byte[] cmd = mProtocol.blockWrite6b(mReaderAddr, addr, value);
        mLogger.log("blockWrite6b#cmd: " + HexStringUtils.bytesToHexString(cmd));
        write(cmd);
        byte[] resp = readResponse();
        mLogger.log("blockWrite6b#resp: " + HexStringUtils.bytesToHexString(resp));
        return mProtocol.blockWrite6b(resp);
    }

    @Override
    public Result<UhfTag> singleTagRead6b(byte addr) throws IOException, RespException {
        byte[] cmd = mProtocol.singleTagRead6b(mReaderAddr, addr);
        mLogger.log("singleTagRead6b#cmd: " + HexStringUtils.bytesToHexString(cmd));
        write(cmd);
        byte[] resp = readResponse();
        mLogger.log("singleTagRead6b#resp: " + HexStringUtils.bytesToHexString(resp));
        return mProtocol.singleTagRead6b(resp);
    }

    @Override
    public Result<Byte> multiTagIdentify6c() throws IOException, RespException {
        byte[] cmd = mProtocol.multiTagIdentify6c(mReaderAddr);
        mLogger.log("multiTagIdentify6c#cmd: " + HexStringUtils.bytesToHexString(cmd));
        write(cmd);
        byte[] resp = readResponse();
        mLogger.log("multiTagIdentify6c#resp: " + HexStringUtils.bytesToHexString(resp));
        return mProtocol.multiTagIdentify6c(resp);
    }

    @Override
    public Result<Void> epcWrite6c(byte addr, byte[] value) throws IOException, RespException {
        byte[] cmd = mProtocol.epcWrite6c(mReaderAddr, addr, value);
        mLogger.log("epcWrite6c#cmd: " + HexStringUtils.bytesToHexString(cmd));
        write(cmd);
        byte[] resp = readResponse();
        mLogger.log("epcWrite6c#resp: " + HexStringUtils.bytesToHexString(resp));
        return mProtocol.epcWrite6c(resp);
    }

    @Override
    public Result<Void> lock6c(byte memBank, byte control) throws IOException, RespException {
        byte[] cmd = mProtocol.lock6c(mReaderAddr, memBank, control);
        mLogger.log("lock6c#cmd: " + HexStringUtils.bytesToHexString(cmd));
        write(cmd);
        byte[] resp = readResponse();
        mLogger.log("lock6c#resp: " + HexStringUtils.bytesToHexString(resp));
        return mProtocol.lock6c(resp);
    }

    @Override
    public Result<Void> kill6c(byte[] password) throws IOException, RespException {
        byte[] cmd = mProtocol.kill6c(mReaderAddr, password);
        mLogger.log("kill6c#cmd: " + HexStringUtils.bytesToHexString(cmd));
        write(cmd);
        byte[] resp = readResponse();
        mLogger.log("kill6c#resp: " + HexStringUtils.bytesToHexString(resp));
        return mProtocol.kill6c(resp);
    }

    @Override
    public Result<Void> init6c(byte bitCount) throws IOException, RespException {
        byte[] cmd = mProtocol.init6c(mReaderAddr, bitCount);
        mLogger.log("init6c#cmd: " + HexStringUtils.bytesToHexString(cmd));
        write(cmd);
        byte[] resp = readResponse();
        mLogger.log("init6c#resp: " + HexStringUtils.bytesToHexString(resp));
        return mProtocol.init6c(resp);
    }

    @Override
    public Result<UhfTag> read6c(byte memBank, byte addr, byte len) throws IOException, RespException {
        byte[] cmd = mProtocol.read6c(mReaderAddr, memBank, addr, len);
        mLogger.log("read6c#cmd: " + HexStringUtils.bytesToHexString(cmd));
        write(cmd);
        byte[] resp = readResponse();
        mLogger.log("read6c#resp: " + HexStringUtils.bytesToHexString(resp));
        return mProtocol.read6c(resp);
    }

    @Override
    public Result<Void> write6c(byte memBank, byte addr, byte[] value) throws IOException, RespException {
        byte[] cmd = mProtocol.write6c(mReaderAddr, memBank, addr, value);
        mLogger.log("write6c#cmd: " + HexStringUtils.bytesToHexString(cmd));
        write(cmd);
        byte[] resp = readResponse();
        mLogger.log("write6c#resp: " + HexStringUtils.bytesToHexString(resp));
        return mProtocol.write6c(resp);
    }

    @Override
    public Result<UhfTag[]> getIdAndDelete(byte count) throws IOException, RespException {
        byte[] cmd = mProtocol.getIdAndDelete(mReaderAddr, count);
        mLogger.log("getIdAndDelete#cmd: " + HexStringUtils.bytesToHexString(cmd));
        write(cmd);
        byte[] resp = readResponse();
        mLogger.log("getIdAndDelete#resp: " + HexStringUtils.bytesToHexString(resp));
        return mProtocol.getIdAndDelete(resp);
    }

    @Override
    public Result<UhfTag> getId() throws IOException, RespException {
        byte[] cmd = mProtocol.getId(mReaderAddr);
        mLogger.log("getId#cmd: " + HexStringUtils.bytesToHexString(cmd));
        write(cmd);
        byte[] resp = readResponse();
        mLogger.log("getId#resp: " + HexStringUtils.bytesToHexString(resp));
        return mProtocol.getId(resp);
    }

    @Override
    public Result getIdAck() throws IOException, RespException {
        byte[] cmd = mProtocol.getIdAck(mReaderAddr);
        mLogger.log("getIdAck#cmd: " + HexStringUtils.bytesToHexString(cmd));
        write(cmd);
        byte[] resp = readResponse();
        mLogger.log("getIdAck#resp: " + HexStringUtils.bytesToHexString(resp));
        return mProtocol.getIdAck(resp);
    }

    @Override
    public Result<Byte> queryIdCount() throws IOException, RespException {
        byte[] cmd = mProtocol.queryIdCount(mReaderAddr);
        mLogger.log("queryIdCount#cmd: " + HexStringUtils.bytesToHexString(cmd));
        write(cmd);
        byte[] resp = readResponse();
        mLogger.log("queryIdCount#resp: " + HexStringUtils.bytesToHexString(resp));
        return mProtocol.queryIdCount(resp);
    }

    @Override
    public Result<Void> clearIdBuffer() throws IOException, RespException {
        byte[] cmd = mProtocol.clearIdBuffer(mReaderAddr);
        mLogger.log("clearIdBuffer#cmd: " + HexStringUtils.bytesToHexString(cmd));
        write(cmd);
        byte[] resp = readResponse();
        mLogger.log("clearIdBuffer#resp: " + HexStringUtils.bytesToHexString(resp));
        return mProtocol.clearIdBuffer(resp);
    }

    @Override
    public boolean fastBatchBlockWrite6b(byte startAddr, @NotNull byte[] values) throws IOException, RespException {
        int len = values.length / 4;
        int index = 0;
        for (int i = 0; i < len; i++) {
            byte addr = (byte) (startAddr + i * 4);
            byte[] value = new byte[]{values[index++], values[index++], values[index++], values[index++]};
            Result<Void> result = blockWrite6b(addr, value);
            mLogger.log(result.toString());
            if (result.isError()) {
                mLogger.log(result.getScName());
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean fastBatchWrite6b(byte startAddr, @NotNull byte[] values) throws IOException, RespException {
        int len = values.length;
        int index = 0;
        for (int i = 0; i < len; i++) {
            byte addr = (byte) (startAddr + i);
            Result<Void> result = write6b(addr, values[index++]);
            mLogger.log(result.toString());
            if (result.isError()) {
                mLogger.log(result.getScName());
                return false;
            }
        }
        return true;
    }


    @NotNull
    @Override
    public UhfTag[] fastMultiTagRead6b(byte startAddr) throws IOException, RespException {
        // 清空缓存区
        clearIdBuffer();
        // 多标签读取
        Result<Byte> byteResult = multiTagRead6b(startAddr);
        // 从缓存区中取出标签
        Result<UhfTag[]> result = getIdAndDelete(byteResult.getPayload());
        return returnNonNullUhfTagArray(result.getPayload());
    }


    @NotNull
    @Override
    public UhfTag[] fastMultiTagIdentify6b() throws IOException, RespException {
        // 清空缓存区
        clearIdBuffer();
        // 多标签识别
        Result<Byte> byteResult = multiTagIdentify6b();
        // 从缓存区中取出标签
        Result<UhfTag[]> result = getIdAndDelete(byteResult.getPayload());
        return returnNonNullUhfTagArray(result.getPayload());
    }

    @Override
    public boolean fastBatchWrite6c(byte memBank, byte startAddr, @NotNull byte[] values) throws IOException, RespException {
        int len = values.length / 2;
        int index = 0;
        for (int i = 0; i < len; i++) {
            byte addr = (byte) (startAddr + i);
            byte[] value = new byte[]{values[index++], values[index++]};
            Result<Void> result = write6c(memBank, addr, value);
            mLogger.log(result.toString());
            if (result.isError()) {
                mLogger.log(result.getScName());
                return false;
            }
        }
        return true;
    }

    @NotNull
    @Override
    public UhfTag[] fastMultiTagIdentify6c() throws IOException, RespException {
        // 清空缓存区
        clearIdBuffer();
        // 多标签识别
        Result<Byte> byteResult = multiTagIdentify6c();
        // 从缓存区中取出标签
        Result<UhfTag[]> result = getIdAndDelete(byteResult.getPayload());
        return returnNonNullUhfTagArray(result.getPayload());
    }

    @NotNull
    private static UhfTag[] returnNonNullUhfTagArray(final UhfTag[] array) {
        if (array == null) {
            return EMPTY_UHF_TAG;
        }
        return array;
    }

}
