package com.example.localserviceandactivity;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class myService extends Service implements IcountService {
	
	
	private boolean threadDisable;

    private int count;
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return serviceBinder;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return count;
	}
	
	
	
	private ServiceBinder serviceBinder = new ServiceBinder();
	
	public class ServiceBinder extends Binder implements IcountService{

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return count;
		}
		
	}
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
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
        Log.v("CountService", "on destroy");
	}

}
