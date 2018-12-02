package Tools;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import static tools.FileUntils.sleep;

public class DataUntils {


    public static String timeDate(){
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String timestr = dateFormat.format(now);
        return timestr;

    }

    public static String getNow(String format){
        Date date = new Date();
        return new SimpleDateFormat(format).format(date);
    }

    public static String getNewDate(int days){
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE, days);
        return (new SimpleDateFormat("yyyy-MM-dd")).format(cal.getTime());
    }



    public static String getTimestamp(){
        String curDate = getNow("yyyy-MM-dd");
        Calendar curCalendar = Calendar.getInstance();
        int hour = curCalendar.get(Calendar.HOUR_OF_DAY);//24H
        int minute = curCalendar.get(Calendar.MINUTE);
        int second = curCalendar.get(Calendar.SECOND);

        return curDate + "_" + hour + "_" + minute + "_" + second;
    }


    /**
     * 获取精确到秒的时间戳
     * @return
     */
    public static int getSecondTimestamp(){
        Date date = new Date();
        if (null == date) {
            return 0;
        }
        String timestamp = String.valueOf(date.getTime()/1000);
        return Integer.valueOf(timestamp);
    }


}
