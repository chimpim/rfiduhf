package com.chimpim.rfiduhf;

import com.chimpim.rfiduhf.data.PowerAndFreq;
import com.chimpim.rfiduhf.data.Result;
import com.chimpim.rfiduhf.data.UhfTag;
import com.chimpim.rfiduhf.data.Version;
import com.chimpim.rfiduhf.exception.RespException;
import com.chimpim.rfiduhf.internal.RfidUhfProtocol;
import com.chimpim.rfiduhf.internal.RfidUhfReader;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Logger;

class RfidUhfReaderApiImpl implements RfidUhfReaderApi {
    private static final Logger logger = Logger.getLogger("RfidUhfReaderApiImpl");
    private final RfidUhfReader mReader;
    private final byte mAddress;
    private final RfidUhfProtocol mProtocol;


    RfidUhfReaderApiImpl(@NotNull RfidUhfReader reader, byte address, @NotNull RfidUhfProtocol protocol) {
        mReader = reader;
        mAddress = address;
        mProtocol = protocol;
    }

    @Override
    public void connect() throws Exception {
        mReader.connect();
    }

    @Override
    public boolean hasConnected() {
        return mReader.hasConnected();
    }

    @Override
    public void disconnect() {
        mReader.disconnect();
    }

    @Override
    public Result<Void> setBaudRate(byte baudRate) throws IOException, RespException {
        byte[] cmd = mProtocol.setBaudRate(mAddress, baudRate);
        logger.info("cmd: " + Arrays.toString(cmd));
        mReader.write(cmd);
        byte[] resp = mReader.readResponse();
        logger.info("resp: " + Arrays.toString(resp));
        return mProtocol.setBaudRate(resp);
    }

    @Override
    public Result<Void> resetReader() throws IOException, RespException {
        byte[] cmd = mProtocol.resetReader(mAddress);
        logger.info("cmd: " + Arrays.toString(cmd));
        mReader.write(cmd);
        byte[] resp = mReader.readResponse();
        logger.info("resp: " + Arrays.toString(resp));
        return mProtocol.resetReader(resp);
    }

    @Override
    public Result<Version> getFirmwareVersion() throws IOException, RespException {
        byte[] cmd = mProtocol.getFirmwareVersion(mAddress);
        logger.info("cmd: " + Arrays.toString(cmd));
        mReader.write(cmd);
        byte[] resp = mReader.readResponse();
        logger.info("resp: " + Arrays.toString(resp));
        return mProtocol.getFirmwareVersion(resp);
    }

    @Override
    public Result<Void> setRF(byte power, byte freq) throws IOException, RespException {
        byte[] cmd = mProtocol.setRF(mAddress, power, freq);
        logger.info("cmd: " + Arrays.toString(cmd));
        mReader.write(cmd);
        byte[] resp = mReader.readResponse();
        logger.info("resp: " + Arrays.toString(resp));
        return mProtocol.setRF(resp);
    }

    @Override
    public Result<PowerAndFreq> getRF() throws IOException, RespException {
        byte[] cmd = mProtocol.getRF(mAddress);
        logger.info("cmd: " + Arrays.toString(cmd));
        mReader.write(cmd);
        byte[] resp = mReader.readResponse();
        logger.info("resp: " + Arrays.toString(resp));
        return mProtocol.getRF(resp);
    }

    @Override
    public Result<Void> setWorkAntenna(byte antCfg) throws IOException, RespException {
        byte[] cmd = mProtocol.setWorkAntenna(mAddress, antCfg);
        logger.info("cmd: " + Arrays.toString(cmd));
        mReader.write(cmd);
        byte[] resp = mReader.readResponse();
        logger.info("resp: " + Arrays.toString(resp));
        return mProtocol.setWorkAntenna(resp);
    }

    @Override
    public Result<Byte> getWorkAntenna() throws IOException, RespException {
        byte[] cmd = mProtocol.getWorkAntenna(mAddress);
        logger.info("cmd: " + Arrays.toString(cmd));
        mReader.write(cmd);
        byte[] resp = mReader.readResponse();
        logger.info("resp: " + Arrays.toString(resp));
        return mProtocol.getWorkAntenna(resp);
    }


