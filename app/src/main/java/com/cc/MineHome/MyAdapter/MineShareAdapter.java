package com.cc.MineHome.MyAdapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.cc.MineHome.AsyncImageLoader.AsyncImageLoader;
import com.cc.MineHome.POJO.MineShare;
import com.cc.MineHome.R;
import com.cc.MineHome.util.Const;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * Author:ChenChen
 * Date:2020/2/16
 * Description:
 */
public class MineShareAdapter extends BaseAdapter {
    private List<MineShare> mContent;
    private LayoutInflater mInflater;
    ImageView shared_pic;

    private String[] list;
    private Context context;
    private AsyncImageLoader imageLoader;

    public MineShareAdapter(LayoutInflater mInflater,List<MineShare> mContent) {
        this.mContent = mContent;
        this.mInflater = mInflater;
    }

    @Override
    public int getCount() {
        return mContent.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //获得ListView中的view
        View viewContent = mInflater.inflate(R.layout.mine_layout, null);
        //获得对象
        MineShare content = mContent.get(position);
        //获得自定义布局中每一个控件的对象
        TextView time = viewContent.findViewById(R.id.share_time);
        TextView desc = viewContent.findViewById(R.id.decrible_of_pic);
        shared_pic = viewContent.findViewById(R.id.share_pic_home);
        //将数据一一添加到自定义的布局中
        time.setText(content.getDate());
        desc.setText(content.getDescrible());

        mHandler.sendEmptyMessageDelayed(1, 1000);
        bitmap = returnBitMap(Const.URL + content.getPicture());
        shared_pic.setImageBitmap(bitmap);

        return viewContent;
    }
    private Bitmap bitmap;
    public Bitmap returnBitMap(final String url){

        new Thread(new Runnable() {
            @Override
            public void run() {
                URL imageurl = null;

                try {
                    imageurl = new URL(url);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                try {
                    HttpURLConnection conn = (HttpURLConnection)imageurl.openConnection();
                    conn.setDoInput(true);
                    conn.connect();
                    InputStream is = conn.getInputStream();
                    bitmap = BitmapFactory.decodeStream(is);
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        return bitmap;
    }
    private Handler mHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case 1:
                    shared_pic.setImageBitmap(bitmap);//显示
                    break;
            }
        }
    };
}
