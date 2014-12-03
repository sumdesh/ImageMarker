package com.example.imagemarker;


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

public class MainActivity extends Activity implements OnTouchListener {

	private Paint paint;
	private Bitmap bmp;
	private Canvas canvas;
	private ImageView imageView;
	private FrameLayout frame;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		frame = (FrameLayout) findViewById(R.id.myFrame);
		imageView = (ImageView) findViewById(R.id.myImageView);
		// imageView.setBackgroundResource(R.drawable.ic_launcherweb);

		DrawingView drawingView = new DrawingView(this);
		frame.addView(drawingView);

		// Display currentDisplay = getWindowManager().getDefaultDisplay();
		// float dw = currentDisplay.getWidth();
		// float dh = currentDisplay.getHeight();
		//
		// paint = new Paint();
		// paint.setColor(Color.RED);
		// // paint.setAntiAlias(true);
		// // paint.setDither(true);
		// // paint.setStyle(Paint.Style.STROKE);
		// // paint.setStrokeJoin(Paint.Join.ROUND);
		// // paint.setStrokeCap(Paint.Cap.ROUND);
		// // paint.setStrokeWidth(20);
		//
		// bmp = Bitmap.createBitmap((int) dw, (int) dh, Bitmap.Config.ARGB_8888);
		// canvas = new Canvas(bmp);
		// imageView.setImageBitmap(bmp);
		// imageView.setOnTouchListener(this);
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

	@Override
	public boolean onTouch(View v, MotionEvent event) {

		float startX = event.getX();
		float startY = event.getY();
		Log.i("MainActivity", "X:" + startX + " Y: " + startY);

		float endX = 0;
		float endY = 0;
		int action = event.getAction();

		switch (action) {
		case MotionEvent.ACTION_DOWN:
			startX = event.getX();
			startY = event.getY();
			break;

		case MotionEvent.ACTION_UP:
			endX = event.getX();
			endY = event.getY();
			Log.i("MainActivity", startX + " " + startY + " " + endX + " " + endY);
			canvas.drawLine(startX, startY, endX, endY, paint);
			imageView.invalidate();
			break;

		case MotionEvent.ACTION_MOVE:
			break;
		}
		return true;
	}
}
