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
import a315i.youcai.Tools.DataBaseTools;

/**
 * Created by zhouzunxian on 2017/7/10.
 */

public class PrdouctLienaAdapter extends BaseQuickAdapter<HomeModel.HomeChildModel,BaseViewHolder> {
    private Context mContex;
    public PrdouctLienaAdapter(List<HomeModel.HomeChildModel> itms, Context context){
        super(R.layout.prdouct_linearlayout_item,itms);
        this.mContex = context;
    }
    @Override
    protected void convert(BaseViewHolder helper, final HomeModel.HomeChildModel item) {
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
        buyTextView.setText("" + item.buyCout);
        subImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item.buyCout--;

                if (item.buyCout == 0) {
                    item.buyCout = 0;
                    shopImageView.setVisibility(View.VISIBLE);
                    shopLayout.setVisibility(View.GONE);
                    DataBaseTools.getInstance(mContext).delete(item);
                }else {
                    DataBaseTools.getInstance(mContext).save(item);
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
                DataBaseTools.getInstance(mContext).save(item);
                notifyDataSetChanged();
                Intent intent = new Intent();
                intent.setAction("shoppingCountAdd");
//                   Bundle bundle = new Bundle();
//                   bundle.putSerializable("model", (Serializable) model);
//                   intent.putExtras(bundle);
                mContex.sendBroadcast(intent);

            }
        });

        if (item.buyCout > 0) {
            shopImageView.setVisibility(View.GONE);
            shopLayout.setVisibility(View.VISIBLE);
        } else {
            shopImageView.setVisibility(View.VISIBLE);
            shopLayout.setVisibility(View.GONE);
        }

        shopImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item.buyCout = 1;
                DataBaseTools.getInstance(mContext).save(item);
                notifyDataSetChanged();
                Intent intent = new Intent();
                intent.setAction("shoppingCountAdd");
                mContex.sendBroadcast(intent);
            }
        });
        title.setText(item.title);
        guiGe.setText("规格:   " + item.quantity + item.unit + "/份");
        price.setText("¥  " + item.price / 100 + "0");
        if (item.mprice == 0) {
            mPrice.setVisibility(View.GONE);
        } else {
            mPrice.setVisibility(View.VISIBLE);
        }
        mPrice.setText("¥  " + item.mprice / 100 + "0");
        //添加中划线
        mPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        if (item.imgs != null) {
            Picasso.with(mContext).load(item.imgs.get(0)).into(imageView);
        }

    }

}
