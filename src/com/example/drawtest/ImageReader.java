package com.example.drawtest;

import java.io.IOException;
import java.io.InputStream;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.util.Log;

public class ImageReader {

	private static final int OPAQUE = 0;
	private static final int TRASPARENT = 255;


	public static int[] readBitmap(Context context) {
		Bitmap btm = getBitmapFromAsset(context, "bitmap.png");
		int imageWidth = btm.getWidth();
		int imageHeight = btm.getHeight();
		int[] vals1 = new int[imageWidth];
		int[] vals2 = new int[imageWidth];
		for (int i = 0; i < imageWidth; i++) {
			int val1 = 0;
			for (int j = 0; j < imageHeight / 2; j++) {
				int alpha = Color.alpha(btm.getPixel(i, j));
				if (alpha == TRASPARENT) {
					val1++;
				}
			}
			vals1[i] = val1;
			int val2 = 0;
			for (int k = imageHeight / 2; k < imageHeight; k++) {
				int alpha = Color.alpha(btm.getPixel(i, k));
				if (alpha == TRASPARENT) {
					val2++;
				}
			}
			vals2[i] = val2;

		}

		return vals1;
	}

	public static Bitmap getBitmapFromAsset(Context context, String strName) {
		AssetManager assetManager = context.getAssets();

		InputStream istr;
		Bitmap bitmap = null;
		try {
			istr = assetManager.open(strName);
			bitmap = BitmapFactory.decodeStream(istr);
		} catch (IOException e) {
			return null;
		}

		return bitmap;
	}
}
