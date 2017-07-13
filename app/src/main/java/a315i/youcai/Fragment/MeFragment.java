package a315i.youcai.Fragment;



import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import a315i.youcai.Activity.LoginActivity;
import a315i.youcai.Activity.MoreInfoActivity;
import a315i.youcai.BaseClass.MainFragment;
import a315i.youcai.R;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by zhouzunxian on 2017/7/3.
 */

public class MeFragment extends MainFragment implements View.OnClickListener {

    private LinearLayout mItemLayout;
    private int[] mImageResources = {
            R.drawable.icon_my_order,
            R.drawable.icon_my_exchange,
            R.drawable.icon_my_card,
            R.drawable.icon_my_collect,
            R.drawable.icon_my_settings
    };
    private String[] mTitles = {
            "我的套餐",
            "商品兑换",
            "我的余额",
            "我的收藏",
            "更多信息"
    };
    private String[] mTitleChilds = {
            "选菜",
            "兑换",
            "余额",
            "收藏",
            ""
    };
    @Override
    public void setupData() {

    }

    @Override
    public View setupView() {
        View meView = View.inflate(getActivity(), R.layout.fragment_me,null);
        mItemLayout = (LinearLayout) meView.findViewById(R.id.mine_layout);
        meView.findViewById(R.id.mine_login).setOnClickListener(this);
        meView.findViewById(R.id.mine_order).setOnClickListener(this);
        meView.findViewById(R.id.mine_red_package).setOnClickListener(this);
        meView.findViewById(R.id.mine_location).setOnClickListener(this);
        setupItemView();
        return meView;
    }

    private void setupItemView(){
        for (int i = 0;i<5;i++){
            View itemView = View.inflate(getContext(),R.layout.mine_item,null);
            itemView.setId(i);
            itemView.setOnClickListener(this);
            CircleImageView item_icon = (CircleImageView) itemView.findViewById(R.id.mine_item_icon);
            item_icon.setImageResource(mImageResources[i]);
            TextView item_title = (TextView) itemView.findViewById(R.id.mine_item_title);
            item_title.setText(mTitles[i]);
            TextView item_mtitle = (TextView) itemView.findViewById(R.id.mine_item_mtitle);
            item_mtitle.setText(mTitleChilds[i]);
            mItemLayout.addView(itemView);
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case 0:
                setupLoginAcitity();
                break;
            case 1:
                setupLoginAcitity();
                break;
            case 2:
                setupLoginAcitity();
                break;
            case 3:
                setupLoginAcitity();
                break;
            case 4:
                Intent intent = new Intent(getContext(), MoreInfoActivity.class);
                startActivity(intent);
                break;
            case R.id.mine_red_package:
                setupLoginAcitity();
                break;
            case R.id.mine_order:
                setupLoginAcitity();
                break;
            case R.id.mine_location:
                setupLoginAcitity();
                break;
            case R.id.mine_login:
                setupLoginAcitity();
                break;

        }

    }

    private void setupLoginAcitity(){
        Intent intent = new Intent(getContext(), LoginActivity.class);
        startActivity(intent);
    }
}
