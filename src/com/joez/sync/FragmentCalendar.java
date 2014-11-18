package com.joez.sync;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;

import com.joez.callback.DataCallback;
import com.joez.sync.R;
import com.joez.widget.CalendarAdapter;

public class FragmentCalendar extends Fragment implements OnClickListener,Observer{
	private CalendarAdapter mAdapter;
	private int mCurrentWeek=38;
	private List<Model> mList;
	public FragmentCalendar() {
	}
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		CalendarDataResolver.getInstance().addObserver(this);
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView=inflater.inflate(R.layout.fragment_calendar, container,false);
		Button btn_previous=(Button)rootView.findViewById(R.id.btn_calendar_previous);
		Button btn_next=(Button)rootView.findViewById(R.id.btn_calendar_next);
		Button btn_add=(Button)rootView.findViewById(R.id.btn_calendar_add);
		btn_add.setOnClickListener(this);
		btn_previous.setOnClickListener(this);
		btn_next.setOnClickListener(this);
		
		ListView lv=(ListView)rootView.findViewById(R.id.lv_calendar);
		mAdapter=new CalendarAdapter(getActivity(), null);
		lv.setAdapter(mAdapter);
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				((MainActivity)getActivity()).addOrEditItem(mCurrentWeek,mList.get(position));
				
			}
		});
		CalendarDataResolver.getInstance().fetchData(mCurrentWeek, mDataCallback);
		return rootView;
	}
	
	private DataCallback mDataCallback=new DataCallback() {
		
		@Override
		public void dataCallback(List<Model> listData) {
			mList=listData;
			mAdapter.updatedata(listData,mCurrentWeek);
		}
	};
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_calendar_previous:
			if(mCurrentWeek>4){
				mCurrentWeek--;
				CalendarDataResolver.getInstance().fetchData(mCurrentWeek, mDataCallback);
			}
			break;
		case R.id.btn_calendar_next:
			if(mCurrentWeek<42){
				mCurrentWeek++;
				CalendarDataResolver.getInstance().fetchData(mCurrentWeek, mDataCallback);
			}
			break;
		case R.id.btn_calendar_add:
			((MainActivity)getActivity()).addOrEditItem(mCurrentWeek,null);
			break;
		default:
			break;
		}
	}
	@Override
	public void update(Observable observable, Object data) {
		int week=(Integer)data;
		if(week==mCurrentWeek){
			CalendarDataResolver.getInstance().fetchData(mCurrentWeek, mDataCallback);
		}
	}

	@Override
	public void onDetach() {
		super.onDetach();
		CalendarDataResolver.getInstance().deleteObserver(this);
	}
}
