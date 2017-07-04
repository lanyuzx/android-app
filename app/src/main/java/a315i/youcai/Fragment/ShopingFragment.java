package a315i.youcai.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import a315i.youcai.BaseClass.MainFragment;
import a315i.youcai.R;

/**
 * Created by zhouzunxian on 2017/7/3.
 */

public class ShopingFragment extends MainFragment {


    @Override
    public void setupData() {

    }

    @Override
    public View setupView() {
        View shopView = View.inflate(getActivity(), R.layout.fragment_shop,null);

        return shopView;
    }
}
