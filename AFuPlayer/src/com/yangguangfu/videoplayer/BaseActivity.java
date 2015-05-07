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
 * @author 阿福
 * @version BaseActivity类说明:Acitivty的子类 基础该类的子类必须实现onCreate 方法
 *          在该类中注册了一个BroadcastReceiver 用于接收退出消息 在接收到消息之后结束自身
 * 
 *          -------------------------------- 使用 在自己所有的activity中继承该类
 *          到需要退出程序的时候发送广播 Intent intent = new
 *          Intent(context.getPackageName()+".ExitListenerReceiver");
 * 
 *          context.sendBroadcast(intent); 即可。
 * 
 *          退出整个程序 复用代码
 */
public abstract class BaseActivity extends Activity {

	/**
	 * 退出事件监听
	 * 
	 */
	public ExitListenerReceiver exitReceiver = null;

	@Override
	public void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ActivityHolder.getInstance().addActivity(this);
		regListener();
		// 自杀
//		android.os.Process.killProcess(android.os.Process.myPid());
	}

	/**
	 * 注册退出事件监听
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
	 * 注册取消退出事件监听
	 * 
	 */
	private void unregisterListener() {
		if (exitReceiver != null) {
			unregisterReceiver(exitReceiver);
			exitReceiver = null;
		}

	}

	/**
	 * 广播放接收者，用于接收关闭Activity信息， 并关闭没有关的Activity
	 * 
	 * @author 阿福
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
		// 把一个activity的实例从集合里移除
		ActivityHolder.getInstance().removeActivity(this);
		// 取消注册监听
		unregisterListener();

	}

}