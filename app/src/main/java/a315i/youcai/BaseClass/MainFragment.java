package a315i.youcai.BaseClass;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by zhouzunxian on 2017/7/3.
 */

public abstract class MainFragment extends android.support.v4.app.Fragment  {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupData();

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return setupView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    //抽象的方法,子类必须实现
    public abstract View setupView();
    public abstract void setupData();


}
