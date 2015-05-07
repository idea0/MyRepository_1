package com.yangguangfu.videoplayer.util;

import java.util.Locale;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Configuration;

/**
 * 
 * @author 阿福 
 * 1.负责保存设置的语言
 * 2.是否有点击音效
 * 3.是否默认播放作者家乡音乐 
 * 4.其实它是一个工具类
 */
public class SharedPreferencesUtils {

	/**
	 * 用来保存软件参数
	 */
	private SharedPreferences sPref;

	public SharedPreferencesUtils(Context context) {
		sPref = context.getSharedPreferences(Constants.SOFTWARE_NAME,
				Context.MODE_PRIVATE);
	}

	/**
	 * 是否开启按钮音效
	 * 
	 * @return
	 */
	public boolean isButtonMusic() {
		return sPref.getBoolean(Constants.PRE_BUTTON_MUSIC, true);
	}

	/**
	 * 播放家乡音乐是否生效
	 * @return
	 */
	public boolean isHometownMusic() {
		return sPref.getBoolean(Constants.PRE_AUTHOR_HOMETOWN_MUSIC, true);
	}

	/**
	 * 设置语言的方法
	 * 
	 * @param language
	 */
	public void setLanguage(int language) {
		Editor ed = sPref.edit();
		ed.putInt(Constants.PREF_LANGUAGE, language);
		ed.commit();
	}

	/**
	 * 得到上次保存的语言
	 * 
	 * @return
	 */
	public int getLanguage() {
		return sPref.getInt(Constants.PREF_LANGUAGE, Constants.LANGUAGE_CHINESE);
	}

	/**
	 * 设置是否播放按钮声音
	 * 
	 * @param b
	 */
	public void setButtonMusic(boolean b) {
		Editor ed = sPref.edit();
		ed.putBoolean(Constants.PRE_BUTTON_MUSIC, b);
		ed.commit();
	}

	/**
	 * 设置是否播放作者家乡音乐
	 * 
	 * @param b
	 */
	public void setHometownMusic(boolean b) {
		Editor ed = sPref.edit();
		ed.putBoolean(Constants.PRE_AUTHOR_HOMETOWN_MUSIC, b);
		ed.commit();
	}

	/**
	 * 人为改动语言方法
	 * 
	 * @param context
	 * @param prefService
	 */
	public static void changleLocale(Context context, SharedPreferencesUtils prefService) {
		// 获取上次保存的语言
		int languageOption = prefService.getLanguage();
		/*
		 * java.util.Locale主要在软件的本地化时使用。它本身没有什么功能， 更多的是作为一个参数辅助其他方法完成输出的本地化。
		 */
		Locale locale = Locale.CHINESE;//中文你选择的语言
		switch (languageOption) {
		case Constants.LANGUAGE_CHINESE:
			locale = Locale.CHINESE;
			break;
		case Constants.LANGUAGE_ENGLISH:
			locale = Locale.ENGLISH;
			break;
		case Constants.LANGUAGE_TRAIDTION_CHINESE:
			locale = Locale.TRADITIONAL_CHINESE;
			break;
		default:
			break;
		}

		//人为设置加载不同语言的资源文件
		Configuration config = context.getResources().getConfiguration();
		config.locale = locale;
		context.getResources().updateConfiguration(config,
				context.getResources().getDisplayMetrics());

	}

}
