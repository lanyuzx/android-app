package a315i.youcai;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.xutils.ex.DbException;

import java.util.ArrayList;
import java.util.List;

import a315i.youcai.BaseClass.MainFragment;
import a315i.youcai.Fragment.CaipuFragment;
import a315i.youcai.Fragment.CycleFragment;
import a315i.youcai.Fragment.HomeFrament;
import a315i.youcai.Fragment.MeFragment;
import a315i.youcai.Fragment.ShopingFragment;
import a315i.youcai.Model.Home.HomeModel;
import a315i.youcai.Tools.DataBaseTools;
import a315i.youcai.Tools.LogTools;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener {
    private List<ImageView> mImageViews;
    private int[] mImageSelectId = {
            R.mipmap.icon_home_hl,
            R.mipmap.icon_home_cycler_combo_hl,
            R.mipmap.icon_recipe_hl,
            R.mipmap.icon_shopping_cart_hl,
            R.mipmap.icon_mine_hl
    };
    private int[] mImageNormalId = {
            R.mipmap.icon_home_normal,
            R.mipmap.icon_home_cycler_combo_normal,
            R.mipmap.icon_recipe_normal,
            R.mipmap.icon_shopping_cart_normal,
            R.mipmap.icon_mine_normal
    };
    private  List<MainFragment> mFragments;
    public  TextView mShopCountTextView;
    public Context mContex;
    public  BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            List<HomeModel.HomeChildModel> saveList = DataBaseTools.getInstance(mContex).findAll();
           ShopingFragment shopingFragment = (ShopingFragment) mFragments.get(3);
           shopingFragment.setupSaveData(saveList);

            HomeFrament homeFrament = (HomeFrament) mFragments.get(0);
            homeFrament.setupSaveData(saveList);
            int buyCount = 0;
            for (HomeModel.HomeChildModel model:saveList){
                buyCount += model.buyCout;
            }
            if (buyCount > 0){
                mShopCountTextView.setVisibility(View.VISIBLE);
                mShopCountTextView.setText(buyCount+"");
            }else {
                mShopCountTextView.setVisibility(View.GONE);
            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //注册一个广播
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("shoppingCountSub");
        intentFilter.addAction("shoppingCountAdd");
        intentFilter.addAction("shoppingCountDelete");
        registerReceiver(mReceiver,intentFilter);
        mContex = this;
        mImageViews = new ArrayList<>();
        mFragments = new ArrayList<>();
        mShopCountTextView = (TextView) findViewById(R.id.shopCount);
        ImageView mHomeImage = (ImageView) findViewById(R.id.home);
        mHomeImage.setOnClickListener(this);
        ImageView mCyclerImage = (ImageView) findViewById(R.id.cycler);
        mCyclerImage.setOnClickListener(this);
        ImageView mCaipuImage = (ImageView) findViewById(R.id.recipe);
        mCaipuImage.setOnClickListener(this);
        ImageView mShopImage = (ImageView) findViewById(R.id.shop);
        mShopImage.setOnClickListener(this);
        ImageView mMineImage = (ImageView) findViewById(R.id.mine);
        mMineImage.setOnClickListener(this);
        mImageViews.add(mHomeImage);
        mImageViews.add(mCyclerImage);
        mImageViews.add(mCaipuImage);
        mImageViews.add(mShopImage);
        mImageViews.add(mMineImage);

        mFragments.add(new HomeFrament());
        mFragments.add(new CycleFragment());
        mFragments.add(new CaipuFragment());
        mFragments.add(new ShopingFragment());
        mFragments.add(new MeFragment());

        //显示第一个fragment
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container,mFragments.get(0))
                .add(R.id.fragment_container,mFragments.get(1))
                .add(R.id.fragment_container,mFragments.get(2))
                .add(R.id.fragment_container,mFragments.get(3))
                .add(R.id.fragment_container,mFragments.get(4))
                .show(mFragments.get(0))
                .hide(mFragments.get(1))
                .hide(mFragments.get(2))
                .hide(mFragments.get(3))
                .hide(mFragments.get(4))
                .commit();


            List<HomeModel.HomeChildModel> modelList = DataBaseTools.getInstance(getApplicationContext()).findAll();
            int buyCount = 0;
            for (HomeModel.HomeChildModel model:modelList){
                buyCount += model.buyCout;
            }
           if (buyCount > 0){
               mShopCountTextView.setVisibility(View.VISIBLE);
               mShopCountTextView.setText(buyCount+"");
           }else {
               mShopCountTextView.setVisibility(View.GONE);
           }


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mReceiver);
    }

    @Override
    public void onClick(View v) {

        int index = 0;
        switch (v.getId()){
            case R.id.home:
                index = 0;
                break;
            case R.id.cycler:
                index = 1;
                break;
            case R.id.recipe:
                index = 2;
                break;
            case R.id.shop:
                index = 3;
                break;
            case R.id.mine:
                index = 4;
                break;
        }

        for (int i = 0;i<mImageViews.size();i++){

            if (index == i){
                mImageViews.get(index).setImageResource(mImageSelectId[index]);
                getSupportFragmentManager().beginTransaction().show(mFragments.get(index)).commit();

            }else {

                mImageViews.get(i).setImageResource(mImageNormalId[i]);
                getSupportFragmentManager().beginTransaction().hide(mFragments.get(i)).commit();
            }

        }

    }

}
