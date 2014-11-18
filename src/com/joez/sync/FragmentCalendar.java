package com.joez.sync;

import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.joez.callback.DataCallback;
import com.joez.sync.R;

public class FragmentCalendar extends Fragment implements OnClickListener{
	private CalendarAdapter mAdapter;
	private int mCurrentWeek=38;
	public FragmentCalendar() {
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView=inflater.inflate(R.layout.fragment_calendar, container,false);
		Button btn_previous=(Button)rootView.findViewById(R.id.btn_calendar_previous);
		Button btn_next=(Button)rootView.findViewById(R.id.btn_calendar_next);
		btn_previous.setOnClickListener(this);
		btn_next.setOnClickListener(this);
		ListView lv=(ListView)rootView.findViewById(R.id.lv_calendar);
		mAdapter=new CalendarAdapter(getActivity(), null);
		lv.setAdapter(mAdapter);
		CalendarDataSource.getInstance().fetchData(mCurrentWeek, mDataCallback);
		registerForContextMenu(lv);
		return rootView;
	}
	
	private DataCallback mDataCallback=new DataCallback() {
		
		@Override
		public void dataCallback(List<Model> listData) {
			mAdapter.updatedata(listData);
		}
	};
	
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		return super.onContextItemSelected(item);
	}
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		menu.setHeaderTitle("action calendar item");
		menu.add(0, 1, Menu.NONE, "delete");
		menu.add(0,2,Menu.NONE,"update a add");
		menu.add(0,3,Menu.NONE,"update b add");
		menu.add(0, 4, Menu.NONE, "add item zzz");
		super.onCreateContextMenu(menu, v, menuInfo);
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_calendar_previous:
			if(mCurrentWeek>4){
				mCurrentWeek--;
				CalendarDataSource.getInstance().fetchData(mCurrentWeek, mDataCallback);
			}
			break;
		case R.id.btn_calendar_next:
			if(mCurrentWeek<42){
				mCurrentWeek++;
				CalendarDataSource.getInstance().fetchData(mCurrentWeek, mDataCallback);
			}
			break;
		default:
			break;
		}
		
	}

}
