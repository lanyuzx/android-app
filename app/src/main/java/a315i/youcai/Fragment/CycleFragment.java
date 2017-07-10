package a315i.youcai.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.xutils.http.RequestParams;

import java.util.ArrayList;
import java.util.List;

import a315i.youcai.Adapter.CycleAdapter;
import a315i.youcai.BaseClass.MainFragment;
import a315i.youcai.Model.Home.CycleMode.CycleModel;
import a315i.youcai.R;
import a315i.youcai.Tools.GsonTools;
import a315i.youcai.Tools.LogTools;
import a315i.youcai.Tools.NetWorkTools;
import a315i.youcai.Tools.ToastTools;

/**
 * Created by zhouzunxian on 2017/7/3.
 */

public class CycleFragment extends MainFragment {

    private RecyclerView mCycle_Rv;
    @Override
    public void setupData() {
        RequestParams params = new RequestParams("https://api.youcai.xin/combo/list");
        NetWorkTools.request(NetWorkTools.requestType.GET, params, new NetWorkTools.CallBack() {
            @Override
            public void onResponse(String response) {
                LogTools.e(response);
               CycleModel cycleModel = GsonTools.parseJsonWithGson(response,CycleModel.class);
                List<CycleModel.itemModel> itemModels = cycleModel.combos;

                mCycle_Rv.setAdapter(new CycleAdapter( itemModels,getContext()));

            }

            @Override
            public void onResponseError(Throwable error) {
                ToastTools.showShort(getContext(),error.getMessage());
            }
        });

    }

    @Override
    public View setupView() {
        View cycleView = View.inflate(getActivity(), R.layout.fragment_cycle,null);
        mCycle_Rv =  (RecyclerView)cycleView.findViewById(R.id.cycle_Rv);
        mCycle_Rv.setLayoutManager(new LinearLayoutManager(getContext()));
        return cycleView;
    }
}
