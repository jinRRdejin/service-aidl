package com.example.localserviceandactivity;

import android.os.Bundle;
import android.os.IBinder;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.view.Menu;

public class MainActivity extends Activity {
	
	private IcountService countService;

	private ServiceConnection serviceConnection = new ServiceConnection() {
		
		@Override
		public void onServiceDisconnected(ComponentName arg0) {
			countService = null;
		}
		
		@Override
		public void onServiceConnected(ComponentName arg0, IBinder arg1) {
			
			countService = (IcountService) arg1;
		}
	};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        Intent intent = new Intent();  
        intent.setAction("com.example.localserviceandactivity");
        this.bindService(intent, this.serviceConnection, BIND_AUTO_CREATE);      
                      
    }
    @Override
    protected void onDestroy() {
    	
    	super.onDestroy();
    	this.unbindService(serviceConnection);
    }
}
