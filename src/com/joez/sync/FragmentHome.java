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

public class FragmentHome extends Fragment {
	private CalendarAdapter mAdapter;
	private ListView mLv;
	public FragmentHome() {
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView=inflater.inflate(R.layout.fragment_home, container,false);
		mLv=(ListView)rootView.findViewById(R.id.lv_home);
		mAdapter=new CalendarAdapter(getActivity(), null);
		mLv.setAdapter(mAdapter);
		CalendarDataSource.getInstance().fetchData(40, mDataCallback);
		return rootView;
	}
	
	private DataCallback mDataCallback=new DataCallback() {
		
		@Override
		public void dataCallback(List<Model> listData) {
			mAdapter.updatedata(listData);
		}
	};

}
