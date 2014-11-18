package com.joez.sync;


import java.util.ArrayList;
import java.util.List;

import android.util.SparseArray;

public class CalendarDataSource {
	private static CalendarDataSource mCalendarDataSource;
	private SparseArray<List<Model>> mDataMap=new SparseArray<List<Model>>();
	private CalendarDataSource() {
		initData();
	}
	public CalendarDataSource getInstance(){
		if(mCalendarDataSource==null){
			mCalendarDataSource=new CalendarDataSource();
		}
		return mCalendarDataSource;
	}
	private void initData(){
		List<Model> listWeek=null;
		for(int week=4;week<=42;week++){
			listWeek=new ArrayList<Model>();
			for(int i=0;i<10;i++){
				listWeek.add(new Model(week,"name ","desciption"));
			}
			mDataMap.put(week, listWeek);
		}
	}
	public List<Model> fetchData(int weekIndex){
		//fetch data
		List<Model> list=mDataMap.get(weekIndex);
		return list;
	}

}
