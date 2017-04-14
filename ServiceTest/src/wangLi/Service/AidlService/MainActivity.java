package wangLi.Service.AidlService;

import wangLi.Service.AidlService.R;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.app.Activity;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {

	private ICat catService;     
    private Button get;    
    EditText color, weight;
    
    private ServiceConnection conn = new ServiceConnection()    
    {    
        @Override    
        public void onServiceConnected(ComponentName name, IBinder service)    
        {    
            // 获取远程Service的onBind方法返回的对象的代理    
            catService = ICat.Stub.asInterface(service); //通过ICat.Stub.asInterface转换  
        }    
    
        @Override    
        public void onServiceDisconnected(ComponentName name)    
        {    
            catService = null;    
        }    
    };    
    
    @Override    
    public void onCreate(Bundle savedInstanceState)    
    {    
        super.onCreate(savedInstanceState);    
        setContentView(R.layout.activity_main);    
        get = (Button) findViewById(R.id.button1);    
        color = (EditText) findViewById(R.id.color);    
        weight = (EditText) findViewById(R.id.weight);    
        // 创建所需绑定服务的Intent    
        Intent intent = new Intent();    
        intent.setAction("wangLi.Service.AidlService");  //注意这个Intent -------------// 
        
        
        // 绑定远程服务    
        bindService(intent, conn, Service.BIND_AUTO_CREATE);    
        
        get.setOnClickListener(new OnClickListener()    
        {    
            @Override    
            public void onClick(View arg0)    
            {    
                try    
                {    
                    // 获取、并显示远程Service的状态    
                    color.setText(catService.getColor());    
                    weight.setText(catService.getWeight() + "");    
                }    
                catch (RemoteException e)    
                {    
                    e.printStackTrace();    
                }    
            }    
        });    
    }    
    
    @Override    
    public void onDestroy()    
    {    
        super.onDestroy();    
        // 解除绑定    
        this.unbindService(conn);    
    }    
}
    

