package com.chimpim.lib.rfiduhf;

public final class RfidUhfConstants {
    /**
     * 请求头
     */
    public static final byte REQ_HEAD = (byte) 0x0A;
    /**
     * 响应头
     */
    public static final byte RESP_HEAD = (byte) 0x0B;

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
    /**
     * 中国标准
     */
    public static final byte FREQ_CHINA = (byte) 0x00;
    /**
     * 北美标准
     */
    public static final byte FREQ_AMERICA = (byte) 0x01;
    /**
     * 欧洲标准
     */
    public static final byte FREQ_EUROPE = (byte) 0x02;
    /**
     * 其他标准
     */
    public static final byte FREQ_OTHER = (byte) 0x03;

    /**
     * 功率
     */
    public static final byte POWER_1 = (byte) 0x01;
    public static final byte POWER_2 = (byte) 0x02;
    public static final byte POWER_3 = (byte) 0x03;
    public static final byte POWER_4 = (byte) 0x04;
    public static final byte POWER_5 = (byte) 0x05;
    public static final byte POWER_6 = (byte) 0x06;
    public static final byte POWER_7 = (byte) 0x07;
    public static final byte POWER_8 = (byte) 0x08;
    public static final byte POWER_9 = (byte) 0x09;
    public static final byte POWER_10 = (byte) 0x0A;
    public static final byte POWER_11 = (byte) 0x0B;
    public static final byte POWER_12 = (byte) 0x0C;
    public static final byte POWER_13 = (byte) 0x0D;
    public static final byte POWER_14 = (byte) 0x0E;
    public static final byte POWER_15 = (byte) 0x0F;
    public static final byte POWER_16 = (byte) 0x10;
    public static final byte POWER_17 = (byte) 0x11;
    public static final byte POWER_18 = (byte) 0x12;
    public static final byte POWER_19 = (byte) 0x13;
    public static final byte POWER_20 = (byte) 0x14;
    public static final byte POWER_21 = (byte) 0x15;
    public static final byte POWER_22 = (byte) 0x16;
    public static final byte POWER_23 = (byte) 0x17;
    public static final byte POWER_24 = (byte) 0x18;
    public static final byte POWER_25 = (byte) 0x19;
    public static final byte POWER_26 = (byte) 0x1A;
    public static final byte POWER_27 = (byte) 0x1B;
    public static final byte POWER_28 = (byte) 0x1C;
    public static final byte POWER_29 = (byte) 0x1D;
    public static final byte POWER_30 = (byte) 0x1E;
}
