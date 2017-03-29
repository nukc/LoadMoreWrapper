package com.github.nukc.LoadMoreWrapper;

import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * @author Nukc
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

    public LoadMoreWrapper setNoMoreView(@LayoutRes int resId) {
        mLoadMoreAdapter.setNoMoreView(resId);
        return this;
    }

    public LoadMoreWrapper setNoMoreView(View noMoreView) {
        mLoadMoreAdapter.setNoMoreView(noMoreView);
        return this;
    }

    /**
     * 监听加载更多触发事件
     * @param listener {@link com.github.nukc.LoadMoreWrapper.LoadMoreAdapter.OnLoadMoreListener}
     */
    public LoadMoreWrapper setListener(LoadMoreAdapter.OnLoadMoreListener listener) {
        mLoadMoreAdapter.setLoadMoreListener(listener);
        return this;
    }

    /**
     * 设置是否启用加载更多
     * @param enabled default true
     */
    public LoadMoreWrapper setLoadMoreEnabled(boolean enabled) {
        mLoadMoreAdapter.setLoadMoreEnabled(enabled);
        if (!enabled) {
            mLoadMoreAdapter.setShouldRemove(true);
        }
        return this;
    }

    /**
     * 设置全部加载完后是否显示没有更多视图
     * @param enabled default false
     */
    public LoadMoreWrapper setShowNoMoreEnabled(boolean enabled) {
        mLoadMoreAdapter.setShowNoMoreEnabled(enabled);
        return this;
    }

    /**
     * 获取原来的 adapter
     */
    public RecyclerView.Adapter getOriginalAdapter() {
        return mLoadMoreAdapter.getOriginalAdapter();
    }

    public LoadMoreAdapter into(RecyclerView recyclerView) {
        recyclerView.setAdapter(mLoadMoreAdapter);
        return mLoadMoreAdapter;
    }
}
