package a315i.youcai.Adapter;

import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.google.gson.Gson;

import java.util.List;
import java.util.Map;

import a315i.youcai.Model.Home.Detai.DetailModel;
import a315i.youcai.R;
import a315i.youcai.Tools.GsonTools;
import a315i.youcai.Tools.LogTools;

/**
 * Created by zhouzunxian on 2017/7/5.
 */

public class MainDetailAdapater extends  BaseQuickAdapter<DetailModel.detailItem,BaseViewHolder>  {

    public MainDetailAdapater(List<DetailModel.detailItem> list){
        super(R.layout.detail_item,list);

    }

    @Override
    protected void convert(BaseViewHolder helper, DetailModel.detailItem item) {
        //商品内容
        TextView contentTv = helper.getView(R.id.contentTv);
        TextView contentinfoTv = helper.getView(R.id.contentinfoTv);
        //保存方法
        TextView saveTv = helper.getView(R.id.saveTv);
        TextView saveinfoTv = helper.getView(R.id.saveinfoTv);
        //保质期
        TextView ShelfLifeTv = helper.getView(R.id.ShelfLifeTv);
        TextView ShelfLifeInfoTv = helper.getView(R.id.ShelfLifeInfoTv);
        //温馨提示
        TextView promptTv = helper.getView(R.id.promptTv);
        TextView promptInfoTv = helper.getView(R.id.promptInfoTv);
        //有菜推荐
        TextView tuiJianTv = helper.getView(R.id.tuiJianTv);
        TextView tuiJianInfoTv = helper.getView(R.id.tuiJianInfoTv);

        if (item.more != null){
          List<String> mores = (List<String>) item.more;
           Object detail =  item.detail;
            Gson gson = new Gson();
            DetailModel.detail detailMdoel = GsonTools.parseJsonWithGson(gson.toJson(item.detail),DetailModel.detail.class);
            int size = mores.size()-1;
            if (size == -1){
                contentTv.setVisibility(View.GONE);
                contentinfoTv.setVisibility(View.GONE);
                saveTv.setVisibility(View.GONE);
                saveinfoTv.setVisibility(View.GONE);
                ShelfLifeTv.setVisibility(View.GONE);
                ShelfLifeInfoTv.setVisibility(View.GONE);
                promptTv.setVisibility(View.GONE);
                promptInfoTv.setVisibility(View.GONE);
                if (detailMdoel.content ==null){
                    tuiJianTv.setVisibility(View.GONE);
                    tuiJianInfoTv.setVisibility(View.GONE);

                }else {
                    tuiJianTv.setText("【有菜推荐】");
                    tuiJianInfoTv.setText(detailMdoel.content);
                }

            }else if (size == 0){
                contentTv.setVisibility(View.VISIBLE);
                contentinfoTv.setVisibility(View.VISIBLE);
                saveTv.setVisibility(View.VISIBLE);
                saveinfoTv.setVisibility(View.VISIBLE);
                ShelfLifeTv.setVisibility(View.GONE);
                ShelfLifeInfoTv.setVisibility(View.GONE);
                promptTv.setVisibility(View.GONE);
                promptInfoTv.setVisibility(View.GONE);
                if (detailMdoel.content ==null){
                    tuiJianTv.setVisibility(View.GONE);
                    tuiJianInfoTv.setVisibility(View.GONE);

                }else {
                    tuiJianTv.setText("【有菜推荐】");
                    tuiJianInfoTv.setText(detailMdoel.content);
                }
                Object content = mores.get(0);
                List<String> indexArr = (List<String>) content;
                contentTv.setText(indexArr.get(0));
                contentinfoTv.setText(indexArr.get(1));


            }else if (size == 1){
                contentTv.setVisibility(View.VISIBLE);
                contentinfoTv.setVisibility(View.VISIBLE);
                saveTv.setVisibility(View.VISIBLE);
                saveinfoTv.setVisibility(View.VISIBLE);
                ShelfLifeTv.setVisibility(View.GONE);
                ShelfLifeInfoTv.setVisibility(View.GONE);
                promptTv.setVisibility(View.GONE);
                promptInfoTv.setVisibility(View.GONE);
                if (detailMdoel.content ==null){
                    tuiJianTv.setVisibility(View.GONE);
                    tuiJianInfoTv.setVisibility(View.GONE);

                }else {
                    tuiJianTv.setText("【有菜推荐】");
                    tuiJianInfoTv.setText(detailMdoel.content);
                }
                Object content = mores.get(0);
                List<String> indexArr = (List<String>) content;
                contentTv.setText(indexArr.get(0));
                contentinfoTv.setText(indexArr.get(1));
                Object save = mores.get(1);
                List<String> saveArr = (List<String>) save;
                saveTv.setText(saveArr.get(0));
                saveinfoTv.setText(saveArr.get(1));

            }else if (size ==2){
                contentTv.setVisibility(View.VISIBLE);
                contentinfoTv.setVisibility(View.VISIBLE);
                saveTv.setVisibility(View.VISIBLE);
                saveinfoTv.setVisibility(View.VISIBLE);
                ShelfLifeTv.setVisibility(View.VISIBLE);
                ShelfLifeInfoTv.setVisibility(View.VISIBLE);
                promptTv.setVisibility(View.GONE);
                promptInfoTv.setVisibility(View.GONE);
                if (detailMdoel.content ==null){
                    tuiJianTv.setVisibility(View.GONE);
                    tuiJianInfoTv.setVisibility(View.GONE);

                }else {
                    tuiJianTv.setText("【有菜推荐】");
                    tuiJianInfoTv.setText(detailMdoel.content);
                }
                Object content = mores.get(0);
                List<String> indexArr = (List<String>) content;
                contentTv.setText(indexArr.get(0));
                contentinfoTv.setText(indexArr.get(1));

                Object save = mores.get(1);
                List<String> saveArr = (List<String>) save;
                saveTv.setText(saveArr.get(0));
                saveinfoTv.setText(saveArr.get(1));

                Object ShelfLife = mores.get(2);
                List<String> ShelfLifeArr = (List<String>) ShelfLife;
                ShelfLifeTv.setText(ShelfLifeArr.get(0));
                ShelfLifeInfoTv.setText(ShelfLifeArr.get(1));

            }else if (size ==3){

                contentTv.setVisibility(View.VISIBLE);
                contentinfoTv.setVisibility(View.VISIBLE);
                saveTv.setVisibility(View.VISIBLE);
                saveinfoTv.setVisibility(View.VISIBLE);
                ShelfLifeTv.setVisibility(View.VISIBLE);
                ShelfLifeInfoTv.setVisibility(View.VISIBLE);
                promptTv.setVisibility(View.VISIBLE);
                promptInfoTv.setVisibility(View.VISIBLE);
                if (detailMdoel.content ==null){
                    tuiJianTv.setVisibility(View.GONE);
                    tuiJianInfoTv.setVisibility(View.GONE);

                }else {
                    tuiJianTv.setText("【有菜推荐】");
                    tuiJianInfoTv.setText(detailMdoel.content);
                }
                Object content = mores.get(0);
                List<String> indexArr = (List<String>) content;
                contentTv.setText(indexArr.get(0));
                contentinfoTv.setText(indexArr.get(1));

                Object save = mores.get(1);
                List<String> saveArr = (List<String>) save;
                saveTv.setText(saveArr.get(0));
                saveinfoTv.setText(saveArr.get(1));

                Object ShelfLife = mores.get(2);
                List<String> ShelfLifeArr = (List<String>) ShelfLife;
                ShelfLifeTv.setText(ShelfLifeArr.get(0));
                ShelfLifeInfoTv.setText(ShelfLifeArr.get(1));

                Object prompt = mores.get(3);
                List<String> promptArr = (List<String>) prompt;
                promptTv.setText(promptArr.get(0));
                promptInfoTv.setText(promptArr.get(1));

            }

        }

    }
}
