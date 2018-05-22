package com.chimpim.rfiduhf.internal;

import com.chimpim.rfiduhf.data.PowerAndFreq;
import com.chimpim.rfiduhf.data.Result;
import com.chimpim.rfiduhf.data.UhfTag;
import com.chimpim.rfiduhf.data.Version;
import com.chimpim.rfiduhf.exception.*;
import org.jetbrains.annotations.NotNull;

import static com.chimpim.rfiduhf.RfidUhfConstant.REQ_HEAD;
import static com.chimpim.rfiduhf.RfidUhfConstant.RESP_HEAD;

public class RfidUhfProtocolImpl implements RfidUhfProtocol {


    @NotNull
    @Override
    public byte[] setBaudRate(byte readerAddr, byte baudRate) {
        // 0x0A address 0x03 0x20 baudRate cc
        byte[] cmd = new byte[]{
                REQ_HEAD,
                readerAddr,
                (byte) 0x03, // len
                (byte) 0x20, // cmd
                baudRate,
                (byte) 0x00, // cc
        };
        cmd[cmd.length - 1] = checkCode(cmd);
        return cmd;
    }

    @NotNull
    @Override
    public byte[] resetReader(byte readerAddr) {
        // 0x0A address 0x02 0x21 cc
        byte[] cmd = new byte[]{
                REQ_HEAD,
                readerAddr,
                (byte) 0x02, // len
                (byte) 0x21, // cmd
                (byte) 0x00, // cc
        };
        cmd[cmd.length - 1] = checkCode(cmd);
        return cmd;
    }

    @NotNull
    @Override
    public byte[] getFirmwareVersion(byte readerAddr) {
        // 0x0A address 0x02 0x22 cc
        byte[] cmd = new byte[]{
                REQ_HEAD,
                readerAddr,
                (byte) 0x02, // len
                (byte) 0x22, // cmd
                (byte) 0x00, // cc
        };
        cmd[cmd.length - 1] = checkCode(cmd);
        return cmd;
    }

    @NotNull
    @Override
    public byte[] setWorkMode(byte readerAddr, byte workMode) {
        // 0A address 04 23 70 00 60
        byte[] cmd = new byte[]{
                REQ_HEAD,
                readerAddr,
                (byte) 0x04, // len
                (byte) 0x23, // cmd
                (byte) 0x70, // Register address
                workMode, //workMode
                (byte) 0x00, // cc
        };
        cmd[cmd.length - 1] = checkCode(cmd);
        return cmd;
    }

    @NotNull
    @Override
    public byte[] getWorkMode(byte readerAddr) {
        // 0A address 03 24 70 60
        byte[] cmd = new byte[]{
                REQ_HEAD,
                readerAddr,
                (byte) 0x03, // len
                (byte) 0x24, // cmd
                (byte) 0x70, // Register address
                (byte) 0x00, // cc
        };
        cmd[cmd.length - 1] = checkCode(cmd);
        return cmd;
    }

    @NotNull
    @Override
    public byte[] setRf(byte readerAddr, byte power, byte freq) {
        // 0x0A address 0x04 0x25 power freq cc
        byte[] cmd = new byte[]{
                REQ_HEAD,
                readerAddr,
                (byte) 0x04, // len
                (byte) 0x25, // cmd
                power,
                freq,
                (byte) 0x00, // cc
        };
        cmd[cmd.length - 1] = checkCode(cmd);
        return cmd;
    }

    @NotNull
    @Override
    public byte[] getRf(byte readerAddr) {
        // 0x0A address 0x02 0x26 cc
        byte[] cmd = new byte[]{
                REQ_HEAD,
                readerAddr,
                (byte) 0x02, // len
                (byte) 0x26, // cmd
                (byte) 0x00, // cc
        };
        cmd[cmd.length - 1] = checkCode(cmd);
        return cmd;
    }

