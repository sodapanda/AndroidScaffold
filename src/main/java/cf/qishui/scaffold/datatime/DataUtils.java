package cf.qishui.scaffold.datatime;

import org.joda.time.DateTime;

import java.util.Arrays;
import java.util.List;

/**
 * Created by wangxiao on 2017/11/8.
 */

public class DataUtils {
    public static int getYear(String str) {
        int year = 2017;
        try {
            String formatedYear = getFormatedData(str);
            DateTime dateTime = new DateTime(formatedYear);
            year = dateTime.getYear();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return year;
    }

    public static int getMonth(String str) {
        int month = 1;

        try {
            String formatedYear = getFormatedData(str);
            DateTime dateTime = new DateTime(formatedYear);
            month = dateTime.getMonthOfYear();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return month;
    }

    public static int getDay(String str) {
        int day = 1;

        try {
            String formatedYear = getFormatedData(str);
            DateTime dateTime = new DateTime(formatedYear);
            day = dateTime.getDayOfMonth();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return day;
    }

    private static String getFormatedData(String str) {
        if (str.contains(" ")) {
            List<String> strs = Arrays.asList(str.split(" "));
            if (strs.size() > 0) {
                return strs.get(0);
            }
        }

        return str;
    }

    public static String getTime(String str) {
        if (str.contains(" ")) {
            List<String> strs = Arrays.asList(str.split(" "));
            if (strs.size() > 1) {
                return strs.get(1);
            }
        }

        return str;
    }
}
