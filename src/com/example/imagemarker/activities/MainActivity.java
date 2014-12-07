package com.example.imagemarker.activities;


import com.example.imagemarker.R;
import com.example.imagemarker.R.id;
import com.example.imagemarker.R.layout;
import com.example.imagemarker.R.menu;
import com.example.imagemarker.core.DrawingView;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.FrameLayout;
import android.widget.ImageView;

public class MainActivity extends Activity {

	private ImageView imageView;
	private FrameLayout frame;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		frame = (FrameLayout) findViewById(R.id.myFrame);
		imageView = (ImageView) findViewById(R.id.myImageView);

		DrawingView drawingView = new DrawingView(this);
		frame.addView(drawingView);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
