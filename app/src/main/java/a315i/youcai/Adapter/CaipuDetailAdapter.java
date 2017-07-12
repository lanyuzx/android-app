package a315i.youcai.Adapter;

import android.content.Context;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.squareup.picasso.Picasso;

import java.util.List;

import a315i.youcai.Model.Home.CaiPuModel.CaipuDetailModel;
import a315i.youcai.R;

/**
 * Created by zhouzunxian on 2017/7/12.
 */

public class CaipuDetailAdapter extends BaseQuickAdapter<CaipuDetailModel.recipe.stepsModel,BaseViewHolder> {

    private Context mContenx;
   public CaipuDetailAdapter(List<CaipuDetailModel.recipe.stepsModel> list,Context context){
       super(R.layout.caipu_detal_item,list);
       this.mContenx = context;
   }

    @Override
    protected void convert(BaseViewHolder helper, CaipuDetailModel.recipe.stepsModel item) {
        Picasso.with(mContext).load(item.img).into((ImageView) helper.getView(R.id.caipudetail_item_icon));
        helper.setText(R.id.caipudetail_item_introduce,item.text);

    }
}