    @NotNull
    @Override
    public byte[] setWorkAntenna(byte readerAddr, byte antCfg) {
        // 0x0A address 0x03 0x27 antCfg cc
        byte[] cmd = new byte[]{
                REQ_HEAD,
                readerAddr,
                (byte) 0x03, // len
                (byte) 0x27, // cmd
                antCfg,
                (byte) 0x00, // cc
        };
        cmd[cmd.length - 1] = checkCode(cmd);
        return cmd;
    }

    @NotNull
    @Override
    public byte[] getWorkAntenna(byte readerAddr) {
        // 0x0A address 0x02 0x28 cc
        byte[] cmd = new byte[]{
                REQ_HEAD,
                readerAddr,
                (byte) 0x02, // len
                (byte) 0x28, // cmd
                (byte) 0x00, // cc
        };
        cmd[cmd.length - 1] = checkCode(cmd);
        return cmd;
    }


    @NotNull
    @Override
    public byte[] multiTagIdentify6b(byte readerAddr) {
        // 0x0A address 0x02 0x60 cc
        byte[] cmd = new byte[]{
                REQ_HEAD,
                readerAddr,
                (byte) 0x02, // len
                (byte) 0x60, // cmd
                (byte) 0x00, // cc
        };
        cmd[cmd.length - 1] = checkCode(cmd);
        return cmd;
    }

    @NotNull
    @Override
    public byte[] multiTagRead6b(byte readerAddr, byte startAddr) {
        // 0x0A address 0x03 0x61 startAddr cc
        byte[] cmd = new byte[]{
                REQ_HEAD,
                readerAddr,
                (byte) 0x03, // len
                (byte) 0x61, // cmd
                startAddr,
                (byte) 0x00, // cc
        };
        cmd[cmd.length - 1] = checkCode(cmd);
        return cmd;
    }

    @NotNull
    @Override
    public byte[] write6b(byte readerAddr, byte addr, byte value) {
        // 0x0A address 0x04 0x62 addr value cc
        byte[] cmd = new byte[]{
                REQ_HEAD,
                readerAddr,
                (byte) 0x04, // len
                (byte) 0x62, // cmd
                addr,
                value,
                (byte) 0x00, // cc
        };
        cmd[cmd.length - 1] = checkCode(cmd);
        return cmd;
    }

    @NotNull
    @Override
    public byte[] readWithUid6b(byte readerAddr, byte[] uid, byte addr) {
        // 0x0A address 0x0B 0x63 UID(8byte) addr cc
        byte[] cmd = new byte[]{
                REQ_HEAD,
                readerAddr,
                (byte) 0x0B, // len
                (byte) 0x63, // cmd
                uid[0], uid[1], uid[2], uid[3],
                uid[4], uid[5], uid[6], uid[7],
                addr,
                (byte) 0x00, // cc
        };
        cmd[cmd.length - 1] = checkCode(cmd);
        return cmd;
    }

    @NotNull
    @Override
    public byte[] writeWithUid6b(byte readerAddr, byte[] uid, byte addr, byte value) {
        // 0x0A address 0x0B 0x64 UID(8byte) addr value cc
        byte[] cmd = new byte[]{
                REQ_HEAD,
                readerAddr,
                (byte) 0x0B, // len
                (byte) 0x64, // cmd
                uid[0], uid[1], uid[2], uid[3],
                uid[4], uid[5], uid[6], uid[7],
                addr,
                value,
                (byte) 0x00, // cc
        };
        cmd[cmd.length - 1] = checkCode(cmd);
        return cmd;
    }

    @NotNull
    @Override
    public byte[] lock6b(byte readerAddr, byte addr) {
        // 0x0A address 0x03 0x65 addr cc
        byte[] cmd = new byte[]{
                REQ_HEAD,
                readerAddr,
                (byte) 0x03, // len
                (byte) 0x65, // cmd
                addr,
                (byte) 0x00, // cc
        };
        cmd[cmd.length - 1] = checkCode(cmd);
        return cmd;
    }

    @NotNull
    @Override
    public byte[] queryLock6b(byte readerAddr, byte addr) {
        // 0x0A address 0x03 0x66 addr cc
        byte[] cmd = new byte[]{
                REQ_HEAD,
                readerAddr,
                (byte) 0x03, // len
                (byte) 0x66, // cmd
                addr,
                (byte) 0x00, // cc
        };
        cmd[cmd.length - 1] = checkCode(cmd);
        return cmd;
    }

