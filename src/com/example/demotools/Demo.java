package com.example.demotools;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class Demo extends Activity implements OnClickListener{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
    }
    private void initUi(){
    	Button but = (Button)findViewById(R.id.butSendBrocastId);
    	but.setOnClickListener(this);
    	Button wait = (Button)findViewById(R.id.butwaitId);
    	wait.setOnClickListener(this);
    }
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.butSendBrocastId:
            SendBroadcast(""+KeyEvent.KEYCODE_POWER,"com.kdscateye.keydown");
			break;
		case R.id.butwaitId:
            SendBroadcast(""+KeyEvent.KEYCODE_VOLUME_UP,"com.kdscateye.keyup");
			break;
		default:
			break;
		}
		Toast.makeText(getApplicationContext(), "发送广播成功", Toast.LENGTH_SHORT).show();  
	}
	private void SendBroadcast(Object obj ,String action){
		Intent intent = new Intent(); 
		intent.setAction(action); 
		intent.putExtra(Receiver.Message,(String)obj); 
		this.sendBroadcast(intent);  
	}
	
	public boolean onKeyDown(int keyCode, android.view.KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK
				&& event.getAction() == KeyEvent.ACTION_DOWN) {
			return false;
		}else if(keyCode == KeyEvent.KEYCODE_VOLUME_UP&& event.getAction() == KeyEvent.ACTION_DOWN){
			SendBroadcast(""+KeyEvent.KEYCODE_POWER,"com.kdscateye.keydown");
		}else if(keyCode == KeyEvent.KEYCODE_VOLUME_DOWN&& event.getAction() == KeyEvent.ACTION_DOWN){
			SendBroadcast(""+KeyEvent.KEYCODE_VOLUME_UP,"com.kdscateye.keydown");
		}
		return super.onKeyDown(keyCode, event);
	}
}
