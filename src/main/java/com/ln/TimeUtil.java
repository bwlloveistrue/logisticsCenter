package com.ln;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class TimeUtil {
    public TimeUtil() {
    }

    public static String timeAdd(String datetime, int second) {
        Calendar calendar = getCalendar(datetime);
        if(calendar == null) {
            return null;
        } else {
            calendar.add(13, second);
            return getTimeString(calendar);
        }
    }

    public static long timeInterval(String fromdatetime, String todatetime) {
        Calendar fromcalendar = getCalendar(fromdatetime);
        Calendar tocalendar = getCalendar(todatetime);
        return fromcalendar != null && tocalendar != null?(tocalendar.getTime().getTime() - fromcalendar.getTime().getTime()) / 1000L:0L;
    }

    public static String dateAdd(String date, int day) {
        Calendar calendar = getCalendar(date);
        if(calendar == null) {
            return null;
        } else {
            calendar.add(5, day);
            return getDateString(calendar);
        }
    }

    public static int dateInterval(String fromdate, String todate) {
        Calendar fromcalendar = getCalendar(fromdate);
        Calendar tocalendar = getCalendar(todate);
        if(fromcalendar != null && tocalendar != null) {
            int isascorder = 1;
            int dateinterval = 0;
            if(fromcalendar.after(tocalendar)) {
                isascorder = -1;
                Calendar tempcalendar = fromcalendar;
                fromcalendar = tocalendar;
                tocalendar = tempcalendar;
            }

            while(fromcalendar.before(tocalendar)) {
                ++dateinterval;
                fromcalendar.add(5, 1);
            }

            return isascorder * dateinterval;
        } else {
            return 0;
        }
    }

    public static String timeInterval2(String fromdatetime, String todatetime, int languageid) {
        String returnStr = "";
        long intervaltime = timeInterval(fromdatetime, todatetime);
        if(intervaltime >= 0L) {
            long tmpDay = 0L;
            long tmpHour = 0L;
            long tmpMinute = 0L;
            long tmpSecond = 0L;
            tmpDay = intervaltime / 86400L;
            tmpHour = intervaltime % 86400L / 3600L;
            tmpMinute = intervaltime % 3600L / 60L;
            tmpSecond = intervaltime % 60L;
            if(languageid == 8) {
                if(tmpDay > 0L) {
                    returnStr = String.valueOf(tmpDay) + "days " + tmpHour + "hours " + tmpMinute + "minutes " + tmpSecond + "seconds";
                } else if(tmpHour > 0L) {
                    returnStr = String.valueOf(tmpHour) + "hours " + tmpMinute + "minutes " + tmpSecond + "seconds";
                } else if(tmpMinute > 0L) {
                    returnStr = String.valueOf(tmpMinute) + "minutes " + tmpSecond + "seconds";
                } else if(tmpSecond >= 0L) {
                    returnStr = String.valueOf(tmpSecond) + "seconds";
                }
            } else if(tmpDay > 0L) {
                returnStr = String.valueOf(tmpDay) + "天" + tmpHour + "小时" + tmpMinute + "分" + tmpSecond + "秒";
            } else if(tmpHour > 0L) {
                returnStr = String.valueOf(tmpHour) + "小时" + tmpMinute + "分" + tmpSecond + "秒";
            } else if(tmpMinute > 0L) {
                returnStr = String.valueOf(tmpMinute) + "分" + tmpSecond + "秒";
            } else if(tmpSecond >= 0L) {
                returnStr = String.valueOf(tmpSecond) + "秒";
            }
        }

        return returnStr;
    }

    public static int dateWeekday(String date) {
        Calendar calendar = getCalendar(date);
        return calendar == null?-1:calendar.get(7) - 1;
    }

    public static int dateMonthday(String date) {
        Calendar calendar = getCalendar(date);
        return calendar == null?-1:calendar.get(5);
    }

    public static String getCurrentTimeString() {
        String timestrformart = "yyyy'-'MM'-'dd' 'HH:mm:ss";
        SimpleDateFormat SDF = new SimpleDateFormat(timestrformart);
        Calendar calendar = Calendar.getInstance();
        return SDF.format(calendar.getTime());
    }

    public static String getCurrentDateString() {
        String timestrformart = "yyyy'-'MM'-'dd";
        SimpleDateFormat SDF = new SimpleDateFormat(timestrformart);
        Calendar calendar = Calendar.getInstance();
        return SDF.format(calendar.getTime());
    }

    public static String getOnlyCurrentTimeString() {
        String timestrformart = "HH:mm:ss";
        SimpleDateFormat SDF = new SimpleDateFormat(timestrformart);
        Calendar calendar = Calendar.getInstance();
        return SDF.format(calendar.getTime());
    }

    public static String getTimeString(Date date) {
        String timestrformart = "yyyy'-'MM'-'dd' 'HH:mm:ss";
        SimpleDateFormat SDF = new SimpleDateFormat(timestrformart);
        return SDF.format(date);
    }

    public static String getTimeString(Calendar calendar) {
        String timestrformart = "yyyy'-'MM'-'dd' 'HH:mm:ss";
        SimpleDateFormat SDF = new SimpleDateFormat(timestrformart);
        return SDF.format(calendar.getTime());
    }

    public static String getDateString(Date date) {
        String timestrformart = "yyyy'-'MM'-'dd";
        SimpleDateFormat SDF = new SimpleDateFormat(timestrformart);
        return SDF.format(date);
    }

    public static Date getString2Date(String strDate, String format) {
        Date date = null;

        try {
            date = (new SimpleDateFormat(format)).parse(strDate);
        } catch (Exception var4) {
            date = null;
        }

        return date;
    }

    public static String getDateString(Calendar calendar) {
        String timestrformart = "yyyy'-'MM'-'dd";
        SimpleDateFormat SDF = new SimpleDateFormat(timestrformart);
        return SDF.format(calendar.getTime());
    }

    public static String getFormartString(Date date, String formart) {
        SimpleDateFormat SDF = new SimpleDateFormat(formart);
        return SDF.format(date);
    }

    public static String getFormartString(Calendar calendar, String formart) {
        SimpleDateFormat SDF = new SimpleDateFormat(formart);
        return SDF.format(calendar.getTime());
    }

    public static Calendar getCalendar(String datetime) {
        int datetimelength = datetime.length();
        switch(datetimelength) {
            case 10:
                return getCalendar(datetime, "yyyy'-'MM'-'dd");
            case 19:
                return getCalendar(datetime, "yyyy'-'MM'-'dd' 'HH:mm:ss");
            default:
                return null;
        }
    }

    public static Calendar getCalendar(String datetime, String formart) {
        SimpleDateFormat SDF = new SimpleDateFormat(formart);
        Calendar calendar = Calendar.getInstance();

        try {
            calendar.setTime(SDF.parse(datetime));
            return calendar;
        } catch (ParseException var5) {
            return null;
        }
    }

    public static String getCurrentSeason() {
        String m = getFormartString(Calendar.getInstance(), "MM");
        String strSeason = "";
        if(!m.equals("01") && !m.equals("02") && !m.equals("03")) {
            if(!m.equals("04") && !m.equals("05") && !m.equals("06")) {
                if(!m.equals("07") && !m.equals("08") && !m.equals("09")) {
                    if(m.equals("10") || m.equals("11") || m.equals("12")) {
                        strSeason = "4";
                    }
                } else {
                    strSeason = "3";
                }
            } else {
                strSeason = "2";
            }
        } else {
            strSeason = "1";
        }

        return strSeason;
    }

    public static int getWeeksOfMonth(String years, int months) {
        int s = 0;
        String datetime = "";
        String month = "";
        month = months < 10?"0" + months:"" + months;
        datetime = years + "-" + month + "-" + "01";
        Calendar calendar = getCalendar(datetime);
        int days = calendar.getActualMaximum(5);

        for(int i = 0; i < days; ++i) {
            String tempmonth = i + 1 < 10?"0" + (i + 1):"" + (i + 1);
            String tempDay = years + "-" + month + "-" + tempmonth;
            if(dateWeekday(tempDay) == 1) {
                ++s;
            }
        }

        return s;
    }

    public static int getMaxWeekNumOfYear(int year) {
        Calendar c = new GregorianCalendar();
        c.set(year, 11, 31, 23, 59, 59);
        return getWeekOfYear(c.getTime());
    }

    public static int getWeekOfYear(Date date) {
        Calendar c = new GregorianCalendar();
        c.setFirstDayOfWeek(2);
        c.setMinimalDaysInFirstWeek(7);
        c.setTime(date);
        return c.get(3);
    }

    public static Date getFirstDayOfWeek(int year, int week) {
        Calendar c = new GregorianCalendar();
        c.set(1, year);
        c.set(2, 0);
        c.set(5, 1);
        Calendar cal = (GregorianCalendar)c.clone();
        cal.add(5, week * 7);
        return getFirstDayOfWeek(cal.getTime());
    }

    public static Date getLastDayOfWeek(int year, int week) {
        Calendar c = new GregorianCalendar();
        c.set(1, year);
        c.set(2, 0);
        c.set(5, 1);
        Calendar cal = (GregorianCalendar)c.clone();
        cal.add(5, week * 7);
        return getLastDayOfWeek(cal.getTime());
    }

    public static Date getFirstDayOfWeek(Date date) {
        Calendar c = new GregorianCalendar();
        c.setFirstDayOfWeek(2);
        c.setTime(date);
        c.set(7, c.getFirstDayOfWeek());
        return c.getTime();
    }

    public static Date getLastDayOfWeek(Date date) {
        Calendar c = new GregorianCalendar();
        c.setFirstDayOfWeek(2);
        c.setTime(date);
        c.set(7, c.getFirstDayOfWeek() + 6);
        return c.getTime();
    }

    public static String SetDateFormat(String myDate, String strFormat) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(strFormat);
        String sDate = sdf.format(sdf.parse(myDate));
        return sDate;
    }

    public static String getYearMonthFirstDay(int yearNum, int monthNum) throws ParseException {
        String tempYear = Integer.toString(yearNum);
        String tempMonth = Integer.toString(monthNum);
        String tempDay = "1";
        String tempDate = tempYear + "-" + tempMonth + "-" + tempDay;
        return SetDateFormat(tempDate, "yyyy-MM-dd");
    }

    public static boolean isLeapYear(int yearNum) {
        boolean isLeep = false;
        if(yearNum % 4 == 0 && yearNum % 100 != 0) {
            isLeep = true;
        } else if(yearNum % 400 == 0) {
            isLeep = true;
        } else {
            isLeep = false;
        }

        return isLeep;
    }

    public static String getYearMonthEndDay(int yearNum, int monthNum) throws ParseException {
        String tempYear = Integer.toString(yearNum);
        String tempMonth = Integer.toString(monthNum);
        String tempDay = "31";
        if(tempMonth.equals("1") || tempMonth.equals("3") || tempMonth.equals("5") || tempMonth.equals("7") || tempMonth.equals("8") || tempMonth.equals("10") || tempMonth.equals("12")) {
            tempDay = "31";
        }

        if(tempMonth.equals("4") || tempMonth.equals("6") || tempMonth.equals("9") || tempMonth.equals("11")) {
            tempDay = "30";
        }

        if(tempMonth.equals("2")) {
            if(isLeapYear(yearNum)) {
                tempDay = "29";
            } else {
                tempDay = "28";
            }
        }

        String tempDate = tempYear + "-" + tempMonth + "-" + tempDay;
        return SetDateFormat(tempDate, "yyyy-MM-dd");
    }
}
