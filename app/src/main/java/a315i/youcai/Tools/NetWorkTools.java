package a315i.youcai.Tools;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by zhouzunxian on 2017/7/4.
 */

public class NetWorkTools {
    public enum requestType {
        GET,
        POST
    }

   public interface  CallBack{
        void  onResponse(String response);
        void onResponseError(Throwable error);
   }

    public static void request(requestType type, RequestParams params, final CallBack block){

        if (type == requestType.GET){//GET

            x.http().get(params, new Callback.CommonCallback<String>() {
                @Override
                public void onSuccess(String result) {
                    block.onResponse(result);
                }

                @Override
                public void onError(Throwable ex, boolean isOnCallback) {
                    block.onResponseError(ex);
                }

                @Override
                public void onCancelled(CancelledException cex) {

                }

                @Override
                public void onFinished() {

                }
            });

        }else {//POST

            x.http().post(params, new Callback.CommonCallback<String>() {
                @Override
                public void onFinished() {

                }

                @Override
                public void onSuccess(String result) {
                    block.onResponse(result);
                }

                @Override
                public void onError(Throwable ex, boolean isOnCallback) {
                    block.onResponseError(ex);
                }

                @Override
                public void onCancelled(CancelledException cex) {

                }
            });

        }


    }

}
