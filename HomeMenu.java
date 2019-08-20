package app.com.imeitransaction;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home_menu);

        Button send_view = (Button) findViewById(R.id.btn_send_view);

        Button payment_report = (Button) findViewById(R.id.btn_payment_report);

        Button balance = (Button) findViewById(R.id.btn_balance);

        Button transaction = (Button) findViewById(R.id.btn_transaction);

        send_view.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent i = new Intent(getApplicationContext(), SendTransaction.class);
                startActivity(i);
            }
        });

        payment_report.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), SendTransaction.class);
                startActivity(i);
            }
        });

        balance.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), CheckBalance.class);
               startActivity(i);
            }
        });

        transaction.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), TransactionList.class);
                startActivity(i);
            }
        });



    }
}

