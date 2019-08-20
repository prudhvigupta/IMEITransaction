package app.com.imeitransaction;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainOption extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_option);
    }

    public void Login(View v)
    {
        Intent nextScreen = new Intent(MainOption.this, LoginMenu.class);
        startActivity(nextScreen);

    }

    public void Register(View v)
    {
        Intent i = new Intent(MainOption.this, RegisterMenu.class);
        startActivity(i);
        finish();

    }
}


