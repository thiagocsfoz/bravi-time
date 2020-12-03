package com.bravi.time;

public class ConvertTime
{
    private final String[] tensNames = {
            "",
            "ten",
            "twenty",
            "thirty",
            "forty",
            "fifty",
            "sixty",
            "seventy",
            "eighty",
            "ninety"
    };

    private final String[] numNames = {
            "",
            "one",
            "two",
            "three",
            "four",
            "five",
            "six",
            "seven",
            "eight",
            "nine",
            "ten",
            "eleven",
            "twelve",
            "thirteen",
            "fourteen",
            "fifteen",
            "sixteen",
            "seventeen",
            "eighteen",
            "nineteen"
    };

    private int hour;
    private int minute;

    public String convert( String time ) throws Exception
    {
        this.verifyFormatHour(time);

        return this.getTimeInText();
    }

    private String getTimeInText()
    {
        String text = "";

        String hourText = this.numNames[this.hour];
        String minuteText = "";
        if( this.minute == 15  || this.minute == 45)
        {
            minuteText = "quarter";
        }
        else if( this.minute < 20 )
        {
            minuteText = this.numNames[this.minute];
            minuteText += this.minute == 1 ? " minute" : " minutes";
        }
        else
        {
            minuteText = this.tensNames[(this.minute/10) % 100];
            minuteText += this.minute % 10 > 0 ? " " + this.numNames[this.minute % 10] + " minutes" : "";
        }

        if( this.minute == 0 )
        {
            text = hourText + " o'clock";
        }
        else if( this.minute <= 30 )
        {
            text = minuteText + " past " + hourText;
        }
        else
        {
            text = minuteText + " to " + hourText;
        }

        return text;
    }

    private void verifyFormatHour( String time ) throws Exception
    {
        if(time.length() != 5)
            throw new Exception("invalid format");

        String[] hours = time.split(":");

        if(hours.length != 2)
            throw new Exception("invalid format");

        this.getIntHourFromText(hours);
    }

    private void getIntHourFromText(String[] hours) throws Exception
    {
        try
        {
            this.hour = Integer.parseInt(hours[0]);
            this.minute = Integer.parseInt(hours[1]);

            if(hour < 1 || hour > 12)
                throw new Exception("invalid format");

            if(minute < 0 || minute > 60)
                throw new Exception("invalid format");
        }
        catch (NumberFormatException e)
        {
            throw new Exception("invalid format");
        }
    }
}
