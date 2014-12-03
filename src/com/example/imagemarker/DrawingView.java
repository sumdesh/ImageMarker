package com.example.imagemarker;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.MotionEvent;
import android.view.View;

public class DrawingView extends View {

	private Path mPath;
	private Paint mPaint;
	private Bitmap mBitmap;
	private Canvas mCanvas;
	private Paint mBitmapPaint;

	private float mX, mY;
	private static final float TOUCH_TOLERANCE = 4;

	private List<Path> moveList = null;
	private List<Path> undoList = null;
	private List<Path> currentMoveList = null;

	public DrawingView(Context context) {
		super(context);
		mPaint = new Paint();
		mPaint.setColor(Color.BLUE);
		mPaint.setAntiAlias(true);
		mPaint.setDither(true);
		mPaint.setStyle(Paint.Style.STROKE);
		mPaint.setStrokeJoin(Paint.Join.ROUND);
		mPaint.setStrokeCap(Paint.Cap.ROUND);
		mPaint.setStrokeWidth(5);

		mPath = new Path();
		mBitmapPaint = new Paint();
		mBitmapPaint.setColor(Color.GREEN);

		moveList = new ArrayList<Path>();
		undoList = new ArrayList<Path>();
		currentMoveList = new ArrayList<Path>();
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		mBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
		mCanvas = new Canvas(mBitmap);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		canvas.drawBitmap(mBitmap, 0, 0, mBitmapPaint);
		canvas.drawPath(mPath, mPaint);
	}

	private void touch_start(float x, float y) {
		// mPath.reset();
		mPath.moveTo(x, y);
		mX = x;
		mY = y;
	}

	private void touch_move(float x, float y) {
		float dx = Math.abs(x - mX);
		float dy = Math.abs(y - mY);
		if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE) {
			mPath.quadTo(mX, mY, (x + mX) / 2, (y + mY) / 2);
			mX = x;
			mY = y;
		}

		mPath.lineTo(mX, mY);
		currentMoveList.add(mPath);
	}

	private void touch_up() {
		mPath.lineTo(mX, mY);
		// commit the path to our offscreen
		mCanvas.drawPath(mPath, mPaint);
		moveList.add(mPath);
		currentMoveList.clear();
		// mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SCREEN));
		// kill this so we don't double draw
		mPath.reset();
		// mPath= new Path();
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {

		float x = event.getX();
		float y = event.getY();
		int action = event.getAction();

		switch (action) {
		case MotionEvent.ACTION_DOWN:
			touch_start(x, y);
			invalidate();
			break;

		case MotionEvent.ACTION_MOVE:
			touch_move(x, y);
			invalidate();
			break;

		case MotionEvent.ACTION_UP:
			touch_up();
			invalidate();
			break;
		}

		return true;
	}

	public void undo() {
		if (moveList.size() > 0) {
			undoList.add(moveList.remove(moveList.size() - 1));
			invalidate();
		}
	}
}
