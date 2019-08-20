package app.com.imeitransaction;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Payment extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
    }

    public void Back(View v)
    {
        Intent nextScreen = new Intent(Payment.this, HomeMenu.class);
        startActivity(nextScreen);

    }


}
