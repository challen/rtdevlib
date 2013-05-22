package com.rarnu.devlib.demo.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.widget.TextView;

import com.anjuke.devlib.base.BaseFragment;
import com.anjuke.devlib.component.PullDownLayout;
import com.anjuke.devlib.component.PullDownLayout.RefreshListener;
import com.anjuke.devlib.component.PullDownScrollView;
import com.rarnu.devlib.demo.MainActivity;
import com.rarnu.devlib.demo.R;

public class PullDownLayoutFragment extends BaseFragment implements
		RefreshListener {

	PullDownLayout pdl;
	PullDownScrollView pdsv;
	TextView tvScroll;

	Handler hUpdate = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			if (msg.what == PullDownLayout.WHAT_DID_REFRESH) {
				String t = tvScroll.getText().toString();
				String txt = (String) msg.obj;
				t = txt + t;
				tvScroll.setText(t);

				pdl.finishRefresh();
			}
			super.handleMessage(msg);
		};

	};

	@Override
	public int getBarTitle() {
		return R.string.pulldown_layout_name;
	}

	@Override
	public int getBarTitleWithPath() {
		return R.string.pulldown_layout_name_with_path;
	}

	@Override
	public void initComponents() {
		pdl = (PullDownLayout) innerView.findViewById(R.id.pdl);
		pdsv = (PullDownScrollView) innerView.findViewById(R.id.pdsv);
		tvScroll = (TextView) innerView.findViewById(R.id.tvScroll);

		pdl.sv = pdsv;

	}

	@Override
	public void initEvents() {
		pdl.setRefreshListener(this);

	}

	@Override
	public void initLogic() {
		String str = "test test test test test test test test test test ";
		String text = "";
		for (int i = 0; i < 50; i++) {
			text += str;
		}
		tvScroll.setText(text);
	}

	@Override
	public int getFragmentLayoutResId() {
		return R.layout.fragment_pulldown_layout;
	}

	@Override
	public String getMainActivityName() {
		return MainActivity.class.getName();
	}

	@Override
	public void initMenu(Menu menu) {

	}

	@Override
	public void onGetNewArguments(Bundle bn) {

	}

	@Override
	public void onRefresh() {

		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					Thread.sleep(1000);
				} catch (Exception e) {

				}
				Message msg = new Message();
				msg.what = PullDownLayout.WHAT_DID_REFRESH;
				msg.obj = new String("abcdefg ");
				hUpdate.sendMessage(msg);
			}
		}).start();
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
