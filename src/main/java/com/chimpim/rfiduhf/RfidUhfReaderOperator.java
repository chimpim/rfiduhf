package com.chimpim.rfiduhf;

import com.chimpim.rfiduhf.data.PowerAndFreq;
import com.chimpim.rfiduhf.data.Result;
import com.chimpim.rfiduhf.data.UhfTag;
import com.chimpim.rfiduhf.data.Version;
import com.chimpim.rfiduhf.exception.RespException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Logger;

public class RfidUhfReaderOperator {
    private static final Logger logger = Logger.getLogger("RfidUhfReaderOperator");
    private final RfidUhfReader mReader;
    private final byte mAddress;
    private final RfidUhfProtocol mProtocol;

    public static RfidUhfReaderOperator of(@NotNull RfidUhfReaderConnAdapter adapter, byte address) {
        RfidUhfReader reader = new RfidUhfReader(adapter);
        return new RfidUhfReaderOperator(reader, address, DefaultRfidUhfProtocol.INSTANCE);
    }

    private RfidUhfReaderOperator(@NotNull RfidUhfReader reader, byte address, @NotNull RfidUhfProtocol protocol) {
        mReader = reader;
        mAddress = address;
        mProtocol = protocol;
    }

    public void connect() throws Exception {
        mReader.connect();
    }

    public boolean hasConnected() {
        return mReader.hasConnected();
    }

    public void disconnect() {
        mReader.disconnect();
    }

    public Result<Void> setBaudRate(byte baudRate) throws IOException, RespException {
        @NotNull byte[] cmd = mProtocol.setBaudRate(mAddress, baudRate);
        logger.info("cmd: " + Arrays.toString(cmd));
        mReader.write(cmd);
        @Nullable byte[] resp = mReader.readResponse();
        logger.info("resp: " + Arrays.toString(resp));
        return mProtocol.setBaudRate(resp);
    }

    public Result<Void> resetReader() throws IOException, RespException {
        @NotNull byte[] cmd = mProtocol.resetReader(mAddress);
        logger.info("cmd: " + Arrays.toString(cmd));
        mReader.write(cmd);
        @Nullable byte[] resp = mReader.readResponse();
        logger.info("resp: " + Arrays.toString(resp));
        return mProtocol.resetReader(resp);
    }

    public Result<Version> getFirmwareVersion() throws IOException, RespException {
        @NotNull byte[] cmd = mProtocol.getFirmwareVersion(mAddress);
        logger.info("cmd: " + Arrays.toString(cmd));
        mReader.write(cmd);
        @Nullable byte[] resp = mReader.readResponse();
        logger.info("resp: " + Arrays.toString(resp));
        return mProtocol.getFirmwareVersion(resp);
    }

    public Result<Void> setRF(byte power, byte freq) throws IOException, RespException {
        @NotNull byte[] cmd = mProtocol.setRF(mAddress, power, freq);
        logger.info("cmd: " + Arrays.toString(cmd));
        mReader.write(cmd);
        @Nullable byte[] resp = mReader.readResponse();
        logger.info("resp: " + Arrays.toString(resp));
        return mProtocol.setRF(resp);
    }

    public Result<PowerAndFreq> getRF() throws IOException, RespException {
        @NotNull byte[] cmd = mProtocol.getRF(mAddress);
        logger.info("cmd: " + Arrays.toString(cmd));
        mReader.write(cmd);
        @Nullable byte[] resp = mReader.readResponse();
        logger.info("resp: " + Arrays.toString(resp));
        return mProtocol.getRF(resp);
    }

    public Result<Void> setWorkAntenna(byte antenna) throws IOException, RespException {
        @NotNull byte[] cmd = mProtocol.setWorkAntenna(mAddress, antenna);
        logger.info("cmd: " + Arrays.toString(cmd));
        mReader.write(cmd);
        @Nullable byte[] resp = mReader.readResponse();
        logger.info("resp: " + Arrays.toString(resp));
        return mProtocol.setWorkAntenna(resp);
    }

    public Result<Byte> getWorkAntenna() throws IOException, RespException {
        @NotNull byte[] cmd = mProtocol.getWorkAntenna(mAddress);
        logger.info("cmd: " + Arrays.toString(cmd));
        mReader.write(cmd);
        @Nullable byte[] resp = mReader.readResponse();
        logger.info("resp: " + Arrays.toString(resp));
        return mProtocol.getWorkAntenna(resp);
    }

