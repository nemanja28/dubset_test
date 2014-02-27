package com.example.drawtest;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class MainActivity extends Activity {

	SeekBar mSeekBarProgress;
	CustomWaveformView mSampleView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mSampleView = (CustomWaveformView) findViewById(R.id.sampleView1);

		mSeekBarProgress = (SeekBar) findViewById(R.id.seekBar_Progress);
		mSeekBarProgress
				.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

					@Override
					public void onStopTrackingTouch(SeekBar seekBar) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onStartTrackingTouch(SeekBar seekBar) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onProgressChanged(SeekBar seekBar,
							int progress, boolean fromUser) {
						Log.d("PROGRES", String.valueOf(progress));
						
						updateWaveForm((double)progress/100);

					}

					
				});

	}
	
	private void updateWaveForm(double progress) {
		mSampleView.setProgress(progress);
		mSampleView.invalidate();
	}

}
