package app.com.imeitransaction;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.splash);

        getSupportActionBar().hide();

        new Handler().postDelayed(new Runnable() {


            @Override
            public void run() {

                // This method will be executed once the timer is over
                Intent i = new Intent(Splash.this, MainOption.class);
                startActivity(i);

                //Intent i = new Intent(Splash.this, OtpMenu.class);
                //startActivity(i);

                finish();
            }
        }, 3000);
    }
}



