package com.github.nukc.sample;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);

        String[] titles = getResources().getStringArray(R.array.tab_main);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), titles);
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.setOffscreenPageLimit(3);
        tabLayout.setupWithViewPager(viewPager);
    }


    private static class ViewPagerAdapter extends FragmentPagerAdapter {

        private String[] mTitles;

        public ViewPagerAdapter(FragmentManager fm, String[] titles) {
            super(fm);
            mTitles = titles;
        }

        @Override
        public Fragment getItem(int position) {
            int count;
            int managerMode = SampleFragment.MODE_LINEARLAYOUT;
            switch (position) {
                case 0:
                    count = 2;
                    break;
                case 1:
                    count = 15;
                    break;
                case 2:
                    count = 20;
                    managerMode = SampleFragment.MODE_GRIDLAYOUT;
                    break;
                case 3:
                    count = 30;
                    managerMode = SampleFragment.MODE_STAGGEREDGRIDLAYOUT;
                    break;
                default:
                    throw new IndexOutOfBoundsException();
            }

            return SampleFragment.newInstance(count, managerMode);
        }

        @Override
        public int getCount() {
            return mTitles.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles[position];
        }
    }
}
