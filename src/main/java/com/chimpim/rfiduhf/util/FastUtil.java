package com.chimpim.rfiduhf.util;

import com.chimpim.rfiduhf.RfidUhfReaderOperator;
import com.chimpim.rfiduhf.data.Result;
import com.chimpim.rfiduhf.data.UhfTag;
import com.chimpim.rfiduhf.exception.RespException;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.logging.Logger;

public class FastUtil {
    private static final Logger logger = Logger.getLogger("FastUtil");

    public static UhfTag[] isoMultiTagIdentify(@NotNull RfidUhfReaderOperator operator) throws IOException, RespException {
        // 清空缓存区
        operator.clearIDBuffer();
        // 多标签识别
        Result<Byte> result = operator.isoMultiTagIdentify();
        // 从缓存区中取出标签
        Result<UhfTag[]> result1 = operator.getIDAndDelete(result.getPayload());
        return result1.getPayload();
    }

    public static UhfTag[] isoMultiTagRead(@NotNull RfidUhfReaderOperator operator, byte startAddr) throws IOException, RespException {
        // 清空缓存区
        operator.clearIDBuffer();
        // 多标签读取
        Result<Byte> result = operator.isoMultiTagRead(startAddr);
        // 从缓存区中取出标签
        Result<UhfTag[]> result1 = operator.getIDAndDelete(result.getPayload());
        return result1.getPayload();
    }

    public static boolean batchGen2Write(@NotNull RfidUhfReaderOperator operator,
                                         byte memBank, byte startAddr,
                                         @NotNull byte[] values) throws IOException, RespException {
        int len = values.length / 2;
        int index = 0;
        for (int i = 0; i < len; i++) {
            byte addr = (byte) (startAddr + i);
            byte[] value = new byte[]{values[index++], values[index++]};
            Result<Void> result = operator.gen2Write(memBank, addr, value);
            logger.info(result.toString());
            if (!result.isOk()) {
                logger.info(result.getScName());
                return false;
            }
        }
        return true;
    }

    public static UhfTag[] gen2MultiTagIdentify(@NotNull RfidUhfReaderOperator operator) throws IOException, RespException {
        // 清空缓存区
        operator.clearIDBuffer();
        // 多标签识别
        Result<Byte> result = operator.gen2MultiTagIdentify();
        // 从缓存区中取出标签
        Result<UhfTag[]> result1 = operator.getIDAndDelete(result.getPayload());
        return result1.getPayload();
    }

}
