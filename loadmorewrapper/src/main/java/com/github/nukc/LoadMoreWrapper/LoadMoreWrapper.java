package com.github.nukc.LoadMoreWrapper;

import android.view.View;

import androidx.annotation.LayoutRes;
import androidx.recyclerview.widget.RecyclerView;

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

	public View getFooterView() {
		return mLoadMoreAdapter.getFooterView();
	}

	public LoadMoreWrapper setNoMoreView(@LayoutRes int resId) {
		mLoadMoreAdapter.setNoMoreView(resId);
		return this;
	}

	public LoadMoreWrapper setNoMoreView(View noMoreView) {
		mLoadMoreAdapter.setNoMoreView(noMoreView);
		return this;
	}

	public View getNoMoreView() {
		return mLoadMoreAdapter.getNoMoreView();
	}

	public LoadMoreWrapper setLoadFailedView(@LayoutRes int resId) {
		mLoadMoreAdapter.setLoadFailedView(resId);
		return this;
	}

	public LoadMoreWrapper setLoadFailedView(View view) {
		mLoadMoreAdapter.setLoadFailedView(view);
		return this;
	}

	public View getLoadFailedView() {
		return mLoadMoreAdapter.getLoadFailedView();
	}

	/**
	 * 监听加载更多触发事件
	 *
	 * @param listener {@link com.github.nukc.LoadMoreWrapper.LoadMoreAdapter.OnLoadMoreListener}
	 */
	public LoadMoreWrapper setListener(LoadMoreAdapter.OnLoadMoreListener listener) {
		mLoadMoreAdapter.setLoadMoreListener(listener);
		return this;
	}

	/**
	 * 设置是否启用加载更多
	 *
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
	 *
	 * @param enabled default false
	 */
	public LoadMoreWrapper setShowNoMoreEnabled(boolean enabled) {
		mLoadMoreAdapter.setShowNoMoreEnabled(enabled);
		return this;
	}

	/**
	 * 设置加载失败
	 */
	public void setLoadFailed(boolean isLoadFailed) {
		mLoadMoreAdapter.setLoadFailed(isLoadFailed);
	}

	/**
	 * 在 item 未铺满屏幕的时候是否不显示底部视图
	 *
	 * @param notShow default false
	 */
	public LoadMoreWrapper setNotShowFooterWhenNotCoveredScreen(boolean notShow) {
		mLoadMoreAdapter.setNotShowFooterWhenNotCoveredScreen(notShow);
		return this;
	}

	/**
	 * 获取原来的 adapter
	 */
	public RecyclerView.Adapter getOriginalAdapter() {
		return mLoadMoreAdapter.getOriginalAdapter();
	}

	public LoadMoreAdapter into(RecyclerView recyclerView) {
		mLoadMoreAdapter.setHasStableIds(mLoadMoreAdapter.getOriginalAdapter().hasStableIds());
		recyclerView.setAdapter(mLoadMoreAdapter);
		return mLoadMoreAdapter;
	}
}
