package a315i.youcai.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;

import org.xutils.http.RequestParams;

import java.util.List;

import a315i.youcai.Activity.CaiPuDetailActivity;
import a315i.youcai.Adapter.CaiPuAdapter;
import a315i.youcai.BaseClass.MainFragment;
import a315i.youcai.Model.Home.CaiPuModel.CaipuModel;
import a315i.youcai.R;
import a315i.youcai.Tools.GsonTools;
import a315i.youcai.Tools.NetWorkTools;
import a315i.youcai.Tools.ToastTools;

/**
 * Created by zhouzunxian on 2017/7/3.
 */

public class CaipuFragment extends MainFragment {
    private RecyclerView mRecyclerView;
    private CaiPuAdapter mCaiPuAdapter;
    @Override
    public void setupData() {
        RequestParams params = new RequestParams("https://api.youcai.xin/recipe/list?length=10&scope=1&start=0");

        NetWorkTools.request(NetWorkTools.requestType.GET, params, new NetWorkTools.CallBack() {
            @Override
            public void onResponse(String response) {
                CaipuModel model = GsonTools.parseJsonWithGson(response,CaipuModel.class);
                List<CaipuModel.recipesModel> modelList = model.recipes;
                mCaiPuAdapter = new CaiPuAdapter(modelList,getContext());
                mRecyclerView.setAdapter(mCaiPuAdapter);
                setupItemClick(modelList);

            }

            @Override
            public void onResponseError(Throwable error) {
                ToastTools.showShort(getContext(),error.getMessage());
            }
        });

    }

    @Override
    public View setupView() {
        View caiPuView = View.inflate(getActivity(), R.layout.fragment_caipu,null);
        mRecyclerView = (RecyclerView)caiPuView.findViewById(R.id.caipu_Rv);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return caiPuView;
    }

    private void setupItemClick(final List<CaipuModel.recipesModel> modelList){

        mCaiPuAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                CaipuModel.recipesModel recipesModel = modelList.get(position);
                Intent intent = new Intent(getContext(), CaiPuDetailActivity.class);
                intent.putExtra("caipudetailUrl","https://api.youcai.xin/recipe/detail?id=" +recipesModel.id);
                startActivity(intent);
            }
        });

    }
}
