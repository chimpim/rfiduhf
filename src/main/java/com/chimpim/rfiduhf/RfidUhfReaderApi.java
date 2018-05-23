package com.chimpim.rfiduhf;

import com.chimpim.rfiduhf.data.PowerAndFreq;
import com.chimpim.rfiduhf.data.Result;
import com.chimpim.rfiduhf.data.UhfTag;
import com.chimpim.rfiduhf.data.Version;
import com.chimpim.rfiduhf.exception.RespException;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public interface RfidUhfReaderApi {

    /**
     * 连接读写器
     *
     * @throws Exception 连接失败异常
     */
    void connect() throws Exception;

    /**
     * 判断读写器是否连接
     *
     * @return {@code true} 已连接，{@code false} 未连接
     */
    boolean hasConnected();

    /**
     * 断开读写器连接
     */
    void disconnect();

    //======================== 设置命令 ========================//

    /**
     * 设置读写器波特率
     *
     * @param baudRate 波特率
     *                 {@link RfidUhfConstant#BAUD_RATE_9600}
     *                 {@link RfidUhfConstant#BAUD_RATE_19200}
     *                 {@link RfidUhfConstant#BAUD_RATE_38400}
     *                 {@link RfidUhfConstant#BAUD_RATE_57600}
     *                 {@link RfidUhfConstant#BAUD_RATE_115200}
     * @return 无负载数据的结果
     * @throws IOException   读卡器IO异常
     * @throws RespException 响应异常
     */
    Result<Void> setBaudRate(byte baudRate) throws IOException, RespException;

    /**
     * 重置读写器
     *
     * @return 无负载数据的结果
     * @throws IOException   读卡器IO异常
     * @throws RespException 响应异常
     */
    Result<Void> resetReader() throws IOException, RespException;

    /**
     * 获取硬件版本
     *
     * @return 带有版本负载数据的结果
     * @throws IOException   读卡器IO异常
     * @throws RespException 响应异常
     */
    Result<Version> getFirmwareVersion() throws IOException, RespException;

    /**
     * 设置读写器工作天线
     *
     * @param antCfg 工作天线参数。采用位掩码的设计方式，D0~D7为0或1表示相应的天线不工作或工作。
     * @return 无负载数据的结果
     * @throws IOException   读卡器IO异常
     * @throws RespException 响应异常
     */
    Result<Void> setWorkAntenna(byte antCfg) throws IOException, RespException;

    /**
     * 获取读写器工作天线
     *
     * @return 带有工作天线参数负载数据的结果
     * @throws IOException   读卡器IO异常
     * @throws RespException 响应异常
     */
    Result<Byte> getWorkAntenna() throws IOException, RespException;

    /**
     * 获取读写器工作模式
     *
     * @return 带有读写器工作模式负载数据的结果
     * @throws IOException   读卡器IO异常
     * @throws RespException 响应异常
     */
    Result<Byte> getWorkMode() throws IOException, RespException;

    /**
     * @param workMode 工作模式
     *                 {@link RfidUhfConstant#WORK_MODE_COMMAND}
     *                 {@link RfidUhfConstant#WORK_MODE_TIMEING}
     *                 {@link RfidUhfConstant#WORK_MODE_TRIGGER}
     * @return 无负载数据的结果
     * @throws IOException   读卡器IO异常
     * @throws RespException 响应异常
     */
    Result<Void> setWorkMode(byte workMode) throws IOException, RespException;

    /**
     * 获取读写器工作模式
     *
     * @return 带有读写器工作模式负载数据的结果
     * @throws IOException   读卡器IO异常
     * @throws RespException 响应异常
     */
    Result<Byte> getAddr() throws IOException, RespException;

    /**
     * @param newReaderAddr 读卡器地址，0~253
     * @return 无负载数据的结果
     * @throws IOException   读卡器IO异常
     * @throws RespException 响应异常
     */
    Result<Void> setAddr(byte newReaderAddr) throws IOException, RespException;


    /**
     * 设置功率和频率
     *
     * @param power 功率，0x00~0x1E
     * @param freq  频率
     *              {@link RfidUhfConstant#FREQ_CHINA}
     *              {@link RfidUhfConstant#FREQ_AMERICA}
     *              {@link RfidUhfConstant#FREQ_EUROPE}
     *              {@link RfidUhfConstant#FREQ_EUROPE}
     * @return 无负载数据的结果
     * @throws IOException   读卡器IO异常
     * @throws RespException 响应异常
     */
    Result<Void> setRf(byte power, byte freq) throws IOException, RespException;

    /**
     * 获取功率和频率
     *
     * @return 带有功率和频率负载数据的结果
     * @throws IOException   读卡器IO异常
     * @throws RespException 响应异常
     */
    Result<PowerAndFreq> getRf() throws IOException, RespException;


    //======================== 6B标签 ========================//

    /**
     * 6B多标签识别，使用前先调用{@link RfidUhfReaderApi#clearIdBuffer()}
     *
     * @return 带有识别到的标签数量负载数据的结果
     * @throws IOException   读卡器IO异常
     * @throws RespException 响应异常
     */
    Result<Byte> multiTagIdentify6b() throws IOException, RespException;

    /**
     * 6B多标签读取,使用前先调用{@link RfidUhfReaderApi#clearIdBuffer()} ()}
     *
     * @param startAddr 标签开始地址
     * @return 带有读到标签数量负载数据的结果
     * @throws IOException   读卡器IO异常
     * @throws RespException 响应异常
     */
    Result<Byte> multiTagRead6b(byte startAddr) throws IOException, RespException;

    /**
     * 将数据写入6B标签
     *
     * @param addr  标签地址
     * @param value 写入的数据
     * @return 无负载数据的结果
     * @throws IOException   读卡器IO异常
     * @throws RespException 响应异常
     */
    Result<Void> write6b(byte addr, byte value) throws IOException, RespException;

    /**
     * 读取特定UID的6B标签，每次可读取8个字节
     *
     * @param uid  UID,长度为8的字节数组
     * @param addr 标签地址
     * @return 带有标签数据负载数据的结果
     * @throws IOException   读卡器IO异常
     * @throws RespException 响应异常
     */
    Result<UhfTag> readWithUid6b(byte[] uid, byte addr) throws IOException, RespException;

    /**
     * 将数据写入特定UID的6B标签
     *
     * @param uid   UID,长度为8的字节数组
     * @param addr  标签地址
     * @param value 写入的数据
     * @return 无负载数据的结果
     * @throws IOException   读卡器IO异常
     * @throws RespException 响应异常
     */
    Result<Void> writeWithUid6b(byte[] uid, byte addr, byte value) throws IOException, RespException;

    /**
     * 锁定6B标签的特定地址
     *
     * @param addr 标签地址
     * @return 无负载数据的结果
     * @throws IOException   读卡器IO异常
     * @throws RespException 响应异常
     */
    Result<Void> lock6b(byte addr) throws IOException, RespException;

    /**
     * 查询6B标签特定的特定地址是否被锁定
     *
     * @param addr 标签地址
     * @return 带有是否锁定负载数据的结果
     * @throws IOException   读卡器IO异常
     * @throws RespException 响应异常
     */
    Result<Boolean> queryLock6b(byte addr) throws IOException, RespException;

    /**
     * 将数据块写入6B标签
     *
     * @param addr  标签地址,必须是4的整数倍
     * @param value 写入的数据块,长度为4的字节数组
     * @return 无负载数据的结果
     * @throws IOException   读卡器IO异常
     * @throws RespException 响应异常
     */
    Result<Void> blockWrite6b(byte addr, byte[] value) throws IOException, RespException;

    /**
     * 6B标签单标签读取，每次可读取8个字节
     *
     * @param addr 标签地址
     * @return 带有标签信息负载数据的结果
     * @throws IOException   读卡器IO异常
     * @throws RespException 响应异常
     */
    Result<UhfTag> singleTagRead6b(byte addr) throws IOException, RespException;

    //======================== 6C标签 ========================//

    /**
     * 6C多标签识别，使用前先调用{@link RfidUhfReaderApi#clearIdBuffer()} ()}
     *
     * @return 带有读到标签数量负载数据的结果
     * @throws IOException   读卡器IO异常
     * @throws RespException 响应异常
     */
    Result<Byte> multiTagIdentify6c() throws IOException, RespException;

    /**
     * 将数据写入6C标签
     *
     * @param addr  标签地址
     * @param value 写入的数据，长度为2的字节数组
     * @return 无负载数据的结果
     * @throws IOException   读卡器IO异常
     * @throws RespException 响应异常
     */
    Result<Void> epcWrite6c(byte addr, byte[] value) throws IOException, RespException;

    /**
     * 锁定6C卡特定的标签区域
     *
     * @param memBank 标签区域
     * @param control 控制位，值可以是{@link RfidUhfConstant#CONTROL_NOT_LOCK,RfidUhfConstant#CONTROL_NEVER_LOCK,RfidUhfConstant#CONTROL_FOREVER_LOCK,RfidUhfConstant#CONTROL_SAFETY_LOCK}
     * @return 无负载数据的结果
     * @throws IOException   读卡器IO异常
     * @throws RespException 响应异常
     */
    Result<Void> lock6c(byte memBank, byte control) throws IOException, RespException;

    /**
     * 销毁6C标签
     *
     * @param password 标签的销毁密码，长度为4的字节数组
     * @return 无负载数据的结果
     * @throws IOException   读卡器IO异常
     * @throws RespException 响应异常
     */
    Result<Void> kill6c(byte[] password) throws IOException, RespException;

    /**
     * 初始化6C标签
     *
     * @param bitCount 要初始化的EPC位数，一般是96
     * @return 无负载数据的结果
     * @throws IOException   读卡器IO异常
     * @throws RespException 响应异常
     */
    Result<Void> init6c(byte bitCount) throws IOException, RespException;

    /**
     * 读取6C标签的特定区域
     *
     * @param memBank 标签区域
     * @param addr    标签地址
     * @param len     读取字节长度
     * @return 带有标签数据负载数据的结果
     * @throws IOException   读卡器IO异常
     * @throws RespException 响应异常
     */
    Result<UhfTag> read6c(byte memBank, byte addr, byte len) throws IOException, RespException;

    /**
     * 将数据写入6C标签的特定区域
     *
     * @param memBank 标签区域
     * @param addr    标签地址
     * @param value   写入的数据，长度为2的字节数组
     * @return 无负载数据的结果
     * @throws IOException   读卡器IO异常
     * @throws RespException 响应异常
     */
    Result<Void> write6c(byte memBank, byte addr, byte[] value) throws IOException, RespException;

    //======================== 缓存命令 ========================//


    /**
     * 从缓存区中取标签数据
     *
     * @param count 标签数量
     * @return 带有标签数据数组负载数据的结果
     * @throws IOException   读卡器IO异常
     * @throws RespException 响应异常
     */
    Result<UhfTag[]> getIdAndDelete(byte count) throws IOException, RespException;

    /**
     * 从缓存中取标签数据命令，取完后保留数据
     *
     * @return 带有标签数据负载数据的结果
     * @throws IOException   读卡器IO异常
     * @throws RespException 响应异常
     */
    Result<UhfTag> getId() throws IOException, RespException;

    /**
     * 数据取出成功的反馈，读写器收到此命令后，删除之前所传的数据
     *
     * @return 无负载数据的结果（缓存区中没有标签时）或带有标签数据负载数据的结果
     * @throws IOException   读卡器IO异常
     * @throws RespException 响应异常
     */
    Result getIdAck() throws IOException, RespException;

    /**
     * 查询缓存区中的标签数量
     *
     * @return 带有标签数量负载数据的结果
     * @throws IOException   读卡器IO异常
     * @throws RespException 响应异常
     */
    Result<Byte> queryIdCount() throws IOException, RespException;

    /**
     * 清空缓存区
     *
     * @return 无负载数据的结果
     * @throws IOException   读卡器IO异常
     * @throws RespException 响应异常
     */
    Result<Void> clearIdBuffer() throws IOException, RespException;

    //======================== 便捷操作 ========================//

    boolean fastBatchBlockWrite6b(byte startAddr, @NotNull byte[] values) throws IOException, RespException;

    boolean fastBatchWrite6b(byte startAddr, @NotNull byte[] values) throws IOException, RespException;

    UhfTag[] fastMultiTagRead6b(byte startAddr) throws IOException, RespException;

    UhfTag[] fastMultiTagIdentify6b() throws IOException, RespException;

    boolean fastBatchWrite6c(byte memBank, byte startAddr, @NotNull byte[] values) throws IOException, RespException;

    UhfTag[] fastMultiTagIdentify6c() throws IOException, RespException;

}
