package com.bw.guoxiaolin2019732.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bw.guoxiaolin2019732.R;
import com.bw.guoxiaolin2019732.bean.Bean1;

import java.util.List;

/**
 * <p>文件描述：<p>
 * <p>作者：xiaolin<p>
 * <p>创建时间:2019/7/3<p>
 * <p>更改时间:2019/7/3<p>
 * <p>版本号：1<p>
 */
public class Myadapter extends BaseAdapter {
    private List<Bean1.DataBean.TopnewsBean> list;
    private Context context;
    private H1 h1;
    private H2 h2;

    public Myadapter(List<Bean1.DataBean.TopnewsBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position%2;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        switch (getItemViewType(position)){
            case 0:
                if (convertView==null){
                    h1 = new H1();
                    convertView=View.inflate(context,R.layout.item,null);
                   h1.imageView1= convertView.findViewById(R.id.i1);
                   h1.textView1= convertView.findViewById(R.id.t1);
                   convertView.setTag(h1);
                }
                else {
                    h1= (H1) convertView.getTag();
                }
                h1.textView1.setText(list.get(position).getTitle());
                Glide.with(context).load("http://blog.zhaoliang5156.cn/zixunnew/"+list.get(position).getImageUrl()).into(h1.imageView1);
                break;
            case 1:
                if (convertView==null){
                    h2 = new H2();
                    convertView=View.inflate(context,R.layout.item1,null);
                    h2.imageView2= convertView.findViewById(R.id.i2);
                    h2.textView2= convertView.findViewById(R.id.t2);
                    convertView.setTag(h2);
                }
                else {
                    h2= (H2) convertView.getTag();
                }
                h2.textView2.setText(list.get(position).getTitle());
                Glide.with(context).load("http://blog.zhaoliang5156.cn/zixunnew/"+list.get(position).getImageUrl()).into(h2.imageView2);
                break;
        }
        return convertView;
    }
    public  class H1{
        TextView textView1;
        ImageView imageView1;
    }
    public  class H2{
        TextView textView2;
        ImageView imageView2;
    }
}
