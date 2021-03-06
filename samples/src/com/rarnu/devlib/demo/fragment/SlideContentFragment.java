package com.rarnu.devlib.demo.fragment;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.anjuke.devlib.base.BaseFragment;
import com.rarnu.devlib.demo.MainActivity;
import com.rarnu.devlib.demo.R;

public class SlideContentFragment extends BaseFragment {

	TextView tvText;
	MenuItem itemBack;
	
	@Override
	public int getBarTitle() {
		return R.string.slidemenu_name;
	}

	@Override
	public int getBarTitleWithPath() {
		return R.string.slidemenu_name;
	}

	@Override
	public void initComponents() {
		tvText = (TextView) innerView.findViewById(R.id.tvText);

	}

	@Override
	public void initEvents() {


	}

	@Override
	public void initLogic() {

	}

	@Override
	public int getFragmentLayoutResId() {
		return R.layout.fragment_slide_content;
	}

	@Override
	public String getMainActivityName() {
		return MainActivity.class.getName();
	}

	@Override
	public void initMenu(Menu menu) {
		itemBack = menu.add(0, 1, 99, "Back");
		itemBack.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
		itemBack.setIcon(android.R.drawable.ic_menu_revert);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case 1:
			getActivity().finish();
			break;
		}
		return true;
	}
	
	@Override
	public void onGetNewArguments(Bundle bn) {
		tvText.setText(bn.getString("text"));

	}

	@Override
	public String getCustomTitle() {
		return null;
	}

	@Override
	public Bundle getFragmentState() {
		return null;
	}

}
