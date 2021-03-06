package com.joez.widget;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.joez.sync.CalendarDataResolver;
import com.joez.sync.MainActivity;
import com.joez.sync.R;

public class CalendarAdapter extends BaseAdapter {
	private List<Model> mList;
	private LayoutInflater mInflater;
	private int mWeek;
	private Context mContext;
	public CalendarAdapter(Context context,List<Model> list) {
		mContext=context;
		if(list==null){
			mList=new ArrayList<Model>();
		}else{
			mList=list;
		}
		mInflater=LayoutInflater.from(context);
	}
	
	public void updatedata(List<Model> list,int week){
		if(list!=null){
			mWeek=week;
			mList=list;
		}
		notifyDataSetChanged();
	}
	
	@Override
	public int getCount() {
		return mList.size();
	}

	@Override
	public Object getItem(int position) {
		return mList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder=null;
		if(convertView==null){
			viewHolder=new ViewHolder();
			convertView=mInflater.inflate(R.layout.itemlayout, null);
			viewHolder.tv_name=(TextView)convertView.findViewById(R.id.tv_name);
			viewHolder.tv_description=(TextView)convertView.findViewById(R.id.tv_description);
			convertView.setTag(viewHolder);
		}else{
			viewHolder=(ViewHolder) convertView.getTag();
		}
		viewHolder.tv_name.setText(mList.get(position).getName());
		viewHolder.tv_name.setOnLongClickListener(new OnLongClickListener() {
			
			@Override
			public boolean onLongClick(View v) {
				((MainActivity)mContext).addOrEditItem(mWeek,mList.get(position));
				CalendarDataResolver.getInstance().updateItem(mWeek);
				return true;
			}
		});
		viewHolder.tv_description.setText(mList.get(position).getDescription());
		viewHolder.tv_description.setOnLongClickListener(new OnLongClickListener() {
			
			@Override
			public boolean onLongClick(View v) {
				mList.remove(position);
				CalendarDataResolver.getInstance().updateItem(mWeek);
				return true;
			}
		});
		return convertView;
	}

	class ViewHolder{
		private TextView tv_name;
		private TextView tv_description;
	}
}
