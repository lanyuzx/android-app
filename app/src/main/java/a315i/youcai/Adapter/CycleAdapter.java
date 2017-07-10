package a315i.youcai.Adapter;

import android.content.Context;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import a315i.youcai.Model.Home.CycleMode.CycleModel;
import a315i.youcai.R;

/**
 * Created by zhouzunxian on 2017/7/10.
 */

public class CycleAdapter extends BaseQuickAdapter<CycleModel.itemModel,BaseViewHolder> {
    private Context mContex;
    public CycleAdapter(List<CycleModel.itemModel> arrayList, Context context){
        super(R.layout.cycle_item,arrayList);
        mContex = context;
    }
    @Override
    protected void convert(BaseViewHolder helper, CycleModel.itemModel item) {
        Picasso.with(mContext).load(item.thumb).into((ImageView) helper.getView(R.id.cycle_item_Iv));

    }
}
