package com.marakana.android.camera;

import java.io.IOException;

import android.content.Context;
import android.hardware.Camera;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

class Preview extends SurfaceView implements SurfaceHolder.Callback { // <1>
	private static final String TAG = "Preview";

	SurfaceHolder mHolder; // <2>
	public Camera camera; // <3>

	Preview(Context context) {
		super(context);

		// Install a SurfaceHolder.Callback so we get notified when the
		// underlying surface is created and destroyed.
		mHolder = getHolder(); // <4>
		mHolder.addCallback(this); // <5>
		mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS); // <6>
	}

	// Called once the holder is ready
	public void surfaceCreated(SurfaceHolder holder) { // <7>
		// The Surface has been created, acquire the camera and tell it where
		// to draw.
		camera = Camera.open(); // <8>

		try {
			camera.setPreviewDisplay(holder);
		} catch (IOException e) {
			Log.e(TAG, "Error getting camera preview", e);
		} 

	}

	// Called when the holder is destroyed
	public void surfaceDestroyed(SurfaceHolder holder) { // <14>
		camera.stopPreview();
		camera = null;
	}

	// Called when holder has changed
	public void surfaceChanged(SurfaceHolder holder, int format, int w, int h) { // <15>
		camera.startPreview();
	}

}