package a315i.youcai.Adapter;

import android.content.Context;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.squareup.picasso.Picasso;

import java.util.List;

import a315i.youcai.Model.Home.CaiPuModel.CaipuModel;
import a315i.youcai.R;

/**
 * Created by zhouzunxian on 2017/7/11.
 */

public class CaiPuAdapter extends BaseQuickAdapter<CaipuModel.recipesModel,BaseViewHolder> {
    private Context mContex;
    public CaiPuAdapter(List<CaipuModel.recipesModel> list,Context context){
        super(R.layout.caipu_item,list);
        this.mContex = context;
    }
    @Override
    protected void convert(BaseViewHolder helper, CaipuModel.recipesModel item) {
        Picasso.with(mContext).load(item.imgs.get(0)).into((ImageView) helper.getView(R.id.caipu_icon));
        helper.setText(R.id.caipu_title,item.title);
        helper.setText(R.id.caipu_nikeName,item.nickname);
        String contenStr = "";
        for (String itemStr :item.items){
            contenStr += itemStr + ",";

        }
        helper.setText(R.id.caipu_content,contenStr);
        if (item.status == 1){
            helper.setVisible(R.id.caipu_jing,false);
        }else {
            helper.setVisible(R.id.caipu_jing,true);
        }

    }
}
