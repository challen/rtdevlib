package com.anjuke.devlib.base.adapter;

import java.util.List;

import android.app.Fragment;
import android.app.FragmentManager;


public class BaseFragmentAdapter extends FragmentPagerAdapter {

	private List<Fragment> list;

	public BaseFragmentAdapter(FragmentManager fragmentManager,
			List<Fragment> listFragment, List<String> tags) {
		super(fragmentManager, tags);
		this.list = listFragment;
	}

	@Override
	public Fragment getItem(int position) {
		return list.get(position);
	}

	@Override
	public int getCount() {
		return list.size();
	}

}
