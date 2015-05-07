package com.yangguangfu.videoplayer.util;

import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.media.MediaPlayer;

/**
 * 
 * @author ���� ���������� 1���������߼������� 2�����Ű�ť��Ч
 */
public class SoundService {

	/**
	 * ������Դ����, ��������Դ��ID��ΪKEY��
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
	 * ���Ű�ť��Ч
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
	 * �������߼�������
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
	 * ���������Ƿ��ڲ���
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
	 * �����������û����������װ�����������ҷŵ����档
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