    public Result<Byte> isoMultiTagIdentify() throws IOException, RespException {
        @NotNull byte[] cmd = mProtocol.isoMultiTagIdentify(mAddress);
        logger.info("cmd: " + Arrays.toString(cmd));
        mReader.write(cmd);
        @Nullable byte[] resp = mReader.readResponse();
        logger.info("resp: " + Arrays.toString(resp));
        return mProtocol.isoMultiTagIdentify(resp);
    }

    public Result<Byte> isoMultiTagRead(byte startAddr) throws IOException, RespException {
        @NotNull byte[] cmd = mProtocol.isoMultiTagRead(mAddress, startAddr);
        logger.info("cmd: " + Arrays.toString(cmd));
        mReader.write(cmd);
        @Nullable byte[] resp = mReader.readResponse();
        logger.info("resp: " + Arrays.toString(resp));
        return mProtocol.isoMultiTagRead(resp);
    }

    public Result<Void> isoWrite(byte addr, byte value) throws IOException, RespException {
        @NotNull byte[] cmd = mProtocol.isoWrite(mAddress, addr, value);
        logger.info("cmd: " + Arrays.toString(cmd));
        mReader.write(cmd);
        @Nullable byte[] resp = mReader.readResponse();
        logger.info("resp: " + Arrays.toString(resp));
        return mProtocol.isoWrite(resp);
    }

    public Result<UhfTag> isoReadWithUID(byte[] uid, byte addr) throws IOException, RespException {
        @NotNull byte[] cmd = mProtocol.isoReadWithUID(mAddress, uid, addr);
        logger.info("cmd: " + Arrays.toString(cmd));
        mReader.write(cmd);
        @Nullable byte[] resp = mReader.readResponse();
        logger.info("resp: " + Arrays.toString(resp));
        return mProtocol.isoReadWithUID(resp);
    }

    public Result<Void> isoWriteWithUID(byte[] uid, byte addr, byte value) throws IOException, RespException {
        @NotNull byte[] cmd = mProtocol.isoWriteWithUID(mAddress, uid, addr, value);
        logger.info("cmd: " + Arrays.toString(cmd));
        mReader.write(cmd);
        @Nullable byte[] resp = mReader.readResponse();
        logger.info("resp: " + Arrays.toString(resp));
        return mProtocol.isoWriteWithUID(resp);
    }

    public Result<Void> isoLock(byte addr) throws IOException, RespException {
        @NotNull byte[] cmd = mProtocol.isoLock(mAddress, addr);
        logger.info("cmd: " + Arrays.toString(cmd));
        mReader.write(cmd);
        @Nullable byte[] resp = mReader.readResponse();
        logger.info("resp: " + Arrays.toString(resp));
        return mProtocol.isoLock(resp);
    }

    public Result<Boolean> isoQueryLock(byte addr) throws IOException, RespException {
        @NotNull byte[] cmd = mProtocol.isoQueryLock(mAddress, addr);
        logger.info("cmd: " + Arrays.toString(cmd));
        mReader.write(cmd);
        @Nullable byte[] resp = mReader.readResponse();
        logger.info("resp: " + Arrays.toString(resp));
        return mProtocol.isoQueryLock(resp);
    }

    public Result<Void> isoBlockWrite(byte addr, byte[] value) throws IOException, RespException {
        @NotNull byte[] cmd = mProtocol.isoBlockWrite(mAddress, addr, value);
        logger.info("cmd: " + Arrays.toString(cmd));
        mReader.write(cmd);
        @Nullable byte[] resp = mReader.readResponse();
        logger.info("resp: " + Arrays.toString(resp));
        return mProtocol.isoBlockWrite(resp);
    }

    public Result<UhfTag> isoSingleTagRead(byte addr) throws IOException, RespException {
        @NotNull byte[] cmd = mProtocol.isoSingleTagRead(mAddress, addr);
        logger.info("cmd: " + Arrays.toString(cmd));
        mReader.write(cmd);
        @Nullable byte[] resp = mReader.readResponse();
        logger.info("resp: " + Arrays.toString(resp));
        return mProtocol.isoSingleTagRead(resp);
    }

    public Result<Byte> gen2MultiTagIdentify() throws IOException, RespException {
        @NotNull byte[] cmd = mProtocol.gen2MultiTagIdentify(mAddress);
        logger.info("cmd: " + Arrays.toString(cmd));
        mReader.write(cmd);
        @Nullable byte[] resp = mReader.readResponse();
        logger.info("resp: " + Arrays.toString(resp));
        return mProtocol.gen2MultiTagIdentify(resp);
    }

