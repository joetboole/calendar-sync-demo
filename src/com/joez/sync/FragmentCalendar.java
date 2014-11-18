package com.joez.sync;

import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.joez.callback.DataCallback;
import com.joez.sync.R;

public class FragmentCalendar extends Fragment {
	private CalendarAdapter mAdapter;
	public FragmentCalendar() {
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView=inflater.inflate(R.layout.fragment_calendar, container,false);
		ListView lv=(ListView)rootView.findViewById(R.id.lv_calendar);
		mAdapter=new CalendarAdapter(getActivity(), null);
		lv.setAdapter(mAdapter);
		CalendarDataSource.getInstance().fetchData(38, mDataCallback);
		return rootView;
	}
	
	private DataCallback mDataCallback=new DataCallback() {
		
		@Override
		public void dataCallback(List<Model> listData) {
			mAdapter.updatedata(listData);
		}
	};

}
