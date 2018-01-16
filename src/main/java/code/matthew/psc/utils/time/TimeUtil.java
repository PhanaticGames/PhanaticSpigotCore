package code.matthew.psc.utils.time;

import java.util.Date;

/**
 * A utility class to handle common time stuff!
 */
public class TimeUtil {

    /**
     * Turn a user's input data to a real java date
     *
     * @param time     The time of the date. For ex 1
     * @param timeUnit The unit of time measurement. Note: this is not j standard. m, h, d ,M ,y
     * @return A date repersentation of the date with the time added
     */
    public static Date rawTimeToDate(String time, String timeUnit) {

        Date date = new Date();
        long end;

        switch (timeUnit) {
            case "m":
                end = 1000 * 60 * Integer.parseInt(time);
            case "h":
                end = 1000 * 60 * 60 * Integer.parseInt(time);
            case "d":
                end = 1000 * 60 * 24 * Integer.parseInt(time);
            case "M":
                end = 1000 * 60 * 24 * 30 * Integer.parseInt(time);
            case "y":
                end = 1000 * 60 * 24 * 30 * 12 * Integer.parseInt(time);
            default:
                end = 0;
        }

        long endDateMilli = date.getTime() + end;
        return new Date(endDateMilli);
    }

}
