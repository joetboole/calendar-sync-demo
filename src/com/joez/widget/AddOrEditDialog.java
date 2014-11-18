package com.joez.widget;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.joez.sync.CalendarDataResolver;
import com.joez.sync.Model;
import com.joez.sync.R;

public class AddOrEditDialog extends Dialog implements OnClickListener{
	
	private Context mContext;
	private EditText mEtName,mEtDescription;
	private Model mModel;
	private int mWeek;

	public AddOrEditDialog(Activity activity,Model model,int week) {
		super(activity);
		mWeek=week;
		mModel=model;
		mContext = activity;
		getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.addoredititem);
		mEtName=(EditText)findViewById(R.id.et_name);
		mEtDescription=(EditText)findViewById(R.id.et_description);
		
		if(mModel!=null){
			mEtName.setText(mModel.getName());
			mEtDescription.setText(mModel.getDescription());
		}
		
		Button btnDone=(Button)findViewById(R.id.btn_done);
		Button btnCancel=(Button)findViewById(R.id.btn_cancel);
		btnDone.setOnClickListener(this);
		btnCancel.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()){
			case R.id.btn_done:
				String name=mEtName.getText().toString();
				String description=mEtDescription.getText().toString();
				if(mModel==null){
					if(!TextUtils.isEmpty(name)&&!TextUtils.isEmpty(description)){
						//add on week
						Model model=new Model(mWeek, name, description);
						CalendarDataResolver.getInstance().addItem(mWeek, model);
					}
				}else{
					//update on week
					if(!TextUtils.isEmpty(name)&&!TextUtils.isEmpty(description)){
						//add on week
						mModel.setName(name);
						mModel.setDescription(description);
						CalendarDataResolver.getInstance().updateItem(mWeek);
					}
				}
				dismiss();
				break;
			case R.id.btn_cancel:
				dismiss();
				break;
			default:
				break;
		}
	}
	
	@Override
	public void onBackPressed() {
		dismiss();
		super.onBackPressed();
	}
	
}
