

import android.content.Context;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;


public class ClickToSlideListView extends ListView implements OnItemClickListener{

	private boolean isTranslated;
	private View currentView;
	private View currentButton;
	private float slideOffset;

	public ClickToSlideListView(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);
		setOnItemClickListener();
	}

	public ClickToSlideListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		setOnItemClickListener();
	}

	public ClickToSlideListView(Context context) {
		super(context);
		setOnItemClickListener();
	}
	
	private void setOnItemClickListener() {
		this.setOnItemClickListener(this);
		
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View view, int pos, long arg3) {
		int xShift = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, slideOffset, 
				getResources().getDisplayMetrics());

		View frontContent = view.findViewById(R.id.front_content);
		View clickButton = view.findViewById(R.id.clickButton);
		
		if(currentView != frontContent && isTranslated) {
			animateItem(currentView,xShift,true,currentButton);
			animateItem(frontContent,xShift,false,downloadButton);	
			downloadButton.setVisibility(View.VISIBLE);
		} else {
		
			if(!isTranslated) {
				animateItem(frontContent,xShift,isTranslated,downloadButton);
				downloadButton.setVisibility(View.VISIBLE);
				isTranslated = true;
				
			} else {
				animateItem(frontContent,xShift,isTranslated,downloadButton);
				isTranslated = false;
			}
			
		}
		
		currentView = frontContent;
		currentButton = downloadButton;
		
	}
	
	private void animateItem(View v,float value,final boolean isTranslated,final View buttonView) {
		Animation animation = null;
		if(isTranslated) {
			animation = new TranslateAnimation(-value, 0,0, 0);
		} else {
			animation = new TranslateAnimation(0, -value,0, 0);
		}
		animation.setDuration(500);
		animation.setFillAfter(true);
		animation.setAnimationListener(new AnimationListener() {
			
			@Override
			public void onAnimationStart(Animation animation) {
				
			}
			
			@Override
			public void onAnimationRepeat(Animation animation) {
				
			}
			
			@Override
			public void onAnimationEnd(Animation animation) {
				if(isTranslated) {
					buttonView.setVisibility(View.GONE);
				}
				
			}
		});
		v.startAnimation(animation);
	}

	public float getSlideOffset() {
		return slideOffset;
	}

	public void setSlideOffset(float slideOffset) {
		this.slideOffset = slideOffset;
	}
	
	

}