    @NotNull
    @Override
    public byte[] blockWrite6b(byte readerAddr, byte addr, byte[] value) {
        // 0x0A address 0x07 0x67 addr value(4byte) cc
        byte[] cmd = new byte[]{
                REQ_HEAD,
                readerAddr,
                (byte) 0x07, // len
                (byte) 0x67, // cmd
                addr,
                value[0], value[1], value[2], value[3],
                (byte) 0x00, // cc
        };
        cmd[cmd.length - 1] = checkCode(cmd);
        return cmd;
    }

    @NotNull
    @Override
    public byte[] singleTagRead6b(byte readerAddr, byte addr) {
        // 0x0A address 0x03 0x68 addr cc
        byte[] cmd = new byte[]{
                REQ_HEAD,
                readerAddr,
                (byte) 0x03, // len
                (byte) 0x68, // cmd
                addr,
                (byte) 0x00, // cc
        };
        cmd[cmd.length - 1] = checkCode(cmd);
        return cmd;
    }


    @NotNull
    @Override
    public byte[] multiTagIdentify6c(byte readerAddr) {
        // 0x0A address 0x02 0x80 cc
        byte[] cmd = new byte[]{
                REQ_HEAD,
                readerAddr,
                (byte) 0x02, // len
                (byte) 0x80, // cmd
                (byte) 0x00, // cc
        };
        cmd[cmd.length - 1] = checkCode(cmd);
        return cmd;
    }

    @NotNull
    @Override
    public byte[] epcWrite6c(byte readerAddr, byte addr, byte[] value) {
        // 0x0A address 0x05 0x81 addr value(2byte) cc
        byte[] cmd = new byte[]{
                REQ_HEAD,
                readerAddr,
                (byte) 0x05, // len
                (byte) 0x81, // cmd
                addr,
                value[0],
                value[1],
                (byte) 0x00, // cc
        };
        cmd[cmd.length - 1] = checkCode(cmd);
        return cmd;
    }

    @NotNull
    @Override
    public byte[] lock6c(byte readerAddr, byte memBank, byte control) {
        // 0x0A address 0x04 0x82 memBank control cc
        byte[] cmd = new byte[]{
                REQ_HEAD,
                readerAddr,
                (byte) 0x04, // len
                (byte) 0x82, // cmd
                memBank,
                control,
                (byte) 0x00, // cc
        };
        cmd[cmd.length - 1] = checkCode(cmd);
        return cmd;
    }

    @NotNull
    @Override
    public byte[] kill6c(byte readerAddr, byte[] password) {
        // 0x0A address 0x06 0x83 password(4byte) cc
        byte[] cmd = new byte[]{
                REQ_HEAD,
                readerAddr,
                (byte) 0x06, // len
                (byte) 0x83, // cmd
                password[0], password[1], password[2], password[3],
                (byte) 0x00, // cc
        };
        cmd[cmd.length - 1] = checkCode(cmd);
        return cmd;
    }

    @NotNull
    @Override
    public byte[] init6c(byte readerAddr, byte bitCount) {
        // 0x0A address 0x03 0x84 bitCount cc
        byte[] cmd = new byte[]{
                REQ_HEAD,
                readerAddr,
                (byte) 0x03, // len
                (byte) 0x84, // cmd
                bitCount,
                (byte) 0x00, // cc
        };
        cmd[cmd.length - 1] = checkCode(cmd);
        return cmd;
    }

    @NotNull
    @Override
    public byte[] read6c(byte readerAddr, byte memBank, byte addr, byte len) {
        // 0x0A address 0x05 0x85 memBank addr count cc
        byte[] cmd = new byte[]{
                REQ_HEAD,
                readerAddr,
                (byte) 0x05, // len
                (byte) 0x85, // cmd
                memBank,
                addr,
                len,
                (byte) 0x00, // cc
        };
        cmd[cmd.length - 1] = checkCode(cmd);
        return cmd;
    }

