package com.github.nukc.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.nukc.LoadMoreWrapper.LoadMoreAdapter;
import com.github.nukc.LoadMoreWrapper.LoadMoreWrapper;

public class AnotherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_another);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(new AnotherAdapter(5));
    }

    private static class AnotherAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        private LoadMoreWrapper mWrapper;
        private int mCount;

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
        public void onAttachedToRecyclerView(RecyclerView recyclerView) {
            super.onAttachedToRecyclerView(recyclerView);

            mWrapper = LoadMoreWrapper.with(this);
            mWrapper.setListener(
                    new LoadMoreAdapter.OnLoadMoreListener() {
                        @Override
                        public void onLoadMore(LoadMoreAdapter.Enabled enabled) {
                            addItem();
                        }
                    })
                    .setShowNoMoreEnabled(true)
                    .into(recyclerView);
        }

        public void addItem() {
            if (mCount > 30) {
                mWrapper.setLoadMoreEnabled(false);
            }

            final int positionStart = mCount;
            mCount += 10;
            notifyItemRangeInserted(positionStart, 10);
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
