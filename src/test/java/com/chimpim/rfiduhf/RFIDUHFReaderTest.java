package com.chimpim.rfiduhf;

import com.chimpim.rfiduhf.data.Result;
import com.chimpim.rfiduhf.data.UHFTag;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.logging.Logger;

public class RFIDUHFReaderTest {
    private static final Logger logger = Logger.getLogger("RfidUhfReaderApiTest3");

    private static final String port = "/dev/ttyUSB0";

    private static final byte[] UID = new byte[]{-32, 4, 0, 0, -64, -13, 100, 6};

    private RFIDUHFReader mRFIDUHFReader;

    @Before
    public void setUp() throws Exception {
        RFIDUHFReaderConnAdapter adapter = new RFIDUHFReaderConnAdapterImpl(port);
        mRFIDUHFReader = RFIDUHFReaderFactory.createRFIDUHFReader(adapter);
        mRFIDUHFReader.connect();
    }

    @After
    public void tearDown() throws Exception {
        if (mRFIDUHFReader != null) {
            mRFIDUHFReader.disconnect();
        }
    }

    @Test
    public void connect() throws Exception {
        // see setUp
    }

    @Test
    public void hasConnected() throws Exception {
        boolean connected = mRFIDUHFReader.hasConnected();
        logger.info("connected = " + connected);
    }

    @Test
    public void disconnect() throws Exception {
        // see tearDown
    }

    @Test
    public void setBaudRate() throws Exception {
        logger.info(mRFIDUHFReader.setBaudRate(RFIDUHFConstants.BAUD_RATE_9600).toString());
    }

    @Test
    public void resetReader() throws Exception {
        logger.info(mRFIDUHFReader.resetReader().toString());
    }

    @Test
    public void getFirmwareVersion() throws Exception {
        logger.info(mRFIDUHFReader.getFirmwareVersion().toString());
    }

    @Test
    public void setRf() throws Exception {
        logger.info(mRFIDUHFReader.setRf(RFIDUHFConstants.POWER_30, RFIDUHFConstants.FREQ_AMERICA).toString());

    }

    @Test
    public void getRf() throws Exception {
        logger.info(mRFIDUHFReader.getRf().toString());
    }

    @Test
    public void setWorkAntenna() throws Exception {
        logger.info(mRFIDUHFReader.setWorkAntenna((byte) -1).toString());
    }

    @Test
    public void getWorkAntenna() throws Exception {
        logger.info(mRFIDUHFReader.getWorkAntenna().toString());
    }

    @Test
    public void setWorkMode() throws Exception {
        logger.info(mRFIDUHFReader.setWorkMode(RFIDUHFConstants.WORK_MODE_COMMAND).toString());
        // 重置读卡器设置生效
        logger.info(mRFIDUHFReader.resetReader().toString());
    }

    @Test
    public void getWorkMode() throws Exception {
        logger.info(mRFIDUHFReader.getWorkMode().toString());
    }

    @Test
    public void setAddr() throws Exception {
        logger.info(mRFIDUHFReader.setAddr((byte) 0xF1).toString());
        // 重置读卡器设置生效
        logger.info(mRFIDUHFReader.resetReader().toString());
    }

    @Test
    public void getAddr() throws Exception {
        logger.info(mRFIDUHFReader.getAddr().toString());
    }


    @Test
    public void multiTagIdentify6b() throws Exception {
        // 清空缓存区
        clearIdBuffer();
        // 多标签识别
        logger.info(mRFIDUHFReader.multiTagIdentify6b().toString());
        // 查询缓存区标签数量
        Result<Byte> result = mRFIDUHFReader.queryIdCount();
        logger.info(result.toString());
        // 从缓存区中取出标签
        Result<UHFTag[]> result1 = mRFIDUHFReader.getIdAndDelete(result.getPayload());
        logger.info(result1.toString());
        logger.info(Arrays.toString(result1.getPayload()));
    }

    @Test
    public void multiTagRead6b() throws Exception {
        // 清空缓存区
        clearIdBuffer();
        // 多标签识别
        logger.info(mRFIDUHFReader.multiTagRead6b((byte) 0).toString());
        // 查询缓存区标签数量
        Result<Byte> result = mRFIDUHFReader.queryIdCount();
        logger.info(result.toString());
        // 从缓存区中取出标签
        Result<UHFTag[]> result1 = mRFIDUHFReader.getIdAndDelete(result.getPayload());
        logger.info(result1.toString());
        logger.info(Arrays.toString(result1.getPayload()));
    }

    @Test
    public void write6b() throws Exception {
        logger.info(mRFIDUHFReader.write6b((byte) 0, (byte) 0xFF).toString());
    }

    @Test
    public void readWithUid6b() throws Exception {
        logger.info(mRFIDUHFReader.readWithUid6b(UID, (byte) 0).toString());
    }

    @Test
    public void writeWithUid6b() throws Exception {
        logger.info(mRFIDUHFReader.writeWithUid6b(UID, (byte) 0, (byte) 0xFF).toString());
    }

    @Test
    public void lock6b() throws Exception {
        logger.info(mRFIDUHFReader.lock6b((byte) 0).toString());

    }

    @Test
    public void queryLock6b() throws Exception {
        logger.info(mRFIDUHFReader.queryLock6b((byte) 0).toString());

    }

    @Test
    public void blockWrite6b() throws Exception {
        byte[] value = new byte[4];
        logger.info(mRFIDUHFReader.blockWrite6b((byte) 0, value).toString());
    }


