package com.example.drawtest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class SampleView extends View {
	Paint mPaintLine;
	Paint mPaintFill;
	Paint mPaintWaveform;
	Paint mPaintProgress;
	Context mContext;
	
	double progress = 0.0; // 0.0 to 1.0
	
//	Paint mPaintOval;
//	RectF mRectFOval;
//
//	private float mPosX;
//	private float mPosY;
//
//	private float mLastTouchX;
//	private float mLastTouchY;
//
//	private ScaleGestureDetector mScaleDetector;
//	private float mScaleFactor = 1.f;
//
//	private static final int INVALID_POINTER_ID = -1;
//
//	private int mActivePointerId = INVALID_POINTER_ID;


	// CONSTRUCTOR
	public SampleView(Context context) {
		super(context);

	}

	public SampleView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
	}

	public SampleView(Context context, AttributeSet attrs) {
		super(context, attrs);
//		mScaleDetector = new ScaleGestureDetector(context, new ScaleListener());
		mContext = context;
		vals1 = ImageReader.readBitmap(context);
		init();
		setFocusable(true);
	}

	@Override
	protected int getSuggestedMinimumHeight() {
		// TODO Auto-generated method stub
		return (int)(2 * (r0 + lineWidth + barHeight));
	}

	@Override
	protected int getSuggestedMinimumWidth() {
		// TODO Auto-generated method stub
		return (int)(2 * (r0 + lineWidth + barHeight));
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		Log.d("ONMEASUER", "onMeasure");

		int minw = getPaddingLeft() + getPaddingRight()
				+ getSuggestedMinimumWidth();
		int w = Math.max(minw, MeasureSpec.getSize(widthMeasureSpec));
		Log.d("WIDTH", String.valueOf(w));
		int minh = getPaddingBottom() + getPaddingTop()
				+ getSuggestedMinimumHeight();

		int h = Math.max(MeasureSpec.getSize(heightMeasureSpec), minh);
		Log.d("HEIGHT", String.valueOf(h));

		setMeasuredDimension(getSuggestedMinimumWidth(),
				getSuggestedMinimumHeight());

	}

	private void init() {
		Log.d("INIT", "INIT");
		ImageReader.readBitmap(mContext);

		mPaintLine = new Paint();
		mPaintLine.setAntiAlias(true);
		mPaintLine.setColor(Color.WHITE);
		mPaintLine.setStrokeWidth(lineWidth);
		mPaintLine.setStyle(Style.STROKE);
		
		mPaintFill = new Paint();
		mPaintFill.setAntiAlias(true);
		mPaintFill.setColor(Color.BLACK);
		mPaintFill.setAlpha(107);
		
		mPaintWaveform = new Paint();
		mPaintWaveform.setAntiAlias(true);
		mPaintWaveform.setAntiAlias(true);
		mPaintWaveform.setColor(Color.WHITE);
		mPaintWaveform.setStrokeWidth(barWidth);
		mPaintWaveform.setStyle(Style.STROKE);

		mPaintProgress = new Paint();
		mPaintProgress.setAntiAlias(true);
		mPaintProgress.setAntiAlias(true);
		mPaintProgress.setColor(Color.RED);
		mPaintProgress.setStrokeWidth(barWidth);
		mPaintProgress.setStyle(Style.STROKE);

	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		Log.d("onSizeChanged", "onSizeChanged");
		super.onSizeChanged(w, h, oldw, oldh);
	}

	double r0 = 320.0;
