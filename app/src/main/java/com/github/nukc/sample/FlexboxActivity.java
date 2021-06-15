package com.github.nukc.sample;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.github.nukc.LoadMoreWrapper.LoadMoreWrapper;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;

public class FlexboxActivity extends AppCompatActivity {

    private boolean mShowLoadFailedEnabled = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flexbox);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        FlexboxLayoutManager layoutManager = new FlexboxLayoutManager(this);
        layoutManager.setFlexDirection(FlexDirection.ROW);
        layoutManager.setJustifyContent(JustifyContent.FLEX_END);
        recyclerView.setLayoutManager(layoutManager);

        FlexAdapter flexAdapter = new FlexAdapter(20);
//        recyclerView.setAdapter(flexAdapter);
        LoadMoreWrapper.with(flexAdapter)
//                .setFooterView(R.layout.view_flex_loading)
//                .setNoMoreView(R.layout.view_flex_no_more)
//                .setLoadFailedView(R.layout.view_flex_retry)
                .setShowNoMoreEnabled(true)
                .setListener(enabled -> {
                    int itemCount = flexAdapter.getItemCount();
                    if (itemCount > 20 && mShowLoadFailedEnabled) {
                        mShowLoadFailedEnabled = false;
                        recyclerView.postDelayed(() -> enabled.setLoadFailed(true), 800);
                    } else {
                        //not enable load more
                        if (itemCount >= 80) {
                            enabled.setLoadMoreEnabled(false);
                        }

                        recyclerView.postDelayed(flexAdapter::addItem, 1200);
                    }
                })
                .into(recyclerView);
    }

    private static class FlexAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        private int mCount;

        public FlexAdapter(int count) {
            mCount = count;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_flex, parent, false);
            return new SampleHolder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            ((SampleHolder) holder).mTextView.setText(position + "");
            ViewGroup.LayoutParams lp = ((SampleHolder) holder).itemView.getLayoutParams();
            if (lp instanceof com.google.android.flexbox.FlexboxLayoutManager.LayoutParams) {
                ((FlexboxLayoutManager.LayoutParams) lp).setFlexGrow(1);
            }
        }

        @Override
        public int getItemCount() {
            return mCount;
        }

        public void addItem() {
            final int positionStart = mCount;
            mCount+= 5;
            notifyItemRangeInserted(positionStart, 5);
        }

        static class SampleHolder extends RecyclerView.ViewHolder {
            TextView mTextView;

            public SampleHolder(View itemView) {
                super(itemView);
                mTextView = (TextView) itemView.findViewById(R.id.text);
            }
        }
    }
}