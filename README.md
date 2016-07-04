# RecyclerAdapter

Without changes to the original adater of recyclerView, make recyclerView supports load more and customize the footer view

在不改动RecyclerView原有adapter的情况下，使其拥有加载更多功能和自定义底部视图。


#Installation

add the dependency to your build.gradle:
```
    compile 'com.github.nukc.recycleradapter:recycleradapter:0.2.1'
```

# Usage

```java

    //the adapter is the original (这个adapter是原有的, 不改动它)
    RecyclerAdapter recyclerAdapter = new RecyclerAdapter(adapter);
    recyclerView.setAdapter(recyclerAdapter);

    //set load more listener
    recyclerAdapter.setLoadMoreListener(new RecyclerAdapter.OnLoadMoreListener() {
        @Override
        public void onLoadMore() {
            //load data
        }
    });

    //customize the footer view
    public RecyclerAdapter(@NonNull RecyclerView.Adapter adapter, View footerView)

    public RecyclerAdapter(@NonNull RecyclerView.Adapter adapter, @LayoutRes int resId)

    setFooterView(View footerView)

    setFooterView(@LayoutRes int resId)
```

to be continued...