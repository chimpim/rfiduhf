package com.chimpim.rfiduhf;

public class RfidUhfConstant {

    /**
     * 请求头
     */
    public static final  byte REQ_HEAD = (byte) 0x0A;
    /**
     * 响应头
     */
    public static final  byte RESP_HEAD = (byte) 0x0B;

    // =========================== 响应状态码 =========================== //
    /**
     * 命令成功完成
     */
    public static final byte SC_ERR_NONE = (byte) 0x00;
    /**
     * 笼统的错误
     */
    public static final byte SC_ERR_GENERAL_ERR = (byte) 0x01;
    /**
     * 参数设置失败
     */
    public static final byte SC_ERR_PAR_SET_FAILED = (byte) 0x02;
    /**
     * 参数读取失败
     */
    public static final byte SC_ERR_PAR_GET_FAILED = (byte) 0x03;
    /**
     * 无标签
     */
    public static final byte SC_ERR_NO_TAG = (byte) 0x04;
    /**
     * 标签读失败
     */
    public static final byte SC_ERR_READ_FAILED = (byte) 0x05;
    /**
     * 标签写失败
     */
    public static final byte SC_ERR_WRITE_FAILED = (byte) 0x06;
    /**
     * 标签锁定失败
     */
    public static final byte SC_ERR_LOCK_FAILED = (byte) 0x07;
    /**
     * 标签擦除失败
     */
    public static final byte SC_ERR_ERASE_FAILED = (byte) 0x08;
    /**
     * 命令不支持或参数超出范围
     */
    public static final byte SC_ERR_CMD_ERR = (byte) 0xFE;
    /**
     * 未定义错误
     */
    public static final byte SC_ERR_UNDEFINED = (byte) 0xFF;

    // =========================== 波特率 =========================== //
    /**
     * 波特率 9600
     */
    public static final byte BAUD_RATE_9600 = (byte) 0x00;
    /**
     * 波特率 19200
     */
    public static final byte BAUD_RATE_19200 = (byte) 0x01;
    /**
     * 波特率 38400
     */
    public static final byte BAUD_RATE_38400 = (byte) 0x02;
    /**
     * 波特率 57600
     */
    public static final byte BAUD_RATE_57600 = (byte) 0x03;
    /**
     * 波特率 115200
     */
    public static final byte BAUD_RATE_115200 = (byte) 0x04;

    /**
     * 命令工作模式
     */
    public static final byte WORK_MODE_COMMAND = (byte) 0x00;
    /**
     * 定时工作模式
     */
    public static final byte WORK_MODE_TIMEING = (byte) 0x01;
    /**
     * 触发工作模式
     */
    public static final byte WORK_MODE_TRIGGER = (byte) 0x02;

    // =========================== 读写器地址 =========================== //
    /**
     * 公用地址
     */
    public static final byte ADDRESS_COMMUNAL = (byte) 0xFF;
    /**
     * 广播地址
     */
    public static final byte ADDRESS_BROADCAST = (byte) 0xFE;

    // =========================== MenBank =========================== //
    /**
     * 保留区
     */
    public static final byte MEM_BANK_RESERVED = (byte) 0x00;
    /**
     * EPC 区
     */
    public static final byte MEM_BANK_EPC = (byte) 0x01;
    /**
     * TID 区
     */
    public static final byte MEM_BANK_TID = (byte) 0x02;
    /**
     * USER 区
     */
    public static final byte MEM_BANK_USER = (byte) 0x03;

    // =========================== Lock =========================== //
    /**
     * 不锁
     */
    public static final byte CONTROL_NOT_LOCK = (byte) 0x00;
    /**
     * 永远不锁
     */
    public static final byte CONTROL_NEVER_LOCK = (byte) 0x01;
    /**
     * 安全锁
     */
    public static final byte CONTROL_SAFETY_LOCK = (byte) 0x10;
    /**
     * 永远锁
     */
    public static final byte CONTROL_FOREVER_LOCK = (byte) 0x11;

}