    public Result<Void> gen2EPCWrite(byte addr, byte[] value) throws IOException, RespException {
        @NotNull byte[] cmd = mProtocol.gen2EPCWrite(mAddress, addr, value);
        logger.info("cmd: " + Arrays.toString(cmd));
        mReader.write(cmd);
        @Nullable byte[] resp = mReader.readResponse();
        logger.info("resp: " + Arrays.toString(resp));
        return mProtocol.gen2EPCWrite(resp);
    }

    public Result<Void> gen2Lock(byte memBank, byte control) throws IOException, RespException {
        @NotNull byte[] cmd = mProtocol.gen2Lock(mAddress, memBank, control);
        logger.info("cmd: " + Arrays.toString(cmd));
        mReader.write(cmd);
        @Nullable byte[] resp = mReader.readResponse();
        logger.info("resp: " + Arrays.toString(resp));
        return mProtocol.gen2Lock(resp);
    }

    public Result<Void> gen2Kill(byte[] password) throws IOException, RespException {
        @NotNull byte[] cmd = mProtocol.gen2Kill(mAddress, password);
        logger.info("cmd: " + Arrays.toString(cmd));
        mReader.write(cmd);
        @Nullable byte[] resp = mReader.readResponse();
        logger.info("resp: " + Arrays.toString(resp));
        return mProtocol.gen2Kill(resp);
    }

    public Result<Void> gen2Init(byte bitCount) throws IOException, RespException {
        @NotNull byte[] cmd = mProtocol.gen2Init(mAddress, bitCount);
        logger.info("cmd: " + Arrays.toString(cmd));
        mReader.write(cmd);
        @Nullable byte[] resp = mReader.readResponse();
        logger.info("resp: " + Arrays.toString(resp));
        return mProtocol.gen2Init(resp);
    }

    public Result<UhfTag> gen2Read(byte memBank, byte addr, byte count) throws IOException, RespException {
        @NotNull byte[] cmd = mProtocol.gen2Read(mAddress, memBank, addr, count);
        logger.info("cmd: " + Arrays.toString(cmd));
        mReader.write(cmd);
        @Nullable byte[] resp = mReader.readResponse();
        logger.info("resp: " + Arrays.toString(resp));
        return mProtocol.gen2Read(resp);
    }

    public Result<Void> gen2Write(byte memBank, byte addr, byte[] value) throws IOException, RespException {
        @NotNull byte[] cmd = mProtocol.gen2Write(mAddress, memBank, addr, value);
        logger.info("cmd: " + Arrays.toString(cmd));
        mReader.write(cmd);
        @Nullable byte[] resp = mReader.readResponse();
        logger.info("resp: " + Arrays.toString(resp));
        return mProtocol.gen2Write(resp);
    }

    public Result<UhfTag[]> getIDAndDelete(byte count) throws IOException, RespException {
        @NotNull byte[] cmd = mProtocol.getIDAndDelete(mAddress, count);
        logger.info("cmd: " + Arrays.toString(cmd));
        mReader.write(cmd);
        @Nullable byte[] resp = mReader.readResponse();
        logger.info("resp: " + Arrays.toString(resp));
        return mProtocol.getIDAndDelete(resp);
    }

    public Result<UhfTag> getID() throws IOException, RespException {
        @NotNull byte[] cmd = mProtocol.getID(mAddress);
        logger.info("cmd: " + Arrays.toString(cmd));
        mReader.write(cmd);
        @Nullable byte[] resp = mReader.readResponse();
        logger.info("resp: " + Arrays.toString(resp));
        return mProtocol.getID(resp);
    }

    public Result getIDACK() throws IOException, RespException {
        @NotNull byte[] cmd = mProtocol.getIDACK(mAddress);
        logger.info("cmd: " + Arrays.toString(cmd));
        mReader.write(cmd);
        @Nullable byte[] resp = mReader.readResponse();
        logger.info("resp: " + Arrays.toString(resp));
        return mProtocol.getIDACK(resp);
    }

    public Result<Byte> queryIDCount() throws IOException, RespException {
        @NotNull byte[] cmd = mProtocol.queryIDCount(mAddress);
        logger.info("cmd: " + Arrays.toString(cmd));
        mReader.write(cmd);
        @Nullable byte[] resp = mReader.readResponse();
        logger.info("resp: " + Arrays.toString(resp));
        return mProtocol.queryIDCount(resp);
    }

    public Result<Void> clearIDBuffer() throws IOException, RespException {
        @NotNull byte[] cmd = mProtocol.clearIDBuffer(mAddress);
        logger.info("cmd: " + Arrays.toString(cmd));
        mReader.write(cmd);
        @Nullable byte[] resp = mReader.readResponse();
        logger.info("resp: " + Arrays.toString(resp));
        return mProtocol.clearIDBuffer(resp);
    }

}
