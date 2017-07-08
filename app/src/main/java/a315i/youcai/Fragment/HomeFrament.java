package a315i.youcai.Fragment;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import org.xutils.http.RequestParams;

import java.util.ArrayList;
import java.util.List;

import a315i.youcai.Activity.ProductActivity;
import a315i.youcai.Adapter.HomeRecycerViewAdapter;
import a315i.youcai.Activity.MainDetailActivity;
import a315i.youcai.BaseClass.MainFragment;
import a315i.youcai.Model.Home.HomeModel;
import a315i.youcai.R;
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



    @Override
    public void setupData() {

        RequestParams params = new RequestParams("https://api.youcai.xin/app/home");

        NetWorkTools.request(NetWorkTools.requestType.GET, params, new NetWorkTools.CallBack() {
            @Override
            public void onResponse(String response) {
                LogTools.i(response);
               HomeModel model =   GsonTools.parseJsonWithGson(response, HomeModel.class);
                items = model.items;
                tops = model.tops;
                entries = model.entries;
                List<HomeModel.slidesModel> slidesModels = model.slides;
                ToastTools.showLong(getActivity(),"请求成功");
                mRecycerViewAdapter = new HomeRecycerViewAdapter(R.layout.home_item,R.layout.home_header_item,getSampleData(model),getActivity());
                mRecyclerView.setAdapter(mRecycerViewAdapter);
                setupHeaderView(slidesModels);
                setupDetailItemClick();

            }
            @Override
            public void onResponseError(Throwable error) {
                ToastTools.showLong(getActivity(),error.getMessage());

            }
        });

    }

    @Override
    public View setupView() {
        View homeView = View.inflate(getActivity(), R.layout.fragment_home,null);

        mRecyclerView = (RecyclerView) homeView.findViewById(R.id.homeRecyclerView);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(manager);

        return homeView;
    }

    private void setupHeaderView(List<HomeModel.slidesModel> slides){

        View headerView = View.inflate(getActivity(),R.layout.home_recycerview_header,null);
        headerView.findViewById(R.id.vegetables).setOnClickListener(this);
        headerView.findViewById(R.id.meat_egg).setOnClickListener(this);
        headerView.findViewById(R.id.grain_oil).setOnClickListener(this);
        headerView.findViewById(R.id.icon_baby).setOnClickListener(this);
        headerView.findViewById(R.id.choose_food).setOnClickListener(this);
        headerView.findViewById(R.id.coupons).setOnClickListener(this);
        headerView.findViewById(R.id.exchange).setOnClickListener(this);
        headerView.findViewById(R.id.recharge).setOnClickListener(this);

        Banner banner = (Banner) headerView.findViewById(R.id.banner);
        List<String> paths = new ArrayList();
        for (HomeModel.slidesModel model : slides){
            paths.add(model.img);
        }
        banner.setImageLoader(new GlideImageLoader());
        banner.setImages(paths);
        banner.start();

        mRecycerViewAdapter.addHeaderView(headerView);


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
                break;
            case R.id.meat_egg:
                index = 1;
                break;
            case R.id.grain_oil:
                index = 2;
                break;
            case R.id.icon_baby:
                index = 3;
                break;
            case R.id.choose_food:
                index = 4;
                break;
            case R.id.coupons:
                index = 5;
                break;
            case R.id.exchange:
                index = 6;
                break;
            case R.id.recharge:
                index = 7;
                break;
        }
        setupProductActivity(index);


    }
    private void setupProductActivity(int index){
        HomeModel.entriesModel intem =  entries.get(index);
//        Gson gson = new Gson();
//        HomeModel.slidesModel entriesModel = GsonTools.parseJsonWithGson(gson.toJson(intem),HomeModel.slidesModel.class);

        Intent intent = new Intent(getContext(), ProductActivity.class);
        intent.putExtra("","");
        intent.putExtra("title","");
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
