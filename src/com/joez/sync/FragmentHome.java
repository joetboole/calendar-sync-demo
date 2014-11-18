package com.joez.sync;



import com.joez.widget.HomeAdapter;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;


public class FragmentHome extends Fragment implements OnClickListener{
	private HomeAdapter mAdapter;
	private ListView mLv;
	private int mCurrentWeek=40;
	private Handler mHandler=new Handler();
	public FragmentHome() {
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView=inflater.inflate(R.layout.fragment_home, container,false);
		Button btn_previous=(Button)rootView.findViewById(R.id.btn_home_previous);
		Button btn_next=(Button)rootView.findViewById(R.id.btn_home_next);
		Button btn_add=(Button)rootView.findViewById(R.id.btn_home_add);
		btn_previous.setOnClickListener(this);
		btn_next.setOnClickListener(this);
		btn_add.setOnClickListener(this);
		mLv=(ListView)rootView.findViewById(R.id.lv_home);
		mAdapter=new HomeAdapter(mHandler, getActivity());
		mLv.setAdapter(mAdapter);
		mAdapter.updateFeeds(mCurrentWeek);
		return rootView;
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_home_previous:
			if(mCurrentWeek>4){
				mCurrentWeek--;
				mAdapter.updateFeeds(mCurrentWeek);
			}
			break;
		case R.id.btn_home_next:
			if(mCurrentWeek<42){
				mCurrentWeek++;
				mAdapter.updateFeeds(mCurrentWeek);
			}
			break;
		case R.id.btn_home_add:
			break;
		default:
			break;
		}
		
	}

}