    @Override
    public Result<Byte> isoMultiTagIdentify() throws IOException, RespException {
        byte[] cmd = mProtocol.isoMultiTagIdentify(mAddress);
        logger.info("cmd: " + Arrays.toString(cmd));
        mReader.write(cmd);
        byte[] resp = mReader.readResponse();
        logger.info("resp: " + Arrays.toString(resp));
        return mProtocol.isoMultiTagIdentify(resp);
    }

    @Override
    public Result<Byte> isoMultiTagRead(byte startAddr) throws IOException, RespException {
        byte[] cmd = mProtocol.isoMultiTagRead(mAddress, startAddr);
        logger.info("cmd: " + Arrays.toString(cmd));
        mReader.write(cmd);
        byte[] resp = mReader.readResponse();
        logger.info("resp: " + Arrays.toString(resp));
        return mProtocol.isoMultiTagRead(resp);
    }


    @Override
    public Result<Void> isoWrite(byte addr, byte value) throws IOException, RespException {
        byte[] cmd = mProtocol.isoWrite(mAddress, addr, value);
        logger.info("cmd: " + Arrays.toString(cmd));
        mReader.write(cmd);
        byte[] resp = mReader.readResponse();
        logger.info("resp: " + Arrays.toString(resp));
        return mProtocol.isoWrite(resp);
    }

    @Override
    public Result<UhfTag> isoReadWithUID(byte[] uid, byte addr) throws IOException, RespException {
        byte[] cmd = mProtocol.isoReadWithUID(mAddress, uid, addr);
        logger.info("cmd: " + Arrays.toString(cmd));
        mReader.write(cmd);
        byte[] resp = mReader.readResponse();
        logger.info("resp: " + Arrays.toString(resp));
        return mProtocol.isoReadWithUID(resp);
    }

    @Override
    public Result<Void> isoWriteWithUID(byte[] uid, byte addr, byte value) throws IOException, RespException {
        byte[] cmd = mProtocol.isoWriteWithUID(mAddress, uid, addr, value);
        logger.info("cmd: " + Arrays.toString(cmd));
        mReader.write(cmd);
        byte[] resp = mReader.readResponse();
        logger.info("resp: " + Arrays.toString(resp));
        return mProtocol.isoWriteWithUID(resp);
    }

    @Override
    public Result<Void> isoLock(byte addr) throws IOException, RespException {
        byte[] cmd = mProtocol.isoLock(mAddress, addr);
        logger.info("cmd: " + Arrays.toString(cmd));
        mReader.write(cmd);
        byte[] resp = mReader.readResponse();
        logger.info("resp: " + Arrays.toString(resp));
        return mProtocol.isoLock(resp);
    }

    @Override
    public Result<Boolean> isoQueryLock(byte addr) throws IOException, RespException {
        byte[] cmd = mProtocol.isoQueryLock(mAddress, addr);
        logger.info("cmd: " + Arrays.toString(cmd));
        mReader.write(cmd);
        byte[] resp = mReader.readResponse();
        logger.info("resp: " + Arrays.toString(resp));
        return mProtocol.isoQueryLock(resp);
    }

    @Override
    public Result<Void> isoBlockWrite(byte addr, byte[] value) throws IOException, RespException {
        byte[] cmd = mProtocol.isoBlockWrite(mAddress, addr, value);
        logger.info("cmd: " + Arrays.toString(cmd));
        mReader.write(cmd);
        byte[] resp = mReader.readResponse();
        logger.info("resp: " + Arrays.toString(resp));
        return mProtocol.isoBlockWrite(resp);
    }

    @Override
    public Result<UhfTag> isoSingleTagRead(byte addr) throws IOException, RespException {
        byte[] cmd = mProtocol.isoSingleTagRead(mAddress, addr);
        logger.info("cmd: " + Arrays.toString(cmd));
        mReader.write(cmd);
        byte[] resp = mReader.readResponse();
        logger.info("resp: " + Arrays.toString(resp));
        return mProtocol.isoSingleTagRead(resp);
    }

