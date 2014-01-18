package com.kepp.android.test.helloworld;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import com.kepp.android.test.helloworld.util.SystemUiHider;

/**
 * An example full-screen activity that shows and hides the system UI (i.e. status bar and navigation/system bar) with user interaction.
 * 
 * @see SystemUiHider
 */
public class FullscreenActivity extends Activity {
	private static final String screen_name = "Spotify";

	/**
	 * Whether or not the system UI should be auto-hidden after {@link #AUTO_HIDE_DELAY_MILLIS} milliseconds.
	 */
	private static final boolean AUTO_HIDE = false;

	/**
	 * If {@link #AUTO_HIDE} is set, the number of milliseconds to wait after user interaction before hiding the system UI.
	 */
	private static final int AUTO_HIDE_DELAY_MILLIS = 3000;

	/**
	 * If set, will toggle the system UI visibility upon interaction. Otherwise, will show the system UI visibility upon interaction.
	 */
	private static final boolean TOGGLE_ON_CLICK = true;

	/**
	 * The flags to pass to {@link SystemUiHider#getInstance}.
	 */
	private static final int HIDER_FLAGS = SystemUiHider.FLAG_HIDE_NAVIGATION;

	/**
	 * The instance of the {@link SystemUiHider} for this activity.
	 */
	private SystemUiHider mSystemUiHider;

	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		this.setContentView(R.layout.activity_fullscreen);

		final View controlsView = this.findViewById(R.id.fullscreen_content_controls);
		final View contentView = this.findViewById(R.id.listview);
		final EditText name = (EditText) this.findViewById(R.id.name);
		name.setText(screen_name);

		// Set up an instance of SystemUiHider to control the system UI for
		// this activity.
		this.mSystemUiHider = SystemUiHider.getInstance(this, contentView, HIDER_FLAGS);
		this.mSystemUiHider.setup();
		this.mSystemUiHider.setOnVisibilityChangeListener(new SystemUiHider.OnVisibilityChangeListener() {
			// Cached values.
			int mControlsHeight;
			int mShortAnimTime;

			@Override
			@TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
			public void onVisibilityChange(final boolean visible) {
				if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
					// If the ViewPropertyAnimator API is available
					// (Honeycomb MR2 and later), use it to animate the
					// in-layout UI controls at the bottom of the
					// screen.
					if (this.mControlsHeight == 0) {
						this.mControlsHeight = controlsView.getHeight();
					}
					if (this.mShortAnimTime == 0) {
						this.mShortAnimTime = FullscreenActivity.this.getResources().getInteger(android.R.integer.config_shortAnimTime);
					}
					controlsView.animate().translationY(visible ? 0 : this.mControlsHeight).setDuration(this.mShortAnimTime);
				} else {
					// If the ViewPropertyAnimator APIs aren't
					// available, simply show or hide the in-layout UI
					// controls.
					controlsView.setVisibility(visible ? View.VISIBLE : View.GONE);
				}

				if (visible && AUTO_HIDE) {
					// Schedule a hide().
					FullscreenActivity.this.delayedHide(AUTO_HIDE_DELAY_MILLIS);
				}
			}
		});

		// // Set up the user interaction to manually show or hide the system UI.
		// contentView.setOnClickListener(new View.OnClickListener() {
		// @Override
		// public void onClick(final View view) {
		// if (TOGGLE_ON_CLICK) {
		// FullscreenActivity.this.mSystemUiHider.toggle();
		// } else {
		// FullscreenActivity.this.mSystemUiHider.show();
		// }
		// }
		// });

		// Upon interacting with UI controls, delay any scheduled hide()
		// operations to prevent the jarring behavior of controls going away
		// while interacting with the UI.
		this.findViewById(R.id.dummy_button).setOnTouchListener(this.mDelayHideTouchListener);
	}

	@Override
	protected void onPostCreate(final Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);

		// Trigger the initial hide() shortly after the activity has been
		// created, to briefly hint to the user that UI controls
		// are available.
		this.delayedHide(100);
	}

	/**
	 * Touch listener to use for in-layout UI controls to delay hiding the system UI. This is to prevent the jarring behavior of controls
	 * going away while interacting with activity UI.
	 */
	View.OnTouchListener mDelayHideTouchListener = new View.OnTouchListener() {
		@Override
		public boolean onTouch(final View view, final MotionEvent motionEvent) {
			if (AUTO_HIDE) {
				FullscreenActivity.this.delayedHide(AUTO_HIDE_DELAY_MILLIS);
			}
			return false;
		}
	};

	Handler mHideHandler = new Handler();
	Runnable mHideRunnable = new Runnable() {
		@Override
		public void run() {
			FullscreenActivity.this.mSystemUiHider.hide();
		}
	};

	/**
	 * Schedules a call to hide() in [delay] milliseconds, canceling any previously scheduled calls.
	 */
	private void delayedHide(final int delayMillis) {
		this.mHideHandler.removeCallbacks(this.mHideRunnable);
		this.mHideHandler.postDelayed(this.mHideRunnable, delayMillis);
	}

	/**
	 * Called when the user clicks the Send button
	 */
	public void getTweets(final View view) {
		final EditText name = (EditText) this.findViewById(R.id.name);
		new DownloadTwitterTask(this).execute(name.getText().toString());
	}

}
