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
 * Created by zhouzunxian on 2017/7/13.
 */

public class CaipuDetailFooterCommnetAdapter extends BaseQuickAdapter<DetailModel.detailComments,BaseViewHolder> {
    private Context mContext;
    public CaipuDetailFooterCommnetAdapter(List<DetailModel.detailComments>  list,Context context){
        super(R.layout.caipu_detail_conments,list);
        this.mContext = context;
    }
    @Override
    protected void convert(BaseViewHolder helper, DetailModel.detailComments item) {
        Picasso.with(mContext).load(item.headimg).into((ImageView) helper.getView(R.id.caipu_detai_comment_icon));
        helper.setText(R.id.caipu_detai_comment_nikename,item.nickname);
        helper.setText(R.id.caipu_detai_comment_time, DateTools.timeDate(item.created));
        helper.setText(R.id.caipu_detai_comment_content,item.content);

    }
}
