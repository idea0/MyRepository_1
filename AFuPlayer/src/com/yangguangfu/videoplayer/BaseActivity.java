package com.yangguangfu.videoplayer;

import com.yangguangfu.videoplayer.util.ActivityHolder;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

/**
 * 
 * @author ����
 * @version BaseActivity��˵��:Acitivty������ ����������������ʵ��onCreate ����
 *          �ڸ�����ע����һ��BroadcastReceiver ���ڽ����˳���Ϣ �ڽ��յ���Ϣ֮���������
 * 
 *          -------------------------------- ʹ�� ���Լ����е�activity�м̳и���
 *          ����Ҫ�˳������ʱ���͹㲥 Intent intent = new
 *          Intent(context.getPackageName()+".ExitListenerReceiver");
 * 
 *          context.sendBroadcast(intent); ���ɡ�
 * 
 *          �˳��������� ���ô���
 */
public abstract class BaseActivity extends Activity {

	/**
	 * �˳��¼�����
	 * 
	 */
	public ExitListenerReceiver exitReceiver = null;

	@Override
	public void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ActivityHolder.getInstance().addActivity(this);
		regListener();
		// ��ɱ
//		android.os.Process.killProcess(android.os.Process.myPid());
	}

	/**
	 * ע���˳��¼�����
	 * 
	 */
	private void regListener() {
		exitReceiver = new ExitListenerReceiver();
		IntentFilter intentfilter = new IntentFilter();
		intentfilter.addAction(this.getPackageName() + "."
				+ "ExitListenerReceiver");
		// com.yangguangfu.videoplayer.ExitListenerReceiver
		registerReceiver(exitReceiver, intentfilter);
	}

	/**
	 * ע��ȡ���˳��¼�����
	 * 
	 */
	private void unregisterListener() {
		if (exitReceiver != null) {
			unregisterReceiver(exitReceiver);
			exitReceiver = null;
		}

	}

	/**
	 * �㲥�Ž����ߣ����ڽ��չر�Activity��Ϣ�� ���ر�û�йص�Activity
	 * 
	 * @author ����
	 * 
	 */
	class ExitListenerReceiver extends BroadcastReceiver {

		@Override
		public void onReceive(Context arg0, Intent arg1) {
			BaseActivity.this.finish();
		}

	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		// ��һ��activity��ʵ���Ӽ������Ƴ�
		ActivityHolder.getInstance().removeActivity(this);
		// ȡ��ע�����
		unregisterListener();

	}

}