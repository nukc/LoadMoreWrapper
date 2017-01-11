package com.github.nukc.LoadMoreWrapper;

import android.support.annotation.LayoutRes;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author Nukc.
 */

public class LoadMoreHelper {

    public static View inflate(ViewGroup parent, @LayoutRes int resource) {
        return LayoutInflater.from(parent.getContext()).inflate(resource, parent, false);
    }

    public static void setItemViewFullSpan(View itemView) {
        //当为StaggeredGridLayoutManager的时候,设置footerView占据整整一行
        ViewGroup.LayoutParams layoutParams = itemView.getLayoutParams();
        if (layoutParams instanceof StaggeredGridLayoutManager.LayoutParams) {
            ((StaggeredGridLayoutManager.LayoutParams) layoutParams).setFullSpan(true);
        }
    }
}
