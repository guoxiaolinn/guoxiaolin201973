package com.bw.guoxiaolin2019732.http;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * <p>文件描述：<p>
 * <p>作者：xiaolin<p>
 * <p>创建时间:2019/7/3<p>
 * <p>更改时间:2019/7/3<p>
 * <p>版本号：1<p>
 */
public class Https {
    private  static  Https https=new Https();
    private Https(){

    }

    public static Https getHttps() {
        return https;
    }
    public boolean iswl(Context context){
        if (context!=null){
            ConnectivityManager connectivityManager= (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            @SuppressLint("MissingPermission") NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            if (activeNetworkInfo!=null){
                return activeNetworkInfo.isAvailable();
            }

        }
        return false;
    }
    public  String getstring(String strngurl){

        try {
            URL url = new URL(strngurl);
          HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setConnectTimeout(5000);
        httpURLConnection.setReadTimeout(5000);
            int responseCode = httpURLConnection.getResponseCode();
if (responseCode==HttpURLConnection.HTTP_OK){
    InputStream inputStream = httpURLConnection.getInputStream();
    BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
    StringBuffer stringBuffer=new StringBuffer();
    String str="";
    while ((str=bufferedReader.readLine())!=null){
        stringBuffer.append(str);
    }
    bufferedReader.close();
    httpURLConnection.disconnect();
    return stringBuffer.toString();
}
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
