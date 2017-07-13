package a315i.youcai.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import a315i.youcai.R;

public class MoreInfoActivity extends AppCompatActivity implements View.OnClickListener {

    private LinearLayout mLinearLayout;
    private String[] item_titles = {
            "设置密码",
            "客服电话",
            "使用帮助",
            "拼团流程",
            "问题反馈",
            "关于有菜"
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moreinfo);
        findViewById(R.id.moreInfo_backIv).setOnClickListener(this);
        mLinearLayout = (LinearLayout) findViewById(R.id.moreInfo_itemLayout);
        for (int i = 0;i<item_titles.length;i++){
            View moreInfoItem = View.inflate(getApplicationContext(),R.layout.moreinfo_item,null );
            moreInfoItem.setId(i + 100);
            moreInfoItem.setOnClickListener(this);
             TextView item_title = (TextView) moreInfoItem.findViewById(R.id.moreinfo_item_title);
            item_title.setText(item_titles[i]);
            mLinearLayout.addView(moreInfoItem);
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.moreInfo_backIv:
                finish();
                break;
            case 0+ 100:

                break;
            case 1+ 100:

                break;
            case 2+ 100:
                setupWebViewActivity("帮助","https://m.youcai.xin/help");

                break;
            case 3+ 100:
                setupWebViewActivity("拼团流程","https://m.youcai.xin/static/help/group_rule.html");
                break;
            case 4+ 100:
                Intent intent = new Intent(getApplicationContext(),FeedbackActivity.class);
                startActivity(intent);
                break;
            case 5+ 100:
                setupWebViewActivity("关于有菜","https://m.youcai.xin/about");
                break;
        }

    }

    private void setupWebViewActivity(String title,String url){
        Intent intent = new Intent(getApplicationContext(),WebViewActivity.class);
        intent.putExtra("title",title);
        intent.putExtra("webViewUrl",url);
        startActivity(intent);
    }
}
