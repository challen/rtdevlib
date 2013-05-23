package com.anjuke.devlib.base.adapter;

import java.util.List;

import android.content.Context;

import com.anjuke.devlib.base.inner.InnerAdapter;

public abstract class BaseAdapter<T> extends InnerAdapter<T> {

	public BaseAdapter(Context context, List<T> list) {
		super(context, list);
	}

}
