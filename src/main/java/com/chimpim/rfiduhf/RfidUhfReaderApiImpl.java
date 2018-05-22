package com.chimpim.rfiduhf;

import com.chimpim.rfiduhf.data.PowerAndFreq;
import com.chimpim.rfiduhf.data.Result;
import com.chimpim.rfiduhf.data.UhfTag;
import com.chimpim.rfiduhf.data.Version;
import com.chimpim.rfiduhf.exception.RespException;
import com.chimpim.rfiduhf.internal.RfidUhfProtocol;
import com.chimpim.rfiduhf.internal.RfidUhfReader;
import com.chimpim.rfiduhf.util.HexStringUtil;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.logging.Logger;

class RfidUhfReaderApiImpl implements RfidUhfReaderApi {
    private static final Logger logger = Logger.getLogger("RfidUhfReaderApiImpl");
    private final RfidUhfReader mReader;
    private final byte mReaderAddr;
    private final RfidUhfProtocol mProtocol;

    RfidUhfReaderApiImpl(@NotNull RfidUhfReader reader, byte readerAddr, @NotNull RfidUhfProtocol protocol) {
        mReader = reader;
        mReaderAddr = readerAddr;
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
        byte[] cmd = mProtocol.setBaudRate(mReaderAddr, baudRate);
        logger.info("cmd: " + HexStringUtil.bytesToHexString(cmd));
        mReader.write(cmd);
        byte[] resp = mReader.readResponse();
        logger.info("resp: " + HexStringUtil.bytesToHexString(resp));
        return mProtocol.setBaudRate(resp);
    }

    @Override
    public Result<Void> resetReader() throws IOException, RespException {
        byte[] cmd = mProtocol.resetReader(mReaderAddr);
        logger.info("cmd: " + HexStringUtil.bytesToHexString(cmd));
        mReader.write(cmd);
        byte[] resp = mReader.readResponse();
        logger.info("resp: " + HexStringUtil.bytesToHexString(resp));
        return mProtocol.resetReader(resp);
    }

    @Override
    public Result<Version> getFirmwareVersion() throws IOException, RespException {
        byte[] cmd = mProtocol.getFirmwareVersion(mReaderAddr);
        logger.info("cmd: " + HexStringUtil.bytesToHexString(cmd));
        mReader.write(cmd);
        byte[] resp = mReader.readResponse();
        logger.info("resp: " + HexStringUtil.bytesToHexString(resp));
        return mProtocol.getFirmwareVersion(resp);
    }

    @Override
    public Result<Void> setWorkAntenna(byte antCfg) throws IOException, RespException {
        byte[] cmd = mProtocol.setWorkAntenna(mReaderAddr, antCfg);
        logger.info("cmd: " + HexStringUtil.bytesToHexString(cmd));
        mReader.write(cmd);
        byte[] resp = mReader.readResponse();
        logger.info("resp: " + HexStringUtil.bytesToHexString(resp));
        return mProtocol.setWorkAntenna(resp);
    }

    @Override
    public Result<Byte> getWorkAntenna() throws IOException, RespException {
        byte[] cmd = mProtocol.getWorkAntenna(mReaderAddr);
        logger.info("cmd: " + HexStringUtil.bytesToHexString(cmd));
        mReader.write(cmd);
        byte[] resp = mReader.readResponse();
        logger.info("resp: " + HexStringUtil.bytesToHexString(resp));
        return mProtocol.getWorkAntenna(resp);
    }

    @Override
    public Result<Byte> getWorkMode() throws IOException, RespException {
        byte[] cmd = mProtocol.getWorkMode(mReaderAddr);
        logger.info("cmd: " + HexStringUtil.bytesToHexString(cmd));
        mReader.write(cmd);
        byte[] resp = mReader.readResponse();
        logger.info("resp: " + HexStringUtil.bytesToHexString(resp));
        return mProtocol.getWorkMode(resp);
    }

    @Override
    public Result<Void> setWorkMode(byte workMode) throws IOException, RespException {
        byte[] cmd = mProtocol.setWorkMode(mReaderAddr, workMode);
        logger.info("cmd: " + HexStringUtil.bytesToHexString(cmd));
        mReader.write(cmd);
        byte[] resp = mReader.readResponse();
        logger.info("resp: " + HexStringUtil.bytesToHexString(resp));
        return mProtocol.setWorkMode(resp);
    }


