package com.cc.MineHome.MyAdapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.cc.MineHome.AsyncImageLoader.AsyncImageLoader;
import com.cc.MineHome.POJO.MineShare;
import com.cc.MineHome.R;

import java.util.List;

public class MyAdapter extends BaseAdapter {
	private String[] list;
	private Context context;
	private AsyncImageLoader imageLoader;
	private List<MineShare> mContent;
	private LayoutInflater mInflater;
	public MyAdapter(Context context, String[] list,List<MineShare> mContent) {
		this.context = context;
		this.list = list;
		this.mContent = mContent;
		imageLoader = new AsyncImageLoader(context);
	}

	@Override
	public int getCount() {
		return list.length;
	}

	@Override
	public Object getItem(int position) {
		return list[position];
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		ViewHolder holder = null;

		if (convertView == null) {
			holder = new ViewHolder();
			convertView = LayoutInflater.from(context).inflate(R.layout.mine_layout, null);
			holder.img = (ImageView) convertView.findViewById(R.id.share_pic_home);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		final String imgUrl = list[position];
		// 给 ImageView 设置一个 tag
		holder.img.setTag(imgUrl);
		// 预设一个图片
		holder.img.setImageResource(R.mipmap.image_practice_repast_1);

		if (!TextUtils.isEmpty(imgUrl)) {
			Bitmap bitmap = imageLoader.loadImage(holder.img, imgUrl);
			if (bitmap != null) {
				holder.img.setImageBitmap(bitmap);
			}
		}

		//上面为异步加载图片

		//获得对象
		MineShare content = mContent.get(position);
		//获得自定义布局中每一个控件的对象
		TextView time = convertView.findViewById(R.id.share_time);
		TextView desc = convertView.findViewById(R.id.decrible_of_pic);
		//将数据一一添加到自定义的布局中
		time.setText(content.getDate());
		desc.setText(content.getDescrible());

		return convertView;
	}

	class ViewHolder {
		ImageView img;
	}
}
