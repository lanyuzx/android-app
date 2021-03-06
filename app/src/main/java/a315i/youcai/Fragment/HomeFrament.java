package a315i.youcai.Fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.RelativeLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.squareup.picasso.Picasso;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import org.xutils.http.RequestParams;

import java.util.ArrayList;
import java.util.List;

import a315i.youcai.Activity.LoginActivity;
import a315i.youcai.Activity.ProductActivity;
import a315i.youcai.Activity.SearchActivity;
import a315i.youcai.Adapter.HomeRecycerViewAdapter;
import a315i.youcai.Activity.MainDetailActivity;
import a315i.youcai.BaseClass.MainFragment;
import a315i.youcai.Model.Home.HomeModel;
import a315i.youcai.R;
import a315i.youcai.Tools.DataBaseTools;
import a315i.youcai.Tools.GsonTools;
import a315i.youcai.Tools.LogTools;
import a315i.youcai.Tools.NetWorkTools;
import a315i.youcai.Tools.ToastTools;

/**
 * Created by zhouzunxian on 2017/7/3.
 */

public class HomeFrament extends MainFragment  implements View.OnClickListener{
    private RecyclerView mRecyclerView;
    private HomeRecycerViewAdapter mRecycerViewAdapter;
    private List<HomeModel.HomeChildModel> tops;
    private  List<HomeModel.HomeChildModel> items;
    private  List<HomeModel.entriesModel> entries;
    private HomeModel mModel;
    private RelativeLayout mHome_NavView;
    private ImageView mScanImageView;
    private static  int currentDy;
    private  RefreshLayout mRefreshLayout;
    private View mHeaderView;



    @Override
    public void setupData() {

        RequestParams params = new RequestParams("https://api.youcai.xin/app/home");

        NetWorkTools.request(NetWorkTools.requestType.GET, params, new NetWorkTools.CallBack() {
            @Override
            public void onResponse(String response) {
                LogTools.i(response);
                mModel =   GsonTools.parseJsonWithGson(response, HomeModel.class);
                items = mModel.items;
                tops = mModel.tops;
                entries = mModel.entries;
                List<HomeModel.slidesModel> slidesModels = mModel.slides;
                ToastTools.showLong(getActivity(),"请求成功");
                List<HomeModel.HomeChildModel> modelList = DataBaseTools.getInstance(getContext()).findAll();
                for (HomeModel.HomeChildModel saveModel : modelList){
                    for (HomeModel.HomeChildModel topItem : mModel.tops){
                        if (saveModel.id == topItem.id){
                            topItem.buyCout = saveModel.buyCout;
                        }
                    }
                    for (HomeModel.HomeChildModel bootomItem : mModel.items){
                        if (bootomItem.id == saveModel.id){
                            bootomItem.buyCout = saveModel.buyCout;
                        }
                    }

                }
                mRecycerViewAdapter = new HomeRecycerViewAdapter(R.layout.home_item,R.layout.home_header_item,getSampleData(mModel),getActivity());
                mRecyclerView.setAdapter(mRecycerViewAdapter);
                setupHeaderView(slidesModels);
                setupDetailItemClick();
                mRefreshLayout.finishRefresh();
                mRecyclerView.scrollBy(0, -(295*4));

            }
            @Override
            public void onResponseError(Throwable error) {
                ToastTools.showLong(getActivity(),error.getMessage());

            }
        });

    }

    public void setupSaveData(List<HomeModel.HomeChildModel> modelList){

        if (!modelList.isEmpty()&& modelList!=null){
            //读取本地数据库,给新请求的数据赋值
            for (HomeModel.HomeChildModel saveModel : modelList){
                for (HomeModel.HomeChildModel topItem : mModel.tops){
                    if (saveModel.id == topItem.id){
                        topItem.buyCout = saveModel.buyCout;
                    }
                }
                for (HomeModel.HomeChildModel bootomItem : mModel.items){
                    if (bootomItem.id == saveModel.id){
                        bootomItem.buyCout = saveModel.buyCout;
                    }
                }

            }
        }else {
            for (HomeModel.HomeChildModel topItem : mModel.tops){
                topItem.buyCout = 0;
            }
            for (HomeModel.HomeChildModel bootomItem : mModel.items){
                bootomItem.buyCout = 0;
            }
        }

        mRecycerViewAdapter.notifyDataSetChanged();

    }

