package com.github.nukc.LoadMoreWrapper;

import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by C on 16/7/4.
 */
public class LoadMoreWrapper {

    private final LoadMoreAdapter mLoadMoreAdapter;

    public LoadMoreWrapper(LoadMoreAdapter loadMoreAdapter) {
        mLoadMoreAdapter = loadMoreAdapter;
    }

    public static LoadMoreWrapper with(RecyclerView.Adapter adapter) {
        LoadMoreAdapter loadMoreAdapter = new LoadMoreAdapter(adapter);
        return new LoadMoreWrapper(loadMoreAdapter);
    }

    public LoadMoreWrapper setFooterView(@LayoutRes int resId) {
        mLoadMoreAdapter.setFooterView(resId);
        return this;
    }

    public LoadMoreWrapper setFooterView(View footerView) {
        mLoadMoreAdapter.setFooterView(footerView);
        return this;
    }

    public LoadMoreWrapper setListener(LoadMoreAdapter.OnLoadMoreListener listener) {
        mLoadMoreAdapter.setLoadMoreListener(listener);
        return this;
    }

    public LoadMoreWrapper setLoadMoreEnabled(boolean enabled) {
        mLoadMoreAdapter.setLoadMoreEnabled(enabled);
        if (!enabled) {
            mLoadMoreAdapter.setShouldRemove(enabled);
        }
        return this;
    }

    public RecyclerView.Adapter getRealAdapter() {
        return mLoadMoreAdapter.getRealAdapter();
    }

    public LoadMoreAdapter into(RecyclerView recyclerView) {
        recyclerView.setAdapter(mLoadMoreAdapter);
        return mLoadMoreAdapter;
    }
}
