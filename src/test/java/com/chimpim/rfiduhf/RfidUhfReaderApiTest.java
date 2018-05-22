package com.chimpim.rfiduhf;

import com.chimpim.rfiduhf.data.Result;
import com.chimpim.rfiduhf.data.UhfTag;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.logging.Logger;

public class RfidUhfReaderApiTest {
    private static final Logger logger = Logger.getLogger("RfidUhfReaderApiTest");

    private static final String port = "COM13";
    private static final byte[] UID = new byte[]{-32, 4, 0, 0, -64, -13, 100, 6};
    private RfidUhfReaderApi mRfidUhfReaderApi;

    @Before
    public void setUp() throws Exception {
        DefaultRfidUhfReaderConnAdapter adapter = new DefaultRfidUhfReaderConnAdapter(port);
        mRfidUhfReaderApi = RfidUhfReaderApiFactory.create(adapter);
        mRfidUhfReaderApi.connect();
    }

    @After
    public void tearDown() throws Exception {
        if (mRfidUhfReaderApi != null) {
            mRfidUhfReaderApi.disconnect();
        }
    }

    @Test
    public void connect() throws Exception {
        // see setup
    }

    @Test
    public void hasConnected() throws Exception {
        boolean connected = mRfidUhfReaderApi.hasConnected();
        logger.info("connected = " + connected);
    }

    @Test
    public void disconnect() throws Exception {
        // see shutdown
    }

    @Test
    public void setBaudRate() throws Exception {
        logger.info(mRfidUhfReaderApi.setBaudRate(RfidUhfConstant.BAUD_RATE_9600).toString());
    }

    @Test
    public void resetReader() throws Exception {
        logger.info(mRfidUhfReaderApi.resetReader().toString());
    }

    @Test
    public void getFirmwareVersion() throws Exception {
        logger.info(mRfidUhfReaderApi.getFirmwareVersion().toString());
    }

    @Test
    public void setRF() throws Exception {
        logger.info(mRfidUhfReaderApi.setRF((byte) 10, (byte) 1).toString());
    }

    @Test
    public void getRF() throws Exception {
        logger.info(mRfidUhfReaderApi.getRF().toString());
    }

    @Test
    public void setWorkAntenna() throws Exception {
        logger.info(mRfidUhfReaderApi.setWorkAntenna((byte) -1).toString());
    }

    @Test
    public void getWorkAntenna() throws Exception {
        logger.info(mRfidUhfReaderApi.getWorkAntenna().toString());
    }

    @Test
    public void isoMultiTagIdentify() throws Exception {
        // 清空缓存区
        clearIDBuffer();
        // 多标签识别
        logger.info(mRfidUhfReaderApi.isoMultiTagIdentify().toString());
        // 查询缓存区标签数量
        Result<Byte> result = mRfidUhfReaderApi.queryIDCount();
        logger.info(result.toString());
        // 从缓存区中取出标签
        Result<UhfTag[]> result1 = mRfidUhfReaderApi.getIDAndDelete(result.getPayload());
        logger.info(result1.toString());
        logger.info(Arrays.toString(result1.getPayload()));
    }

    @Test
    public void isoMultiTagRead() throws Exception {
        // 清空缓存区
        clearIDBuffer();
        // 多标签识别
        logger.info(mRfidUhfReaderApi.isoMultiTagRead((byte) 0).toString());
        // 查询缓存区标签数量
        Result<Byte> result = mRfidUhfReaderApi.queryIDCount();
        logger.info(result.toString());
        // 从缓存区中取出标签
        Result<UhfTag[]> result1 = mRfidUhfReaderApi.getIDAndDelete(result.getPayload());
        logger.info(result1.toString());
        logger.info(Arrays.toString(result1.getPayload()));
    }

    @Test
    public void isoWrite() throws Exception {
        logger.info(mRfidUhfReaderApi.isoWrite((byte) 0, (byte) 0xFF).toString());
    }

    @Test
    public void isoReadWithUID() throws Exception {
        logger.info(mRfidUhfReaderApi.isoReadWithUID(UID, (byte) 0).toString());
    }

    @Test
    public void isoWriteWithUID() throws Exception {
        logger.info(mRfidUhfReaderApi.isoWriteWithUID(UID, (byte) 0, (byte) 0xFF).toString());
    }

    @Test
    public void isoLock() throws Exception {
        logger.info(mRfidUhfReaderApi.isoLock((byte) 0).toString());
    }

    @Test
    public void isoQueryLock() throws Exception {
        logger.info(mRfidUhfReaderApi.isoQueryLock((byte) 0).toString());
    }

    @Test
    public void isoBlockWrite() throws Exception {
        byte[] value = new byte[4];
        logger.info(mRfidUhfReaderApi.isoBlockWrite((byte) 0, value).toString());
    }

    @Test
    public void isoSingleTagRead() throws Exception {
        logger.info(mRfidUhfReaderApi.isoSingleTagRead((byte) 0).toString());
    }

