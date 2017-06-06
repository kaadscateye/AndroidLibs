package com.example.demotools;

import java.io.File;
import java.io.IOException;

import android.media.MediaPlayer;

public class PlayRing {
	private static MediaPlayer mp=null;
	public static String strMusic[] = { "/mnt/sdcard/external_sd/hongmeigui.mp3",
			"/mnt/sdcard/external_sd/taiduo.mp3" };

	public PlayRing() {
		// TODO Auto-generated constructor stub
		if(mp==null)
			mp= new MediaPlayer();
	}
	public void playMusicFile(String musicName,final int playTime) {
		File file = new File(musicName);
	
		if (!file.exists()) {
			LogUtils.e("not found file :"+musicName);
			return;
		}
		try {
			mp.stop();
			mp.reset();
			mp.setDataSource(musicName);
			mp.prepare();
			mp.seekTo(0);
			mp.start();

			new Thread(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					int count =0;
					while (isPlaying()) {
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						if(++count>=playTime){
							stop();
						}
//						LogUtils.e("count ="+count);
					}
					LogUtils.e("thread exit ");	
				}
			}).start();
			
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void playOrPause() {
		if (mp.isPlaying()) {
			mp.pause();
		} else {
			mp.start();
		}
	}

	public void stop() {
		if (mp != null) {
			mp.stop();
			try {
				mp.prepare();
				mp.seekTo(0);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	public boolean isPlaying(){
		return mp.isPlaying();
	}
	
	public void destoryMusicResource(){
         if(isPlaying()){
         	stop();
         }     
         LogUtils.e(" close music ");  
	}
}
