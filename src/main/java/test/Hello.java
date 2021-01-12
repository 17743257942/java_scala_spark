package test;

import _1stack._4pet.Cat;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.atomic.AtomicReference;

public class Hello {
    public static void main(String[] args) throws Exception {
        String a = "hello";
        System.out.println(a);
        System.out.println("-------------------");
        String writeToPath = "E:\\work-20191021\\工作日志";
        String dfrom = "2019-11-02";
        String dto = "2021-01-01";
        yMdWeek(dfrom, dto);
        AtomicReference<Cat> aa = new AtomicReference<Cat>();
        System.out.println("-------------------");
        byte b1 = -128; //10000000 -0 表示-128
        System.out.println(Integer.toBinaryString(b1)); //11111111111111111111111110000000
        byte b2 = (byte) 0B11111111;
        System.out.println(b2);

    }

    /**
     * 获取指定起止日期中每一天年月日周的信息
     *
     * @param dfrom
     * @param dto
     * @throws ParseException
     */
    private static void yMdWeek(String dfrom, String dto) throws ParseException {
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Date d1 = new SimpleDateFormat("yyyy-MM-dd").parse(dfrom);
        Date d2 = new SimpleDateFormat("yyyy-MM-dd").parse(dto);
        Calendar c = Calendar.getInstance();
        c.setTime(d1);
        while (c.getTime().before(d2)) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String str = sdf.format(c.getTime());
            System.out.println(str + "  " + weekDays[c.get(Calendar.DAY_OF_WEEK) - 1]);
            c.add(Calendar.DAY_OF_MONTH, 1);
        }
    }


    /**
     * 获得一个日期所在的周的星期几的日期，如要找出2002年2月3日所在周的星期一是几号
     *
     * @param sdate：日期
     * @param num：星期几（星期天是一周的第一天）
     * @return
     */
    public static String getWeekN(String sdate, String num) {
        // 再转换为时间
        Date dd = strToDate(sdate);
        Calendar c = Calendar.getInstance();
        c.setTime(dd);
        if (num.equals("1")) // 返回星期一所在的日期
            c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        else if (num.equals("2")) // 返回星期二所在的日期
            c.set(Calendar.DAY_OF_WEEK, Calendar.TUESDAY);
        else if (num.equals("3")) // 返回星期三所在的日期
            c.set(Calendar.DAY_OF_WEEK, Calendar.WEDNESDAY);
        else if (num.equals("4")) // 返回星期四所在的日期
            c.set(Calendar.DAY_OF_WEEK, Calendar.THURSDAY);
        else if (num.equals("5")) // 返回星期五所在的日期
            c.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
        else if (num.equals("6")) // 返回星期六所在的日期
            c.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
        else if (num.equals("0")) // 返回星期日所在的日期
            c.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        return new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
    }

    /**
     * 将短时间格式字符串转换为时间 yyyy-MM-dd
     *
     * @param strDate
     * @return
     */
    public static Date strToDate(String strDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(strDate, pos);
        return strtodate;
    }


}



