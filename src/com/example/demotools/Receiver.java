package com.example.demotools;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Message;
import android.view.KeyEvent;

public class Receiver extends BroadcastReceiver implements DoMsg{

	private static Boolean EventPthread = false;
	public static final String Message = "message";

	public static final int Key_menu_up = 255;//���ϰ���
	public static final int Key_menu_down = 256;//���°���
	public static final int Key_menu_ok = 257;//ȷ������
	public static final int Key_menu_exit = 258;//�˳�����
	public static final int Key_menu_picture = 259;//ץ������ͷ����
	public static final int Key_menu_ring = 260;//�����¼�
	public static final int Key_menu_pir = 261;//pir �¼�

	
	public static final int talk_pir = 262;		//pir �¼�
	public static final int StartRecoder = 263;	//pir �¼�
	public static final int StopRecoder = 264;//pir �¼�
	public static final int applinphone = 265;//pir �¼�
	public static final int ConnectAppOk=266;
	@Override
	public void onReceive(Context arg0, Intent arg1) {
		// TODO Auto-generated method stub
		String message = arg1.getExtras().getString(Receiver.Message);
		String action = arg1.getAction();
		LogUtils.e("message :" + message + "action " + action);

		if (!Tools.isNumeric(message)) {
			LogUtils.e("message error ...");
			return;
		}
		int keyCode = Integer.parseInt(message);
		Handler_KeyEvent(keyCode, action);
	}
	//����ײ㷢�������Ĺ㲥�����¼�
	private void Handler_KeyEvent(int keyCode, String action) {
		Message message;
		switch (keyCode) {
			case KeyEvent.KEYCODE_POWER:
				if (EventPthread) {
					return;
				}
				LogUtils.e("create pthread run ");
				EventPthread = true;
				PushBlockQueue.getInstance().start();
				PushBlockQueue.getInstance().callBack(this);
				break;
			case KeyEvent.KEYCODE_VOLUME_DOWN:
				break;
			case KeyEvent.KEYCODE_VOLUME_UP:
				LogUtils.e("KEYCODE_VOLUME_UP ");
				message = new Message();
				message.what = KeyEvent.KEYCODE_VOLUME_UP;
				message.obj = PlayRing.strMusic[0];
				try {
					PushBlockQueue.getInstance().put(message);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case Key_menu_up:		//���ϰ���
				break;
			case Key_menu_down:		//���°���
				break;
			case Key_menu_ok:		//ȷ������
				break;
			case Key_menu_exit:		//�˳�����
				break;
			case Key_menu_picture:	//ץ������ͷ����
				break;
			case Key_menu_ring:		//�����¼�
				break;
			case Key_menu_pir:		//ץ�� �¼�
				break;
			}
	}
	@Override
	public void handlerMsg(Object obj) {
		// TODO Auto-generated method stub
		PlayRing player = new PlayRing();
		Message message = (Message)obj;
		switch (message.what) {
			case KeyEvent.KEYCODE_VOLUME_UP:
				LogUtils.e("handlerMsg KEYCODE_VOLUME_UP ");
				player.playMusicFile(message.obj.toString(), 20);
				break;
			case Key_menu_picture:	//ץ������ͷ����
				break;
			case talk_pir:
				break;
			case applinphone:
				player.stop();
				break;
			case Key_menu_ring:		//�����¼�
				handlerRing();	
				break;
			case Key_menu_pir:		//ץ�� �¼�
				player.playMusicFile(message.obj.toString(), 20);
				break;
			case ConnectAppOk:
				break;
		}
	}
	
	private void handlerRing(){
		PlayRing player = new PlayRing();
		String music="";
		player.playMusicFile(music, 20);//�������õ�����
		//����app
		
	}
	private void handlerPir(){
		
	}
}
