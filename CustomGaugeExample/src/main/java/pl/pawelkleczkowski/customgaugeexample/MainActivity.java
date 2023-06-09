package pl.pawelkleczkowski.customgaugeexample;

import java.util.Locale;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import pl.pawelkleczkowski.customgauge.CustomGauge;


public class MainActivity extends AppCompatActivity {

	private CustomGauge gauge3;

	int i;
	private TextView text2;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Toolbar toolbar = findViewById(R.id.toolbar);
		toolbar.setTitle(getString(R.string.app_name));
		setSupportActionBar(toolbar);

		Button button = findViewById(R.id.button);
		gauge3 = findViewById(R.id.gauge3);
		text2 = findViewById(R.id.textView2);

		text2.setText(String.format(Locale.getDefault(), "%1d/%2d", gauge3.getValue(), gauge3.getEndValue()));
    	
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {

				text2.setText(String.format(Locale.getDefault(), "%1d/%2d", gauge3.getValue(), gauge3.getEndValue()));
				new Thread() {
			        public void run() {
			        	for (i=0;i<=100;i++) {
			                try {
			                    runOnUiThread(new Runnable() {
			                        @Override
			                        public void run() {
			                        	gauge3.setValue(i);
			                        	text2.setText(String.format(Locale.getDefault(), "%1d/%2d", gauge3.getValue(), gauge3.getEndValue()));
			                        }
			                    });
			                    Thread.sleep(50);
			                } catch (InterruptedException e) {
			                    e.printStackTrace();
			                }
			            }
			        }
			    }.start();
			}
		});
	}
	
}
