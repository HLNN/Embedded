package com.topeet.ledtest;



import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.topeet.ledtesta.R;
//import android.widget.EditText;

public class MainActivity extends Activity {

	/* add by cym 20140617 */
	led led = new led();
	
	private Button led1_on;
	private Button led1_off;
	private Button led2_on;
	private Button led2_off;
	private Button led_all_on;
	private Button led_all_off;
	private Button led_on;
	private Button led_off;
	/* end add */


	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		/* add by cym 20140617 */
		led1_on = (Button)findViewById(R.id.button1);
		led1_off = (Button)findViewById(R.id.button2);
		led2_on = (Button)findViewById(R.id.button3);
		led2_off = (Button)findViewById(R.id.button4);

		led_all_on = (Button)findViewById(R.id.button);
		led_all_off = (Button)findViewById(R.id.button5);
		led_on = (Button)findViewById(R.id.button6);
		led_off = (Button)findViewById(R.id.button7);

		led.Open();

		manager m = new manager();
		led1_on.setOnClickListener(m);
		led1_off.setOnClickListener(m);
		led2_on.setOnClickListener(m);
		led2_off.setOnClickListener(m);

		led_all_on.setOnClickListener(m);
		led_all_off.setOnClickListener(m);

		led_on.setOnClickListener(m);
		led_off.setOnClickListener(m);
		/* end add */
	}


	class manager implements OnClickListener{
		private final Handler handler = new Handler();
		private boolean bling = false;
		private int onOff = 1;

		private final Runnable runnable = new Runnable() {
			@Override
			public void run() {
				led.Ioctl(0, onOff);
				led.Ioctl(1, onOff);
				onOff = (onOff + 1) % 2;

				handler.postDelayed(this, 1000);
			}
		};

		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.button1:
				if (bling) {break;}
				led.Ioctl(0, 1);
				break;
			case R.id.button2:
				if (bling) {break;}
				led.Ioctl(0, 0);
				break;
			case R.id.button3:
				if (bling) {break;}
				led.Ioctl(1, 1);
				break;
			case R.id.button4:
				if (bling) {break;}
				led.Ioctl(1, 0);
				break;
			case R.id.button:
				if (bling) {break;}
				led.Ioctl(0, 1);
				led.Ioctl(1, 1);
				break;
			case R.id.button5:
				if (bling) {break;}
				led.Ioctl(0, 0);
				led.Ioctl(1, 0);
				break;
			case R.id.button6:
				if (!bling) {
					led.Ioctl(0, 0);
					led.Ioctl(1, 0);
					bling = true;
					handler.postDelayed(runnable, 1000);
				}
				break;
			case R.id.button7:
				bling = false;
				handler.removeCallbacks(runnable);
				break;
			}
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	/* add by cym 20140617 */
	static {
        System.loadLibrary("led");
	}
	/* end add */
}
