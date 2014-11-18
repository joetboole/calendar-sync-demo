package com.joez.sync;


import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import com.joez.callback.DataCallback;

import android.util.Log;
import android.util.SparseArray;

public class CalendarDataResolver extends Observable{
	private static CalendarDataResolver mCalendarDataSource;
	private SparseArray<List<Model>> mDataMap=new SparseArray<List<Model>>();
	private CalendarDataResolver() {
		initData();
	}
	public static CalendarDataResolver getInstance(){
		if(mCalendarDataSource==null){
			mCalendarDataSource=new CalendarDataResolver();
		}
		return mCalendarDataSource;
	}
	private void initData(){
		List<Model> listWeek=null;
		for(int week=4;week<=42;week++){
			listWeek=new ArrayList<Model>();
			for(int i=0;i<7;i++){
				listWeek.add(new Model(week,"name"+i,"desciption"+i));
			}
			mDataMap.put(week, listWeek);
		}
	}
	
	public void addItem(int week,Model model){
		List<Model> list=mDataMap.get(week);
		Log.e("debug", "additem@@@"+mDataMap.get(week).size());
		if(list!=null){
			list.add(model);
			Log.e("debug", "additem@@@"+mDataMap.get(week).size());
			setChanged();
			notifyObservers(week);
		}
	}
	
	public void updateItem(int week){
		setChanged();
		notifyObservers(week);
	}
	
	public void fetchData(int weekIndex,DataCallback dataCallback){
		//fetch data
		List<Model> list=mDataMap.get(weekIndex);
		if(list==null){
			//fetch from net ..
			dataCallback.dataCallback(list);
		}else{
			dataCallback.dataCallback(list);
		}
	}

}
