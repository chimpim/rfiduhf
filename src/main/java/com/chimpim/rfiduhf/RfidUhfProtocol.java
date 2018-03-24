package com.chimpim.rfiduhf;

import com.chimpim.rfiduhf.data.PowerAndFreq;
import com.chimpim.rfiduhf.data.Result;
import com.chimpim.rfiduhf.data.UhfTag;
import com.chimpim.rfiduhf.data.Version;
import com.chimpim.rfiduhf.exception.RespException;
import org.jetbrains.annotations.NotNull;

public interface RfidUhfProtocol {
    /**
     * 请求头
     */
    byte REQ_HEAD = (byte) 0x0A;
    /**
     * 响应头
     */
    byte RESP_HEAD = (byte) 0x0B;

    // =========================== 响应状态码 =========================== //
    /**
     * 命令成功完成
     */
    byte SC_ERR_NONE = (byte) 0x00;
    /**
     * 笼统的错误
     */
    byte SC_ERR_GENERAL_ERR = (byte) 0x01;
    /**
     * 参数设置失败
     */
    byte SC_ERR_PAR_SET_FAILED = (byte) 0x02;
    /**
     * 参数读取失败
     */
    byte SC_ERR_PAR_GET_FAILED = (byte) 0x03;
    /**
     * 无标签
     */
    byte SC_ERR_NO_TAG = (byte) 0x04;
    /**
     * 标签读失败
     */
    byte SC_ERR_READ_FAILED = (byte) 0x05;
    /**
     * 标签写失败
     */
    byte SC_ERR_WRITE_FAILED = (byte) 0x06;
    /**
     * 标签锁定失败
     */
    byte SC_ERR_LOCK_FAILED = (byte) 0x07;
    /**
     * 标签擦除失败
     */
    byte SC_ERR_ERASE_FAILED = (byte) 0x08;
    /**
     * 命令不支持或参数超出范围
     */
    byte SC_ERR_CMD_ERR = (byte) 0xFE;
    /**
     * 未定义错误
     */
    byte SC_ERR_UNDEFINED = (byte) 0xFF;
    // =========================== 波特率 =========================== //
    /**
     * 波特率 9600
     */
    byte BAUD_RATE_9600 = (byte) 0x00;
    /**
     * 波特率 19200
     */
    byte BAUD_RATE_19200 = (byte) 0x01;
    /**
     * 波特率 38400
     */
    byte BAUD_RATE_38400 = (byte) 0x02;
    /**
     * 波特率 57600
     */
    byte BAUD_RATE_57600 = (byte) 0x03;
    /**
     * 波特率 115200
     */
    byte BAUD_RATE_115200 = (byte) 0x04;
    // =========================== 读写器地址 =========================== //
    /**
     * 公用地址
     */
    byte ADDRESS_COMMUNAL = (byte) 0xFF;
    /**
     * 广播地址
     */
    byte ADDRESS_BROADCAST = (byte) 0xFE;

    // =========================== MenBank =========================== //
    /**
     * 保留区
     */
    byte MEM_BANK_RESERVED = (byte) 0x00;
    /**
     * EPC 区
     */
    byte MEM_BANK_EPC = (byte) 0x01;
    /**
     * TID 区
     */
    byte MEM_BANK_TID = (byte) 0x02;
    /**
     * USER 区
     */
    byte MEM_BANK_USER = (byte) 0x03;

    // =========================== Lock =========================== //
    /**
     * 不锁
     */
    byte CONTROL_NOT_LOCK = (byte) 0x00;
    /**
     * 永远不锁
     */
    byte CONTROL_NEVER_LOCK = (byte) 0x01;
    /**
     * 安全锁
     */
    byte CONTROL_SAFETY_LOCK = (byte) 0x10;
    /**
     * 永远锁
     */
    byte CONTROL_FOREVER_LOCK = (byte) 0x11;

    // =========================== 系统设置命令 =========================== //

    /**
     * 设置读写器通信波特率命令
     *
     * @param address  读写器地址
     * @param baudRate 波特率
     * @return 命令
     */
    @NotNull byte[] setBaudRate(byte address, byte baudRate);

    @NotNull Result<Void> setBaudRate(byte[] resp) throws RespException;

    /**
     * 复位读写器命令
     *
     * @param address 读写器地址
     * @return 命令
     */
    @NotNull byte[] resetReader(byte address);

