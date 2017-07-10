package a315i.youcai.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.squareup.picasso.Picasso;

import java.util.List;

import a315i.youcai.Model.Home.HomeModel;
import a315i.youcai.R;

/**
 * Created by zhouzunxian on 2017/7/10.
 */

public class PrdouctGridAdapter extends BaseQuickAdapter<HomeModel.HomeChildModel,BaseViewHolder> {
    private Context mContex;
    public PrdouctGridAdapter(List< HomeModel.HomeChildModel> items,Context context){
        super(R.layout.prdouct_grildlayout_item,items);
        this.mContex = context;
    }
    @Override
    protected void convert(BaseViewHolder helper,final HomeModel.HomeChildModel item) {
      if (helper.getLayoutPosition()% 13 == 0){
          helper.setVisible(R.id.free_shiping,true);
      } else {
          helper.setVisible(R.id.free_shiping,false);
      }
      if (item.imgs != null){
          if (!item.imgs.isEmpty() ){
              Picasso.with(mContext).load(item.imgs.get(0)).into((ImageView) helper.getView(R.id.productIcon));
          }
      }
        helper.setText(R.id.pruductTitle,item.title);
        helper.setText(R.id.pruductUnit,item.quantity+ item.unit +"/份");
        helper.setText(R.id.pruductPrice,"¥  "+item.price/100 + "0");
        //添加中划线
        TextView pruductmPrice = helper.getView(R.id.pruductmPrice);
        pruductmPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        if (item.mprice == 0){
            pruductmPrice.setVisibility(View.GONE);
        }else {
            pruductmPrice.setVisibility(View.VISIBLE);
        }
        helper.setText(R.id.pruductmPrice,"¥  "+item.mprice/100 + "0");
        final ImageView shopImageView = helper.getView(R.id.productShopCar);
        final LinearLayout shopLayout = helper.getView(R.id.shopLayout);
        ImageView subImage = (ImageView) shopLayout.findViewById(R.id.subImage);
        ImageView addImage = (ImageView) shopLayout.findViewById(R.id.addImage);
        TextView buyTextView = (TextView) shopLayout.findViewById(R.id.buyCout);
        buyTextView.setText("" + item.buyCout);
        if (item.buyCout > 0) {
            shopImageView.setVisibility(View.GONE);
            shopLayout.setVisibility(View.VISIBLE);
        } else {
            shopImageView.setVisibility(View.VISIBLE);
            shopLayout.setVisibility(View.GONE);
        }
        subImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item.buyCout--;

                if (item.buyCout == 0) {
                    item.buyCout = 0;
                    shopImageView.setVisibility(View.VISIBLE);
                    shopLayout.setVisibility(View.GONE);
                }
                notifyDataSetChanged();
                Intent intent = new Intent();
                intent.setAction("shoppingCountSub");
                mContex.sendBroadcast(intent);
            }
        });
        addImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item.buyCout++;
                notifyDataSetChanged();
                Intent intent = new Intent();
                intent.setAction("shoppingCountAdd");
//                   Bundle bundle = new Bundle();
//                   bundle.putSerializable("model", (Serializable) model);
//                   intent.putExtras(bundle);
                mContex.sendBroadcast(intent);

            }
        });
        shopImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item.buyCout = 1;
                notifyDataSetChanged();
                Intent intent = new Intent();
                intent.setAction("shoppingCountAdd");
                mContex.sendBroadcast(intent);
            }
        });



    }
}
