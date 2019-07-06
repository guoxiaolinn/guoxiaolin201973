package com.bw.guoxiaolin2019732.maintivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * <p>文件描述：<p>
 * <p>作者：xiaolin<p>
 * <p>创建时间:2019/7/3<p>
 * <p>更改时间:2019/7/3<p>
 * <p>版本号：1<p>
 */
public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(initlayout());
        initView();
        init1();
        initlayout();
    }

    protected abstract int initlayout();
    protected abstract void initView();
    protected abstract void init1();
}
