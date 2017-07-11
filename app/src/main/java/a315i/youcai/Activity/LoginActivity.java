package a315i.youcai.Activity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import a315i.youcai.R;


public class LoginActivity extends AppCompatActivity {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private List<View> mViewList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mViewList = new ArrayList<>();
        mTabLayout = (TabLayout) findViewById(R.id.login_tabLaout);
        mViewPager = (ViewPager) findViewById(R.id.login_viewPager);
        mTabLayout.addTab(mTabLayout.newTab().setText("密码登录"));
        mTabLayout.addTab(mTabLayout.newTab().setText("短信登录"));
        mViewPager.setAdapter(new setupViewPage());

        mTabLayout.setupWithViewPager(mViewPager);

        findViewById(R.id.login_backIv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        View loginPasswordView = View.inflate(getApplicationContext(),R.layout.login_password,null);
        mViewList.add(loginPasswordView);
        View loginMessageView = View.inflate(getApplicationContext(),R.layout.login_message,null);
        mViewList.add(loginMessageView);





    }

    private class setupViewPage extends PagerAdapter {
        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            container.addView(mViewList.get(position));

            return mViewList.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {

            container.removeViewAt(position);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            if (position == 0){
                return "密码登录";
            }else {
                return "短信登录";
            }
        }
    }



}
