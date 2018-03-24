package com.chimpim.rfiduhf;

import com.chimpim.rfiduhf.data.PowerAndFreq;
import com.chimpim.rfiduhf.data.Result;
import com.chimpim.rfiduhf.data.UhfTag;
import com.chimpim.rfiduhf.data.Version;
import com.chimpim.rfiduhf.exception.*;
import org.jetbrains.annotations.NotNull;

class DefaultRfidUhfProtocol implements RfidUhfProtocol {
    static final DefaultRfidUhfProtocol INSTANCE = new DefaultRfidUhfProtocol();

    private DefaultRfidUhfProtocol() {
    }

    @Override
    public @NotNull byte[] setBaudRate(byte address, byte baudRate) {
        // 0x0A address 0x03 0x20 baudRate cc
        byte[] cmd = new byte[]{
                REQ_HEAD,
                address,
                (byte) 0x03, // len
                (byte) 0x20, // cmd
                baudRate,
                (byte) 0x00, // cc
        };
        cmd[cmd.length - 1] = checkCode(cmd);
        return cmd;
    }

    @Override
    public @NotNull
    Result<Void> setBaudRate(byte[] resp) throws RespException {
        return obtionNonDataResp(resp);
    }

    @Override
    public @NotNull byte[] resetReader(byte address) {
        // 0x0A address 0x02 0x21 cc
        byte[] cmd = new byte[]{
                REQ_HEAD,
                address,
                (byte) 0x02, // len
                (byte) 0x21, // cmd
                (byte) 0x00, // cc
        };
        cmd[cmd.length - 1] = checkCode(cmd);
        return cmd;
    }

    @Override
    public @NotNull
    Result<Void> resetReader(byte[] resp) throws RespException {
        return obtionNonDataResp(resp);
    }

    @Override
    public @NotNull byte[] getFirmwareVersion(byte address) {
        // 0x0A address 0x02 0x22 cc
        byte[] cmd = new byte[]{
                REQ_HEAD,
                address,
                (byte) 0x02, // len
                (byte) 0x22, // cmd
                (byte) 0x00, // cc
        };
        cmd[cmd.length - 1] = checkCode(cmd);
        return cmd;
    }

    @Override
    public @NotNull
    Result<Version> getFirmwareVersion(byte[] resp) throws RespException {
        checkBasicResp(resp);
        return Result.of(resp, new Version(resp[4], resp[5]));
    }

    @Override
    public @NotNull byte[] setRF(byte address, byte power, byte freq) {
        // 0x0A address 0x04 0x25 power freq cc
        byte[] cmd = new byte[]{
                REQ_HEAD,
                address,
                (byte) 0x04, // len
                (byte) 0x25, // cmd
                power,
                freq,
                (byte) 0x00, // cc
        };
        cmd[cmd.length - 1] = checkCode(cmd);
        return cmd;
    }

    @Override
    public @NotNull
    Result<Void> setRF(byte[] resp) throws RespException {
        return obtionNonDataResp(resp);
    }

    @Override
    public @NotNull byte[] getRF(byte address) {
        // 0x0A address 0x02 0x26 cc
        byte[] cmd = new byte[]{
                REQ_HEAD,
                address,
                (byte) 0x02, // len
                (byte) 0x26, // cmd
                (byte) 0x00, // cc
        };
        cmd[cmd.length - 1] = checkCode(cmd);
        return cmd;
    }

    @Override
    public @NotNull
    Result<PowerAndFreq> getRF(byte[] resp) throws RespException {
        // 0x0B address 0x04 0x00 power freq cc
        checkBasicResp(resp);
        return Result.of(resp, new PowerAndFreq(resp[4], resp[5]));
    }

    @Override
    public @NotNull byte[] setWorkAntenna(byte address, byte antCfg) {
        // 0x0A address 0x03 0x27 antCfg cc
        byte[] cmd = new byte[]{
                REQ_HEAD,
                address,
                (byte) 0x03, // len
                (byte) 0x27, // cmd
                antCfg,
                (byte) 0x00, // cc
        };
        cmd[cmd.length - 1] = checkCode(cmd);
        return cmd;
    }

    @Override
    public @NotNull
    Result<Void> setWorkAntenna(byte[] resp) throws RespException {
        return obtionNonDataResp(resp);
    }

    @Override
    public @NotNull byte[] getWorkAntenna(byte address) {
        // 0x0A address 0x02 0x28 cc
        byte[] cmd = new byte[]{
                REQ_HEAD,
                address,
                (byte) 0x02, // len
                (byte) 0x28, // cmd
                (byte) 0x00, // cc
        };
        cmd[cmd.length - 1] = checkCode(cmd);
        return cmd;
    }

