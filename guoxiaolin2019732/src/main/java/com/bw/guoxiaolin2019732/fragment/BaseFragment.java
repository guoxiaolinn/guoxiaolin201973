package com.bw.guoxiaolin2019732.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * <p>文件描述：<p>
 * <p>作者：xiaolin<p>
 * <p>创建时间:2019/7/3<p>
 * <p>更改时间:2019/7/3<p>
 * <p>版本号：1<p>
 */
public abstract class BaseFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(bj(), null);
        initView(view);
        initData();
        initData1();
        initsx();
        
        return view;
    }

    protected abstract void initsx();

    protected abstract void initData1();

    protected abstract void initData();

    protected abstract void initView(View view);
   

    protected abstract int bj();
}
