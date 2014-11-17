package com.joez.sync;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.joez.calendarsyncdemo.R;

public class FragmentHome extends Fragment {
	private CalendarAdapter mAdapter;
	public FragmentHome() {
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView=inflater.inflate(R.layout.fragment_home, container,false);
		ListView lv=(ListView)rootView.findViewById(R.id.lv_home);
		mAdapter=new CalendarAdapter(getActivity(), null);
		lv.setAdapter(mAdapter);
		return rootView;
	}

}
