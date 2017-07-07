package a315i.youcai.Tools;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by zhouzunxian on 2017/7/7.
 */

public class DateTools {
    /**
     * 调用此方法输入所要转换的时间戳输入例如（1402733340）输出（"2014-06-14  16:09:00"）
     *
     * @param time
     * @return
     */
    public static String timeDate(String time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if (time.length() == 13) {
            String date = sdf.format(new Date(toLong(time)));
            LogTools.e("将13位时间戳:" + "转化为字符串:", date);
            return date;
        } else {
            String date = sdf.format(new Date(toInt(time) * 1000L));
            LogTools.d( "将10位时间戳:" + "转化为字符串:", date);
            return date;
        }

    }

    public static long toLong(String obj) {
        try {
            return Long.parseLong(obj);
        } catch (Exception e) {
        }
        return 0;
    }

    public static int toInt(Object obj) {
        if (obj == null)
            return 0;
        return toInt(obj.toString());
    }


}
