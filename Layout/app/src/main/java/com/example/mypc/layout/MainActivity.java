package com.example.mypc.layout;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {

    private ImageView img1, img2, img3;
    private float scale1 = 67.0f, scale2 = 34.0f, scale3 = 1.0f;
    private int count = 0;

    private final Handler handler = new Handler();

    private final Runnable runnable = new Runnable() {
        @Override
        public void run() {
            // TODO Auto-generated method stub
            // 要做的事情
            scale1 += 1;
            scale2 += 1;
            scale3 += 1;

            if (scale1 >= 99.9f) {
                scale1 = scale2;
                scale2 = scale3;
                scale3 = 1.0f;
                count += 1;
                if (count % 3 == 0) {
                    img1.setBackgroundColor(Color.rgb(0, 0, 255));
                    img2.setBackgroundColor(Color.rgb(0, 255, 0));
                    img3.setBackgroundColor(Color.rgb(255, 0, 0));
                }
                else if (count % 3 == 1) {
                    img1.setBackgroundColor(Color.rgb(0, 255, 0));
                    img2.setBackgroundColor(Color.rgb(255, 0, 0));
                    img3.setBackgroundColor(Color.rgb(0, 0, 255));
                }
                else {
                    img1.setBackgroundColor(Color.rgb(255, 0, 0));
                    img2.setBackgroundColor(Color.rgb(0, 0, 255));
                    img3.setBackgroundColor(Color.rgb(0, 255, 0));
                }
            }

            img1.setScaleX(scale1 / 100.0f);
            img1.setScaleY(scale1 / 100.0f);
            img2.setScaleX(scale2 / 100.0f);
            img2.setScaleY(scale2 / 100.0f);
            img3.setScaleX(scale3 / 100.0f);
            img3.setScaleY(scale3 / 100.0f);

            handler.postDelayed(this, 20);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        img1 = (ImageView) findViewById(R.id.imageView);
        img2 = (ImageView) findViewById(R.id.imageView2);
        img3 = (ImageView) findViewById(R.id.imageView3);
        img1.setScaleX(scale1 / 100.0f);
        img1.setScaleY(scale1 / 100.0f);
        img2.setScaleX(scale2 / 100.0f);
        img2.setScaleY(scale2 / 100.0f);
        img3.setScaleX(scale3 / 100.0f);
        img3.setScaleY(scale3 / 100.0f);

        handler.postDelayed(runnable, 20);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
