package app.com.imeitransaction;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainMenus extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
    }

    public void Login(View v)
    {
        Intent nextScreen = new Intent(MainMenus.this, LoginMenu.class);
        startActivity(nextScreen);

    }

    public void Register(View v)
    {
        Intent i = new Intent(MainMenus.this, RegisterMenu.class);
        startActivity(i);
        finish();

    }
}
