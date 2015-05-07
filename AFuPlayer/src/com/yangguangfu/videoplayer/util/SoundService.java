package com.yangguangfu.videoplayer.util;

import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.media.MediaPlayer;

/**
 * 
 * @author 阿福 声音服务类 1、播放作者家乡音乐 2、播放按钮音效
 */
public class SoundService {

	/**
	 * 声音资源缓存, 以声音资源的ID作为KEY。
	 */
	private Map<Integer, MediaPlayer> soundCacheButtonMusic = new HashMap<Integer, MediaPlayer>();
	private Map<Integer, MediaPlayer> soundCacheAuthorHometownMusic = new HashMap<Integer, MediaPlayer>();
	private Context context;
	private SharedPreferencesUtils pref;

	public SoundService(Context context) {
		this.context = context;
		if (pref == null) {
			pref = new SharedPreferencesUtils(context);
		}
	}

	/**
	 * 播放按钮音效
	 * 
	 * @param resId
	 */
	public void playButtonMusic(int resId) {

		if (!pref.isButtonMusic()) {
			return;
		}
		MediaPlayer mp = soundCacheButtonMusic.get(resId);
		if (mp == null) {
			mp = MediaPlayer.create(context, resId);
		  //mp = MediaPlayer.create(context, R.raw.button);
			soundCacheButtonMusic.put(resId, mp);
			if (mp != null) {
				mp.start();
			}
		} else {
			if (mp != null) {
				mp.start();
			}
		}
	}
	
	/**
	 * 播放作者家乡音乐
	 * @param resId
	 */
	public void hometownMusicPlay(int resId) {

		if (!pref.isHometownMusic()) {
			return;
		}
		
		MediaPlayer mp = soundCacheAuthorHometownMusic.get(resId);
		if (mp == null) {
			mp = MediaPlayer.create(context, resId);
			// mp = MediaPlayer.create(context, R.raw.myhome);
			soundCacheAuthorHometownMusic.put(resId, mp);
			mp.start();
			mp.setLooping(true);

		} else {
			if (mp != null) {
				mp.start();
				mp.setLooping(true);
			}

		}

	}

	public void pauseHometownMusic(int resId) {
		if (!pref.isHometownMusic()) {
			return;
		}
		MediaPlayer mp = soundCacheAuthorHometownMusic.get(resId);
		if (mp != null) {
			if (mp.isPlaying()) {
				mp.pause();

			}
		}
	}

	/**
	 * 家乡音乐是否在播放
	 * @param resId
	 * @return
	 */
	public boolean isHometownPlaying(int resId) {
		Boolean isAuthorMusicPlaying = false;
		if (!pref.isHometownMusic()) {
			isAuthorMusicPlaying = false;
			return isAuthorMusicPlaying;
		}
		MediaPlayer mp = soundCacheAuthorHometownMusic.get(resId);
		if (mp != null) {
			if (mp.isPlaying()) {
				isAuthorMusicPlaying = true;
			} else {
				isAuthorMusicPlaying = false;
			}
		}
		return isAuthorMusicPlaying;
	}

	public void stopHometownMusic(int resId) {

		if (!pref.isHometownMusic()) {
			return;
		}
		MediaPlayer mp = soundCacheAuthorHometownMusic.get(resId);
		if (mp != null) {
			mp.stop();
			soundCacheAuthorHometownMusic.remove(resId);
			mp.release();
			mp = null;

		}
	}

	/**
	 * 如果缓存里面没有声音，就装载声音，并且放到缓存。
	 * @param resId
	 * @return
	 */
	private MediaPlayer findResourceFromCache(int resId) {
		MediaPlayer mp = null;
		if (soundCacheButtonMusic.get(resId) == null) {
			mp = MediaPlayer.create(context, resId);
			soundCacheButtonMusic.put(resId, mp);
		} else {
			mp = soundCacheButtonMusic.get(resId);
		}
		return mp;
	}
	

	public void release() {

		for (Map.Entry<Integer, MediaPlayer> entry : soundCacheButtonMusic
				.entrySet()) {
			MediaPlayer mp = entry.getValue();
			if (mp != null) {
				if (mp.isPlaying()) {
					mp.stop();
				}
				mp.release();
			}
		}
	}

}
