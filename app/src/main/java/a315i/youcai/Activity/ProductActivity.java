package a315i.youcai.Activity;

import android.content.Intent;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;

import org.xutils.http.RequestParams;

import java.util.ArrayList;
import java.util.List;

import a315i.youcai.Adapter.HomeRecycerViewAdapter;
import a315i.youcai.Adapter.PrdouctGridAdapter;
import a315i.youcai.Adapter.PrdouctLienaAdapter;
import a315i.youcai.Model.Home.HomeModel;
import a315i.youcai.R;
import a315i.youcai.Tools.DataBaseTools;
import a315i.youcai.Tools.GsonTools;
import a315i.youcai.Tools.LogTools;
import a315i.youcai.Tools.NetWorkTools;
import a315i.youcai.Tools.ToastTools;

public class ProductActivity extends AppCompatActivity implements View.OnClickListener {
    private RecyclerView mProductLiena_RV;
    private RecyclerView mProductGrid_RV;
    private boolean mIsGridLayout;
    private List<HomeModel.HomeChildModel> modelList;
    private ImageView mProduct_changeLayout;
    private LinearLayoutManager mManager;
    private PrdouctGridAdapter mGridAdapter;
    private GridLayoutManager mGridLayoutManager;
    private PrdouctLienaAdapter mLienaAdapter;
    private SpacesItemDecoration mSpacesItemDecoration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        findViewById(R.id.product_backIv).setOnClickListener(this);
        mProduct_changeLayout = (ImageView) findViewById(R.id.product_changeLayout);
        mProduct_changeLayout.setOnClickListener(this);
        findViewById(R.id.product_search_delete).setOnClickListener(this);
        mProductLiena_RV = (RecyclerView) findViewById(R.id.productLiena_RV);
        mProductGrid_RV = (RecyclerView) findViewById(R.id.productGrid_RV);
        Intent intent = getIntent();
        String url = intent.getStringExtra("prdouctUrl");
        setupData(url);



    }

    private void setupData(String url){
        RequestParams params = new RequestParams(url);
        NetWorkTools.request(NetWorkTools.requestType.GET, params, new NetWorkTools.CallBack() {
            @Override
            public void onResponse(String response) {
                HomeModel items = GsonTools.parseJsonWithGson(response,HomeModel.class);
                modelList = items.items;
                //读取本地数据库,给新请求的数据赋值
                List<HomeModel.HomeChildModel> saveList = DataBaseTools.getInstance(getApplicationContext()).findAll();
                for (HomeModel.HomeChildModel saveModel : saveList){

                    for (HomeModel.HomeChildModel productModel : modelList){
                        if (productModel.id == saveModel.id){
                            productModel.buyCout = saveModel.buyCout;
                        }
                    }

                }
                if (mManager == null){
                    mManager = new LinearLayoutManager(getApplicationContext());
                    //mGridAdapter = new PrdouctGridAdapter(modelList,getApplicationContext());
                    mGridLayoutManager = new GridLayoutManager(getApplicationContext(),2);
                   // mLienaAdapter = new PrdouctLienaAdapter(modelList,getApplicationContext());
                    mSpacesItemDecoration = new SpacesItemDecoration(20);
                }

                changeLayout();
                setupItemClick();


            }

            @Override
            public void onResponseError(Throwable error) {
                ToastTools.showShort(getApplicationContext(),error.getMessage());
            }
        });

    }
    private void changeLayout(){
        mIsGridLayout = !mIsGridLayout;
        if (mIsGridLayout == false){
            mProduct_changeLayout.setImageResource(R.drawable.icon_tiao);
            mProductLiena_RV.setVisibility(View.VISIBLE);
            mProductGrid_RV.setVisibility(View.GONE);
            if (mLienaAdapter == null){
                mLienaAdapter = new PrdouctLienaAdapter(modelList,getApplicationContext());
                mProductLiena_RV.setLayoutManager(mManager);
                mProductLiena_RV.setAdapter(mLienaAdapter);

            }
            setupItemClick();

        }else {
            mProductLiena_RV.setVisibility(View.GONE);
            mProductGrid_RV.setVisibility(View.VISIBLE);
            if (mGridAdapter == null){
                mGridAdapter = new PrdouctGridAdapter(modelList,getApplicationContext());
                mProductGrid_RV.setLayoutManager(mGridLayoutManager);
                mProductGrid_RV.setAdapter(mGridAdapter);
                mProductGrid_RV.addItemDecoration(mSpacesItemDecoration);
            }
            mProduct_changeLayout.setImageResource(R.drawable.icon_kuai);
            setupItemClick();
        }


    }

    private void setupItemClick() {

        if (mProductLiena_RV.getVisibility() == View.VISIBLE){
            mLienaAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    Intent intent = new Intent(getApplicationContext(), MainDetailActivity.class);
                    HomeModel.HomeChildModel model = modelList.get(position);
                    String detailUrl = "https://api.youcai.xin/item/detail?id="+ model.id;
                    intent.putExtra("detailUrl",detailUrl);
                    startActivity(intent);
                }
            });
        }else {
            mGridAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    Intent intent = new Intent(getApplicationContext(), MainDetailActivity.class);
                    HomeModel.HomeChildModel model = modelList.get(position);
                    String detailUrl = "https://api.youcai.xin/item/detail?id="+ model.id;
                    intent.putExtra("detailUrl",detailUrl);
                    startActivity(intent);
                }
            });
        }

    }

    public class SpacesItemDecoration extends RecyclerView.ItemDecoration {

        private int space;

        public SpacesItemDecoration(int space) {
            this.space=space;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            outRect.left=space;
            outRect.right=space;
            outRect.bottom=space;
            //注释这两行是为了上下间距相同
//        if(parent.getChildAdapterPosition(view)==0){
            outRect.top=space;
//        }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.product_backIv:
                finish();
                break;
            case R.id.product_changeLayout:
                changeLayout();
                break;
        }

    }
}