    @Override
    public Result<Void> setRf(byte power, byte freq) throws IOException, RespException {
        byte[] cmd = mProtocol.setRf(mReaderAddr, power, freq);
        logger.info("cmd: " + HexStringUtil.bytesToHexString(cmd));
        mReader.write(cmd);
        byte[] resp = mReader.readResponse();
        logger.info("resp: " + HexStringUtil.bytesToHexString(resp));
        return mProtocol.setRf(resp);
    }

    @Override
    public Result<PowerAndFreq> getRf() throws IOException, RespException {
        byte[] cmd = mProtocol.getRf(mReaderAddr);
        logger.info("cmd: " + HexStringUtil.bytesToHexString(cmd));
        mReader.write(cmd);
        byte[] resp = mReader.readResponse();
        logger.info("resp: " + HexStringUtil.bytesToHexString(resp));
        return mProtocol.getRf(resp);
    }


    @Override
    public Result<Byte> multiTagIdentify6b() throws IOException, RespException {
        byte[] cmd = mProtocol.multiTagIdentify6b(mReaderAddr);
        logger.info("cmd: " + HexStringUtil.bytesToHexString(cmd));
        mReader.write(cmd);
        byte[] resp = mReader.readResponse();
        logger.info("resp: " + HexStringUtil.bytesToHexString(resp));
        return mProtocol.multiTagIdentify6b(resp);
    }

    @Override
    public Result<Byte> multiTagRead6b(byte startAddr) throws IOException, RespException {
        byte[] cmd = mProtocol.multiTagRead6b(mReaderAddr, startAddr);
        logger.info("cmd: " + HexStringUtil.bytesToHexString(cmd));
        mReader.write(cmd);
        byte[] resp = mReader.readResponse();
        logger.info("resp: " + HexStringUtil.bytesToHexString(resp));
        return mProtocol.multiTagRead6b(resp);
    }


    @Override
    public Result<Void> write6b(byte addr, byte value) throws IOException, RespException {
        byte[] cmd = mProtocol.write6b(mReaderAddr, addr, value);
        logger.info("cmd: " + HexStringUtil.bytesToHexString(cmd));
        mReader.write(cmd);
        byte[] resp = mReader.readResponse();
        logger.info("resp: " + HexStringUtil.bytesToHexString(resp));
        return mProtocol.write6b(resp);
    }

    @Override
    public Result<UhfTag> readWithUid6b(byte[] uid, byte addr) throws IOException, RespException {
        byte[] cmd = mProtocol.readWithUid6b(mReaderAddr, uid, addr);
        logger.info("cmd: " + HexStringUtil.bytesToHexString(cmd));
        mReader.write(cmd);
        byte[] resp = mReader.readResponse();
        logger.info("resp: " + HexStringUtil.bytesToHexString(resp));
        return mProtocol.readWithUid6b(resp);
    }

    @Override
    public Result<Void> writeWithUid6b(byte[] uid, byte addr, byte value) throws IOException, RespException {
        byte[] cmd = mProtocol.writeWithUid6b(mReaderAddr, uid, addr, value);
        logger.info("cmd: " + HexStringUtil.bytesToHexString(cmd));
        mReader.write(cmd);
        byte[] resp = mReader.readResponse();
        logger.info("resp: " + HexStringUtil.bytesToHexString(resp));
        return mProtocol.writeWithUid6b(resp);
    }

    @Override
    public Result<Void> lock6b(byte addr) throws IOException, RespException {
        byte[] cmd = mProtocol.lock6b(mReaderAddr, addr);
        logger.info("cmd: " + HexStringUtil.bytesToHexString(cmd));
        mReader.write(cmd);
        byte[] resp = mReader.readResponse();
        logger.info("resp: " + HexStringUtil.bytesToHexString(resp));
        return mProtocol.lock6b(resp);
    }

    @Override
    public Result<Boolean> queryLock6b(byte addr) throws IOException, RespException {
        byte[] cmd = mProtocol.queryLock6b(mReaderAddr, addr);
        logger.info("cmd: " + HexStringUtil.bytesToHexString(cmd));
        mReader.write(cmd);
        byte[] resp = mReader.readResponse();
        logger.info("resp: " + HexStringUtil.bytesToHexString(resp));
        return mProtocol.queryLock6b(resp);
    }

