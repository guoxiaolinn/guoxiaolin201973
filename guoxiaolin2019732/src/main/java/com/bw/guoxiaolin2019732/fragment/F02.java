package com.bw.guoxiaolin2019732.fragment;

import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.bw.guoxiaolin2019732.R;

/**
 * <p>文件描述：<p>
 * <p>作者：xiaolin<p>
 * <p>创建时间:2019/7/3<p>
 * <p>更改时间:2019/7/3<p>
 * <p>版本号：1<p>
 */
public class F02 extends BaseFragment{

    private WebView webView;
    private ProgressBar progressBar;
    private EditText text;
    private Button button;
    @Override
    protected void initView(View view) {
        webView = view.findViewById(R.id.web);
        progressBar = view.findViewById(R.id.pgrss);
        text = view.findViewById(R.id.e1);
        button = view.findViewById(R.id.tz);



    }

    @Override
    protected void initsx() {
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);

    }

    @Override
    protected void initData1() {
        webView.setWebViewClient(new WebViewClient());
        webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
               progressBar.setVisibility(View.VISIBLE);
               progressBar.setProgress(newProgress);
               if (newProgress==100){
                   progressBar.setVisibility(View.GONE);

               }
            }
        });


    }

    @Override
    protected void initData() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                webView.loadUrl("http://"+text);
            }
        });
    }



    @Override
    protected int bj() {
        return R.layout.f02;
    }
}
