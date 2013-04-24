package com.anjuke.devlib.base.inner;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.anjuke.devlib.base.intf.InnerIntf;
import com.anjuke.devlib.common.GlobalInstance;

public abstract class InnerFragment extends Fragment implements InnerIntf{

	protected View innerView = null;
	protected Bundle innerBundle = null;
	
	protected String tagText;
	protected String tabTitle;

	@Override
	public String getTagText() {
		return tagText;
	}

	public String getTabTitle() {
		return tabTitle;
	}
	
	public InnerFragment() {
		super();
	}
	
	public InnerFragment(String tagText, String tabTitle) {
		super();
		this.tagText = tagText;
		this.tabTitle = tabTitle;
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		if (GlobalInstance.dualPane) {
			getActivity().getActionBar().setTitle(getBarTitleWithPath());
		} else {
			getActivity().getActionBar().setTitle(getBarTitle());
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		innerView = inflater
				.inflate(getFragmentLayoutResId(), container, false);
		initComponents();
		initEvents();
		return innerView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		innerBundle = getArguments();
		initLogic();
	}

	protected abstract int getBarTitle();

	protected abstract int getBarTitleWithPath();

	protected abstract void initComponents();
	
	protected abstract void initEvents();

	protected abstract void initLogic();

	protected abstract int getFragmentLayoutResId();
	
	protected abstract String getMainActivityName();

	protected abstract void initMenu(Menu menu);
	
	protected abstract void onGetNewArguments(Bundle bn);
	
	public void setNewArguments(Bundle bn) {
		innerBundle = getArguments();
		onGetNewArguments(bn);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
	}

	@Override
	public void onPause() {
		getActivity().setTitle(getBarTitle());
		super.onPause();
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		if (getActivity() == null) {
			return;
		}
		if (getActivity().getClass().getName()
				.equals(getMainActivityName())
				&& !GlobalInstance.dualPane) {
			return;
		}

		initMenu(menu);
	}

}