    @Override
    public Result<Void> blockWrite6b(byte addr, byte[] value) throws IOException, RespException {
        byte[] cmd = mProtocol.blockWrite6b(mReaderAddr, addr, value);
        logger.info("cmd: " + HexStringUtil.bytesToHexString(cmd));
        mReader.write(cmd);
        byte[] resp = mReader.readResponse();
        logger.info("resp: " + HexStringUtil.bytesToHexString(resp));
        return mProtocol.blockWrite6b(resp);
    }

    @Override
    public Result<UhfTag> singleTagRead6b(byte addr) throws IOException, RespException {
        byte[] cmd = mProtocol.singleTagRead6b(mReaderAddr, addr);
        logger.info("cmd: " + HexStringUtil.bytesToHexString(cmd));
        mReader.write(cmd);
        byte[] resp = mReader.readResponse();
        logger.info("resp: " + HexStringUtil.bytesToHexString(resp));
        return mProtocol.singleTagRead6b(resp);
    }

    @Override
    public Result<Byte> multiTagIdentify6c() throws IOException, RespException {
        byte[] cmd = mProtocol.multiTagIdentify6c(mReaderAddr);
        logger.info("cmd: " + HexStringUtil.bytesToHexString(cmd));
        mReader.write(cmd);
        byte[] resp = mReader.readResponse();
        logger.info("resp: " + HexStringUtil.bytesToHexString(resp));
        return mProtocol.multiTagIdentify6c(resp);
    }

    @Override
    public Result<Void> epcWrite6c(byte addr, byte[] value) throws IOException, RespException {
        byte[] cmd = mProtocol.epcWrite6c(mReaderAddr, addr, value);
        logger.info("cmd: " + HexStringUtil.bytesToHexString(cmd));
        mReader.write(cmd);
        byte[] resp = mReader.readResponse();
        logger.info("resp: " + HexStringUtil.bytesToHexString(resp));
        return mProtocol.epcWrite6c(resp);
    }

    @Override
    public Result<Void> lock6c(byte memBank, byte control) throws IOException, RespException {
        byte[] cmd = mProtocol.lock6c(mReaderAddr, memBank, control);
        logger.info("cmd: " + HexStringUtil.bytesToHexString(cmd));
        mReader.write(cmd);
        byte[] resp = mReader.readResponse();
        logger.info("resp: " + HexStringUtil.bytesToHexString(resp));
        return mProtocol.lock6c(resp);
    }

    @Override
    public Result<Void> kill6c(byte[] password) throws IOException, RespException {
        byte[] cmd = mProtocol.kill6c(mReaderAddr, password);
        logger.info("cmd: " + HexStringUtil.bytesToHexString(cmd));
        mReader.write(cmd);
        byte[] resp = mReader.readResponse();
        logger.info("resp: " + HexStringUtil.bytesToHexString(resp));
        return mProtocol.kill6c(resp);
    }

    @Override
    public Result<Void> init6c(byte bitCount) throws IOException, RespException {
        byte[] cmd = mProtocol.init6c(mReaderAddr, bitCount);
        logger.info("cmd: " + HexStringUtil.bytesToHexString(cmd));
        mReader.write(cmd);
        byte[] resp = mReader.readResponse();
        logger.info("resp: " + HexStringUtil.bytesToHexString(resp));
        return mProtocol.init6c(resp);
    }

    @Override
    public Result<UhfTag> read6c(byte memBank, byte addr, byte len) throws IOException, RespException {
        byte[] cmd = mProtocol.read6c(mReaderAddr, memBank, addr, len);
        logger.info("cmd: " + HexStringUtil.bytesToHexString(cmd));
        mReader.write(cmd);
        byte[] resp = mReader.readResponse();
        logger.info("resp: " + HexStringUtil.bytesToHexString(resp));
        return mProtocol.read6c(resp);
    }

    @Override
    public Result<Void> write6c(byte memBank, byte addr, byte[] value) throws IOException, RespException {
        byte[] cmd = mProtocol.write6c(mReaderAddr, memBank, addr, value);
        logger.info("cmd: " + HexStringUtil.bytesToHexString(cmd));
        mReader.write(cmd);
        byte[] resp = mReader.readResponse();
        logger.info("resp: " + HexStringUtil.bytesToHexString(resp));
        return mProtocol.write6c(resp);
    }

