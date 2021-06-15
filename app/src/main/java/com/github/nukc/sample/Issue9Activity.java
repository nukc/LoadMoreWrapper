package com.github.nukc.sample;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.github.nukc.LoadMoreWrapper.LoadMoreAdapter;
import com.github.nukc.LoadMoreWrapper.LoadMoreWrapper;

public class Issue9Activity extends AppCompatActivity {

    private Issue9Adapter mIssue9Adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_issue9);

        final SwipeRefreshLayout swipeRefreshLayout =
                (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mIssue9Adapter.clear();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mIssue9Adapter = new Issue9Adapter(5);
        recyclerView.setAdapter(mIssue9Adapter);

        LoadMoreWrapper.with(mIssue9Adapter)
                .setLoadMoreEnabled(true)
                .setListener(new LoadMoreAdapter.OnLoadMoreListener() {
                    @Override
                    public void onLoadMore(LoadMoreAdapter.Enabled enabled) {
                        recyclerView.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                mIssue9Adapter.addItem();
                            }
                        }, 1000);
                    }
                })
                .into(recyclerView);
    }

    private static class Issue9Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        private int mCount;

        public Issue9Adapter(int count) {
            mCount = count;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sample, parent, false);
            return new IssueHolder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            ((IssueHolder) holder).mTextView.setText(position + "");
        }

        @Override
        public int getItemCount() {
            return mCount;
        }

        public void clear() {
            final int count = mCount;
            mCount = 0;
            notifyItemRangeRemoved(0, count);

            mCount = 15;
            notifyItemRangeInserted(0, mCount);
        }

        public void addItem() {
            final int positionStart = mCount;
            mCount+= 5;
            notifyItemRangeInserted(positionStart, 5);
        }

        static class IssueHolder extends RecyclerView.ViewHolder {
            TextView mTextView;

            public IssueHolder(View itemView) {
                super(itemView);
                mTextView = (TextView) itemView.findViewById(R.id.text);
            }
        }
    }
}
