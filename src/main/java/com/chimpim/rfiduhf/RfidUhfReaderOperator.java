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

    /**
     * 设置读写器波特率
     *
     * @param baudRate 波特率
     * @return 无负载数据的结果
     * @throws IOException
     * @throws RespException
     */
    public Result<Void> setBaudRate(byte baudRate) throws IOException, RespException {
        @NotNull byte[] cmd = mProtocol.setBaudRate(mAddress, baudRate);
        logger.info("cmd: " + Arrays.toString(cmd));
        mReader.write(cmd);
        @Nullable byte[] resp = mReader.readResponse();
        logger.info("resp: " + Arrays.toString(resp));
        return mProtocol.setBaudRate(resp);
    }

    /**
     * 重置读写器
     *
     * @return 无负载数据的结果
     * @throws IOException
     * @throws RespException
     */
    public Result<Void> resetReader() throws IOException, RespException {
        @NotNull byte[] cmd = mProtocol.resetReader(mAddress);
        logger.info("cmd: " + Arrays.toString(cmd));
        mReader.write(cmd);
        @Nullable byte[] resp = mReader.readResponse();
        logger.info("resp: " + Arrays.toString(resp));
        return mProtocol.resetReader(resp);
    }

    /**
     * 获取硬件版本
     *
     * @return 带有版本负载数据的结果
     * @throws IOException
     * @throws RespException
     */
    public Result<Version> getFirmwareVersion() throws IOException, RespException {
        @NotNull byte[] cmd = mProtocol.getFirmwareVersion(mAddress);
        logger.info("cmd: " + Arrays.toString(cmd));
        mReader.write(cmd);
        @Nullable byte[] resp = mReader.readResponse();
        logger.info("resp: " + Arrays.toString(resp));
        return mProtocol.getFirmwareVersion(resp);
    }

    /**
     * 设置功率和频率
     *
     * @param power 功率
     * @param freq  频率
     * @return 无负载数据的结果
     * @throws IOException
     * @throws RespException
     */
    public Result<Void> setRF(byte power, byte freq) throws IOException, RespException {
        @NotNull byte[] cmd = mProtocol.setRF(mAddress, power, freq);
        logger.info("cmd: " + Arrays.toString(cmd));
        mReader.write(cmd);
        @Nullable byte[] resp = mReader.readResponse();
        logger.info("resp: " + Arrays.toString(resp));
        return mProtocol.setRF(resp);
    }

    /**
     * 获取功率和频率
     *
     * @return 带有功率和频率负载数据的结果
     * @throws IOException
     * @throws RespException
     */
    public Result<PowerAndFreq> getRF() throws IOException, RespException {
        @NotNull byte[] cmd = mProtocol.getRF(mAddress);
        logger.info("cmd: " + Arrays.toString(cmd));
        mReader.write(cmd);
        @Nullable byte[] resp = mReader.readResponse();
        logger.info("resp: " + Arrays.toString(resp));
        return mProtocol.getRF(resp);
    }

    /**
     * 设置读写器工作天线
     *
     * @param antCfg 工作天线参数。采用位掩码的设计方式，D0~D7为0或1表示相应的天线不工作或工作。
     * @return 无负载数据的结果
     * @throws IOException
     * @throws RespException
     */
    public Result<Void> setWorkAntenna(byte antCfg) throws IOException, RespException {
        @NotNull byte[] cmd = mProtocol.setWorkAntenna(mAddress, antCfg);
        logger.info("cmd: " + Arrays.toString(cmd));
        mReader.write(cmd);
        @Nullable byte[] resp = mReader.readResponse();
        logger.info("resp: " + Arrays.toString(resp));
        return mProtocol.setWorkAntenna(resp);
    }

    /**
     * 获取读写器工作天线
     *
     * @return 带有工作天线参数负载数据的结果
     * @throws IOException
     * @throws RespException
     */
    public Result<Byte> getWorkAntenna() throws IOException, RespException {
        @NotNull byte[] cmd = mProtocol.getWorkAntenna(mAddress);
        logger.info("cmd: " + Arrays.toString(cmd));
        mReader.write(cmd);
        @Nullable byte[] resp = mReader.readResponse();
        logger.info("resp: " + Arrays.toString(resp));
        return mProtocol.getWorkAntenna(resp);
    }

    /**
     * 6B多标签识别，使用前先调用{@link RfidUhfReaderOperator#clearIDBuffer()}
     *
     * @return 带有识别到的标签数量负载数据的结果
     * @throws IOException
     * @throws RespException
     */
    public Result<Byte> isoMultiTagIdentify() throws IOException, RespException {
        @NotNull byte[] cmd = mProtocol.isoMultiTagIdentify(mAddress);
        logger.info("cmd: " + Arrays.toString(cmd));
        mReader.write(cmd);
        @Nullable byte[] resp = mReader.readResponse();
        logger.info("resp: " + Arrays.toString(resp));
        return mProtocol.isoMultiTagIdentify(resp);
    }

    /**
     * 6B多标签读取,使用前先调用{@link RfidUhfReaderOperator#clearIDBuffer()}
     *
     * @param startAddr 标签开始地址
     * @return 带有读到标签数量负载数据的结果
     * @throws IOException
     * @throws RespException
     */
    public Result<Byte> isoMultiTagRead(byte startAddr) throws IOException, RespException {
        @NotNull byte[] cmd = mProtocol.isoMultiTagRead(mAddress, startAddr);
        logger.info("cmd: " + Arrays.toString(cmd));
        mReader.write(cmd);
        @Nullable byte[] resp = mReader.readResponse();
        logger.info("resp: " + Arrays.toString(resp));
        return mProtocol.isoMultiTagRead(resp);
    }

    /**
     * 将数据写入6B标签
     *
     * @param addr  标签地址
     * @param value 写入的数据
     * @return 无负载数据的结果
     * @throws IOException
     * @throws RespException
     */
    public Result<Void> isoWrite(byte addr, byte value) throws IOException, RespException {
        @NotNull byte[] cmd = mProtocol.isoWrite(mAddress, addr, value);
        logger.info("cmd: " + Arrays.toString(cmd));
        mReader.write(cmd);
        @Nullable byte[] resp = mReader.readResponse();
        logger.info("resp: " + Arrays.toString(resp));
        return mProtocol.isoWrite(resp);
    }

    /**
     * 读取特定UID的6B标签，每次可读取8个字节
     *
     * @param uid  UID,长度为8的字节数组
     * @param addr 标签地址
     * @return 带有标签数据负载数据的结果
     * @throws IOException
     * @throws RespException
     */
    public Result<UhfTag> isoReadWithUID(byte[] uid, byte addr) throws IOException, RespException {
        @NotNull byte[] cmd = mProtocol.isoReadWithUID(mAddress, uid, addr);
        logger.info("cmd: " + Arrays.toString(cmd));
        mReader.write(cmd);
        @Nullable byte[] resp = mReader.readResponse();
        logger.info("resp: " + Arrays.toString(resp));
        return mProtocol.isoReadWithUID(resp);
    }

    /**
     * 将数据写入特定UID的6B标签
     *
     * @param uid   UID,长度为8的字节数组
     * @param addr  标签地址
     * @param value 写入的数据
     * @return 无负载数据的结果
     * @throws IOException
     * @throws RespException
     */
    public Result<Void> isoWriteWithUID(byte[] uid, byte addr, byte value) throws IOException, RespException {
        @NotNull byte[] cmd = mProtocol.isoWriteWithUID(mAddress, uid, addr, value);
        logger.info("cmd: " + Arrays.toString(cmd));
        mReader.write(cmd);
        @Nullable byte[] resp = mReader.readResponse();
        logger.info("resp: " + Arrays.toString(resp));
        return mProtocol.isoWriteWithUID(resp);
    }

    /**
     * 锁定6B标签的特定地址
     *
     * @param addr 标签地址
     * @return 无负载数据的结果
     * @throws IOException
     * @throws RespException
     */
    public Result<Void> isoLock(byte addr) throws IOException, RespException {
        @NotNull byte[] cmd = mProtocol.isoLock(mAddress, addr);
        logger.info("cmd: " + Arrays.toString(cmd));
        mReader.write(cmd);
        @Nullable byte[] resp = mReader.readResponse();
        logger.info("resp: " + Arrays.toString(resp));
        return mProtocol.isoLock(resp);
    }

    /**
     * 查询6B标签特定的特定地址是否被锁定
     *
     * @param addr 标签地址
     * @return 带有是否锁定负载数据的结果
     * @throws IOException
     * @throws RespException
     */
    public Result<Boolean> isoQueryLock(byte addr) throws IOException, RespException {
        @NotNull byte[] cmd = mProtocol.isoQueryLock(mAddress, addr);
        logger.info("cmd: " + Arrays.toString(cmd));
        mReader.write(cmd);
        @Nullable byte[] resp = mReader.readResponse();
        logger.info("resp: " + Arrays.toString(resp));
        return mProtocol.isoQueryLock(resp);
    }

    /**
     * 将数据块写入6B标签
     *
     * @param addr  标签地址
     * @param value 写入的数据块,长度为4的字节数组
     * @return 无负载数据的结果
     * @throws IOException
     * @throws RespException
     */
    public Result<Void> isoBlockWrite(byte addr, byte[] value) throws IOException, RespException {
        @NotNull byte[] cmd = mProtocol.isoBlockWrite(mAddress, addr, value);
        logger.info("cmd: " + Arrays.toString(cmd));
        mReader.write(cmd);
        @Nullable byte[] resp = mReader.readResponse();
        logger.info("resp: " + Arrays.toString(resp));
        return mProtocol.isoBlockWrite(resp);
    }

    /**
     * 6B标签单标签读取，每次可读取8个字节
     *
     * @param addr 标签地址
     * @return 带有标签信息负载数据的结果
     * @throws IOException
     * @throws RespException
     */
    public Result<UhfTag> isoSingleTagRead(byte addr) throws IOException, RespException {
        @NotNull byte[] cmd = mProtocol.isoSingleTagRead(mAddress, addr);
        logger.info("cmd: " + Arrays.toString(cmd));
        mReader.write(cmd);
        @Nullable byte[] resp = mReader.readResponse();
        logger.info("resp: " + Arrays.toString(resp));
        return mProtocol.isoSingleTagRead(resp);
    }

    /**
     * 6C多标签识别，使用前先调用{@link RfidUhfReaderOperator#clearIDBuffer()}
     *
     * @return 带有读到标签数量负载数据的结果
     * @throws IOException
     * @throws RespException
     */
    public Result<Byte> gen2MultiTagIdentify() throws IOException, RespException {
        @NotNull byte[] cmd = mProtocol.gen2MultiTagIdentify(mAddress);
        logger.info("cmd: " + Arrays.toString(cmd));
        mReader.write(cmd);
        @Nullable byte[] resp = mReader.readResponse();
        logger.info("resp: " + Arrays.toString(resp));
        return mProtocol.gen2MultiTagIdentify(resp);
    }

    /**
     * 将数据写入6C标签
     *
     * @param addr  标签地址
     * @param value 写入的数据，长度为2的字节数组
     * @return 无负载数据的结果
     * @throws IOException
     * @throws RespException
     */
    public Result<Void> gen2EPCWrite(byte addr, byte[] value) throws IOException, RespException {
        @NotNull byte[] cmd = mProtocol.gen2EPCWrite(mAddress, addr, value);
        logger.info("cmd: " + Arrays.toString(cmd));
        mReader.write(cmd);
        @Nullable byte[] resp = mReader.readResponse();
        logger.info("resp: " + Arrays.toString(resp));
        return mProtocol.gen2EPCWrite(resp);
    }

    /**
     * 锁定6C卡特定的标签区域
     *
     * @param memBank 标签区域
     * @param control 控制位，值可以是{@link RfidUhfConstant#CONTROL_NOT_LOCK,RfidUhfConstant#CONTROL_NEVER_LOCK,RfidUhfConstant#CONTROL_FOREVER_LOCK,RfidUhfConstant#CONTROL_SAFETY_LOCK}
     * @return 无负载数据的结果
     * @throws IOException
     * @throws RespException
     */
    public Result<Void> gen2Lock(byte memBank, byte control) throws IOException, RespException {
        @NotNull byte[] cmd = mProtocol.gen2Lock(mAddress, memBank, control);
        logger.info("cmd: " + Arrays.toString(cmd));
        mReader.write(cmd);
        @Nullable byte[] resp = mReader.readResponse();
        logger.info("resp: " + Arrays.toString(resp));
        return mProtocol.gen2Lock(resp);
    }

    /**
     * 销毁6C标签
     *
     * @param password 标签的销毁密码，长度为4的字节数组
     * @return 无负载数据的结果
     * @throws IOException
     * @throws RespException
     */
    public Result<Void> gen2Kill(byte[] password) throws IOException, RespException {
        @NotNull byte[] cmd = mProtocol.gen2Kill(mAddress, password);
        logger.info("cmd: " + Arrays.toString(cmd));
        mReader.write(cmd);
        @Nullable byte[] resp = mReader.readResponse();
        logger.info("resp: " + Arrays.toString(resp));
        return mProtocol.gen2Kill(resp);
    }

    /**
     * 初始化6C标签
     *
     * @param bitCount 要初始化的EPC位数，一般是96
     * @return 无负载数据的结果
     * @throws IOException
     * @throws RespException
     */
    public Result<Void> gen2Init(byte bitCount) throws IOException, RespException {
        @NotNull byte[] cmd = mProtocol.gen2Init(mAddress, bitCount);
        logger.info("cmd: " + Arrays.toString(cmd));
        mReader.write(cmd);
        @Nullable byte[] resp = mReader.readResponse();
        logger.info("resp: " + Arrays.toString(resp));
        return mProtocol.gen2Init(resp);
    }

    /**
     * 读取6C标签的特定区域
     *
     * @param memBank 标签区域
     * @param addr    标签地址
     * @param len     读取字节长度
     * @return 带有标签数据负载数据的结果
     * @throws IOException
     * @throws RespException
     */
    public Result<UhfTag> gen2Read(byte memBank, byte addr, byte len) throws IOException, RespException {
        @NotNull byte[] cmd = mProtocol.gen2Read(mAddress, memBank, addr, len);
        logger.info("cmd: " + Arrays.toString(cmd));
        mReader.write(cmd);
        @Nullable byte[] resp = mReader.readResponse();
        logger.info("resp: " + Arrays.toString(resp));
        return mProtocol.gen2Read(resp);
    }

    /**
     * 将数据写入6C标签的特定区域
     *
     * @param memBank 标签区域
     * @param addr    标签地址
     * @param value   写入的数据，长度为2的字节数组
     * @return 无负载数据的结果
     * @throws IOException
     * @throws RespException
     */
    public Result<Void> gen2Write(byte memBank, byte addr, byte[] value) throws IOException, RespException {
        @NotNull byte[] cmd = mProtocol.gen2Write(mAddress, memBank, addr, value);
        logger.info("cmd: " + Arrays.toString(cmd));
        mReader.write(cmd);
        @Nullable byte[] resp = mReader.readResponse();
        logger.info("resp: " + Arrays.toString(resp));
        return mProtocol.gen2Write(resp);
    }

    /**
     * 从缓存区中取标签数据
     *
     * @param count 标签数量
     * @return 带有标签数据数组负载数据的结果
     * @throws IOException
     * @throws RespException
     */
    public Result<UhfTag[]> getIDAndDelete(byte count) throws IOException, RespException {
        @NotNull byte[] cmd = mProtocol.getIDAndDelete(mAddress, count);
        logger.info("cmd: " + Arrays.toString(cmd));
        mReader.write(cmd);
        @Nullable byte[] resp = mReader.readResponse();
        logger.info("resp: " + Arrays.toString(resp));
        return mProtocol.getIDAndDelete(resp);
    }

    /**
     * 从缓存中取标签数据命令，取完后保留数据
     *
     * @return 带有标签数据负载数据的结果
     * @throws IOException
     * @throws RespException
     */
    public Result<UhfTag> getID() throws IOException, RespException {
        @NotNull byte[] cmd = mProtocol.getID(mAddress);
        logger.info("cmd: " + Arrays.toString(cmd));
        mReader.write(cmd);
        @Nullable byte[] resp = mReader.readResponse();
        logger.info("resp: " + Arrays.toString(resp));
        return mProtocol.getID(resp);
    }

    /**
     * 数据取出成功的反馈，读写器收到此命令后，删除之前所传的数据
     *
     * @return 无负载数据的结果（缓存区中没有标签时）或带有标签数据负载数据的结果
     * @throws IOException
     * @throws RespException
     */
    public Result getIDACK() throws IOException, RespException {
        @NotNull byte[] cmd = mProtocol.getIDACK(mAddress);
        logger.info("cmd: " + Arrays.toString(cmd));
        mReader.write(cmd);
        @Nullable byte[] resp = mReader.readResponse();
        logger.info("resp: " + Arrays.toString(resp));
        return mProtocol.getIDACK(resp);
    }

    /**
     * 查询缓存区中的标签数量
     *
     * @return 带有标签数量负载数据的结果
     * @throws IOException
     * @throws RespException
     */
    public Result<Byte> queryIDCount() throws IOException, RespException {
        @NotNull byte[] cmd = mProtocol.queryIDCount(mAddress);
        logger.info("cmd: " + Arrays.toString(cmd));
        mReader.write(cmd);
        @Nullable byte[] resp = mReader.readResponse();
        logger.info("resp: " + Arrays.toString(resp));
        return mProtocol.queryIDCount(resp);
    }

    /**
     * 清空缓存区
     *
     * @return 无负载数据的结果
     * @throws IOException
     * @throws RespException
     */
    public Result<Void> clearIDBuffer() throws IOException, RespException {
        @NotNull byte[] cmd = mProtocol.clearIDBuffer(mAddress);
        logger.info("cmd: " + Arrays.toString(cmd));
        mReader.write(cmd);
        @Nullable byte[] resp = mReader.readResponse();
        logger.info("resp: " + Arrays.toString(resp));
        return mProtocol.clearIDBuffer(resp);
    }

}
