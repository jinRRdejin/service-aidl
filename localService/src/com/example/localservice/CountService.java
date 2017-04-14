package com.example.localservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
	/*
	 * ����Activity�����ı��ط�����
	 * �ڷ������ڲ���������һ���߳�
	 */
public class CountService extends Service {
	
	private boolean threadDisable;
	public int count;
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
		public void onCreate() {
			// TODO Auto-generated method stub
			super.onCreate();
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					while (!threadDisable) {
	                    try {
	                        Thread.sleep(1000);
	                    } catch (InterruptedException e) {
	                    }
	                    count++;
	                    Log.v("CountService", "Count is " + count);   
	                }
	            }
			}).start();
		}
	@Override
		public void onDestroy() {
			// TODO Auto-generated method stub
			super.onDestroy();
			this.threadDisable = true;
		}
	public int getCount(){
		return count;
		
	}
}
