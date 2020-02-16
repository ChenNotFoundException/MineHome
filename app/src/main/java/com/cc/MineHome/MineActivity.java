package com.cc.MineHome;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.widget.NestedScrollView;

import com.cc.MineHome.MyAdapter.MyAdapter;
import com.cc.MineHome.POJO.MineShare;
import com.cc.MineHome.util.StatusBarUtil;

import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.SimpleMultiPurposeListener;
import com.scwang.smartrefresh.layout.util.SmartUtil;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * 主页
 */
public class MineActivity extends AppCompatActivity {

    private int mOffset = 0;
    private int mScrollY = 0;

    private List<MineShare> mData;
    private ListView mListViewArray;
    private String[] urls;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice_weibo);

        /**
         * 加载我的图片
         */
        //为ListView对象赋值
        mListViewArray = (ListView) findViewById(R.id.MyHomeListView);
        LayoutInflater inflater = getLayoutInflater();
        //初始化数据
        try {
            initData();
        } catch (ParseException e) {
            finish();
            e.printStackTrace();
        }
        //创建自定义Adapter的对象
        MyAdapter adapter = new MyAdapter(getApplicationContext(), urls,mData);
        //将布局添加到ListView中
        mListViewArray.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        /**/



        final Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //状态栏透明和间距处理
        StatusBarUtil.immersive(this);
        StatusBarUtil.setPaddingSmart(this, toolbar);
        StatusBarUtil.setMargin(this, findViewById(R.id.header));

        final View parallax = findViewById(R.id.parallax);
        final View buttonBar = findViewById(R.id.buttonBarLayout);
        final NestedScrollView scrollView = findViewById(R.id.scrollView);
        final RefreshLayout refreshLayout = findViewById(R.id.refreshLayout);

        findViewById(R.id.attention).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(),"点击了关注",Toast.LENGTH_SHORT).show();
            }
        });
        findViewById(R.id.leaveword).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(),"点击了留言",Toast.LENGTH_SHORT).show();
            }
        });

        refreshLayout.setOnMultiPurposeListener(new SimpleMultiPurposeListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                refreshLayout.finishRefresh(3000);
            }

            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                refreshLayout.finishLoadMore(2000);
            }
            @Override
            public void onHeaderMoving(RefreshHeader header, boolean isDragging, float percent, int offset, int headerHeight, int maxDragHeight) {
                mOffset = offset / 2;
                parallax.setTranslationY(mOffset - mScrollY);
                toolbar.setAlpha(1 - Math.min(percent, 1));
            }
//            @Override
//            public void onHeaderPulling(@NonNull RefreshHeader header, float percent, int offset, int bottomHeight, int maxDragHeight) {
//                mOffset = offset / 2;
//                parallax.setTranslationY(mOffset - mScrollY);
//                toolbar.setAlpha(1 - Math.min(percent, 1));
//            }
//            @Override
//            public void onHeaderReleasing(@NonNull RefreshHeader header, float percent, int offset, int bottomHeight, int maxDragHeight) {
//                mOffset = offset / 2;
//                parallax.setTranslationY(mOffset - mScrollY);
//                toolbar.setAlpha(1 - Math.min(percent, 1));
//            }
        });
        scrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            private int lastScrollY = 0;
            private int h = SmartUtil.dp2px(170);
            private int color = ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary)&0x00ffffff;
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (lastScrollY < h) {
                    scrollY = Math.min(h, scrollY);
                    mScrollY = scrollY > h ? h : scrollY;
                    buttonBar.setAlpha(1f * mScrollY / h);
                    toolbar.setBackgroundColor(((255 * mScrollY / h) << 24) | color);
                    parallax.setTranslationY(mOffset - mScrollY);
                }
                lastScrollY = scrollY;
            }
        });
        buttonBar.setAlpha(0);
        toolbar.setBackgroundColor(0);
    }

    private void initData() throws ParseException {

        urls = new String[]{
                "http://192.168.1.105:8080/img/outslogo.png",
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1581871450952&di=4ba3a369bedb14e6af55012370ca8553&imgtype=0&src=http%3A%2F%2Ft8.baidu.com%2Fit%2Fu%3D3575349163%2C2696218413%26fm%3D79%26app%3D86%26f%3DJPEG%3Fw%3D900%26h%3D1350",
                "http://192.168.1.105:8080/img/outslog.png",
                "http://192.168.1.105:8080/img/photo.jpg",
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1581871450952&di=4ba3a369bedb14e6af55012370ca8553&imgtype=0&src=http%3A%2F%2Ft8.baidu.com%2Fit%2Fu%3D3575349163%2C2696218413%26fm%3D79%26app%3D86%26f%3DJPEG%3Fw%3D900%26h%3D1350",
        };
        mData = new ArrayList<MineShare>();

        String s = "08:01";
        MineShare content1 = new MineShare(s, "/img/outslogo.png", "cc");
        MineShare content2 = new MineShare(s, "/img/photo.jpg", "这里我说了好多好多哈偶卡恢复健康哈市冀凯股份哈数据库凤凰健康");
        MineShare content3 = new MineShare(s, "/img/photo.jpg", "尝试完全的内容");
        MineShare content4 = new MineShare(s, "/img/outslog.png", "english");
        MineShare content5 = new MineShare(s, "/img/outslogo.png", "尝试完全的内容");

        mData.add(content1);
        mData.add(content2);
        mData.add(content3);
        mData.add(content4);
        mData.add(content5);
    }

}
