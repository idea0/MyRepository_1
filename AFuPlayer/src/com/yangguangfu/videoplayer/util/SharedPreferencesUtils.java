package com.yangguangfu.videoplayer.util;

import java.util.Locale;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Configuration;

/**
 * 
 * @author ���� 
 * 1.���𱣴����õ�����
 * 2.�Ƿ��е����Ч
 * 3.�Ƿ�Ĭ�ϲ������߼������� 
 * 4.��ʵ����һ��������
 */
public class SharedPreferencesUtils {

	/**
	 * ���������������
	 */
	private SharedPreferences sPref;

	public SharedPreferencesUtils(Context context) {
		sPref = context.getSharedPreferences(Constants.SOFTWARE_NAME,
				Context.MODE_PRIVATE);
	}

	/**
	 * �Ƿ�����ť��Ч
	 * 
	 * @return
	 */
	public boolean isButtonMusic() {
		return sPref.getBoolean(Constants.PRE_BUTTON_MUSIC, true);
	}

	/**
	 * ���ż��������Ƿ���Ч
	 * @return
	 */
	public boolean isHometownMusic() {
		return sPref.getBoolean(Constants.PRE_AUTHOR_HOMETOWN_MUSIC, true);
	}

	/**
	 * �������Եķ���
	 * 
	 * @param language
	 */
	public void setLanguage(int language) {
		Editor ed = sPref.edit();
		ed.putInt(Constants.PREF_LANGUAGE, language);
		ed.commit();
	}

	/**
	 * �õ��ϴα��������
	 * 
	 * @return
	 */
	public int getLanguage() {
		return sPref.getInt(Constants.PREF_LANGUAGE, Constants.LANGUAGE_CHINESE);
	}

	/**
	 * �����Ƿ񲥷Ű�ť����
	 * 
	 * @param b
	 */
	public void setButtonMusic(boolean b) {
		Editor ed = sPref.edit();
		ed.putBoolean(Constants.PRE_BUTTON_MUSIC, b);
		ed.commit();
	}

	/**
	 * �����Ƿ񲥷����߼�������
	 * 
	 * @param b
	 */
	public void setHometownMusic(boolean b) {
		Editor ed = sPref.edit();
		ed.putBoolean(Constants.PRE_AUTHOR_HOMETOWN_MUSIC, b);
		ed.commit();
	}

	/**
	 * ��Ϊ�Ķ����Է���
	 * 
	 * @param context
	 * @param prefService
	 */
	public static void changleLocale(Context context, SharedPreferencesUtils prefService) {
		// ��ȡ�ϴα��������
		int languageOption = prefService.getLanguage();
		/*
		 * java.util.Locale��Ҫ������ı��ػ�ʱʹ�á�������û��ʲô���ܣ� ���������Ϊһ�������������������������ı��ػ���
		 */
		Locale locale = Locale.CHINESE;//������ѡ�������
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

		//��Ϊ���ü��ز�ͬ���Ե���Դ�ļ�
		Configuration config = context.getResources().getConfiguration();
		config.locale = locale;
		context.getResources().updateConfiguration(config,
				context.getResources().getDisplayMetrics());

	}

}
