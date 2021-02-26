package com.yishuo.utils;


import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 校验工具
 */
public class ValidationUtil {
    /**
     * 判断数字是否不为０
     *
     * @param iint 判断的字符串
     * @return boolean :返回值为boolean
     */
    public static boolean isNotNull(Integer iint) {
        boolean bolret = true;
        if (iint == null) {
            bolret = false;
        }
        return bolret;
    }


    /**
     * 判断对象是否为null
     *
     * @param obj 判断的字符串
     * @return boolean :返回值为boolean
     */

    public static boolean isNotNull(Object obj) {
        boolean ret = true;
        if (obj == null) {
            ret = false;
        }

        return ret;
    }
    public static boolean isNull(Object obj) {
        boolean ret = false;
        if (obj == null) {
            ret = true;
        }

        return ret;
    }



    /**
     * 判断字符串是否为null或为空
     *
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        return str == null || str.trim().equals("");
    }

    /**
     * 判断字符串是否为非null或为非空
     *
     * @param str
     * @return
     */
    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    /**
     * 判断StringBuffer是否不为空
     *
     * @param str
     * @return
     */
    public static boolean isNotEmpty(StringBuffer str) {
        return str != null && str.length() > 0;
    }

    /**
     * 判断Map是否不为空,即至少存在一个
     *
     * @param map
     * @return
     */
    @SuppressWarnings("unchecked")
    public static boolean isNotEmpty(Map map) {
        return map != null && map.size() > 0;
    }

    /**
     * 判断对象数组是否不为空,即至少存在一个
     *
     * @param objArr
     * @return
     */
    public static boolean isNotEmpty(Object[] objArr) {
        return objArr != null && objArr.length > 0;
    }

    /**
     * 判断列表是否不为空,即至少存在一个
     *
     * @param lst
     * @return
     */
    @SuppressWarnings("unchecked")
    public static boolean isNotEmpty(List lst) {
        boolean bolret = false;
        if (lst != null) {
            if (lst.size() > 0) {
                bolret = true;
            }

        }
        return bolret;
    }

    /**
     * 判断时间是否在指定时间范围内，开始时间、结束时间及判断时间格式要统一
     *
     * @param time       要判断的时间
     * @param fromTime   开始时间,不包括该时间
     * @param toTime     结束时间,不包括该时间
     * @param dateFormat 时间格式
     * @return 是否在范围内
     */
    public static boolean checkTimeBetween(String time, String fromTime, String toTime,
                                           String dateFormat) {
        Date date = new Date(), fromDate = new Date(), toDate = new Date();
        try {
            date = TimeUtil.str2date(time, dateFormat);
            fromDate = TimeUtil.str2date(fromTime, dateFormat);
            toDate = TimeUtil.str2date(toTime, dateFormat);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return date.after(fromDate) && date.before(toDate);
    }

    /**
     * 判断时间是否在指定时间范围内，开始时间、结束时间及判断时间格式要统一
     *
     * @param time     要判断的时间
     * @param fromTime 开始时间,不包括该时间
     * @param toTime   结束时间,不包括该时间
     * @return 是否在范围内
     */
    public static boolean checkTimeBetween(Date time, Date fromTime, Date toTime) {
        return time.after(fromTime) && time.before(toTime);
    }


    /**
     * 比较二个对象值是否相等，传入的二个对象可以为null.
     *
     * @param fromObj 比较对象
     * @param toObj   被比较的对象
     * @return boolean 比较结果.
     */
    public static boolean compareObj(Object fromObj, Object toObj) {
        boolean result = false;
        if (fromObj == null) {
            if (toObj == null) {
                result = true;
            }
        } else {
            if (toObj != null) {
                result = fromObj.equals(toObj) ? true : false;
            }
        }

        return result;
    }
}
