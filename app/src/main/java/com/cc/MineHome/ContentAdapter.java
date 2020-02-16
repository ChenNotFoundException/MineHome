package com.cc.MineHome;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.cc.MineHome.POJO.Content;

import java.util.List;

/**
 * Author:ChenChen
 * Date:2020/2/16
 * Description:
 */
public class ContentAdapter extends BaseAdapter {
    private List<Content> mContent;
    private LayoutInflater mInflater;

    public ContentAdapter(LayoutInflater mInflater,List<Content> mContent) {
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
        View viewContent = mInflater.inflate(R.layout.content_layout, null);
        //获得对象
        Content content = mContent.get(position);
        //获得自定义布局中每一个控件的对象
        ImageView user_icon = viewContent.findViewById(R.id.user_icon);
        TextView user_name = viewContent.findViewById(R.id.username);
        ImageView shared_pic = viewContent.findViewById(R.id.shared_pic);
        //将数据一一添加到自定义的布局中
        user_icon.setImageResource(content.getUser_icon());
        user_name.setText(content.getUsername());
        shared_pic.setImageResource(content.getShared_pic());

        return viewContent;
    }
}
