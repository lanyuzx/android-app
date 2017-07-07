package a315i.youcai.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import a315i.youcai.R;

public class ProductActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        findViewById(R.id.product_backIv).setOnClickListener(this);
        findViewById(R.id.product_changeLayout).setOnClickListener(this);
        findViewById(R.id.product_search_delete).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.product_backIv:
                finish();
                break;
        }

    }
}
