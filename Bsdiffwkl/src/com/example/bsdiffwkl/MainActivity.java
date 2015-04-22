package com.example.bsdiffwkl;

import java.io.File;
import java.io.IOException;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.example.bsdiffwkl.util.MD5Util;
import com.wkl.utils.Bsdiff;

public class MainActivity extends Activity {

	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			if (msg.what == 1) {
				Toast.makeText(getApplicationContext(), "diff complet", 0)
						.show();
			}
			if (msg.what == 2) {
				Toast.makeText(getApplicationContext(), "patch complet", 0)
						.show();
			}
			if (msg.what == 3) {
				Toast.makeText(getApplicationContext(), "jiaoyaning...", 0)
						.show();
			}
			if (msg.what == 4) {
				Toast.makeText(getApplicationContext(), "suc", 0).show();
			}
			if (msg.what == 5) {
				Toast.makeText(getApplicationContext(), "fail", 0).show();
			}
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		findViewById(R.id.diff).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Toast.makeText(getApplicationContext(), "start diff", 0).show();
				new Thread(new Runnable() {

					@Override
					public void run() {
						String dir = Environment.getExternalStorageDirectory()
								+ File.separator;
						Bsdiff.bsdiff(dir + "old.apk", dir + "new.apk", dir
								+ "piis.patch");
						handler.sendEmptyMessage(1);
					}
				}).start();
			}
		});
		findViewById(R.id.patch).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Toast.makeText(getApplicationContext(), "start patch", 0).show();
				new Thread(new Runnable() {

					@Override
					public void run() {
						try {
							Thread.sleep(2000);
							String dir = Environment
									.getExternalStorageDirectory()
									+ File.separator;

							Bsdiff.bspatch(dir + "old.apk", dir + "piis.apk",
									dir + "piis.patch");
							handler.sendEmptyMessage(2);
							Thread.sleep(2000);
							handler.sendEmptyMessage(3);
							String oldMd5 = null;
							String newMd5 = null;
							try {
								oldMd5 = MD5Util.getFileMD5String(new File(dir
										+ "new.apk"));
								newMd5 = MD5Util.getFileMD5String(new File(dir
										+ "piis.apk"));
							} catch (IOException e) {
								e.printStackTrace();
							}
							Thread.sleep(2000);
							if (oldMd5.equals(newMd5)) {
								handler.sendEmptyMessage(4);
							} else {
								handler.sendEmptyMessage(5);
							}
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}).start();
			}
		});
	}

}