    @Override
    public @NotNull
    Result<Byte> getWorkAntenna(byte[] resp) throws RespException {
        // 0x0A address 0x03 0x00 antenna cc
        checkBasicResp(resp);
        return Result.of(resp, resp[4]);
    }

    @Override
    public @NotNull byte[] isoMultiTagIdentify(byte address) {
        // 0x0A address 0x02 0x60 cc
        byte[] cmd = new byte[]{
                REQ_HEAD,
                address,
                (byte) 0x02, // len
                (byte) 0x60, // cmd
                (byte) 0x00, // cc
        };
        cmd[cmd.length - 1] = checkCode(cmd);
        return cmd;
    }

    @Override
    public @NotNull
    Result<Byte> isoMultiTagIdentify(byte[] resp) throws RespException {
        // 0x0B address 0x03 0x00 tagCount cc
        checkBasicResp(resp);
        return Result.of(resp, resp[4]);
    }

    @Override
    public @NotNull byte[] isoMultiTagRead(byte address, byte startAddr) {
        // 0x0A address 0x03 0x61 startAddr cc
        byte[] cmd = new byte[]{
                REQ_HEAD,
                address,
                (byte) 0x03, // len
                (byte) 0x61, // cmd
                startAddr,
                (byte) 0x00, // cc
        };
        cmd[cmd.length - 1] = checkCode(cmd);
        return cmd;
    }

    @Override
    public @NotNull
    Result<Byte> isoMultiTagRead(byte[] resp) throws RespException {
        // 0x0B address 0x03 0x00 TagCount cc
        checkBasicResp(resp);
        return Result.of(resp, resp[4]);
    }

    @Override
    public @NotNull byte[] isoWrite(byte address, byte addr, byte value) {
        // 0x0A address 0x04 0x62 addr value cc
        byte[] cmd = new byte[]{
                REQ_HEAD,
                address,
                (byte) 0x04, // len
                (byte) 0x62, // cmd
                addr,
                value,
                (byte) 0x00, // cc
        };
        cmd[cmd.length - 1] = checkCode(cmd);
        return cmd;
    }

    @Override
    public @NotNull
    Result<Void> isoWrite(byte[] resp) throws RespException {
        return obtionNonDataResp(resp);
    }

