package com.mobileaware.deutschetelekom.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

public class CustomTabHost {

	private TabHost mTabHost;
	private LayoutInflater mLayoutInflater;
	private View mRootView;
	private TabSpec mTabSpec;
	private Context mContext;
	private String mTag;
	private int mContentId = -1;
	int mIconRes = -1;
	private int mTabIndicatorLayout = -1;
	private int mBackgroundRes = -1;
	private int mTextViewId = -1;
	
	public CustomTabHost(Context context, int resource, int tabHostId) {
		mLayoutInflater = LayoutInflater.from(context);
		mRootView = mLayoutInflater.inflate(resource, null);
		mTabHost = (TabHost) mRootView.findViewById(tabHostId);
		mContext = context;
		mTabHost.setup();
	}
	
	public CustomTabHost(Context context, int resource) {
		mLayoutInflater = LayoutInflater.from(context);
		mRootView = mLayoutInflater.inflate(resource, null);
		mTabHost = (TabHost) mRootView.findViewById(android.R.id.tabhost);
		mContext = context;
		mTabHost.setup();
	}

	public CustomTabHost(Context context, TabHost tabHost) {
		mLayoutInflater = LayoutInflater.from(context);
		mTabHost = tabHost;
		mContext = context;
		mTabHost.setup();
	}

	public CustomTabHost setTitle(String title){
		mTag = title;
		return this;
	}
	
	public CustomTabHost setIndicatorView(int layoutId) {
		mTabIndicatorLayout = layoutId;
		return this;
	}
	
	public CustomTabHost setContentId(int contentId){
		mContentId = contentId;
		return this;
	}
	
	public CustomTabHost setTextViewId(int textViewId){
		mTextViewId = textViewId;
		return this;
	}
	
	public CustomTabHost setBackgroundRes(int backgroundRes){
		mBackgroundRes = backgroundRes;
		return this;
	}
	
	public CustomTabHost setIconRes(int iconRes){
		mIconRes = iconRes;
		return this;
	}
	
	

	public void addTab() {

		mTabSpec = mTabHost.newTabSpec(mTag);

		if(mTabIndicatorLayout == -1) {
			throw new NullPointerException("Please provide a TextView Id to show the Tab title"); 
		}
		
		View tabIndicatorView = mLayoutInflater.inflate(mTabIndicatorLayout, null);
		
		if (mBackgroundRes != -1) {
			tabIndicatorView.setBackgroundResource(mBackgroundRes);
		}

		TextView tv = (TextView) tabIndicatorView.findViewById(mTextViewId);
		tv.setText(mTag);

		if (mIconRes != -1) {
			tv.setCompoundDrawablesWithIntrinsicBounds(null, mContext.getResources()
					.getDrawable(mIconRes), null, null);
		} 
		
		mTabSpec.setIndicator(tabIndicatorView);
		mTabSpec.setContent(mContentId);
		
		mTabHost.addTab(mTabSpec);

	}
}
