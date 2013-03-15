package com.anjuke.devlib.base;

import android.app.Fragment;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup.LayoutParams;

import com.anjuke.devlib.R;
import com.anjuke.devlib.base.inner.InnerActivity;
import com.anjuke.devlib.common.ISliding;
import com.anjuke.devlib.component.SlidingMenu;
import com.anjuke.devlib.utils.SlidingHelper;

public abstract class BaseSlidingActivity extends InnerActivity implements ISliding {

	private SlidingHelper mHelper;
	
	@Override
	public boolean getCondition() {
		return false;
	}

	@Override
	public int getBaseLayout() {
		return R.layout.layout_replacement;
	}

	@Override
	public int getReplaceId() {
		return R.id.fReplacement;
	}
	
	public abstract Fragment replaceMenuFragment();
	
	public abstract int getBehindOffset();
	
	public abstract int getAboveTouchMode();
	
	public abstract int getBehindTouchMode();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		mHelper = new SlidingHelper(this);
		mHelper.onCreate(savedInstanceState);
		
		super.onCreate(savedInstanceState);
		setBehindContentView(R.layout.layout_menu_replacement);
		replaceMenu();
		
        SlidingMenu sm = getSlidingMenu();
        sm.setShadowWidth(50);
        sm.setShadowDrawable(R.drawable.shadow);
        sm.setBehindOffset(getBehindOffset());
        sm.setFadeDegree(0.35f);
        sm.setTouchModeAbove(getAboveTouchMode());
        sm.setTouchModeBehind(getBehindTouchMode());
	}
	
	public void replaceMenu() {
		getFragmentManager().beginTransaction()
				.replace(R.id.menu, replaceMenuFragment()).commit();
	}

	@Override
	public void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		mHelper.onPostCreate(savedInstanceState);
	}

	@Override
	public View findViewById(int id) {
		View v = super.findViewById(id);
		if (v != null)
			return v;
		return mHelper.findViewById(id);
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		mHelper.onSaveInstanceState(outState);
	}

	@Override
	public void setContentView(int id) {
		setContentView(getLayoutInflater().inflate(id, null));
	}

	@Override
	public void setContentView(View v) {
		setContentView(v, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
	}

	@Override
	public void setContentView(View v, LayoutParams params) {
		super.setContentView(v, params);
		mHelper.registerAboveContentView(v, params);
	}

	public void setBehindContentView(int id) {
		setBehindContentView(getLayoutInflater().inflate(id, null));
	}

	public void setBehindContentView(View v) {
		setBehindContentView(v, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
	}

	public void setBehindContentView(View v, LayoutParams params) {
		mHelper.setBehindContentView(v, params);
	}

	public SlidingMenu getSlidingMenu() {
		return mHelper.getSlidingMenu();
	}

	public void toggle() {
		mHelper.toggle();
	}

	public void showContent() {
		mHelper.showContent();
	}

	public void showMenu() {
		mHelper.showMenu();
	}

	public void showSecondaryMenu() {
		mHelper.showSecondaryMenu();
	}

	public void setSlidingActionBarEnabled(boolean b) {
		mHelper.setSlidingActionBarEnabled(b);
	}

	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event) {
		boolean b = mHelper.onKeyUp(keyCode, event);
		if (b) return b;
		return super.onKeyUp(keyCode, event);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			toggle();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	

}
