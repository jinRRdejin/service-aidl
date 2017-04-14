package wangLi.Service.AidlService;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

public class AidlService extends Service {

	private CatBinder catBinder;    
    Timer timer = new Timer();    
    String[] colors = new String[] { "��ɫ", "��ɫ", "��ɫ" };    
    double[] weights = new double[] { 2.3, 3.1, 1.58 };    
    private String color;    
    private double weight;    
    
    // �̳�Stub,Ҳ����ʵ����ICat�ӿڣ���ʵ����IBinder�ӿڡ�    
    public class CatBinder extends ICat.Stub {    
        @Override    
        public String getColor() throws RemoteException {    
            return color;    
        }    
    
        @Override    
        public double getWeight() throws RemoteException {    
            return weight;    
        }    
    }    
    
    @Override    
    public void onCreate() {    
        super.onCreate();    
        catBinder = new CatBinder();    
        timer.schedule(new TimerTask() {    
            @Override    
            public void run() {    
                // ����ı�Service�����color,weight���Ե�ֵ    
                int rand = (int) (Math.random() * 3);    
                color = colors[rand];    
                weight = weights[rand];    
                System.out.println("--------" + rand);    
            }    
        }, 0, 800);    
    }    
    
    @Override    
    public IBinder onBind(Intent arg0) {    
        /*  
         * ����catBinder�����ڰ󶨱���Service������£�  
         * ��catBinder�����ֱ�Ӵ����ͻ��˵�ServiceConnection����� onServiceConnected�����ĵڶ���������  
         * �ڰ�Զ��Service������£�ֻ��catBinder����Ĵ������ͻ��˵� 
         * ServiceConnection�����onServiceConnected�����ĵڶ������� 
         */    
        return catBinder;    
    }    
        
    @Override    
    public void onDestroy() {    
        timer.cancel();    
    }    

}
