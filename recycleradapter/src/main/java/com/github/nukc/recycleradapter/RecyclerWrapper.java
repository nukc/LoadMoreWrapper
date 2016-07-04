package com.github.nukc.recycleradapter;

import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by C on 16/7/4.
 */
public class RecyclerWrapper {

    private RecyclerAdapter mRecyclerAdapter;

    public RecyclerWrapper(RecyclerAdapter recyclerAdapter) {
        mRecyclerAdapter = recyclerAdapter;
    }

    public static RecyclerWrapper with(RecyclerView.Adapter adapter) {
        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(adapter);
        return new RecyclerWrapper(recyclerAdapter);
    }

    public RecyclerWrapper setFooterView(@LayoutRes int resId) {
        mRecyclerAdapter.setFooterView(resId);
        return this;
    }

    public RecyclerWrapper setFooterView(View footerView) {
        mRecyclerAdapter.setFooterView(footerView);
        return this;
    }

    public RecyclerWrapper setListener(RecyclerAdapter.OnLoadMoreListener listener) {
        mRecyclerAdapter.setLoadMoreListener(listener);
        return this;
    }

    public RecyclerAdapter into(RecyclerView recyclerView) {
        recyclerView.setAdapter(mRecyclerAdapter);
        return mRecyclerAdapter;
    }
}
