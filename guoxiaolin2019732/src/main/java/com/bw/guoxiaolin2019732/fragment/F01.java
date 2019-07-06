package com.bw.guoxiaolin2019732.fragment;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;

import com.bumptech.glide.Glide;
import com.bw.guoxiaolin2019732.R;
import com.bw.guoxiaolin2019732.adapter.Myadapter;
import com.bw.guoxiaolin2019732.bean.Bean1;
import com.bw.guoxiaolin2019732.http.Https;
import com.bw.guoxiaolin2019732.listview.Mylistview;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
import com.youth.banner.Banner;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <p>文件描述：<p>
 * <p>作者：xiaolin<p>
 * <p>创建时间:2019/7/3<p>
 * <p>更改时间:2019/7/3<p>
 * <p>版本号：1<p>
 */
public class F01 extends BaseFragment{

    private PullToRefreshScrollView pull;
    private DrawerLayout drawerLayout;
    private Banner banner;
    private Https https;
    private int i=1;
    private Myadapter myadapter;
    private List<Bean1.DataBean.TopnewsBean> topnews=new ArrayList<>();
    private Mylistview mylistview;

    @Override
    protected void initView(View view) {
        banner = view.findViewById(R.id.bannerr);
        drawerLayout = view.findViewById(R.id.drw);
        pull = view.findViewById(R.id.pull);

        mylistview = view.findViewById(R.id.listview);


    }
//刷新
    @Override
    protected void initsx() {
        pull.setMode(PullToRefreshBase.Mode.BOTH);
        pull.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ScrollView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ScrollView> pullToRefreshBase) {
                myadapter=null;
                i=1;
                topnews.clear();
                initData1();
                initData();

            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ScrollView> pullToRefreshBase) {
                i++;
                initData1();
                initData();
            }
        });
    }
//展示
    @Override
    protected void initData1() {
        https = Https.getHttps();


        AsyncTask<Void,Void, List<Bean1.DataBean.TopnewsBean>> asyncTask=new AsyncTask<Void, Void, List<Bean1.DataBean.TopnewsBean>>() {



            @Override
            protected List<Bean1.DataBean.TopnewsBean> doInBackground(Void... voids) {
                String s = https.getstring("http://blog.zhaoliang5156.cn/zixunnew/fengjing?page="+i);
                Gson gson=new Gson();
                Bean1 bean1 = gson.fromJson(s, Bean1.class);
                bean1.getData().getTopnews();
                return bean1.getData().getTopnews();
            }

            @Override
            protected void onPostExecute(List<Bean1.DataBean.TopnewsBean> topnewsBeans) {
                super.onPostExecute(topnewsBeans);
                myadapter=null;
                topnews.addAll(topnewsBeans);
                Collections.shuffle(topnews);
                myadapter = new Myadapter(topnews, getActivity());
                mylistview.setAdapter(myadapter);
                pull.onRefreshComplete();
            }
        }.execute();

    }
//轮播
    @Override
    protected void initData() {

        AsyncTask<Void,Void, List<Bean1.DataBean.TopnewsBean>> asyncTask=new AsyncTask<Void, Void, List<Bean1.DataBean.TopnewsBean>>() {



            @Override
            protected List<Bean1.DataBean.TopnewsBean> doInBackground(Void... voids) {
                String s = https.getstring("http://blog.zhaoliang5156.cn/zixunnew/fengjing?page="+i);
                Gson gson=new Gson();
                Bean1 bean1 = gson.fromJson(s, Bean1.class);
                bean1.getData().getTopnews();
                return bean1.getData().getTopnews();
            }

            @Override
            protected void onPostExecute(List<Bean1.DataBean.TopnewsBean> topnewsBeans) {
                super.onPostExecute(topnewsBeans);
                Collections.shuffle(topnewsBeans);
               banner.setImages(topnewsBeans);
               banner.setBannerAnimation(Transformer.Accordion);
               banner.setImageLoader(new ImageLoader() {
                   @Override
                   public void displayImage(Context context, Object path, ImageView imageView) {
                       Bean1.DataBean.TopnewsBean bean= (Bean1.DataBean.TopnewsBean) path;
                       Glide.with(getActivity()).load("http://blog.zhaoliang5156.cn/zixunnew/"+bean.getImageUrl()).into(imageView);
                   }
               });
                banner.isAutoPlay(true);
                banner.setDelayTime(2000);
                banner.start();
                pull.onRefreshComplete();
            }
        }.execute();
    }



    @Override
    protected int bj() {
        return R.layout.f01;
    }
}