    @Override
    public Result<UhfTag[]> getIdAndDelete(byte count) throws IOException, RespException {
        byte[] cmd = mProtocol.getIdAndDelete(mReaderAddr, count);
        logger.info("cmd: " + HexStringUtil.bytesToHexString(cmd));
        mReader.write(cmd);
        byte[] resp = mReader.readResponse();
        logger.info("resp: " + HexStringUtil.bytesToHexString(resp));
        return mProtocol.getIdAndDelete(resp);
    }

    @Override
    public Result<UhfTag> getId() throws IOException, RespException {
        byte[] cmd = mProtocol.getId(mReaderAddr);
        logger.info("cmd: " + HexStringUtil.bytesToHexString(cmd));
        mReader.write(cmd);
        byte[] resp = mReader.readResponse();
        logger.info("resp: " + HexStringUtil.bytesToHexString(resp));
        return mProtocol.getId(resp);
    }

    @Override
    public Result getIdAck() throws IOException, RespException {
        byte[] cmd = mProtocol.getIdAck(mReaderAddr);
        logger.info("cmd: " + HexStringUtil.bytesToHexString(cmd));
        mReader.write(cmd);
        byte[] resp = mReader.readResponse();
        logger.info("resp: " + HexStringUtil.bytesToHexString(resp));
        return mProtocol.getIdAck(resp);
    }

    @Override
    public Result<Byte> queryIdCount() throws IOException, RespException {
        byte[] cmd = mProtocol.queryIdCount(mReaderAddr);
        logger.info("cmd: " + HexStringUtil.bytesToHexString(cmd));
        mReader.write(cmd);
        byte[] resp = mReader.readResponse();
        logger.info("resp: " + HexStringUtil.bytesToHexString(resp));
        return mProtocol.queryIdCount(resp);
    }

    @Override
    public Result<Void> clearIdBuffer() throws IOException, RespException {
        byte[] cmd = mProtocol.clearIdBuffer(mReaderAddr);
        logger.info("cmd: " + HexStringUtil.bytesToHexString(cmd));
        mReader.write(cmd);
        byte[] resp = mReader.readResponse();
        logger.info("resp: " + HexStringUtil.bytesToHexString(resp));
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
            logger.info(result.toString());
            if (!result.isOk()) {
                logger.info(result.getScName());
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
            logger.info(result.toString());
            if (!result.isOk()) {
                logger.info(result.getScName());
                return false;
            }
        }
        return true;
    }


    @Override
    public UhfTag[] fastMultiTagRead6b(byte startAddr) throws IOException, RespException {
        // 清空缓存区
        clearIdBuffer();
        // 多标签读取
        Result<Byte> result = multiTagRead6b(startAddr);
        // 从缓存区中取出标签
        Result<UhfTag[]> result1 = getIdAndDelete(result.getPayload());
        return result1.getPayload();
    }


    @Override
    public UhfTag[] fastMultiTagIdentify6b() throws IOException, RespException {
        // 清空缓存区
        clearIdBuffer();
        // 多标签识别
        Result<Byte> result = multiTagIdentify6b();
        // 从缓存区中取出标签
        Result<UhfTag[]> result1 = getIdAndDelete(result.getPayload());
        return result1.getPayload();
    }

    @Override
    public boolean fastBatchWrite6c(byte memBank, byte startAddr, @NotNull byte[] values) throws IOException, RespException {
        int len = values.length / 2;
        int index = 0;
        for (int i = 0; i < len; i++) {
            byte addr = (byte) (startAddr + i);
            byte[] value = new byte[]{values[index++], values[index++]};
            Result<Void> result = write6c(memBank, addr, value);
            logger.info(result.toString());
            if (!result.isOk()) {
                logger.info(result.getScName());
                return false;
            }
        }
        return true;
    }

    @Override
    public UhfTag[] fastMultiTagIdentify6c() throws IOException, RespException {
        // 清空缓存区
        clearIdBuffer();
        // 多标签识别
        Result<Byte> result = multiTagIdentify6c();
        // 从缓存区中取出标签
        Result<UhfTag[]> result1 = getIdAndDelete(result.getPayload());
        return result1.getPayload();
    }
}