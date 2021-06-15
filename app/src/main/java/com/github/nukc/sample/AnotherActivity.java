package com.github.nukc.sample;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.nukc.LoadMoreWrapper.LoadMoreAdapter;
import com.github.nukc.LoadMoreWrapper.LoadMoreWrapper;

public class AnotherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_another);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(new AnotherAdapter(0));
    }

    private static class AnotherAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        private LoadMoreWrapper mWrapper;
        private int mCount;
        // use for demo, please ignore
        private boolean mShowLoadFailedEnabled = true;

        public AnotherAdapter(int count) {
            mCount = count;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sample, parent, false);
            return new AnotherHolder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            ((AnotherHolder) holder).mTextView.setText(position + "");
        }

        @Override
        public int getItemCount() {
            return mCount;
        }

        @Override
        public void onAttachedToRecyclerView(final RecyclerView recyclerView) {
            super.onAttachedToRecyclerView(recyclerView);

            mWrapper = LoadMoreWrapper.with(this);
            mWrapper.setListener(
                    new LoadMoreAdapter.OnLoadMoreListener() {
                        @Override
                        public void onLoadMore(LoadMoreAdapter.Enabled enabled) {
                            recyclerView.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    addItem(recyclerView);
                                }
                            }, 500);
                        }
                    })
                    .setShowNoMoreEnabled(true)
                    .into(recyclerView);
        }

        public void addItem(RecyclerView recyclerView) {
            if (mCount > 0 && mShowLoadFailedEnabled) {
                mShowLoadFailedEnabled = false;
                recyclerView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mWrapper.setLoadFailed(true);
                    }
                }, 800);
            } else {
                if (mCount > 30) {
                    mWrapper.setLoadMoreEnabled(false);
                }

                final int positionStart = mCount;
                mCount += 5;
                notifyItemRangeInserted(positionStart, 5);
            }
        }

        static class AnotherHolder extends RecyclerView.ViewHolder {
            TextView mTextView;

            public AnotherHolder(View itemView) {
                super(itemView);
                mTextView = (TextView) itemView.findViewById(R.id.text);
            }
        }
    }
}
