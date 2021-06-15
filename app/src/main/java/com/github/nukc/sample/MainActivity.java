package com.github.nukc.sample;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		findViewById(R.id.btn_default).setOnClickListener(mOnClickListener);
		findViewById(R.id.btn_another).setOnClickListener(mOnClickListener);
		findViewById(R.id.btn_issues).setOnClickListener(mOnClickListener);
		findViewById(R.id.btn_not_show_footer_when_not_covered_screen).setOnClickListener(mOnClickListener);
		findViewById(R.id.btn_flex_box).setOnClickListener(mOnClickListener);
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
					cls = ScrollingActivity.class;
					break;
				case R.id.btn_not_show_footer_when_not_covered_screen:
					cls = NotShowFooterWhenNotCoveredScreenActivity.class;
					break;
				case R.id.btn_flex_box:
					cls = FlexboxActivity.class;
					break;
			}
			startActivity(new Intent(MainActivity.this, cls));
		}
	};
}