//	double r = 200.0;
//	double density = 1000;
	float lineWidth = 24.f;
	float barWidth = 4.f;
	double barHeight = 120.0;
	int barCount = 360;
	int[] vals1;

	@Override
	protected void onDraw(Canvas canvas) {
		Log.d("DRAW", "DRAW");

		canvas.drawCircle(canvas.getWidth() / 2, canvas.getHeight() / 2,
				(float) r0, mPaintFill);
		canvas.drawCircle(canvas.getWidth() / 2, canvas.getHeight() / 2,
				(float) r0, mPaintLine);

		int valMax = 50;
		int valCount = vals1.length;
		
		long sum = 0;
		int count = 0;
		int barCurrent = 0;
		double R0 = r0 + lineWidth;
		for (int i = 1; i < valCount; i++) {
			// TODO: remove later when real val[] is received
			//vals[i] = (int)(valMax * Math.random());
			
			sum += vals1[i];
			count += 1;
			
			if (count >= valCount / barCount && barCurrent < barCount) {
				double valAverage = sum/count;

				double angle = Math.PI * 2 * barCurrent / barCount;
				// double radians = Math.toRadians(angle);
				double valHeight = barHeight * valAverage / valMax;
				int XR0 = (int) (R0 * Math.cos(angle));
				int YR0 = (int) (R0 * Math.sin(angle));
				int XR = (int) ((R0 + valHeight) * Math.cos(angle));
				int YR = (int) ((R0 + valHeight) * Math.sin(angle));
				canvas.drawLine(canvas.getWidth() / 2 + XR0, canvas.getHeight() / 2
						+ YR0, canvas.getWidth() / 2 + XR, canvas.getHeight() / 2
						+ YR, barCurrent / barCount >= progress ? mPaintWaveform : mPaintProgress);

				sum = 0;
				count = 0;
				barCurrent += 1;
			}
			
			
			
		}

		//
		// canvas.save();
		// canvas.translate(mPosX, mPosY);
		// canvas.scale(mScaleFactor, mScaleFactor);
		// canvas.drawColor(Color.YELLOW);
		// mRectFOval.left = canvas.getWidth() / 3;
		// mRectFOval.top = 6 * canvas.getHeight() / 8;
		// mRectFOval.right = 2 * canvas.getWidth() / 3;
		// mRectFOval.bottom = 7 * canvas.getHeight() / 8;
		//
		// canvas.drawCircle(canvas.getWidth() / 2, canvas.getHeight() / 2, 50,
		// mPaintLine);
		// canvas.drawLine(canvas.getWidth() / 3, canvas.getHeight() / 3,
		// canvas.getWidth() / 4, canvas.getHeight() / 4, mPaintLine);
		// canvas.drawLine(2 * canvas.getWidth() / 3, canvas.getHeight() / 3,
		// 3 * canvas.getWidth() / 4, canvas.getHeight() / 4, mPaintLine);
		// canvas.drawOval(mRectFOval, mPaintOval);
		// canvas.restore();

	}

//	@Override
//	public boolean onTouchEvent(MotionEvent ev) {
//		// Let the ScaleGestureDetector inspect all events.
//		mScaleDetector.onTouchEvent(ev);
//
//		final int action = ev.getAction();
//		switch (action & MotionEvent.ACTION_MASK) {
//		case MotionEvent.ACTION_DOWN: {
//			Log.d("onTouchEvent", "ACTION_DOWN");
//			final float x = ev.getX();
//			final float y = ev.getY();
//
//			mLastTouchX = x;
//			mLastTouchY = y;
//			mActivePointerId = ev.getPointerId(0);
//			break;
//		}
//
//		case MotionEvent.ACTION_MOVE: {
//			Log.d("onTouchEvent", "ACTION_MOVE");
//
//			final int pointerIndex = ev.findPointerIndex(mActivePointerId);
//			final float x = ev.getX(pointerIndex);
//			final float y = ev.getY(pointerIndex);
//
//			// Only move if the ScaleGestureDetector isn't processing a
//			// gesture.
//			if (!mScaleDetector.isInProgress()) {
//				final float dx = x - mLastTouchX;
//				final float dy = y - mLastTouchY;
//
//				mPosX += dx;
//				mPosY += dy;
//
//				invalidate();
//			}
//
//			mLastTouchX = x;
//			mLastTouchY = y;
//
//			break;
//		}
//
//		case MotionEvent.ACTION_UP: {
//			Log.d("onTouchEvent", "ACTION_UP");
//
//			mActivePointerId = INVALID_POINTER_ID;
//			break;
//		}
//
//		case MotionEvent.ACTION_CANCEL: {
//			Log.d("onTouchEvent", "ACTION_CANCEL");
//
//			mActivePointerId = INVALID_POINTER_ID;
//			break;
//		}
//
//		case MotionEvent.ACTION_POINTER_UP: {
//			Log.d("onTouchEvent", "ACTION_POINTER_UP");
//
//			final int pointerIndex = (ev.getAction() & MotionEvent.ACTION_POINTER_INDEX_MASK) >> MotionEvent.ACTION_POINTER_INDEX_SHIFT;
//			final int pointerId = ev.getPointerId(pointerIndex);
//			if (pointerId == mActivePointerId) {
//				// This was our active pointer going up. Choose a new
//				// active pointer and adjust accordingly.
//				final int newPointerIndex = pointerIndex == 0 ? 1 : 0;
//				mLastTouchX = ev.getX(newPointerIndex);
//				mLastTouchY = ev.getY(newPointerIndex);
//				mActivePointerId = ev.getPointerId(newPointerIndex);
//			}
//			break;
//		}
//		}
//
//		return true;
//	}
//
//	private class ScaleListener extends
//			ScaleGestureDetector.SimpleOnScaleGestureListener {
//		@Override
//		public boolean onScale(ScaleGestureDetector detector) {
//			Log.d("ONSCALE", "ONSCALE");
//
//			mScaleFactor *= detector.getScaleFactor();
//
//			// Don't let the object get too small or too large.
//			mScaleFactor = Math.max(0.1f, Math.min(mScaleFactor, 5.0f));
//
//			invalidate();
//			return true;
//		}
//	}

}