    @Override
    public Result<Byte> gen2MultiTagIdentify() throws IOException, RespException {
        byte[] cmd = mProtocol.gen2MultiTagIdentify(mAddress);
        logger.info("cmd: " + Arrays.toString(cmd));
        mReader.write(cmd);
        byte[] resp = mReader.readResponse();
        logger.info("resp: " + Arrays.toString(resp));
        return mProtocol.gen2MultiTagIdentify(resp);
    }

    @Override
    public Result<Void> gen2EPCWrite(byte addr, byte[] value) throws IOException, RespException {
        byte[] cmd = mProtocol.gen2EPCWrite(mAddress, addr, value);
        logger.info("cmd: " + Arrays.toString(cmd));
        mReader.write(cmd);
        byte[] resp = mReader.readResponse();
        logger.info("resp: " + Arrays.toString(resp));
        return mProtocol.gen2EPCWrite(resp);
    }

    @Override
    public Result<Void> gen2Lock(byte memBank, byte control) throws IOException, RespException {
        byte[] cmd = mProtocol.gen2Lock(mAddress, memBank, control);
        logger.info("cmd: " + Arrays.toString(cmd));
        mReader.write(cmd);
        byte[] resp = mReader.readResponse();
        logger.info("resp: " + Arrays.toString(resp));
        return mProtocol.gen2Lock(resp);
    }

    @Override
    public Result<Void> gen2Kill(byte[] password) throws IOException, RespException {
        byte[] cmd = mProtocol.gen2Kill(mAddress, password);
        logger.info("cmd: " + Arrays.toString(cmd));
        mReader.write(cmd);
        byte[] resp = mReader.readResponse();
        logger.info("resp: " + Arrays.toString(resp));
        return mProtocol.gen2Kill(resp);
    }

    @Override
    public Result<Void> gen2Init(byte bitCount) throws IOException, RespException {
        byte[] cmd = mProtocol.gen2Init(mAddress, bitCount);
        logger.info("cmd: " + Arrays.toString(cmd));
        mReader.write(cmd);
        byte[] resp = mReader.readResponse();
        logger.info("resp: " + Arrays.toString(resp));
        return mProtocol.gen2Init(resp);
    }

    @Override
    public Result<UhfTag> gen2Read(byte memBank, byte addr, byte len) throws IOException, RespException {
        byte[] cmd = mProtocol.gen2Read(mAddress, memBank, addr, len);
        logger.info("cmd: " + Arrays.toString(cmd));
        mReader.write(cmd);
        byte[] resp = mReader.readResponse();
        logger.info("resp: " + Arrays.toString(resp));
        return mProtocol.gen2Read(resp);
    }

    @Override
    public Result<Void> gen2Write(byte memBank, byte addr, byte[] value) throws IOException, RespException {
        byte[] cmd = mProtocol.gen2Write(mAddress, memBank, addr, value);
        logger.info("cmd: " + Arrays.toString(cmd));
        mReader.write(cmd);
        byte[] resp = mReader.readResponse();
        logger.info("resp: " + Arrays.toString(resp));
        return mProtocol.gen2Write(resp);
    }

    @Override
    public Result<UhfTag[]> getIDAndDelete(byte count) throws IOException, RespException {
        byte[] cmd = mProtocol.getIDAndDelete(mAddress, count);
        logger.info("cmd: " + Arrays.toString(cmd));
        mReader.write(cmd);
        byte[] resp = mReader.readResponse();
        logger.info("resp: " + Arrays.toString(resp));
        return mProtocol.getIDAndDelete(resp);
    }

    @Override
    public Result<UhfTag> getID() throws IOException, RespException {
        byte[] cmd = mProtocol.getID(mAddress);
        logger.info("cmd: " + Arrays.toString(cmd));
        mReader.write(cmd);
        byte[] resp = mReader.readResponse();
        logger.info("resp: " + Arrays.toString(resp));
        return mProtocol.getID(resp);
    }