    @NotNull Result<Void> resetReader(byte[] resp) throws RespException;

    /**
     * 读取读写器软件版本命令
     *
     * @param address 读写器地址
     * @return 命令
     */
    @NotNull byte[] getFirmwareVersion(byte address);

    @NotNull Result<Version> getFirmwareVersion(byte[] resp) throws RespException;

    /**
     * 设置读写器射频参数命令
     *
     * @param address 读写器地址
     * @param power   功率
     * @param freq    频率
     * @return 命令
     */
    @NotNull byte[] setRF(byte address, byte power, byte freq);

    @NotNull Result<Void> setRF(byte[] resp) throws RespException;

    /**
     * 查询读写器射频参数命令
     *
     * @param address 读写器地址
     * @return 命令
     */
    @NotNull byte[] getRF(byte address);

    @NotNull Result<PowerAndFreq> getRF(byte[] resp) throws RespException;

    /**
     * 设置读写器工作天线命令
     *
     * @param address 读写器地址
     * @param antCfg  工作天线参数。采用位掩码的设计方式，D0~D7为0或1表示相应的天线不工作或工作。
     * @return 命令
     */
    @NotNull byte[] setWorkAntenna(byte address, byte antCfg);

    @NotNull Result<Void> setWorkAntenna(byte[] resp) throws RespException;

    /**
     * 读取读写器工作天线命令
     *
     * @param address 读写器地址
     * @return 命令
     */
    @NotNull byte[] getWorkAntenna(byte address);

    @NotNull Result<Byte> getWorkAntenna(byte[] resp) throws RespException;

    // =========================== ISO18000-6B标签操作命令 =========================== //

    /**
     * Iso18000多标签识别命令
     *
     * @param address 读写器地址
     * @return 命令
     */
    @NotNull byte[] isoMultiTagIdentify(byte address);

    @NotNull Result<Byte> isoMultiTagIdentify(byte[] resp) throws RespException;

    /**
     * Iso18000多标签用户数据读取命令
     *
     * @param address   读写器地址
     * @param startAddr 用户数据的起始地址
     * @return 命令
     */
    @NotNull byte[] isoMultiTagRead(byte address, byte startAddr);

    @NotNull Result<Byte> isoMultiTagRead(byte[] resp) throws RespException;

    /**
     * Iso18000标签单字节写命令
     *
     * @param address 读写器地址
     * @param addr    标签地址
     * @param value   要写入的数据
     * @return 命令
     */
    @NotNull byte[] isoWrite(byte address, byte addr, byte value);

    @NotNull Result<Void> isoWrite(byte[] resp) throws RespException;

    /**
     * 读取特定UID标签数据命令
     *
     * @param address 读写器地址
     * @param uid     UID，长度为8的字节数组
     * @param addr    标签地址
     * @return 命令
     */
    @NotNull byte[] isoReadWithUID(byte address, byte[] uid, byte addr);

    @NotNull Result<UhfTag> isoReadWithUID(byte[] resp) throws RespException;

    /**
     * 写特定UID标签数据命令
     *
     * @param address 读写器地址
     * @param uid     UID，长度为8的字节数组
     * @param addr    标签地址
     * @param value   要写入的数据
     * @return 命令
     */
    @NotNull byte[] isoWriteWithUID(byte address, byte[] uid, byte addr, byte value);

    @NotNull Result<Void> isoWriteWithUID(byte[] resp) throws RespException;

    /**
     * Iso18000数据锁写命令
     *
     * @param address 读写器地址
     * @param addr    标签地址
     * @return 命令
     */
    @NotNull byte[] isoLock(byte address, byte addr);

    @NotNull Result<Void> isoLock(byte[] resp) throws RespException;

    /**
     * Iso18000锁定查询命令
     *
     * @param address 读写器地址
     * @param addr    标签地址
     * @return 命令
     */
    @NotNull byte[] isoQueryLock(byte address, byte addr);

    @NotNull Result<Boolean> isoQueryLock(byte[] resp) throws RespException;

    /**
     * Iso18000标签块（4字节）写命令
     *
     * @param address 读写器地址
     * @param addr    标签地址
     * @param value   要写入的数据,长度为4的字节数组
     * @return 命令
     */
    @NotNull byte[] isoBlockWrite(byte address, byte addr, byte[] value);

    @NotNull Result<Void> isoBlockWrite(byte[] resp) throws RespException;

