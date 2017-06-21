package com.github.nukc.sample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_default).setOnClickListener(mOnClickListener);
        findViewById(R.id.btn_another).setOnClickListener(mOnClickListener);
        findViewById(R.id.btn_issues).setOnClickListener(mOnClickListener);
        findViewById(R.id.btn_coordinator).setOnClickListener(mOnClickListener);
    }

    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Class<?> cls = null;
            switch (v.getId()) {
                case R.id.btn_default:
                    cls = SampleActivity.class;
                    break;
                case R.id.btn_another:
                    cls = AnotherActivity.class;
                    break;
                case R.id.btn_issues:
                    cls = Issue9Activity.class;
                    break;
                case R.id.btn_coordinator:
                    cls = CoordinatorActivity.class;
                    break;
                default:
                    throw new IllegalArgumentException("~");
            }
            startActivity(new Intent(MainActivity.this, cls));
        }
    };
}
