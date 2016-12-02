# LoadMoreWrapper

[ ![Download](https://api.bintray.com/packages/nukc/maven/LoadMoreWrapper/images/download.svg) ](https://bintray.com/nukc/maven/LoadMoreWrapper/_latestVersion)

make recyclerView supports load more and customize the footer view, without changes to the original adater of recyclerView.

在不改动RecyclerView原有adapter的情况下，使其拥有加载更多功能和自定义底部视图。

- 支持当item未铺满屏幕的时候仍能够加载更多
- 支持自定义加载视图
- 当layoutManager为Grid和StaggeredGrid的时候, 加载更多视图footerView仍占据一行

<img src="https://raw.githubusercontent.com/nukc/LoadMoreWrapper/master/images/item.gif">
<img src="https://raw.githubusercontent.com/nukc/LoadMoreWrapper/master/images/grid_custom.gif">
<img src="https://raw.githubusercontent.com/nukc/LoadMoreWrapper/master/images/staggeredgrid.gif">

## Installation

add the dependency to your build.gradle:
```gradle
    compile 'com.github.nukc:LoadMoreWrapper:1.1'
```

## Usage

```java
    //the adapter is the original (这个adapter是原有的, 不改动它)
    LoadMoreWrapper.with(adapter)
        .setFooterView(...) // view or layout resource
        .setListener(new LoadMoreAdapter.OnLoadMoreListener() {
             @Override
             public void onLoadMore(LoadMoreAdapter.Enabled enabled) {
                 //do something
                 //you can enabled.setLoadMoreEnabled(false) when do not need load more
             })
        .into(recyclerView);
```

## License

    Copyright 2016, nukc

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.