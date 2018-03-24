package com.chimpim.rfiduhf.util;

import com.chimpim.rfiduhf.DefaultRfidUhfReaderConnAdapter;
import com.chimpim.rfiduhf.RfidUhfConstant;
import com.chimpim.rfiduhf.RfidUhfReaderOperator;
import com.chimpim.rfiduhf.data.Result;
import com.chimpim.rfiduhf.data.UhfTag;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.logging.Logger;

public class FastUtilTest {
    private static final Logger logger = Logger.getLogger("FastUtilTest");

    private static final String port = "COM3";
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
    public void isoMultiTagIdentify() throws Exception {
        UhfTag[] uhfTags = FastUtil.isoMultiTagIdentify(mOperator);
        logger.info(Arrays.toString(uhfTags));
    }

    @Test
    public void batchGen2Write() throws Exception {
        byte[] epc = new byte[]{
                (byte) 0x01, (byte) 0x02, (byte) 0x03, (byte) 0x04,
                (byte) 0x05, (byte) 0x06, (byte) 0x07, (byte) 0x08,
                (byte) 0x09, (byte) 0x0A, (byte) 0x0B, (byte) 0x0C,
        };
        boolean result = FastUtil.batchGen2Write(mOperator, RfidUhfConstant.MEM_BANK_EPC, (byte) 2, epc);
        logger.info("result = " + result);
        Result<UhfTag> uhfTagResult = mOperator.gen2Read(RfidUhfConstant.MEM_BANK_EPC, (byte) 2, (byte) 6);
        logger.info("result = " + uhfTagResult);

    }

    @Test
    public void gen2MultiTagIdentify() throws Exception {
        UhfTag[] uhfTags = FastUtil.gen2MultiTagIdentify(mOperator);
        logger.info(Arrays.toString(uhfTags));
    }

}