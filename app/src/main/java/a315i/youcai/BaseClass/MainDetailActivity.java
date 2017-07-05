package a315i.youcai.BaseClass;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import org.xutils.http.RequestParams;

import java.util.List;

import a315i.youcai.Adapter.HomeRecycerViewAdapter;
import a315i.youcai.Model.Home.Detai.DetailModel;
import a315i.youcai.Model.Home.HomeModel;
import a315i.youcai.R;
import a315i.youcai.Tools.GsonTools;
import a315i.youcai.Tools.LogTools;
import a315i.youcai.Tools.NetWorkTools;
import a315i.youcai.Tools.ToastTools;

/**
 * Created by zhouzunxian on 2017/7/5.
 */

public class MainDetailActivity extends AppCompatActivity implements View.OnClickListener {
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity);
        findViewById(R.id.detailBackIv).setOnClickListener(this);
        findViewById(R.id.detailShareIv).setOnClickListener(this);
        mRecyclerView = (RecyclerView) findViewById(R.id.detailRv);
        LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(manager);
        Intent intent = getIntent();
        LogTools.i(intent.getStringExtra("detailUrl"));
        setupData(intent.getStringExtra("detailUrl"));

    }

    private void setupData(String detailUrl){
        RequestParams params = new RequestParams(detailUrl);
        NetWorkTools.request(NetWorkTools.requestType.GET, params, new NetWorkTools.CallBack() {
            @Override
            public void onResponse(String response) {
              LogTools.e(response);
                DetailModel model = GsonTools.parseJsonWithGson(response,DetailModel.class);
                List<DetailModel.detailComments> detailCommentses = model.comments;
                List<DetailModel.detailItem> detailItems = model.item;

            }
            @Override
            public void onResponseError(Throwable error) {
                ToastTools.showLong(getApplicationContext(),error.getMessage());

            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.detailBackIv:
                finish();
                break;
            case R.id.detailShareIv:
                break;
        }

    }
}
