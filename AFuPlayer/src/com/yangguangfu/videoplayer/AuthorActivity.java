package com.yangguangfu.videoplayer;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.yangguangfu.videoplayer.util.SoundService;

/**
 * 
 * @author ���� �������߽���
 */
public class AuthorActivity extends BaseActivity implements OnClickListener {
	/**
	 * ������ҳ��ť
	 */
	private Button gomainmenu;
	/**
	 * �ҵ���Ʒ��ť
	 */
	private Button author_give_autor;
	/**
	 * ��Ʒͼ�갴ť
	 */
	private ImageButton author_goversion;
	/**
	 * ����������ͣ����ʼ��ť
	 */
	private Button author_music;
	/**
	 * ����������
	 */
	private SoundService soundService;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// ����Ϊȫ��
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON,
				WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

		// ���ز����ļ�
		setContentView(R.layout.author_activity);
		Log.v("ThAuthor", "onCreate()");

		// ��ʼ����ť
		gomainmenu = (Button) this.findViewById(R.id.author_gomainmenu);
		author_give_autor = (Button) this.findViewById(R.id.author_give_autor);
		author_goversion = (ImageButton) this
				.findViewById(R.id.author_goversion);
		author_music = (Button) this.findViewById(R.id.author_music);

		// ���ð�ť����¼�
		gomainmenu.setOnClickListener(this);
		author_give_autor.setOnClickListener(this);
		author_goversion.setOnClickListener(this);
		author_music.setOnClickListener(this);
		// ��ʼ�����������ࣺ���Ű�ť��Ч���������߼�������
		soundService = new SoundService(AuthorActivity.this);
	}

	@Override
	public void onClick(View v) {

		// ÿ���һ�ΰ�ť������һ�ε����Ч
		if (soundService != null) {
			soundService.playButtonMusic(R.raw.button);
		}

		switch (v.getId()) {
		case R.id.author_gomainmenu:
			finish();
			break;
		case R.id.author_give_autor:
			// AppConnect.getInstance(this).showMore(this);
			// Intent j = new Intent(this, SendEmailAuthor.class);
			// this.startActivityForResult(j, 814);

			break;

		case R.id.author_goversion:
			Intent i = new Intent(this, VersionInfoActivity.class);
			startActivity(i);
			break;

		case R.id.author_music:
			// �������߼������֣�������ŵ�ʱ�򣬵������ͣ��������Ȼ��
			if (soundService.isHometownPlaying(R.raw.myhome)) {
				soundService.pauseHometownMusic(R.raw.myhome);

				author_music.setText(R.string.author_music_play);
			} else {
				soundService.hometownMusicPlay(R.raw.myhome);
				author_music.setText(R.string.author_music_pause);
			}

			break;
		default:
			break;
		}

	}


	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {

		if (soundService != null) {
			soundService.playButtonMusic(R.raw.button);
		}
		if (keyCode == KeyEvent.KEYCODE_MENU) {
			Toast.makeText(AuthorActivity.this, R.string.app_hint_msg, 0)
					.show();
			return true;
		}
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			finish();
			return true;
		}

		return super.onKeyDown(keyCode, event);
	}

	@Override
	protected void onDestroy() {
		// ����������Ӱ��ֹͣ�����ͷ���Դ
		soundService.stopHometownMusic(R.raw.myhome);
		super.onDestroy();
	}

	@Override
	protected void onResume() {
		Log.v("ThAuthor", "onResume()");
		// �����ý����ʱ�򣬲������߼�������
		soundService.hometownMusicPlay(R.raw.myhome);
		super.onResume();
	}

	@Override
	protected void onStop() {
		super.onStop();
		// ����������Ӱ��ֹͣ�����ͷ���Դ
		soundService.stopHometownMusic(R.raw.myhome);
	}

}