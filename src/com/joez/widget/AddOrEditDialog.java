package com.joez.widget;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.joez.sync.Model;
import com.joez.sync.R;

public class AddOrEditDialog extends Dialog implements OnClickListener{
	
	private Context mContext;
	private OnDialogCallback mCallback;
	private EditText mEtName,mEtDescription;
	private Model mModel;
	private int mWeek;

	public AddOrEditDialog(Activity activity,OnDialogCallback callback,Model model,int week) {
		super(activity);
		mWeek=week;
		mModel=model;
		mContext = activity;
		getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
		mCallback=callback;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.addoredititem);
		mEtName=(EditText)findViewById(R.id.et_name);
		mEtDescription=(EditText)findViewById(R.id.et_description);
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
	
	private void dialogDismiss(){
		this.dismiss();
	}
	
	@Override
	public void onBackPressed() {
		mCallback.onCancel();
		super.onBackPressed();
	}
	
	public interface OnDialogCallback { 
        public void onCancel(); 
        public void onDone();
	} 
	
}
