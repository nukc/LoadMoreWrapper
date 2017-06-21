package com.github.nukc.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.github.nukc.LoadMoreWrapper.LoadMoreAdapter;
import com.github.nukc.LoadMoreWrapper.LoadMoreWrapper;

public class CoordinatorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinator);

        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        final Issue9Activity.Issue9Adapter adapter = new Issue9Activity.Issue9Adapter(5);
        recyclerView.setAdapter(adapter);

        LoadMoreWrapper.with(adapter)
                .setLoadMoreEnabled(true)
                .setListener(new LoadMoreAdapter.OnLoadMoreListener() {
                    @Override
                    public void onLoadMore(LoadMoreAdapter.Enabled enabled) {
                        recyclerView.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                adapter.addItem();
                            }
                        }, 1000);
                    }
                })
                .into(recyclerView);
    }
}
