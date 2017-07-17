package a315i.youcai.Activity;

import android.app.Fragment;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Scroller;
import android.widget.TextView;

import org.xutils.http.RequestParams;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import a315i.youcai.Adapter.SearchAdapter;
import a315i.youcai.Model.Home.HomeModel;
import a315i.youcai.R;
import a315i.youcai.Tools.GsonTools;
import a315i.youcai.Tools.LogTools;
import a315i.youcai.Tools.NetWorkTools;

public class SearchActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView mRecyclerView;
    private EditText mEditText;
    private boolean mIsChanged;
    private List<HomeModel.HomeChildModel> modelList;
    private SearchAdapter mSearchAdapter;
    private GridLayoutManager mGridLayoutManager;
    private Set mSet;
    private FrameLayout mFrameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        findViewById(R.id.search_Iv).setOnClickListener(this);
        findViewById(R.id.search_backIv).setOnClickListener(this);
        findViewById(R.id.search_changeLayout).setOnClickListener(this);
        mSet = new HashSet();
        mRecyclerView = (RecyclerView) findViewById(R.id.search_Rv);
        mEditText = (EditText) findViewById(R.id.search_Et);
        mFrameLayout = (FrameLayout) findViewById(R.id.history_search_container_frameLayout);
        setupHistoryView();
        mEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                LogTools.e(s.toString());
                SharedPreferences sharedPreferences = getSharedPreferences("historySearch",MODE_PRIVATE);
               SharedPreferences.Editor editor = sharedPreferences.edit();

                String utfStr = null;
                try {
                    utfStr = new String(s.toString().getBytes("utf-8"),"utf-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                Set set = sharedPreferences.getStringSet("historyKey",new HashSet<String>());
                List<String> historyStrs = new ArrayList<>();
                Iterator iterator = set.iterator();
                while (iterator.hasNext()){
                    String saveStr = (String) iterator.next();
                    historyStrs.add(saveStr);

                }

                if (!historyStrs.contains(utfStr)){
                    for (String saveStr : historyStrs){
                        mSet.add(saveStr);
                    }

                    mSet.add(utfStr);
                    editor.putStringSet("historyKey",mSet);
                    editor.commit();
                }

                mFrameLayout.setVisibility(View.GONE);
                mRecyclerView.setVisibility(View.VISIBLE);


                setupData(utfStr);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void setupHistoryView(){

        View historyView = View.inflate(getApplicationContext(),R.layout.history_serach,null);
        LinearLayout linearLayout = (LinearLayout) historyView.findViewById(R.id.history_search_container);
        mFrameLayout.addView(historyView);
        SharedPreferences sharedPreferences = getSharedPreferences("historySearch",MODE_PRIVATE);
        if (sharedPreferences != null){
            Set set = sharedPreferences.getStringSet("historyKey",new HashSet<String>());
            if (!set.isEmpty()){
                List<String> historyStrs = new ArrayList<>();
                Iterator iterator = set.iterator();
                while (iterator.hasNext()){
                    String saveStr = (String) iterator.next();
                    historyStrs.add(saveStr);
                }
                    mRecyclerView.setVisibility(View.GONE);
                    mFrameLayout.setVisibility(View.VISIBLE);

                    for (int i = 0;i<historyStrs.size();i++){
                        View view = View.inflate(getApplicationContext(),R.layout.moreinfo_item,null);
                        linearLayout.addView(view);
                        TextView textView = (TextView) view.findViewById(R.id.moreinfo_item_title);
                        textView.setText(historyStrs.get(i));
                        view.findViewById(R.id.moreinfo_item_arrow).setVisibility(View.GONE);
                    }




            }else {
                mFrameLayout.setVisibility(View.GONE);

            }

        }



    }

  private void setupData(String keyWord){

      if (keyWord != null){
          RequestParams params = new RequestParams("https://api.youcai.xin/item/search?word=" + keyWord);
          NetWorkTools.request(NetWorkTools.requestType.GET, params, new NetWorkTools.CallBack() {
              @Override
              public void onResponse(String response) {
                  LogTools.e(response);
                 HomeModel homeModel = GsonTools.parseJsonWithGson(response,HomeModel.class);
                  modelList = homeModel.items;
                  mRecyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
                  mSearchAdapter = new SearchAdapter(modelList,getApplicationContext());
                  mRecyclerView.setAdapter(mSearchAdapter);


              }

              @Override
              public void onResponseError(Throwable error) {

              }
          });
      }

  }
  private void setupChangeLayout(){

      for (int i = 0; i < modelList.size(); i++) {
          modelList.get(i).layoutType = (mIsChanged ? 0 : 1);
      }
      if (mIsChanged) {
          mGridLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
          mRecyclerView.setLayoutManager(mGridLayoutManager);
          mRecyclerView.addItemDecoration(new ProductActivity.SpacesItemDecoration(20));
      } else {

          mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
      }
      mSearchAdapter.notifyDataSetChanged();
      mIsChanged = !mIsChanged;


  }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.search_Iv:
                break;
            case R.id.search_backIv:
                finish();
                break;
            case R.id.search_changeLayout:
                setupChangeLayout();
                break;
        }

    }
}
