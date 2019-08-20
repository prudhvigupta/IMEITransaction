package app.com.imeitransaction;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import app.com.imeitransaction.Define.Constant;
import app.com.imeitransaction.Http.HttpCommunication;

public class AmountTransaction extends AppCompatActivity implements View.OnClickListener {
    EditText etamount, etremark;
    Button btnSend;

    String amount, remark, mobile;
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";

    JSONArray jarr = null;
    JSONObject json;

    TelephonyManager tel;
    TextView imei;

    String imeis, androidid;
    public static boolean isMultiSimEnabled = false;
    public static List<SubscriptionInfo> subInfoList;
    public static ArrayList<String> numbers;
    private SubscriptionManager subscriptionManager;
    static final Integer PHONESTATS = 0x1;
    private final String TAG = RegisterMenu.class.getSimpleName();

    TempStorage objTempStorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_transaction);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        objTempStorage=new TempStorage(getApplicationContext());


        etamount = (EditText) findViewById(R.id.etamt);
        etremark = (EditText) findViewById(R.id.etremarks);

       // mobile = getIntent().getExtras().getString("mobile");

         mobile=objTempStorage.getPhone();

        btnSend = (Button) findViewById(R.id.btnNext);


        try {
            btnSend.setOnClickListener(this);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onClick(View v) {
        getData();
    }


    public void getData() {
        amount = etamount.getText().toString().trim();
        remark = etremark.getText().toString().trim();


        // String Type="Atm";
        /// Log.i("Data entry validation","success") ;

        List<NameValuePair> params1 = new ArrayList<NameValuePair>();
        params1.add(new BasicNameValuePair("amount", amount));
        params1.add(new BasicNameValuePair("remark", remark));
        params1.add(new BasicNameValuePair("mobile", mobile));


        json = HttpCommunication.makeHttpRequest(Constant.TEMP_AMOUNT_TRANSFER, "GET", params1, getApplicationContext());
        try {
            //  Log.i("Jsonconvert",getPostDataString(json));
            if (json != null) {

                String message = json.getString(TAG_MESSAGE);
                int success = json.getInt(TAG_SUCCESS);
                if (success == 1) {
                    Toast.makeText(getApplicationContext(), "Data  Registered Succesfull", Toast.LENGTH_LONG).show();

                     Intent i = new Intent(getApplicationContext(), OtpMenu.class);
                   // i.putExtra("mobile",mobile);

                    startActivity(i);
                }
                if (success == 0) {
                    Toast.makeText(getApplicationContext(), "Data  not Register", Toast.LENGTH_LONG).show();

                }
                Log.i("success status : " + success, message);
            }
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Erorr" + e.getMessage().toString(), Toast.LENGTH_LONG).show();

            e.getMessage();
        }

    }
}
