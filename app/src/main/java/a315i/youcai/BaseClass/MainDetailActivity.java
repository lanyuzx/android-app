package a315i.youcai.BaseClass;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import org.xutils.http.RequestParams;

import java.util.ArrayList;
import java.util.List;

import a315i.youcai.Adapter.HomeRecycerViewAdapter;
import a315i.youcai.Adapter.MainDetailAdapater;
import a315i.youcai.Adapter.MainDetailComntensAdapter;
import a315i.youcai.Adapter.MainDetailImageShowAdapter;
import a315i.youcai.Fragment.HomeFrament;
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
    private MainDetailAdapater mDetailAdapater;
    private boolean arrowImageClick;

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
                Gson gson = new Gson();
              String items =   gson.toJson(model.item);

               DetailModel.detailItem detailItem = GsonTools.parseJsonWithGson(items,DetailModel.detailItem.class);
                List<DetailModel.detailItem> list = new ArrayList<DetailModel.detailItem>();
                list.add(detailItem);
                mDetailAdapater = new MainDetailAdapater(list);
                mRecyclerView.setAdapter(mDetailAdapater);
                mDetailAdapater.addHeaderView(setupDetailHeaderView(detailItem));


                DetailModel.detail detailMdoel = GsonTools.parseJsonWithGson(gson.toJson(detailItem.detail),DetailModel.detail.class);
                if (detailCommentses==null &&detailMdoel.imgs ==  null){

                }else {
                    mDetailAdapater.addFooterView(setupDetailFooterView(detailMdoel,detailCommentses));

                }



            }
            @Override
            public void onResponseError(Throwable error) {
                ToastTools.showLong(getApplicationContext(),error.getMessage());

            }
        });

    }
    private View setupDetailFooterView(DetailModel.detail detail,List<DetailModel.detailComments> comments){
        View footerView = View.inflate(getApplicationContext(),R.layout.detail_footer,null);
        RelativeLayout relativeLayout = (RelativeLayout) footerView.findViewById(R.id.detailFooterRv);
        final ImageView arrowImageView = (ImageView) footerView.findViewById(R.id.detailFooterArrowIv);
        LinearLayout detailFooterComentLl = (LinearLayout) footerView.findViewById(R.id.detailFooterComentLl);

        final RecyclerView imageRv = (RecyclerView) footerView.findViewById(R.id.detailFooterImageShowRv);
        RecyclerView comentsRv = (RecyclerView) footerView.findViewById(R.id.detailFooterconmentRv);
        arrowImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrowImageClick = !arrowImageClick;
                if (arrowImageClick){
                    arrowImageView.setImageResource(R.drawable.icon_more_up);
                    imageRv.setVisibility(View.GONE);

                }else {
                    arrowImageView.setImageResource(R.drawable.icon_more_down);
                    imageRv.setVisibility(View.VISIBLE);
                }
            }
        });



        if (comments.size() > 0){
            comentsRv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
            comentsRv.setAdapter(new MainDetailComntensAdapter(comments,getApplicationContext()));



        }else {
            detailFooterComentLl.setVisibility(View.GONE);
            comentsRv.setVisibility(View.GONE);
        }

        if (detail.imgs.size() > 0){
            imageRv.setLayoutManager(new GridLayoutManager(getApplicationContext(),1));
            imageRv.setAdapter(new MainDetailImageShowAdapter(detail.imgs,getApplicationContext()));

        }else {
            arrowImageView.setVisibility(View.GONE);
            relativeLayout.setVisibility(View.GONE);

        }





        return footerView;

    }


    private View setupDetailHeaderView(DetailModel.detailItem detailItem) {
        View detailHeadView = View.inflate(getApplicationContext(),R.layout.detail_header,null);
        Banner banner = (Banner) detailHeadView.findViewById(R.id.detailHeaderBr);
        List<String> paths = new ArrayList();
        for (String img : detailItem.imgs){
            paths.add(img);
        }
        banner.setImageLoader(new GlideImageLoader());
        banner.setImages(paths);
        banner.start();
        TextView detailHeaderTitle = (TextView) detailHeadView.findViewById(R.id.detailHeaderTitle);
        TextView detailHeaderContent = (TextView) detailHeadView.findViewById(R.id.detailHeaderContent);
        TextView detailHeadermPrice = (TextView) detailHeadView.findViewById(R.id.detailHeadermPrice);
        TextView detailHeaderPrice = (TextView) detailHeadView.findViewById(R.id.detailHeaderPrice);
        TextView detailHeaderunit = (TextView) detailHeadView.findViewById(R.id.detailHeaderunit);
        detailHeaderTitle.setText(detailItem.title);
        detailHeaderContent.setText(detailItem.descr);
        detailHeaderPrice.setText("¥ "+detailItem.price/100 +"0");
        if (detailItem.mprice != 0){
            detailHeadermPrice.setVisibility(View.VISIBLE);
            detailHeadermPrice.setText("¥ "+detailItem.mprice/100 +"0");
            //添加中划线
            detailHeadermPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        }
        detailHeaderunit.setText(detailItem.quantity + detailItem.unit + "/份");

        return detailHeadView;

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
