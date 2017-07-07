package a315i.youcai.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.squareup.picasso.Picasso;

import java.util.List;

import a315i.youcai.Model.Home.Detai.DetailModel;
import a315i.youcai.R;
import a315i.youcai.Tools.LogTools;

/**
 * Created by zhouzunxian on 2017/7/7.
 */

public class MainDetailImageShowAdapter extends BaseQuickAdapter<String,BaseViewHolder> {

    private Context mContex;
    public MainDetailImageShowAdapter(List<String> images ,Context contex) {
        super(R.layout.detail_footer_image,images);
        this.mContex = contex;
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        ImageView imageView = helper.getView(R.id.detail_footer_IV);
        Picasso.with(mContext).load(item).into(imageView);
        LogTools.e(item);

    }
}
