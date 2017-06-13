package com.example.demotools;

import java.io.File;
import java.io.IOException;

import android.media.MediaPlayer;

public class PlayRing {
	private static MediaPlayer mp=null;
	private String dooringpath = "/storage/emulated/0/door/";
	public static String strMusic[] = { "door0.mp3","door1.mp3","door2.mp3","door3.mp3"};

	public PlayRing() {
		// TODO Auto-generated constructor stub
		if(mp==null)
			mp= new MediaPlayer();
	}
	public void playMusicFile(String musicName,final int playTime) {
		String playfile = dooringpath+musicName;
		File file = new File(playfile);
	
		if (!file.exists()) {
			LogUtils.e("not found file :"+playfile);
			return;
		}
		try {
			if(mp.isPlaying())
				mp.stop();
			mp.reset();
			mp.setDataSource(playfile);
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
