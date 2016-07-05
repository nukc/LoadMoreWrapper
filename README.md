# RecyclerAdapter

Without changes to the original adater of recyclerView, make recyclerView supports load more and customize the footer view

在不改动RecyclerView原有adapter的情况下，使其拥有加载更多功能和自定义底部视图。

在项目做了好几个页面后, 发现很多都没有做加载更多, 一个一个写很累人的, 用开源的又要对代码进行不少的改动, 然后就有了这个项目.

#Installation

add the dependency to your build.gradle:
```
    compile 'com.github.nukc.recycleradapter:recycleradapter:0.3.1'
```

# Usage

```java

    //the adapter is the original (这个adapter是原有的, 不改动它)
    RecyclerAdapter recyclerAdapter = new RecyclerAdapter(adapter);
    recyclerView.setAdapter(recyclerAdapter);

    //set load more listener
    recyclerAdapter.setLoadMoreListener(new RecyclerAdapter.OnLoadMoreListener() {
        @Override
        public void onLoadMore(RecyclerAdapter.Enabled enabled) {
            //do something
            //you can enabled.setLoadMoreEnabled(false) when do not need load more
        }
    });

    //设置是否启用加载更多
    setLoadMoreEnabled(boolean enabled)

    //customize the footer view
    public RecyclerAdapter(@NonNull RecyclerView.Adapter adapter, View footerView)

    public RecyclerAdapter(@NonNull RecyclerView.Adapter adapter, @LayoutRes int resId)

    setFooterView(View footerView)

    setFooterView(@LayoutRes int resId)
```

since v0.3.0, you can :

```java
    RecyclerWrapper.with(mSampleAdapter)
        .setFooterView(...)
        .setListener(new RecyclerAdapter.OnLoadMoreListener() {
             @Override
             public void onLoadMore(RecyclerAdapter.Enabled enabled) {
                 //do something
             })
        .into(recyclerView);
```

在纠结要不要加下拉刷新...
to be continued...