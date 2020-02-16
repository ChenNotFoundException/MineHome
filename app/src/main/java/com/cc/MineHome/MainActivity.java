package com.cc.MineHome;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.ListView;

import com.cc.MineHome.MyAdapter.ContentAdapter;
import com.cc.MineHome.POJO.Content;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //定义数据
    private List<Content> mData;
    //定义ListView对象
    private ListView mListViewArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RefreshLayout refreshLayout = (RefreshLayout)findViewById(R.id.refreshLayout);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshlayout.finishRefresh(false);//传入false表示刷新失败
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                refreshlayout.finishLoadMore(2000/*,false*/);//传入false表示加载失败
            }
        });

        //为ListView对象赋值
        mListViewArray = (ListView) findViewById(R.id.contentListView);
        LayoutInflater inflater = getLayoutInflater();
        //初始化数据
        initData();
        //创建自定义Adapter的对象
        ContentAdapter adapter = new ContentAdapter(inflater, mData);
        //将布局添加到ListView中
        mListViewArray.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    /*
        初始化数据
         */
    private void initData() {
        mData = new ArrayList<Content>();
        Content content1 = new Content("陈晨", R.drawable.lakers,R.drawable.instalogo );
        Content content2 = new Content("陈晨", R.drawable.lakers, R.drawable.instalogo);
        Content content3 = new Content("陈晨", R.drawable.lakers, R.drawable.instalogo);
        Content content4 = new Content("陈晨", R.drawable.lakers, R.drawable.instalogo);

        mData.add(content1);
        mData.add(content2);
        mData.add(content3);
        mData.add(content4);
        Log.e("initData", "initData: " + mData.size());
    }
    public static Bitmap getImage(String path) throws Exception{
        URL url = new URL(path);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        InputStream is = conn.getInputStream();
        return BitmapFactory.decodeStream(is);
    }
}
