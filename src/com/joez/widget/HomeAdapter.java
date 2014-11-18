package com.joez.widget;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import android.content.Context;
import android.os.Handler;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.joez.callback.DataCallback;
import com.joez.sync.CalendarDataSource;
import com.joez.sync.Model;
import com.joez.sync.R;
import com.joez.sync.R.id;
import com.joez.sync.R.layout;

public class HomeAdapter extends BaseAdapter {
	private List<Model> mListFeeds;
	private static SparseArray<List<Model>> mAllFeeds;
	private LayoutInflater mInflater;
	private Blist mCurrentBlist;
	private Context mContext;
	private Handler mHandler;
	static{
		mAllFeeds=new SparseArray<List<Model>>();
		List<Model> listWeek=null;
		for(int week=4;week<=42;week++){
			listWeek=new ArrayList<Model>();
			for(int i=0;i<4;i++){
				listWeek.add(new Model(week,"other card"," of feeds"+i));
			}
			mAllFeeds.put(week, listWeek);
		}
	}
	public HomeAdapter(Handler handler,Context context) {
		mInflater=LayoutInflater.from(context);
		mContext=context;
		mHandler=handler;
		if(mListFeeds==null){
			mListFeeds=new ArrayList<Model>();
		}
	}
	
	public void updateFeeds(int week){
		mListFeeds=mAllFeeds.get(week);
		mCurrentBlist=new Blist(mContext, week);
		notifyDataSetChanged();
	}
	
	@Override
	public int getCount() {
		int count=0;
		if(mListFeeds!=null)
			count=mListFeeds.size();
		return count;
	}

	@Override
	public Object getItem(int position) {
		return mListFeeds.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if(position==2){
			//show blist card
			convertView=mCurrentBlist.getView(position, convertView, parent);
		}else{
			convertView=mInflater.inflate(R.layout.homeitemlayout, null, false);
			TextView tv_name=(TextView)convertView.findViewById(R.id.tv_name);
			TextView tv_description=(TextView)convertView.findViewById(R.id.tv_description);
			tv_name.setText(mListFeeds.get(position).getName());
			tv_description.setText(mListFeeds.get(position).getDescription());
		}
		return convertView;
	}
	
	public class Blist extends Model implements Observer{
		private ListView mlvCurrentCalendar;
		private CalendarAdapter mAdapter;
		private View mBlistView;
		private int mWeek;
		public Blist(Context context,int week) {
			mWeek=week;
			mBlistView=mInflater.inflate(R.layout.blistcarditem, null, false);
			mlvCurrentCalendar=(ListView)mBlistView.findViewById(R.id.lv_home_blist);
			mAdapter=new CalendarAdapter(context, null);
			mlvCurrentCalendar.setAdapter(mAdapter);
			CalendarDataSource.getInstance().addObserver(this);
			CalendarDataSource.getInstance().fetchData(week, mDataCallback);
		}
		public View getView(int position, View convertView, ViewGroup parent){
			return mBlistView;
		}
		private DataCallback mDataCallback=new DataCallback() {
			
			@Override
			public void dataCallback(List<Model> listData) {
				mAdapter.updatedata(listData,mWeek);
				mHandler.postDelayed(new Runnable() {
					
					@Override
					public void run() {
						setListViewHeightBasedOnChildren(mlvCurrentCalendar);
					}
				}, 400);
			}
		};
		
		public void setListViewHeightBasedOnChildren(ListView listView) {
			ListAdapter listAdapter = listView.getAdapter();
			if (listAdapter == null || listView == null) {
				return;
			}
			int totalHeight = 0;
			for (int i = 0, len = listAdapter.getCount(); i < len; i++) {
				View listItem = listAdapter.getView(i, null, listView);
				if (listItem != null) {
					listItem.measure(0, 0);
					totalHeight += listItem.getMeasuredHeight();
				}

			}
			ViewGroup.LayoutParams params = listView.getLayoutParams();
			params.height = totalHeight
					+ (listView.getDividerHeight() * (listAdapter.getCount() - 1));
			listView.setLayoutParams(params);
		}
		@Override
		public void update(Observable observable, Object data) {
			int week=(Integer)data;
			if(week==mWeek){
				CalendarDataSource.getInstance().fetchData(mWeek, mDataCallback);
			}
		}

	}
}
