package com.chimpim.rfiduhf;

import com.chimpim.rfiduhf.data.Result;
import com.chimpim.rfiduhf.data.UhfTag;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.logging.Logger;

public class RfidUhfReaderOperatorTest {
    private static final Logger logger = Logger.getLogger("RfidUhfReaderOperatorTest");

    private static final String port = "COM3";
    private static final byte[] UID = new byte[]{-32, 4, 0, 0, -64, -13, 100, 6};
    private RfidUhfReaderOperator mOperator;

    @Before
    public void setup() throws Exception {
        DefaultRfidUhfReaderConnAdapter adapter = new DefaultRfidUhfReaderConnAdapter(port);
        mOperator = RfidUhfReaderOperator.of(adapter, RfidUhfConstant.ADDRESS_COMMUNAL);
        mOperator.connect();
    }

    @After
    public void shutdown() {
        if (mOperator != null) {
            mOperator.disconnect();
        }
    }


    @Test
    public void connect() throws Exception {
        // see setup
    }

    @Test
    public void hasConnected() throws Exception {
        boolean connected = mOperator.hasConnected();
        logger.info("connected = " + connected);
    }

    @Test
    public void disconnect() throws Exception {
        // set shutdown
    }

    @Test
    public void setBaudRate() throws Exception {
        logger.info(mOperator.setBaudRate(RfidUhfConstant.BAUD_RATE_9600).toString());
    }

    @Test
    public void resetReader() throws Exception {
        logger.info(mOperator.resetReader().toString());
    }

    @Test
    public void getFirmwareVersion() throws Exception {
        logger.info(mOperator.getFirmwareVersion().toString());
    }

    @Test
    public void setRF() throws Exception {
        logger.info(mOperator.setRF((byte) 10, (byte) 1).toString());
    }

    @Test
    public void getRF() throws Exception {
        logger.info(mOperator.getRF().toString());
    }

    @Test
    public void setWorkAntenna() throws Exception {
        logger.info(mOperator.setWorkAntenna((byte) -1).toString());
    }

    @Test
    public void getWorkAntenna() throws Exception {
        logger.info(mOperator.getWorkAntenna().toString());
    }

    @Test
    public void isoMultiTagIdentify() throws Exception {
        // 清空缓存区
        clearIDBuffer();
        // 多标签识别
        logger.info(mOperator.isoMultiTagIdentify().toString());
        // 查询缓存区标签数量
        Result<Byte> result = mOperator.queryIDCount();
        logger.info(result.toString());
        // 从缓存区中取出标签
        Result<UhfTag[]> result1 = mOperator.getIDAndDelete(result.getPayload());
        logger.info(result1.toString());
        logger.info(Arrays.toString(result1.getPayload()));
    }

    @Test
    public void isoMultiTagRead() throws Exception {
        logger.info(mOperator.isoMultiTagRead((byte) 0).toString());
    }

    @Test
    public void isoWrite() throws Exception {
        logger.info(mOperator.isoWrite((byte) 0, (byte) 0xFF).toString());
    }

    @Test
    public void isoReadWithUID() throws Exception {
        logger.info(mOperator.isoReadWithUID(UID, (byte) 0).toString());
    }

    @Test
    public void isoWriteWithUID() throws Exception {
        logger.info(mOperator.isoWriteWithUID(UID, (byte) 0, (byte) 0xFF).toString());
    }

    @Test
    public void isoLock() throws Exception {
        logger.info(mOperator.isoLock((byte) 0).toString());
    }

    @Test
    public void isoQueryLock() throws Exception {
        logger.info(mOperator.isoQueryLock((byte) 0).toString());
    }

    @Test
    public void isoBlockWrite() throws Exception {
        byte[] value = new byte[4];
        logger.info(mOperator.isoBlockWrite((byte) 0, value).toString());
    }

    @Test
    public void isoSingleTagRead() throws Exception {
        logger.info(mOperator.isoSingleTagRead((byte) 0).toString());
    }

    @Test
    public void gen2MultiTagIdentify() throws Exception {
        // 清空缓存区
        clearIDBuffer();
        // 多标签识别
        logger.info(mOperator.gen2MultiTagIdentify().toString());
        // 查询缓存区标签数量
        Result<Byte> result = mOperator.queryIDCount();
        logger.info(result.toString());
        // 从缓存区中取出标签
        Result<UhfTag[]> result1 = mOperator.getIDAndDelete(result.getPayload());
        logger.info(result1.toString());
        logger.info(Arrays.toString(result1.getPayload()));
    }

    @Test
    public void gen2EPCWrite() throws Exception {
        byte[] value = new byte[2];
        logger.info(mOperator.gen2EPCWrite((byte) 0, value).toString());
    }

    @Test
    public void gen2Lock() throws Exception {
        logger.info(mOperator.gen2Lock(RfidUhfConstant.MEM_BANK_EPC, RfidUhfConstant.CONTROL_NOT_LOCK).toString());
    }

    @Test
    public void gen2Kill() throws Exception {
        byte[] password = new byte[4];
        logger.info(mOperator.gen2Kill(password).toString());
    }

    @Test
    public void gen2Init() throws Exception {
        logger.info(mOperator.gen2Init((byte) 96).toString());
    }

    @Test
    public void gen2Read() throws Exception {
        logger.info(mOperator.gen2Read(RfidUhfConstant.MEM_BANK_EPC, (byte) 2, (byte) 6).toString());
    }

    @Test
    public void gen2Write() throws Exception {
        byte[] value = new byte[]{0x01, 0x01};
        logger.info(mOperator.gen2Write(RfidUhfConstant.MEM_BANK_EPC, (byte) 4, value).toString());
    }

    @Test
    public void getIDAndDelete() throws Exception {
        logger.info(mOperator.getIDAndDelete((byte) 1).toString());
    }

    @Test
    public void getID() throws Exception {
        logger.info(mOperator.getID().toString());
    }

    @Test
    public void getIDACK() throws Exception {
        logger.info(mOperator.getIDACK().toString());
    }

    @Test
    public void queryIDCount() throws Exception {
        logger.info(mOperator.queryIDCount().toString());
    }

    @Test
    public void clearIDBuffer() throws Exception {
        logger.info(mOperator.clearIDBuffer().toString());
    }

}