    @NotNull
    @Override
    public byte[] write6c(byte readerAddr, byte memBank, byte addr, byte[] value) {
        // 0x0A address 0x06 0x86 memBank addr value(2byte) cc
        byte[] cmd = new byte[]{
                REQ_HEAD,
                readerAddr,
                (byte) 0x06, // len
                (byte) 0x86, // cmd
                memBank,
                addr,
                value[0], value[1],
                (byte) 0x00, // cc
        };
        cmd[cmd.length - 1] = checkCode(cmd);
        return cmd;
    }


    @NotNull
    @Override
    public byte[] getIdAndDelete(byte readerAddr, byte tagCount) {
        // 0x0A address 0x03 0x40 tagCount cc
        byte[] cmd = new byte[]{
                REQ_HEAD,
                readerAddr,
                (byte) 0x03, // len
                (byte) 0x40, // cmd
                tagCount,
                (byte) 0x00, // cc
        };
        cmd[cmd.length - 1] = checkCode(cmd);
        return cmd;
    }

    @NotNull
    @Override
    public byte[] getId(byte readerAddr) {
        // 0x0A address 0x02 0x41 cc
        byte[] cmd = new byte[]{
                REQ_HEAD,
                readerAddr,
                (byte) 0x02, // len
                (byte) 0x41, // cmd
                (byte) 0x00, // cc
        };
        cmd[cmd.length - 1] = checkCode(cmd);
        return cmd;
    }

    @NotNull
    @Override
    public byte[] getIdAck(byte readerAddr) {
        // 0x0A address 0x02 0x42 cc
        byte[] cmd = new byte[]{
                REQ_HEAD,
                readerAddr,
                0x02, // len
                (byte) 0x42, // cmd
                0x00, // checkCode
        };
        cmd[cmd.length - 1] = checkCode(cmd);
        return cmd;
    }

    @NotNull
    @Override
    public byte[] queryIdCount(byte readerAddr) {
        // 0x0A address 0x02 0x43 cc
        byte[] cmd = new byte[]{
                REQ_HEAD,
                readerAddr,
                (byte) 0x02, // len
                (byte) 0x43, // cmd
                (byte) 0x00, // checkCode
        };
        cmd[cmd.length - 1] = checkCode(cmd);
        return cmd;
    }

    @NotNull
    @Override
    public byte[] clearIdBuffer(byte readerAddr) {
        // 0x0A address 0x02 0x44 cc
        byte[] cmd = new byte[]{
                REQ_HEAD,
                readerAddr,
                (byte) 0x02, // len
                (byte) 0x44, // cmd
                (byte) 0x00, // cc
        };
        cmd[cmd.length - 1] = checkCode(cmd);
        return cmd;
    }

    @NotNull
    @Override
    public Result<Void> setBaudRate(byte[] resp) throws RespException {
        return obtionNonDataResp(resp);
    }

    @NotNull
    @Override
    public Result<Void> resetReader(byte[] resp) throws RespException {
        return obtionNonDataResp(resp);
    }

    @NotNull
    @Override
    public Result<Version> getFirmwareVersion(byte[] resp) throws RespException {
        checkBasicResp(resp);
        return Result.of(resp, new Version(resp[4], resp[5]));
    }

    @NotNull
    @Override
    public Result<Void> setRf(byte[] resp) throws RespException {
        return obtionNonDataResp(resp);
    }

    @NotNull
    @Override
    public Result<PowerAndFreq> getRf(byte[] resp) throws RespException {
        // 0x0B address 0x04 0x00 power freq cc
        checkBasicResp(resp);
        return Result.of(resp, new PowerAndFreq(resp[4], resp[5]));
    }

    @NotNull
    @Override
    public Result<Void> setWorkAntenna(byte[] resp) throws RespException {
        return obtionNonDataResp(resp);
    }

    @NotNull
    @Override
    public Result<Byte> getWorkAntenna(byte[] resp) throws RespException {
        // 0x0A address 0x03 0x00 antenna cc
        checkBasicResp(resp);
        return Result.of(resp, resp[4]);
    }