    @Override
    public @NotNull byte[] isoReadWithUID(byte address, byte[] uid, byte addr) {
        // 0x0A address 0x0B 0x63 UID(8byte) addr cc
        byte[] cmd = new byte[]{
                REQ_HEAD,
                address,
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

    @Override
    public @NotNull
    Result<UhfTag> isoReadWithUID(byte[] resp) throws RespException {
        // 0x0B address 0x0B 0x00 antenna data(8byte) cc
        checkBasicResp(resp);
        byte[] bytes = new byte[8];
        System.arraycopy(resp, 5, bytes, 0, bytes.length);
        return Result.of(resp, new UhfTag(UhfTag.TYPE_ISO_6B, resp[4], bytes));
    }

    @Override
    public @NotNull byte[] isoWriteWithUID(byte address, byte[] uid, byte addr, byte value) {
        // 0x0A address 0x0B 0x64 UID(8byte) addr value cc
        byte[] cmd = new byte[]{
                REQ_HEAD,
                address,
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

    @Override
    public @NotNull
    Result<Void> isoWriteWithUID(byte[] resp) throws RespException {
        return obtionNonDataResp(resp);
    }

    @Override
    public @NotNull byte[] isoLock(byte address, byte addr) {
        // 0x0A address 0x03 0x65 addr cc
        byte[] cmd = new byte[]{
                REQ_HEAD,
                address,
                (byte) 0x03, // len
                (byte) 0x65, // cmd
                addr,
                (byte) 0x00, // cc
        };
        cmd[cmd.length - 1] = checkCode(cmd);
        return cmd;
    }

    @Override
    public @NotNull
    Result<Void> isoLock(byte[] resp) throws RespException {
        return obtionNonDataResp(resp);
    }

    @Override
    public @NotNull byte[] isoQueryLock(byte address, byte addr) {
        // 0x0A address 0x03 0x66 addr cc
        byte[] cmd = new byte[]{
                REQ_HEAD,
                address,
                (byte) 0x03, // len
                (byte) 0x66, // cmd
                addr,
                (byte) 0x00, // cc
        };
        cmd[cmd.length - 1] = checkCode(cmd);
        return cmd;
    }

    @Override
    public @NotNull
    Result<Boolean> isoQueryLock(byte[] resp) throws RespException {
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

    @Override
    public @NotNull byte[] isoBlockWrite(byte address, byte addr, byte[] value) {
        // 0x0A address 0x07 0x67 addr value(4byte) cc
        byte[] cmd = new byte[]{
                REQ_HEAD,
                address,
                (byte) 0x07, // len
                (byte) 0x67, // cmd
                addr,
                value[0], value[1], value[2], value[3],
                (byte) 0x00, // cc
        };
        cmd[cmd.length - 1] = checkCode(cmd);
        return cmd;
    }

    @Override
    public @NotNull
    Result<Void> isoBlockWrite(byte[] resp) throws RespException {
        return obtionNonDataResp(resp);
    }

    @Override
    public @NotNull byte[] isoSingleTagRead(byte address, byte addr) {
        // 0x0A address 0x03 0x68 addr cc
        byte[] cmd = new byte[]{
                REQ_HEAD,
                address,
                (byte) 0x03, // len
                (byte) 0x68, // cmd
                addr,
                (byte) 0x00, // cc
        };
        cmd[cmd.length - 1] = checkCode(cmd);
        return cmd;
    }

    @Override
    public @NotNull
    Result<UhfTag> isoSingleTagRead(byte[] resp) throws RespException {
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

    @Override
    public @NotNull byte[] gen2MultiTagIdentify(byte address) {
        // 0x0A address 0x02 0x80 cc
        byte[] cmd = new byte[]{
                REQ_HEAD,
                address,
                (byte) 0x02, // len
                (byte) 0x80, // cmd
                (byte) 0x00, // cc
        };
        cmd[cmd.length - 1] = checkCode(cmd);
        return cmd;
    }

    @Override
    public @NotNull
    Result<Byte> gen2MultiTagIdentify(byte[] resp) throws RespException {
        // 0x0B address 0x03 0x00 tagCount cc
        checkBasicResp(resp);
        return Result.of(resp, resp[4]);
    }

    @Override
    public @NotNull byte[] gen2EPCWrite(byte address, byte addr, byte[] value) {
        // 0x0A address 0x05 0x81 addr value(2byte) cc
        byte[] cmd = new byte[]{
                REQ_HEAD,
                address,
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

    @Override
    public @NotNull
    Result<Void> gen2EPCWrite(byte[] resp) throws RespException {
        return obtionNonDataResp(resp);
    }

    @Override
    public @NotNull byte[] gen2Lock(byte address, byte memBank, byte control) {
        // 0x0A address 0x04 0x82 memBank control cc
        byte[] cmd = new byte[]{
                REQ_HEAD,
                address,
                (byte) 0x04, // len
                (byte) 0x82, // cmd
                memBank,
                control,
                (byte) 0x00, // cc
        };
        cmd[cmd.length - 1] = checkCode(cmd);
        return cmd;
    }

    @Override
    public @NotNull
    Result<Void> gen2Lock(byte[] resp) throws RespException {
        return obtionNonDataResp(resp);
    }

    @Override
    public @NotNull byte[] gen2Kill(byte address, byte[] password) {
        // 0x0A address 0x06 0x83 password(4byte) cc
        byte[] cmd = new byte[]{
                REQ_HEAD,
                address,
                (byte) 0x06, // len
                (byte) 0x83, // cmd
                password[0], password[1], password[2], password[3],
                (byte) 0x00, // cc
        };
        cmd[cmd.length - 1] = checkCode(cmd);
        return cmd;
    }

    @Override
    public @NotNull
    Result<Void> gen2Kill(byte[] resp) throws RespException {
        return obtionNonDataResp(resp);
    }

    @Override
    public @NotNull byte[] gen2Init(byte address, byte bitCount) {
        // 0x0A address 0x03 0x84 bitCount cc
        byte[] cmd = new byte[]{
                REQ_HEAD,
                address,
                (byte) 0x03, // len
                (byte) 0x84, // cmd
                bitCount,
                (byte) 0x00, // cc
        };
        cmd[cmd.length - 1] = checkCode(cmd);
        return cmd;
    }

    @Override
    public @NotNull
    Result<Void> gen2Init(byte[] resp) throws RespException {
        return obtionNonDataResp(resp);
    }

    @Override
    public @NotNull byte[] gen2Read(byte address, byte memBank, byte addr, byte count) {
        // 0x0A address 0x05 0x85 memBank addr count cc
        byte[] cmd = new byte[]{
                REQ_HEAD,
                address,
                0x05, // len
                (byte) 0x85, // cmd
                memBank,
                addr,
                count,
                0x00, // checkCode
        };
        cmd[cmd.length - 1] = checkCode(cmd);
        return cmd;
    }

    @Override
    public @NotNull
    Result<UhfTag> gen2Read(byte[] resp) throws RespException {
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

    @Override
    public @NotNull byte[] gen2Write(byte address, byte memBank, byte addr, byte[] value) {
        // 0x0A address 0x06 0x86 memBank addr value(2byte) cc
        byte[] cmd = new byte[]{
                REQ_HEAD,
                address,
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

    @Override
    public @NotNull
    Result<Void> gen2Write(byte[] resp) throws RespException {
        return obtionNonDataResp(resp);
    }

    @Override
    public @NotNull byte[] getIDAndDelete(byte address, byte tagCount) {
        // 0x0A address 0x03 0x40 tagCount cc
        byte[] cmd = new byte[]{
                REQ_HEAD,
                address,
                (byte) 0x03, // len
                (byte) 0x40, // cmd
                tagCount,
                (byte) 0x00, // cc
        };
        cmd[cmd.length - 1] = checkCode(cmd);
        return cmd;
    }

    @Override
    public @NotNull
    Result<UhfTag[]> getIDAndDelete(byte[] resp) throws RespException {
        // 0x0B address 14*n+3 0x00 count data(14*n) cc
        checkBasicResp(resp);
        int n = (resp[2] - 3) / 14;
        UhfTag[] uhfTags = new UhfTag[resp[4]];
        byte[] data = new byte[14 * n];
        System.arraycopy(resp, 5, data, 0, data.length);
        for (int i = 0; i < uhfTags.length; i++) {
            byte[] bytes = new byte[14];
            System.arraycopy(data, i * 14, bytes, 0, 14);
            uhfTags[i] = UhfTag.of14bytes(bytes);
        }
        return new Result<>(resp[3], resp[1], uhfTags);
    }

    @Override
    public @NotNull byte[] getID(byte address) {
        // 0x0A address 0x02 0x41 cc
        byte[] cmd = new byte[]{
                REQ_HEAD,
                address,
                (byte) 0x02, // len
                (byte) 0x41, // cmd
                (byte) 0x00, // cc
        };
        cmd[cmd.length - 1] = checkCode(cmd);
        return cmd;
    }

    @SuppressWarnings("Duplicates")
    @Override
    public @NotNull
    Result<UhfTag> getID(byte[] resp) throws RespException {
        // 0x0B address 17 0x00 data(14) cc
        checkBasicResp(resp);
        byte[] bytes = new byte[14];
        System.arraycopy(resp, 0, bytes, 0, 14);
        UhfTag uhfTag = UhfTag.of14bytes(bytes);
        return new Result<>(resp[3], resp[1], uhfTag);
    }

    @Override
    public @NotNull byte[] getIDACK(byte address) {
        // 0x0A address 0x02 0x42 cc
        byte[] cmd = new byte[]{
                REQ_HEAD,
                address,
                0x02, // len
                (byte) 0x42, // cmd
                0x00, // checkCode
        };
        cmd[cmd.length - 1] = checkCode(cmd);
        return cmd;
    }

    @Override
    public @NotNull
    Result getIDACK(byte[] resp) throws RespException {
        // 尝试Obtion无数据应答帧
        try {
            return obtionNonDataResp(resp);
        } catch (RespException e) {
            // 失败
            return getID(resp);
        }
    }

    @Override
    public @NotNull byte[] queryIDCount(byte address) {
        // 0x0A address 0x02 0x43 cc
        byte[] cmd = new byte[]{
                REQ_HEAD,
                address,
                (byte) 0x02, // len
                (byte) 0x43, // cmd
                (byte) 0x00, // checkCode
        };
        cmd[cmd.length - 1] = checkCode(cmd);
        return cmd;
    }

    @Override
    public @NotNull
    Result<Byte> queryIDCount(byte[] resp) throws RespException {
        // 0x0B	address	0x03 0x00 count cc
        checkBasicResp(resp);
        return new Result<>(resp[3], resp[1], resp[4]);
    }

    @Override
    public @NotNull byte[] clearIDBuffer(byte address) {
        // 0x0A address 0x02 0x44 cc
        byte[] cmd = new byte[]{
                REQ_HEAD,
                address,
                (byte) 0x02, // len
                (byte) 0x44, // cmd
                (byte) 0x00, // cc
        };
        cmd[cmd.length - 1] = checkCode(cmd);
        return cmd;
    }

    @Override
    public @NotNull
    Result<Void> clearIDBuffer(byte[] resp) throws RespException {
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

    private static byte checkCode(byte[] bytes) {
        int sum = 0;
        int size = bytes.length;
        for (int i = 0; i < size - 1; i++) {
            sum += bytes[i];
        }
        return (byte) (0xFF & (~sum + 1));
    }
}
