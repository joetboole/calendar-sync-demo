package com.joez.sync;

import com.joez.sync.R;
import com.joez.widget.AddOrEditDialog;
import com.joez.widget.AddOrEditDialog.OnDialogCallback;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class MainActivity extends FragmentActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
	}
	
	public void addOrEditItem(int week,Model updateModel){
		AddOrEditDialog dialog=new AddOrEditDialog(this, mCallback,updateModel,week);
		if(updateModel==null){
			dialog.setTitle(String.format("update week %d",week));
		}else{
			dialog.setTitle(String.format("add week %d",week));
		}
		dialog.show();
	}
	
	private OnDialogCallback mCallback=new OnDialogCallback() {
		
		@Override
		public void onDone() {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void onCancel() {
			// TODO Auto-generated method stub
			
		}
	};
	
}