    @Test
    public void gen2MultiTagIdentify() throws Exception {
        // 清空缓存区
        clearIDBuffer();
        // 多标签识别
        logger.info(mRfidUhfReaderApi.gen2MultiTagIdentify().toString());
        // 查询缓存区标签数量
        Result<Byte> result = mRfidUhfReaderApi.queryIDCount();
        logger.info(result.toString());
        // 从缓存区中取出标签
        Result<UhfTag[]> result1 = mRfidUhfReaderApi.getIDAndDelete(result.getPayload());
        logger.info(result1.toString());
        logger.info(Arrays.toString(result1.getPayload()));
    }

    @Test
    public void gen2EPCWrite() throws Exception {
        byte[] value = new byte[2];
        logger.info(mRfidUhfReaderApi.gen2EPCWrite((byte) 0, value).toString());
    }

    @Test
    public void gen2Lock() throws Exception {
        logger.info(mRfidUhfReaderApi.gen2Lock(RfidUhfConstant.MEM_BANK_EPC, RfidUhfConstant.CONTROL_NOT_LOCK).toString());
    }

    @Test
    public void gen2Kill() throws Exception {
        byte[] password = new byte[4];
        logger.info(mRfidUhfReaderApi.gen2Kill(password).toString());
    }

    @Test
    public void gen2Init() throws Exception {
        logger.info(mRfidUhfReaderApi.gen2Init((byte) 96).toString());
    }

    @Test
    public void gen2Read() throws Exception {
        logger.info(mRfidUhfReaderApi.gen2Read(RfidUhfConstant.MEM_BANK_EPC, (byte) 2, (byte) 6).toString());
    }

    @Test
    public void gen2Write() throws Exception {
        byte[] value = new byte[]{0x01, 0x01};
        logger.info(mRfidUhfReaderApi.gen2Write(RfidUhfConstant.MEM_BANK_EPC, (byte) 4, value).toString());
    }

    @Test
    public void getIDAndDelete() throws Exception {
        logger.info(mRfidUhfReaderApi.getIDAndDelete((byte) 1).toString());
    }

    @Test
    public void getID() throws Exception {
        logger.info(mRfidUhfReaderApi.getID().toString());
    }

    @Test
    public void getIDACK() throws Exception {
        logger.info(mRfidUhfReaderApi.getIDACK().toString());
    }

    @Test
    public void queryIDCount() throws Exception {
        logger.info(mRfidUhfReaderApi.queryIDCount().toString());
    }

    @Test
    public void clearIDBuffer() throws Exception {
        logger.info(mRfidUhfReaderApi.clearIDBuffer().toString());
    }

    @Test
    public void fastIsoMultiTagIdentify() throws Exception {
        UhfTag[] uhfTags = mRfidUhfReaderApi.fastIsoMultiTagIdentify();
        logger.info(Arrays.toString(uhfTags));
    }

    @Test
    public void fastIsoMultiTagRead() throws Exception {
        // 7820A41335335330090A0B0C
        UhfTag[] uhfTags = mRfidUhfReaderApi.fastIsoMultiTagRead((byte) 16);
        logger.info(Arrays.toString(uhfTags));
    }

    @Test
    public void fastBatchIsoWrite() throws Exception {
        byte[] values = new byte[]{
                (byte) 0x01, (byte) 0x02, (byte) 0x03, (byte) 0x04,
                (byte) 0x05, (byte) 0x06, (byte) 0x07, (byte) 0x08,
        };
        boolean result = mRfidUhfReaderApi.fastBatchIsoWrite((byte) 16, values);
        logger.info("result = " + result);
        UhfTag[] uhfTags = mRfidUhfReaderApi.fastIsoMultiTagRead((byte) 16);
        logger.info("uhfTags = " + Arrays.toString(uhfTags));

    }

    @Test
    public void fastBatchIsoBlockWrite() throws Exception {
        byte[] values = new byte[]{
                (byte) 0x01, (byte) 0x02, (byte) 0x03, (byte) 0x04
        };
        boolean result = mRfidUhfReaderApi.fastBatchIsoBlockWrite((byte) 16, values);
        logger.info("result = " + result);
        UhfTag[] uhfTags = mRfidUhfReaderApi.fastIsoMultiTagRead((byte) 16);
        logger.info("uhfTags = " + Arrays.toString(uhfTags));
    }

    @Test
    public void fastGen2MultiTagIdentify() throws Exception {
        UhfTag[] uhfTags = mRfidUhfReaderApi.fastGen2MultiTagIdentify();
        logger.info(Arrays.toString(uhfTags));
    }

    @Test
    public void fastBatchGen2Write() throws Exception {
        byte[] epc = new byte[]{
                (byte) 0x01, (byte) 0x02, (byte) 0x03, (byte) 0x04,
                (byte) 0x05, (byte) 0x06, (byte) 0x07, (byte) 0x08,
                (byte) 0x09, (byte) 0x0A, (byte) 0x0B, (byte) 0x0C,
        };
        boolean result = mRfidUhfReaderApi.fastBatchGen2Write(RfidUhfConstant.MEM_BANK_EPC, (byte) 2, epc);
        logger.info("result = " + result);
        Result<UhfTag> uhfTagResult = mRfidUhfReaderApi.gen2Read(RfidUhfConstant.MEM_BANK_EPC, (byte) 2, (byte) 6);
        logger.info("result = " + uhfTagResult);
        UhfTag[] uhfTags = mRfidUhfReaderApi.fastGen2MultiTagIdentify();
        logger.info(Arrays.toString(uhfTags));
    }
}