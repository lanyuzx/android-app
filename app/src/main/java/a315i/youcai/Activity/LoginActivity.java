package a315i.youcai.Activity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import a315i.youcai.R;


public class LoginActivity extends AppCompatActivity {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mTabLayout = (TabLayout) findViewById(R.id.login_tabLaout);
        mTabLayout.addTab(mTabLayout.newTab().setText("密码登录"));
        mTabLayout.addTab(mTabLayout.newTab().setText("短信登录"));
        mViewPager = (ViewPager) findViewById(R.id.login_viewPager);
       // mViewPager.setAdapter(new setupViewPage());
        findViewById(R.id.login_backIv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private class setupViewPage extends PagerAdapter {
        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            return super.instantiateItem(container, position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeViewAt(position);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    }



}