    @Test
    public void singleTagRead6b() throws Exception {
        logger.info(mRFIDUHFReader.singleTagRead6b((byte) 0).toString());

    }

    @Test
    public void multiTagIdentify6c() throws Exception {
        // 清空缓存区
        clearIdBuffer();
        // 多标签识别
        logger.info(mRFIDUHFReader.multiTagIdentify6c().toString());
        // 查询缓存区标签数量
        Result<Byte> result = mRFIDUHFReader.queryIdCount();
        logger.info(result.toString());
        // 从缓存区中取出标签
        Result<UHFTag[]> result1 = mRFIDUHFReader.getIdAndDelete(result.getPayload());
        logger.info(result1.toString());
        logger.info(Arrays.toString(result1.getPayload()));
    }

    @Test
    public void epcWrite6c() throws Exception {
        byte[] value = new byte[2];
        logger.info(mRFIDUHFReader.epcWrite6c((byte) 0, value).toString());
    }

    @Test
    public void lock6c() throws Exception {
        logger.info(mRFIDUHFReader.lock6c(RFIDUHFConstants.MEM_BANK_EPC, RFIDUHFConstants.CONTROL_NOT_LOCK).toString());
    }

    @Test
    public void kill6c() throws Exception {
        byte[] password = new byte[4];
        logger.info(mRFIDUHFReader.kill6c(password).toString());
    }

    @Test
    public void init6c() throws Exception {
        logger.info(mRFIDUHFReader.init6c((byte) 96).toString());
    }

    @Test
    public void read6c() throws Exception {
        logger.info(mRFIDUHFReader.read6c(RFIDUHFConstants.MEM_BANK_EPC, (byte) 2, (byte) 6).toString());
    }

    @Test
    public void write6c() throws Exception {
        byte[] value = new byte[]{0x01, 0x01};
        logger.info(mRFIDUHFReader.write6c(RFIDUHFConstants.MEM_BANK_EPC, (byte) 4, value).toString());
    }

    @Test
    public void getIdAndDelete() throws Exception {
        logger.info(mRFIDUHFReader.getIdAndDelete((byte) 1).toString());
    }

    @Test
    public void getId() throws Exception {
        logger.info(mRFIDUHFReader.getId().toString());
    }

    @Test
    public void getIdAck() throws Exception {
        logger.info(mRFIDUHFReader.getIdAck().toString());
    }

    @Test
    public void queryIdCount() throws Exception {
        logger.info(mRFIDUHFReader.queryIdCount().toString());
    }

    @Test
    public void clearIdBuffer() throws Exception {
        logger.info(mRFIDUHFReader.clearIdBuffer().toString());
    }

    @Test
    public void fastBatchBlockWrite6b() throws Exception {
        byte[] values = new byte[]{
                (byte) 0x01, (byte) 0x02, (byte) 0x03, (byte) 0x04
        };
        boolean result = mRFIDUHFReader.fastBatchBlockWrite6b((byte) 16, values);
        logger.info("result = " + result);
        UHFTag[] uhfTags = mRFIDUHFReader.fastMultiTagRead6b((byte) 16);
        logger.info("uhfTags = " + Arrays.toString(uhfTags));
    }

    @Test
    public void fastBatchWrite6b() throws Exception {
        byte[] values = new byte[]{
                (byte) 0x01, (byte) 0x02, (byte) 0x03, (byte) 0x04,
                (byte) 0x05, (byte) 0x06, (byte) 0x07, (byte) 0x08,
        };
        boolean result = mRFIDUHFReader.fastBatchWrite6b((byte) 16, values);
        logger.info("result = " + result);
        UHFTag[] uhfTags = mRFIDUHFReader.fastMultiTagRead6b((byte) 16);
        logger.info("uhfTags = " + Arrays.toString(uhfTags));
    }

    @Test
    public void fastMultiTagRead6b() throws Exception {
        // 7820A41335335330090A0B0C
        UHFTag[] uhfTags = mRFIDUHFReader.fastMultiTagRead6b((byte) 16);
        logger.info(Arrays.toString(uhfTags));
    }

    @Test
    public void fastMultiTagIdentify6b() throws Exception {
        UHFTag[] uhfTags = mRFIDUHFReader.fastMultiTagIdentify6b();
        logger.info(Arrays.toString(uhfTags));
    }

    @Test
    public void fastBatchWrite6c() throws Exception {
        byte[] epc = new byte[]{
                (byte) 0x01, (byte) 0x02, (byte) 0x03, (byte) 0x04,
                (byte) 0x05, (byte) 0x06, (byte) 0x07, (byte) 0x08,
                (byte) 0x09, (byte) 0x0A, (byte) 0x0B, (byte) 0x0C,
        };
        boolean result = mRFIDUHFReader.fastBatchWrite6c(RFIDUHFConstants.MEM_BANK_EPC, (byte) 2, epc);
        logger.info("result = " + result);
        Result<UHFTag> uhfTagResult = mRFIDUHFReader.read6c(RFIDUHFConstants.MEM_BANK_EPC, (byte) 2, (byte) 6);
        logger.info("result = " + uhfTagResult);
        UHFTag[] uhfTags = mRFIDUHFReader.fastMultiTagIdentify6c();
        logger.info(Arrays.toString(uhfTags));
    }

    @Test
    public void fastMultiTagIdentify6c() throws Exception {
        UHFTag[] uhfTags = mRFIDUHFReader.fastMultiTagIdentify6c();
        logger.info(Arrays.toString(uhfTags));
    }
}