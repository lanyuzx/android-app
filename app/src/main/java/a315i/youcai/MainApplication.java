package a315i.youcai;

import android.app.Application;

import org.xutils.x;


/**
 * Created by zhouzunxian on 2017/7/3.
 */

public class MainApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(BuildConfig.DEBUG);

    }
}