    /**
     * Iso18000单标签读取命令
     *
     * @param address 读写器地址
     * @param addr    标签地址
     * @return 命令
     */
    @NotNull byte[] isoSingleTagRead(byte address, byte addr);

    @NotNull Result<UhfTag> isoSingleTagRead(byte[] resp) throws RespException;

    // =========================== EPC GEN2标签操作命令 =========================== //

    /**
     * EPC Gen2多标签识别命令
     *
     * @param address 读写器地址
     * @return 命令
     */
    @NotNull byte[] gen2MultiTagIdentify(byte address);

    @NotNull Result<Byte> gen2MultiTagIdentify(byte[] resp) throws RespException;

    /**
     * EPC Gen2 EPC写入命令
     *
     * @param address 读写器地址
     * @param addr    标签地址
     * @param value   写入的数据,长度为2的字节数组
     * @return 命令
     */
    @NotNull byte[] gen2EPCWrite(byte address, byte addr, byte[] value);

    @NotNull Result<Void> gen2EPCWrite(byte[] resp) throws RespException;

    /**
     * EPC Gen2 标签数据锁定命令
     *
     * @param address 读写器地址
     * @param memBank 标签区域
     * @param control 控制位
     * @return 命令
     */
    @NotNull byte[] gen2Lock(byte address, byte memBank, byte control);

    @NotNull Result<Void> gen2Lock(byte[] resp) throws RespException;

    /**
     * EPC Gen2 标签数据锁定命令
     *
     * @param address  读写器地址
     * @param password 销毁密码,长度为4的字节数组
     * @return
     */
    @NotNull byte[] gen2Kill(byte address, byte[] password);

    @NotNull Result<Void> gen2Kill(byte[] resp) throws RespException;

    /**
     * EPC Gen2标签规格初始化命令
     *
     * @param address  读写器地址
     * @param bitCount 要初始化的EPC位数,一般是96
     * @return 命令
     */
    @NotNull byte[] gen2Init(byte address, byte bitCount);

    @NotNull Result<Void> gen2Init(byte[] resp) throws RespException;

    /**
     * EPC Gen2 任意区域读命令
     *
     * @param address 读写器地址
     * @param memBank 标签区域
     * @param addr    标签地址
     * @param count   长度
     * @return 命令
     */
    @NotNull byte[] gen2Read(byte address, byte memBank, byte addr, byte count);

    @NotNull Result<UhfTag> gen2Read(byte[] resp) throws RespException;

    /**
     * EPC Gen2 任意区域写命令
     *
     * @param address 读写器地址
     * @param memBank 标签区域
     * @param addr    标签地址
     * @param value   写入的数据,长度为2的字节数组
     * @return 命令
     */
    @NotNull byte[] gen2Write(byte address, byte memBank, byte addr, byte[] value);

    @NotNull Result<Void> gen2Write(byte[] resp) throws RespException;


    // =========================== 缓存管理命令 =========================== //

    /**
     * 从缓存区中取标签数据命令
     *
     * @param address  读写器地址
     * @param tagCount 标签数量
     * @return 命令
     */
    @NotNull byte[] getIDAndDelete(byte address, byte tagCount);

    @NotNull Result<UhfTag[]> getIDAndDelete(byte[] resp) throws RespException;

    /**
     * 从缓存中取标签数据命令，取完后保留数据。
     *
     * @param address 读写器地址
     * @return 命令
     */
    @NotNull byte[] getID(byte address);

    @NotNull Result<UhfTag> getID(byte[] resp) throws RespException;

    /**
     * 数据取出成功的反馈命令，读写器收到此命令后，删除之前所传的数据。
     *
     * @param address 读写器地址
     * @return 命令
     */
    @NotNull byte[] getIDACK(byte address);

    // Result<Void> or Result<UhfTag>
    @NotNull Result getIDACK(byte[] resp) throws RespException;

    /**
     * 查询缓存区中的标签数量命令
     *
     * @param address 读写器地址
     * @return 命令
     */
    @NotNull byte[] queryIDCount(byte address);

    @NotNull Result<Byte> queryIDCount(byte[] resp) throws RespException;

    /**
     * 清空缓存区命令
     *
     * @param address 读写器地址
     * @return 命令
     */
    @NotNull byte[] clearIDBuffer(byte address);

    @NotNull Result<Void> clearIDBuffer(byte[] resp) throws RespException;

}
