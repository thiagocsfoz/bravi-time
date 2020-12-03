package com.bravi.time;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest
class TimeApplicationTests {

    @Test
    void verifyFormatMustPass()
    {
        ConvertTime convertTime = new ConvertTime();
        try {
            String textTime = convertTime.convert("05:00");
        } catch (Exception e) {
            Assertions.fail();
        }
    }

    @Test
    void verifyFormatMustFail_length_less()
    {
        ConvertTime convertTime = new ConvertTime();

        Exception exception = Assertions.assertThrows(Exception.class, () -> {
            String textTime = convertTime.convert("05:");
        });

        String expectedMessage = "invalid format";
        String actualMessage = exception.getMessage();

        Assertions.assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void verifyFormatMustFailLengthBigger()
    {
        ConvertTime convertTime = new ConvertTime();

        Exception exception = Assertions.assertThrows(Exception.class, () -> {
            String textTime = convertTime.convert("05:000");
        });

        String expectedMessage = "invalid format";
        String actualMessage = exception.getMessage();

        Assertions.assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void verifyFormatMustFailHourLessOne()
    {
        ConvertTime convertTime = new ConvertTime();

        Exception exception = Assertions.assertThrows(Exception.class, () -> {
            String textTime = convertTime.convert("00:40");
        });

        String expectedMessage = "invalid format";
        String actualMessage = exception.getMessage();

        Assertions.assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void verifyFormatMustFailMinuteBiggerSixty()
    {
        ConvertTime convertTime = new ConvertTime();

        Exception exception = Assertions.assertThrows(Exception.class, () -> {
            String textTime = convertTime.convert("00:66");
        });

        String expectedMessage = "invalid format";
        String actualMessage = exception.getMessage();

        Assertions.assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void verifyFormatMustFailHourBiggerTwelve()
    {
        ConvertTime convertTime = new ConvertTime();

        Exception exception = Assertions.assertThrows(Exception.class, () -> {
            String textTime = convertTime.convert("13:40");
        });

        String expectedMessage = "invalid format";
        String actualMessage = exception.getMessage();

        Assertions.assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void verifyFormatMustFailInvalidNumber()
    {
        ConvertTime convertTime = new ConvertTime();

        Exception exception = Assertions.assertThrows(Exception.class, () -> {
            String textTime = convertTime.convert("1x:4z");
        });

        String expectedMessage = "invalid format";
        String actualMessage = exception.getMessage();

        Assertions.assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void convertTimeOclockMustPass()
    {
        ConvertTime convertTime = new ConvertTime();
        try {
            Assertions.assertTrue("five o'clock".contains(convertTime.convert("05:00")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void convertTimeOQuarterMustPass()
    {
        ConvertTime convertTime = new ConvertTime();
        try {
            Assertions.assertEquals("quarter past five",convertTime.convert("05:15"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void convertTimePastMustPass()
    {
        ConvertTime convertTime = new ConvertTime();
        try {
            Assertions.assertEquals("twenty five minutes past eleven",convertTime.convert("11:25"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void convertTimeToMustPass()
    {
        ConvertTime convertTime = new ConvertTime();
        try {
            Assertions.assertEquals("fifty nine minutes to eleven",convertTime.convert("11:59"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
