package a315i.youcai.Tools;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by zhouzunxian on 2017/7/4.
 */

public class ToastTools {
    //短时间弹框
    public static  void showShort(Context context,String message){
        Toast.makeText(context,message,Toast.LENGTH_SHORT).show();
    }
    //长时间弹框
    public static void showLong(Context context,String message){
        Toast.makeText(context,message,Toast.LENGTH_LONG).show();
    }
}
