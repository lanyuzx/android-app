package a315i.youcai.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.List;

import a315i.youcai.Model.Home.HomeModel;
import a315i.youcai.R;
import a315i.youcai.Tools.DataBaseTools;

/**
 * Created by zhouzunxian on 2017/7/12.
 */

public class ShoppingAdapter extends BaseQuickAdapter<HomeModel.HomeChildModel ,BaseViewHolder> {
    private Context mContext;
    public ShoppingAdapter(List<HomeModel.HomeChildModel> list,Context context){
        super(R.layout.shopping_item,list);
        this.mContext = context;
    }
    @Override
    protected void convert(BaseViewHolder helper, final HomeModel.HomeChildModel item) {
        Picasso.with(mContext).load(item.imgs.get(0)).into((ImageView) helper.getView(R.id.shopping_icon));
        helper.setText(R.id.shop_title,item.title);
        helper.setText(R.id.shop_unit_price,item.quantity+item.unit +"/份"+ " ¥" + item.price /100 + "0"+"元");
        helper.setText(R.id.shop_buyCout,item.buyCout+"");
        helper.getView(R.id.shop_addImage).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item.buyCout ++ ;
                DataBaseTools.getInstance(mContext).save(item);
                notifyDataSetChanged();
                Intent intent = new Intent();
                intent.setAction("shoppingCountAdd");
//                   Bundle bundle = new Bundle();
//                   bundle.putSerializable("model", (Serializable) model);
//                   intent.putExtras(bundle);
                mContext.sendBroadcast(intent);

            }
        });
        helper.getView(R.id.shop_subImage).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item.buyCout--;
                if (item.buyCout == 0){
                    item.buyCout = 0;
                    DataBaseTools.getInstance(mContext).delete(item);
                    Intent intent = new Intent();
                    intent.setAction("shoppingCountDelete");
                    mContext.sendBroadcast(intent);
                    notifyDataSetChanged();


                }else {
                    DataBaseTools.getInstance(mContext).save(item);
                    Intent intent = new Intent();
                    intent.setAction("shoppingCountSub");
//                   Bundle bundle = new Bundle();
//                   bundle.putSerializable("model", (Serializable) model);
//                   intent.putExtras(bundle);
                    mContext.sendBroadcast(intent);

                }
                notifyDataSetChanged();

            }
        });

    }

}
