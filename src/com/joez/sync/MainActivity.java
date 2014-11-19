package com.joez.sync;

import com.joez.sync.R;
import com.joez.widget.AddOrEditDialog;
import com.joez.widget.Model;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class MainActivity extends FragmentActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	public void addOrEditItem(int week,Model updateModel){
		AddOrEditDialog dialog=new AddOrEditDialog(this,updateModel,week);
		if(updateModel==null){
			dialog.setTitle(String.format("update week %d",week));
		}else{
			dialog.setTitle(String.format("add week %d",week));
		}
		dialog.show();
	}
}
