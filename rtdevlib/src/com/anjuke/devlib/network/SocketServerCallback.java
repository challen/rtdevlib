package com.anjuke.devlib.network;

public interface SocketServerCallback {

	void onError(String msg);
	void onReceive(String msg);
}
