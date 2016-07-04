package com.github.nukc.sample;


import android.os.Bundle;
import android.support.annotation.BoolRes;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.TextView;

import com.github.nukc.recycleradapter.RecyclerAdapter;
import com.github.nukc.recycleradapter.RecyclerWrapper;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


/**
 * A simple {@link Fragment} subclass.
 */
public class SampleFragment extends Fragment {

    private static final String TAG = SampleFragment.class.getSimpleName();
    private static final String ARGS_COUNT = "count";
    private static final String ARGS_MANAGER_MODE = "managerMode";

    public static final int MODE_LINEARLAYOUT = 1;
    public static final int MODE_GRIDLAYOUT = 2;
    public static final int MODE_STAGGEREDGRIDLAYOUT = 3;

    @IntDef({MODE_LINEARLAYOUT, MODE_GRIDLAYOUT, MODE_STAGGEREDGRIDLAYOUT})
    @Retention(RetentionPolicy.SOURCE)
    public @interface ManagerMode{}

    private SampleAdapter mSampleAdapter;

    public static SampleFragment newInstance(int count, @ManagerMode int managerMode) {
        Bundle args = new Bundle();
        args.putInt(ARGS_COUNT, count);
        args.putInt(ARGS_MANAGER_MODE, managerMode);

        SampleFragment fragment = new SampleFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public SampleFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int count = getArguments().getInt(ARGS_COUNT, 0);
        mSampleAdapter = new SampleAdapter(count);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sample, container, false);

        final RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        int managerMode = getArguments().getInt(ARGS_MANAGER_MODE);

        switch (managerMode) {
            case MODE_LINEARLAYOUT:
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(container.getContext());
                recyclerView.setLayoutManager(linearLayoutManager);
                break;
            case MODE_GRIDLAYOUT:
                GridLayoutManager gridLayoutManager = new GridLayoutManager(container.getContext(), 2);
                recyclerView.setLayoutManager(gridLayoutManager);
                break;
            case MODE_STAGGEREDGRIDLAYOUT:
                StaggeredGridLayoutManager staggeredGridLayoutManager =
                        new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
                recyclerView.setLayoutManager(staggeredGridLayoutManager);
                break;
            default:
                throw new IllegalArgumentException();
        }


//        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(mSampleAdapter);
//        recyclerView.setAdapter(recyclerAdapter);
//        recyclerAdapter.setLoadMoreListener(new RecyclerAdapter.OnLoadMoreListener() {
//            @Override
//            public void onLoadMore(RecyclerAdapter.Enabled enabled) {
//                //not enable load more
//                if (mSampleAdapter.getItemCount() >= 40) {
//                    enabled.setLoadMoreEnabled(false);
//                }
//
//                recyclerView.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        mSampleAdapter.addItem();
//                    }
//                }, 1200);
//            }
//        });

        RecyclerWrapper.with(mSampleAdapter)
//                .setFooterView()
                .setListener(new RecyclerAdapter.OnLoadMoreListener() {
                    @Override
                    public void onLoadMore(RecyclerAdapter.Enabled enabled) {
                        //not enable load more
                        if (mSampleAdapter.getItemCount() >= 40) {
                            enabled.setLoadMoreEnabled(false);
                        }

                        recyclerView.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                mSampleAdapter.addItem();
                            }
                        }, 1200);
                    }
                })
                .into(recyclerView);

        return view;
    }


    private static class SampleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        private int mCount;

        public SampleAdapter(int count) {
            mCount = count;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sample, parent, false);
            return new SampleHolder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            ((SampleHolder) holder).mTextView.setText(position + "");
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
