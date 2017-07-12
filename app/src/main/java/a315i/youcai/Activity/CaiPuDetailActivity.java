package a315i.youcai.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import org.xutils.http.RequestParams;

import a315i.youcai.Adapter.CaipuDetailAdapter;
import a315i.youcai.Adapter.MainDetailComntensAdapter;
import a315i.youcai.Model.Home.CaiPuModel.CaipuDetailModel;
import a315i.youcai.R;
import a315i.youcai.Tools.DateTools;
import a315i.youcai.Tools.GsonTools;
import a315i.youcai.Tools.NetWorkTools;
import a315i.youcai.Tools.ToastTools;

public class CaiPuDetailActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private CaipuDetailModel.recipe mRecipe;
    private CaipuDetailAdapter mCaipuDetailAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caipudetail);
        mRecyclerView = (RecyclerView) findViewById(R.id.caipu_detailRv);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        findViewById(R.id.caipudetail_backIv).setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               finish();
           }
       });
        Intent intent = getIntent();
        setupData(intent.getStringExtra("caipudetailUrl"));
    }

    private void setupData(String url){

        RequestParams params = new RequestParams(url);
        NetWorkTools.request(NetWorkTools.requestType.GET, params, new NetWorkTools.CallBack() {
            @Override
            public void onResponse(String response) {
                CaipuDetailModel detailModel = GsonTools.parseJsonWithGson(response,CaipuDetailModel.class);
                Gson gson = new Gson();
                mRecipe = GsonTools.parseJsonWithGson(gson.toJson(detailModel.recipe),CaipuDetailModel.recipe.class);
                mCaipuDetailAdapter = new CaipuDetailAdapter(mRecipe.steps,getApplicationContext());
                mRecyclerView.setAdapter(mCaipuDetailAdapter);
                setupHeaderView();
                if (detailModel.comments != null){
                    setupFooterView(detailModel);
                }else {
                    mCaipuDetailAdapter.removeAllFooterView();
                }


            }

            @Override
            public void onResponseError(Throwable error) {
                ToastTools.showShort(getApplicationContext(),error.getMessage());
            }
        });


    }

    private void setupFooterView( CaipuDetailModel detailModel){
        View footerView = View.inflate(getApplicationContext(),R.layout.caipudetai_footer,null);
        RecyclerView recyclerView = (RecyclerView) footerView.findViewById(R.id.caipu_detailFooterconmentRv);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(new MainDetailComntensAdapter(detailModel.comments,getApplicationContext()));
        mCaipuDetailAdapter.addFooterView(footerView);

    }

    private void setupHeaderView(){
        View headerView = View.inflate(getApplicationContext(),R.layout.caipu_detail_header,null);
       Picasso.with(getApplicationContext()).load(mRecipe.imgs.get(0)).into((ImageView) headerView.findViewById(R.id.caipudetail_header_bgicon));
       TextView titles = (TextView) headerView.findViewById(R.id.caipudetail_header_titles);
       titles.setText(mRecipe.title);
       Picasso.with(getApplicationContext()).load(mRecipe.headimg).into((ImageView) headerView.findViewById(R.id.caipudetail_header_icon));
        TextView nikeName = (TextView) headerView.findViewById(R.id.caipudetail_header_nikeName);
        nikeName.setText(mRecipe.nickname);
       TextView time = (TextView) headerView.findViewById(R.id.caipudetail_header_time);
        time.setText(DateTools.timeDate(mRecipe.created+""));
      TextView content = (TextView) headerView.findViewById(R.id.caipudetail_header_content);
       content.setText(mRecipe.descr);
        LinearLayout linearLayout = (LinearLayout) headerView.findViewById(R.id.caipudetail_header_layout);

        for (int i = 0;i<mRecipe.items.size();i++){
             CaipuDetailModel.recipe.itemsModel model = mRecipe.items.get(i);
            View itemView = View.inflate(getApplicationContext(),R.layout.caipu_detai_header_yongliao,null);
            TextView item_name = (TextView) itemView.findViewById(R.id.caipudetail_header_item_name);
            item_name.setText(model.title);
            TextView item_unit = (TextView) itemView.findViewById(R.id.caipudetail_header_item_unit);
            item_unit.setText(model.dosage);
            linearLayout.addView(itemView);
        }


        mCaipuDetailAdapter.addHeaderView(headerView);

    }
}
