package a315i.youcai.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.SectionEntity;
import com.squareup.picasso.Picasso;

import org.xutils.ex.DbException;

import java.io.Serializable;
import java.lang.reflect.Modifier;
import java.util.List;

import a315i.youcai.Model.Home.HomeModel;
import a315i.youcai.R;
import a315i.youcai.Tools.DataBaseTools;
import a315i.youcai.Tools.LogTools;

/**
 * Created by zhouzunxian on 2017/7/4.
 */

public class HomeRecycerViewAdapter extends BaseSectionQuickAdapter<HomeRecycerViewAdapter.MySection,BaseViewHolder> {

    private Context mContex;


//    public HomeRecycerViewAdapter(List<HomeModel.HomeChildModel> itemList,Context context){
//        super(R.layout.home_item,itemList);
//        this.mContex = context;
//    }
  public HomeRecycerViewAdapter(int layoutResId, int sectionHeadResId, List data,Context context) {
    super(layoutResId, sectionHeadResId, data);
      this.mContex = context;
  }
    @Override
    protected void convertHead(BaseViewHolder helper, MySection item) {
       if (item.isHeader){
           helper.setText(R.id.headerItemTextView,item.header);
       }

    }

    @Override
    protected void convert(BaseViewHolder helper, MySection item) {

       if (!item.isHeader) {
          final HomeModel.HomeChildModel model = item.t;
           LogTools.i("1111");
           ImageView imageView = helper.getView(R.id.imageIcon);
           TextView title = helper.getView(R.id.title);
           TextView guiGe = helper.getView(R.id.specifications);
           TextView price = helper.getView(R.id.price);
           TextView mPrice = helper.getView(R.id.mprice);
           final ImageView shopImageView = helper.getView(R.id.shoppingcar);
           final LinearLayout shopLayout = helper.getView(R.id.shopLayout);
           ImageView subImage = (ImageView) shopLayout.findViewById(R.id.subImage);
           ImageView addImage = (ImageView) shopLayout.findViewById(R.id.addImage);
           TextView buyTextView = (TextView) shopLayout.findViewById(R.id.buyCout);
           buyTextView.setText("" + model.buyCout);
           subImage.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   model.buyCout--;

                   if (model.buyCout == 0) {
                       model.buyCout = 0;
                       shopImageView.setVisibility(View.VISIBLE);
                       shopLayout.setVisibility(View.GONE);

                       DataBaseTools.getInstance(mContext).delete(model);

                   }else {
                       DataBaseTools.getInstance(mContext).save(model);
                   }
                   notifyDataSetChanged();
                   Intent intent = new Intent();
                   intent.setAction("shoppingCountSub");
//                   Bundle bundle = new Bundle();
//                   bundle.putSerializable("model", (Serializable) model);
//                   intent.putExtras(bundle);
                   mContex.sendBroadcast(intent);
               }
           });
           addImage.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   model.buyCout++;
                   notifyDataSetChanged();
                   Intent intent = new Intent();
                 intent.setAction("shoppingCountAdd");
//                   Bundle bundle = new Bundle();
//                   bundle.putSerializable("model", (Serializable) model);
//                   intent.putExtras(bundle);
                   mContex.sendBroadcast(intent);

                   DataBaseTools.getInstance(mContext).save(model);


               }
           });

           if (model.buyCout > 0) {
               shopImageView.setVisibility(View.GONE);
               shopLayout.setVisibility(View.VISIBLE);
           } else {
               shopImageView.setVisibility(View.VISIBLE);
               shopLayout.setVisibility(View.GONE);
           }

           shopImageView.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   model.buyCout = 1;

                   DataBaseTools.getInstance(mContext).save(model);
                   notifyDataSetChanged();
                   Intent intent = new Intent();
                  intent.setAction("shoppingCountAdd");
//                   Bundle bundle = new Bundle();
//                   bundle.putSerializable("model", (Serializable) model);
//                   intent.putExtras(bundle);
                   mContex.sendBroadcast(intent);
               }
           });
           title.setText(model.title);
           guiGe.setText("规格:   " + model.quantity + model.unit + "/份");
           price.setText("¥  " + model.price / 100 + "0");
           if (model.mprice == 0) {
               mPrice.setVisibility(View.GONE);
           } else {
               mPrice.setVisibility(View.VISIBLE);
           }
           mPrice.setText("¥  " + model.mprice / 100 + "0");
           //添加中划线
           mPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
           if (model.imgs != null) {
               Picasso.with(mContext).load(model.imgs.get(0)).into(imageView);
           }

       }
    }



    //    @Override
//    protected void convert(BaseViewHolder helper, final HomeModel.HomeChildModel item) {
//
//        ImageView imageView =  helper.getView(R.id.imageIcon);
//        TextView title = helper.getView(R.id.title);
//        TextView guiGe = helper.getView(R.id.specifications);
//        TextView price = helper.getView(R.id.price);
//        TextView mPrice = helper.getView(R.id.mprice);
//        final ImageView shopImageView = helper.getView(R.id.shoppingcar);
//        final LinearLayout shopLayout = helper.getView(R.id.shopLayout);
//        ImageView subImage = (ImageView) shopLayout.findViewById(R.id.subImage);
//        ImageView addImage = (ImageView) shopLayout.findViewById(R.id.addImage);
//        TextView buyTextView = (TextView) shopLayout.findViewById(R.id.buyCout);
//        buyTextView.setText("" + item.buyCout);
//        subImage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                item.buyCout --;
//
//                if (item.buyCout == 0){
//                    item.buyCout = 0;
//                    shopImageView.setVisibility(View.VISIBLE);
//                    shopLayout.setVisibility(View.GONE);
//                }
//                notifyDataSetChanged();
//            }
//        });
//        addImage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                item.buyCout++;
//                notifyDataSetChanged();
//            }
//        });
//
//        if (item.buyCout > 0){
//            shopImageView.setVisibility(View.GONE);
//            shopLayout.setVisibility(View.VISIBLE);
//        }else {
//            shopImageView.setVisibility(View.VISIBLE);
//            shopLayout.setVisibility(View.GONE);
//        }
//
//        shopImageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                item.buyCout =1;
//                notifyDataSetChanged();
//            }
//        });
//        title.setText(item.title);
//        guiGe.setText("规格:   " +item.quantity+ item.unit + "/份" );
//        price.setText("¥  "+item.price/100 + "0");
//        if (item.mprice == 0) {
//            mPrice.setVisibility(View.GONE);
//        }else {
//            mPrice.setVisibility(View.VISIBLE);
//        }
//        mPrice.setText("¥  "+item.mprice/100+"0");
//        //添加中划线
//        mPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
//        if (item.imgs != null){
//            Picasso.with(mContext).load(item.imgs.get(0)).into(imageView);
//        }
//
//
//    }

//    @Override
//    protected void convertHead(BaseViewHolder helper, HomeModel.HomeChildModel item) {
//
//    }



    public static class MySection extends SectionEntity<HomeModel.HomeChildModel>{

        public MySection(boolean isHeader, String header) {
            super(isHeader, header);
        }
        public MySection(HomeModel.HomeChildModel v){
            super(v);
        }
    }
}
