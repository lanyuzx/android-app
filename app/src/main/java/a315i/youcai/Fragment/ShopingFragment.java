package a315i.youcai.Fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.List;

import a315i.youcai.Adapter.ShoppingAdapter;
import a315i.youcai.BaseClass.MainFragment;
import a315i.youcai.MainActivity;
import a315i.youcai.Model.Home.HomeModel;
import a315i.youcai.R;
import a315i.youcai.Tools.DataBaseTools;

/**
 * Created by zhouzunxian on 2017/7/3.
 */

public class ShopingFragment extends MainFragment {

    private RecyclerView mRecyclerView;
    private ShoppingAdapter mShoppingAdapter;
    private LinearLayout mShop_message_layout;
    private RelativeLayout mShop_pay_layout;
    @Override
    public void setupData() {

    }

    @Override
    public View setupView() {
        View shopView = View.inflate(getActivity(), R.layout.fragment_shop,null);
        mRecyclerView = (RecyclerView) shopView.findViewById(R.id.shop_Rcv);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mShop_message_layout = (LinearLayout) shopView.findViewById(R.id.shop_message_layout);
        mShop_pay_layout = (RelativeLayout) shopView.findViewById(R.id.shop_pay_layout);
        //读取本地数据库
        List<HomeModel.HomeChildModel> saveList = DataBaseTools.getInstance(getContext()).findAll();
        mShoppingAdapter = new ShoppingAdapter(saveList,getContext());
        mShoppingAdapter.bindToRecyclerView(mRecyclerView);
        mRecyclerView.setAdapter(mShoppingAdapter);
        if (saveList.isEmpty()){
            mShop_message_layout.setVisibility(View.GONE);
            mShop_pay_layout.setVisibility(View.GONE);
            mShoppingAdapter.setEmptyView(R.layout.shopping_empty);
        }

        return shopView;
    }

    public void setupSaveData(List<HomeModel.HomeChildModel> models){
        mShoppingAdapter.setNewData(models);
        mShoppingAdapter.notifyDataSetChanged();
        if (models.isEmpty()){
            mShop_message_layout.setVisibility(View.GONE);
            mShop_pay_layout.setVisibility(View.GONE);
            mShoppingAdapter.setEmptyView(R.layout.shopping_empty);

        }else {
            mShop_message_layout.setVisibility(View.VISIBLE);
            mShop_pay_layout.setVisibility(View.VISIBLE);
        }


    }
}
