package com.bw.guoxiaolin2019732.maintivity;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.bw.guoxiaolin2019732.R;
import com.bw.guoxiaolin2019732.adapter.PageAadapter;
import com.bw.guoxiaolin2019732.bean.Bean;
import com.bw.guoxiaolin2019732.fragment.F01;
import com.bw.guoxiaolin2019732.fragment.F02;
import com.bw.guoxiaolin2019732.fragment.F03;
import com.bw.guoxiaolin2019732.http.Https;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    private TabLayout tab;
    private Https https;
    private ViewPager page1;
    private ArrayList<Fragment> list;
    private List<Bean.ResultBean> result;
    private ImageView ilu1;
    private DrawerLayout drw;
    private ImageView imageView;


    protected void init1() {
        //获取单例
        https = Https.getHttps();
        boolean iswl = https.iswl(MainActivity.this);
        //判断是否有网
        if (iswl) {
            //往结合里面添加fragment
            list = new ArrayList<>();
            list.add(new F01());
            list.add(new F02());
            list.add(new F03());
            list.add(new F01());
            list.add(new F02());
            list.add(new F03());
            list.add(new F01());
            list.add(new F02());
            list.add(new F03());
            //page的适配器
            PageAadapter pageAadapter = new PageAadapter(getSupportFragmentManager(), list);
            page1.setAdapter(pageAadapter);
            //关联tablayout
            tab.setupWithViewPager(page1);

            AsyncTask<Void, Void, List<Bean.ResultBean>> asyncTask = new AsyncTask<Void, Void, List<Bean.ResultBean>>() {
                @Override
                protected List<Bean.ResultBean> doInBackground(Void... voids) {
                    String s = https.getstring("http://blog.zhaoliang5156.cn/zixunnew/categories");
                    Gson gson = new Gson();
                    Bean bean = gson.fromJson(s, Bean.class);
                    result = bean.getResult();
                    return result;
                }

                @Override
                protected void onPostExecute(List<Bean.ResultBean> resultBeans) {
                    tab.getTabAt(0).setText(resultBeans.get(0).getTitle());
                    tab.getTabAt(1).setText(resultBeans.get(1).getTitle());
                    tab.getTabAt(2).setText(resultBeans.get(2).getTitle());
                    tab.getTabAt(3).setText(resultBeans.get(3).getTitle());
                    tab.getTabAt(4).setText(resultBeans.get(0).getTitle());
                    tab.getTabAt(5).setText(resultBeans.get(1).getTitle());
                    tab.getTabAt(6).setText(resultBeans.get(2).getTitle());
                    tab.getTabAt(7).setText(resultBeans.get(3).getTitle());
                    tab.getTabAt(8).setText(resultBeans.get(0).getTitle());
                    super.onPostExecute(resultBeans);
                }
            }.execute();
            Toast.makeText(this, "有网", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "无网", Toast.LENGTH_SHORT).show();

        }
    }



    protected void initView() {
        tab = (TabLayout) findViewById(R.id.tab);

        page1 = (ViewPager) findViewById(R.id.page1);

        ilu1 = (ImageView) findViewById(R.id.ilu1);


        drw = (DrawerLayout) findViewById(R.id.drw);
        imageView = findViewById(R.id.ii1);

    }

    public void dkch(View view) {
drw.openDrawer(Gravity.LEFT);
    }
    @Override
    protected int initlayout() {
        return R.layout.activity_main;
    }

    public void dj(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent,10);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode==10&&resultCode==RESULT_OK){
            Uri data1 = data.getData();
            Glide.with(MainActivity.this).load(data1).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(imageView);
            Glide.with(MainActivity.this).load(data1).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(ilu1);
        }
    }

    public void djdjd(View view) {
        drw.closeDrawer(Gravity.LEFT);
    }
}
