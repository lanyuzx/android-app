package a315i.youcai.Adapter;

import android.content.Context;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.squareup.picasso.Picasso;

import java.util.List;

import a315i.youcai.Model.Home.Detai.DetailModel;
import a315i.youcai.R;
import a315i.youcai.Tools.DateTools;

/**
 * Created by zhouzunxian on 2017/7/7.
 */

public class MainDetailComntensAdapter extends BaseQuickAdapter<DetailModel.detailComments,BaseViewHolder> {
    private Context mContex;
    public MainDetailComntensAdapter(List<DetailModel.detailComments > commentses,Context context){
        super(R.layout.detai_footer_coments,commentses);
        this.mContex = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, DetailModel.detailComments item) {
        ImageView iconImage = helper.getView(R.id.detailFooterComentIcon);
        Picasso.with(mContext).load(item.headimg).into(iconImage);
        helper.setText(R.id.detailFooterComentPhone,item.nickname);
        helper.setText(R.id.detailFooterComentContent,item.content);
       helper.setText(R.id.detailFooterComentTime, DateTools.timeDate(item.modified) + "");






    }
}