    @Override
    public View setupView() {
        View homeView = View.inflate(getActivity(), R.layout.fragment_home,null);

        mRecyclerView = (RecyclerView) homeView.findViewById(R.id.homeRecyclerView);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        homeView.findViewById(R.id.homeSerch).setOnClickListener(this);
        homeView.findViewById(R.id.home_scan).setOnClickListener(this);
        mRecyclerView.setLayoutManager(manager);
        mHome_NavView = (RelativeLayout) homeView.findViewById(R.id.home_NavView);
        mScanImageView = (ImageView) mHome_NavView.findViewById(R.id.home_scan);
        setupScrolListenner();
        mRefreshLayout = (RefreshLayout) homeView.findViewById(R.id.homefragment_refreshLayout);
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                setupData();

            }
        });

        return homeView;
    }

    private  void setupScrolListenner(){
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                currentDy += dy;
                LogTools.e("" +currentDy);
                if (currentDy >= 295 ) {
                    mHome_NavView.setAlpha((float) 0.8);
                    mHome_NavView.setBackgroundColor(Color.WHITE);
                    mScanImageView.setImageResource(R.drawable.scan2);
                } else {
                    mHome_NavView.setAlpha((float) 0.3);
                    mHome_NavView.setBackgroundColor(Color.GRAY);
                    mScanImageView.setImageResource(R.drawable.scan);
                }


            }
        });
    }
    private void setupHeaderView(List<HomeModel.slidesModel> slides){

        mHeaderView = View.inflate(getActivity(),R.layout.home_recycerview_header,null);
        mHeaderView.findViewById(R.id.vegetables).setOnClickListener(this);
        mHeaderView.findViewById(R.id.meat_egg).setOnClickListener(this);
        mHeaderView.findViewById(R.id.grain_oil).setOnClickListener(this);
        mHeaderView.findViewById(R.id.icon_baby).setOnClickListener(this);
        mHeaderView.findViewById(R.id.choose_food).setOnClickListener(this);
        mHeaderView.findViewById(R.id.coupons).setOnClickListener(this);
        mHeaderView.findViewById(R.id.exchange).setOnClickListener(this);
        mHeaderView.findViewById(R.id.recharge).setOnClickListener(this);

        Banner banner = (Banner) mHeaderView.findViewById(R.id.banner);
        List<String> paths = new ArrayList();
        for (HomeModel.slidesModel model : slides){
            paths.add(model.img);
        }
        banner.setImageLoader(new GlideImageLoader());
        banner.setImages(paths);
        banner.start();

        mRecycerViewAdapter.addHeaderView(mHeaderView);




    }
    private  void setupDetailItemClick(){
        mRecycerViewAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ToastTools.showShort(getActivity(),""+position);
                int index = 0;
                if (position == 1){ //
                    index = 0;
                    Intent intent = new Intent(getActivity(), MainDetailActivity.class);
                    HomeModel.HomeChildModel model = items.get(index);
                    String detailUrl = "https://api.youcai.xin/item/detail?id="+ model.id;
                    intent.putExtra("detailUrl",detailUrl);
                    startActivity(intent);
                }else if (position >=3){
                    index = position -3;
                    Intent intent = new Intent(getActivity(), MainDetailActivity.class);
                    HomeModel.HomeChildModel model = tops.get(index);
                    String detailUrl = "https://api.youcai.xin/item/detail?id="+ model.id;
                    intent.putExtra("detailUrl",detailUrl);
                    startActivity(intent);

                }
            }
        });
    }
    private List<HomeRecycerViewAdapter.MySection> getSampleData(HomeModel model){

        List<HomeRecycerViewAdapter.MySection> list = new ArrayList<>();
        list.add(new HomeRecycerViewAdapter.MySection(true, "精品推荐"));
        for (int i = 0;i<model.items.size();i++){
            list.add(new HomeRecycerViewAdapter.MySection(model.items.get(i)));
        }
        list.add(new HomeRecycerViewAdapter.MySection(true, "热销商品"));
        for (int i = 0;i<model.tops.size();i++){
            list.add(new HomeRecycerViewAdapter.MySection(model.tops.get(i)));
        }
        return list;

    }

    @Override
    public void onClick(View v) {
        int index = 0;
        switch (v.getId()){
            case R.id.vegetables:
                index = 0;
                setupProductActivity(index);
                break;
            case R.id.meat_egg:
                index = 1;
                setupProductActivity(index);
                break;
            case R.id.grain_oil:
                index = 2;
                setupProductActivity(index);
                break;
            case R.id.icon_baby:
                index = 3;
                setupProductActivity(index);
                break;
            case R.id.choose_food:
                index = 4;
                setupProductActivity(index);
                break;
            case R.id.coupons:
                index = 5;
                setupProductActivity(index);
                break;
            case R.id.exchange:
                index = 6;
                setupProductActivity(index);
                break;
            case R.id.recharge:
                index = 7;
                setupProductActivity(index);
                break;
            case R.id.homeSerch:
                Intent intent = new Intent(getContext(), SearchActivity.class);
                startActivity(intent);
                break;
            case R.id.home_scan:
                ToastTools.showShort(getContext(),"二维码");
                break;

        }




    }
    private void setupProductActivity(int index){
        if (index == 4||index == 5||index == 6||index == 7 ){
            Intent intent = new Intent(getContext(), LoginActivity.class);
            startActivity(intent);
            return;
        }
        HomeModel.entriesModel intem =  entries.get(index);
//        Gson gson = new Gson();
//        HomeModel.slidesModel entriesModel = GsonTools.parseJsonWithGson(gson.toJson(intem),HomeModel.slidesModel.class);
        String url = "";
        if (intem.link !=null){
            url = "https://api.youcai.xin/item/list?cate=" + (index +1) + "&length=10&start=0";
            if (index == 3){
                url = "https://api.youcai.xin/item/list?tags=" + (index ) + "&length=10&start=0";
            }
        }
        Intent intent = new Intent(getContext(), ProductActivity.class);
        intent.putExtra("prdouctUrl",url);
        intent.putExtra("title",intem.title);
        startActivity(intent);




    }

    private class  GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Picasso.with(context).load((String)path).into(imageView);
        }

        @Override
        public ImageView createImageView(Context context) {
            ImageView imageView = new ImageView(context);
            return  imageView;

        }

    }


}