    @NotNull
    @Override
    public Result<Void> setWorkMode(byte[] resp) throws RespException {
        return obtionNonDataResp(resp);
    }

    @NotNull
    @Override
    public Result<Byte> getWorkMode(byte[] resp) throws RespException {
        // 0B address len 00 workMode F4
        checkBasicResp(resp);
        return Result.of(resp, resp[4]);
    }

    @NotNull
    @Override
    public Result<Byte> multiTagIdentify6b(byte[] resp) throws RespException {
        // 0x0B address 0x03 0x00 tagCount cc
        checkBasicResp(resp);
        return Result.of(resp, resp[4]);
    }

    @NotNull
    @Override
    public Result<Byte> multiTagRead6b(byte[] resp) throws RespException {
        // 0x0B address 0x03 0x00 TagCount cc
        checkBasicResp(resp);
        return Result.of(resp, resp[4]);
    }

    @NotNull
    @Override
    public Result<Void> write6b(byte[] resp) throws RespException {
        return obtionNonDataResp(resp);
    }

    @NotNull
    @Override
    public Result<UhfTag> readWithUid6b(byte[] resp) throws RespException {
        // 0x0B address 0x0B 0x00 antenna data(8byte) cc
        checkBasicResp(resp);
        byte[] bytes = new byte[8];
        System.arraycopy(resp, 5, bytes, 0, bytes.length);
        return Result.of(resp, new UhfTag(UhfTag.TYPE_ISO_6B, resp[4], bytes));
    }

    @NotNull
    @Override
    public Result<Void> writeWithUid6b(byte[] resp) throws RespException {
        return obtionNonDataResp(resp);
    }

    @NotNull
    @Override
    public Result<Void> lock6b(byte[] resp) throws RespException {
        return obtionNonDataResp(resp);
    }

    @NotNull
    @Override
    public Result<Boolean> queryLock6b(byte[] resp) throws RespException {
        // 0x0B address 0x03 0x00 lockStatus cc
        checkBasicResp(resp);
        byte lockStatus = resp[4];
        if (lockStatus == 0x00) {
            return Result.of(resp, false);
        } else if (lockStatus == 0x01) {
            return Result.of(resp, true);
        }
        return Result.of(resp, null);
    }

    @NotNull
    @Override
    public Result<Void> blockWrite6b(byte[] resp) throws RespException {
        return obtionNonDataResp(resp);
    }

    @NotNull
    @Override
    public Result<UhfTag> singleTagRead6b(byte[] resp) throws RespException {
        // 0x0B address 0x0B 0x00 antenna data(8byte) cc
        checkBasicResp(resp);
        int dataLen = resp[2] - 3;
        if (dataLen < 0) {
            return Result.of(resp, null);
        }
        byte[] data = new byte[dataLen];
        System.arraycopy(resp, 5, data, 0, data.length);
        return Result.of(resp, new UhfTag(UhfTag.TYPE_ISO_6B, resp[4], data));
    }

    @NotNull
    @Override
    public Result<Byte> multiTagIdentify6c(byte[] resp) throws RespException {
        // 0x0B address 0x03 0x00 tagCount cc
        checkBasicResp(resp);
        return Result.of(resp, resp[4]);
    }

    @NotNull
    @Override
    public Result<Void> epcWrite6c(byte[] resp) throws RespException {
        return obtionNonDataResp(resp);
    }

    @NotNull
    @Override
    public Result<Void> lock6c(byte[] resp) throws RespException {
        return obtionNonDataResp(resp);
    }

    @NotNull
    @Override
    public Result<Void> kill6c(byte[] resp) throws RespException {
        return obtionNonDataResp(resp);
    }

    @NotNull
    @Override
    public Result<Void> init6c(byte[] resp) throws RespException {
        return obtionNonDataResp(resp);
    }

