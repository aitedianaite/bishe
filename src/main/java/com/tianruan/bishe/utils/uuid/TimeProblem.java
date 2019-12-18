package com.tianruan.bishe.utils.uuid;

import java.util.Calendar;
import java.util.Date;

public  class TimeProblem {
    /**
     * 时间加8小时
     * @param date
     * @return
     */
    public  Date addEightHour(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);//date 换成已经已知的Date对象
        cal.add(Calendar.HOUR_OF_DAY, 8);// before 8 hour
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date time = cal.getTime();
        return time;
    }
    /**
     * 获取精确到秒的时间戳
     * @param date
     * @return
     */
    public static int getSecondTimestampTwo(Date date){
        if (null == date) {
            return 0;
        }
        String timestamp = String.valueOf(date.getTime()/1000);
        return Integer.valueOf(timestamp);
    }
}
