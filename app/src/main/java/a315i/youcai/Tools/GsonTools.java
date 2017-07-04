package a315i.youcai.Tools;

import com.google.gson.Gson;

/**
 * Created by zhouzunxian on 2017/7/4.
 */

public class GsonTools {

    //将Json数据解析成相应的映射对象
     public static <T> T parseJsonWithGson(String jsonData, Class<T> type) {

         Gson gson = new Gson();
         T result = gson.fromJson(jsonData, type);
         return result;

            }
}
