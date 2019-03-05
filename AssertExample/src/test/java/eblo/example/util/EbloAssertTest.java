package eblo.example.util;

import org.junit.Test;

import eblo.common.exception.EbloInvalidRequestException;

public class EbloAssertTest {

    @Test
    public void test() {
        EbloAssert.isTrue(1>0, "test", EbloInvalidRequestException.class);
        EbloAssert.isNumeric("1234567", "숫자만");
        EbloAssert.isFloat("-12345.67", "FLOAT DOUBLE 형태");
        EbloAssert.isFloat("-12345.0", "FLOAT DOUBLE 형태");
        EbloAssert.isMatched("-12345.0", "^[\\+\\-]{0,1}[\\d]+[\\.][0-9]+$", "FLOAT DOUBLE 형태");
    }

}
