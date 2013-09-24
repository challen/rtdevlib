package com.anjuke.devlib.component.tools;

import java.util.ArrayList;

import android.util.SparseIntArray;

public class HeightCache {

		private SparseIntArray mMap;
		private ArrayList<Integer> mOrder;
		private int mMaxSize;

		public HeightCache(int size) {
			mMap = new SparseIntArray(size);
			mOrder = new ArrayList<Integer>(size);
			mMaxSize = size;
		}

		public void add(int position, int height) {
			int currHeight = mMap.get(position, -1);
			if (currHeight != height) {
				if (currHeight == -1) {
					if (mMap.size() == mMaxSize) {
						mMap.delete(mOrder.remove(0));
					}
				} else {
					mOrder.remove((Integer) position);
				}
				mMap.put(position, height);
				mOrder.add(position);
			}
		}

		public int get(int position) {
			return mMap.get(position, -1);
		}

		public void clear() {
			mMap.clear();
			mOrder.clear();
		}

	}