    @Override
    public Result getIDACK() throws IOException, RespException {
        byte[] cmd = mProtocol.getIDACK(mAddress);
        logger.info("cmd: " + Arrays.toString(cmd));
        mReader.write(cmd);
        byte[] resp = mReader.readResponse();
        logger.info("resp: " + Arrays.toString(resp));
        return mProtocol.getIDACK(resp);
    }

    @Override
    public Result<Byte> queryIDCount() throws IOException, RespException {
        byte[] cmd = mProtocol.queryIDCount(mAddress);
        logger.info("cmd: " + Arrays.toString(cmd));
        mReader.write(cmd);
        byte[] resp = mReader.readResponse();
        logger.info("resp: " + Arrays.toString(resp));
        return mProtocol.queryIDCount(resp);
    }

    @Override
    public Result<Void> clearIDBuffer() throws IOException, RespException {
        byte[] cmd = mProtocol.clearIDBuffer(mAddress);
        logger.info("cmd: " + Arrays.toString(cmd));
        mReader.write(cmd);
        byte[] resp = mReader.readResponse();
        logger.info("resp: " + Arrays.toString(resp));
        return mProtocol.clearIDBuffer(resp);
    }

    @Override
    public boolean fastBatchIsoBlockWrite(byte startAddr, @NotNull byte[] values) throws IOException, RespException {
        int len = values.length / 4;
        int index = 0;
        for (int i = 0; i < len; i++) {
            byte addr = (byte) (startAddr + i * 4);
            byte[] value = new byte[]{values[index++], values[index++], values[index++], values[index++]};
            Result<Void> result = isoBlockWrite(addr, value);
            logger.info(result.toString());
            if (!result.isOk()) {
                logger.info(result.getScName());
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean fastBatchIsoWrite(byte startAddr, @NotNull byte[] values) throws IOException, RespException {
        int len = values.length;
        int index = 0;
        for (int i = 0; i < len; i++) {
            byte addr = (byte) (startAddr + i);
            Result<Void> result = isoWrite(addr, values[index++]);
            logger.info(result.toString());
            if (!result.isOk()) {
                logger.info(result.getScName());
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean fastBatchGen2Write(byte memBank, byte startAddr, @NotNull byte[] values) throws IOException, RespException {
        int len = values.length / 2;
        int index = 0;
        for (int i = 0; i < len; i++) {
            byte addr = (byte) (startAddr + i);
            byte[] value = new byte[]{values[index++], values[index++]};
            Result<Void> result = gen2Write(memBank, addr, value);
            logger.info(result.toString());
            if (!result.isOk()) {
                logger.info(result.getScName());
                return false;
            }
        }
        return true;
    }

    @Override
    public UhfTag[] fastIsoMultiTagRead(byte startAddr) throws IOException, RespException {
        // 清空缓存区
        clearIDBuffer();
        // 多标签读取
        Result<Byte> result = isoMultiTagRead(startAddr);
        // 从缓存区中取出标签
        Result<UhfTag[]> result1 = getIDAndDelete(result.getPayload());
        return result1.getPayload();
    }


    @Override
    public UhfTag[] fastIsoMultiTagIdentify() throws IOException, RespException {
        // 清空缓存区
        clearIDBuffer();
        // 多标签识别
        Result<Byte> result = isoMultiTagIdentify();
        // 从缓存区中取出标签
        Result<UhfTag[]> result1 = getIDAndDelete(result.getPayload());
        return result1.getPayload();
    }

    @Override
    public UhfTag[] fastGen2MultiTagIdentify() throws IOException, RespException {
        // 清空缓存区
        clearIDBuffer();
        // 多标签识别
        Result<Byte> result = gen2MultiTagIdentify();
        // 从缓存区中取出标签
        Result<UhfTag[]> result1 = getIDAndDelete(result.getPayload());
        return result1.getPayload();
    }
}