    @NotNull
    @Override
    public Result<UhfTag> read6c(byte[] resp) throws RespException {
        // 0x0B address 0x0B 0x00 antenna data(8byte) cc
        checkBasicResp(resp);
        int dataLen = resp[2] - 3;
        if (dataLen < 0) {
            return Result.of(resp, null);
        }
        byte[] data = new byte[dataLen];
        System.arraycopy(resp, 5, data, 0, data.length);
        return Result.of(resp, new UhfTag(UhfTag.TYPE_GEN2_6C, resp[4], data));
    }

    @NotNull
    @Override
    public Result<Void> write6c(byte[] resp) throws RespException {
        return obtionNonDataResp(resp);
    }

    @NotNull
    @Override
    public Result<UhfTag[]> getIdAndDelete(byte[] resp) throws RespException {
        // 0x0B address 14*n+3 0x00 count data(14*n) cc
        checkBasicResp(resp);
        int n = (resp[2] - 3) / 14;
        UhfTag[] uhfTags = new UhfTag[resp[4]];
        byte[] data = new byte[14 * n];
        System.arraycopy(resp, 5, data, 0, data.length);
        for (int i = 0; i < uhfTags.length; i++) {
            byte[] bytes = new byte[14];
            System.arraycopy(data, i * 14, bytes, 0, 14);
            uhfTags[i] = newUhfTagOf14bytes(bytes);
        }
        return new Result<>(resp[3], resp[1], uhfTags);
    }

    @NotNull
    @Override
    public Result<UhfTag> getId(byte[] resp) throws RespException {
        // 0x0B address 17 0x00 data(14) cc
        checkBasicResp(resp);
        byte[] bytes = new byte[14];
        System.arraycopy(resp, 0, bytes, 0, 14);
        UhfTag uhfTag = newUhfTagOf14bytes(bytes);
        return new Result<>(resp[3], resp[1], uhfTag);
    }

    @NotNull
    @Override
    public Result getIdAck(byte[] resp) throws RespException {
        // 尝试Obtion无数据应答帧
        try {
            return obtionNonDataResp(resp);
        } catch (RespException e) {
            // 失败
            return getId(resp);
        }
    }

    @NotNull
    @Override
    public Result<Byte> queryIdCount(byte[] resp) throws RespException {
        // 0x0B	address	0x03 0x00 count cc
        checkBasicResp(resp);
        return Result.of(resp, resp[4]);
    }

    @NotNull
    @Override
    public Result<Void> clearIdBuffer(byte[] resp) throws RespException {
        return obtionNonDataResp(resp);
    }

    // 获得无数据应答帧
    private static Result<Void> obtionNonDataResp(byte[] resp) throws RespException {
        // 0x0B address len status cc
        checkBasicResp(resp);
        return new Result<>(resp[3], resp[1], null);
    }

    private static void checkBasicResp(byte[] resp) throws RespNullException, RespLenException, RespHeadException, RespScException {
        if (resp == null) {
            throw new RespNullException(null, "resp == null");
        }
        final int minLen = 5;
        if (resp.length < minLen) {
            throw new RespLenException(resp,
                    String.format("resp length: %d < %d", resp.length, minLen));
        }
        int len = resp[2] + 3;
        if (resp.length != len) {
            // 数据长度错误
            throw new RespLenException(resp,
                    String.format("resp length: %d != %d", resp.length, len));
        }
        if (resp[0] != RESP_HEAD) {
            // 响应头错误
            throw new RespHeadException(resp,
                    String.format("%s != %s", resp[0], RESP_HEAD));
        }
        byte cc = checkCode(resp);
        if (resp[resp.length - 1] != cc) {
            // 数据校验码错误
            throw new RespScException(resp,
                    String.format("%s != %s", resp[0], cc));
        }
    }

    private static byte checkCode(@NotNull byte[] bytes) {
        int sum = 0;
        int size = bytes.length;
        for (int i = 0; i < size - 1; i++) {
            sum += bytes[i];
        }
        return (byte) (0xFF & (~sum + 1));
    }

    private static UhfTag newUhfTagOf14bytes(@NotNull byte[] bytes) {
        byte[] data = new byte[12];
        System.arraycopy(bytes, 2, data, 0, 12);
        return new UhfTag(bytes[0], bytes[1], data);
    }
}
