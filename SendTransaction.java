package app.com.imeitransaction;

import android.Manifest;
import android.content.Context;
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

public class SendTransaction extends AppCompatActivity implements View.OnClickListener{

    EditText etname, etmobile, etdob, etidproof, etcardno, etcvv,etexpdate,etupi;
    Button btnSend;

    String name, mobile, dob, proof, cardno, cvv, expdate, upi;
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
        setContentView(R.layout.activity_amount_transaction);

        objTempStorage=new TempStorage(getApplicationContext());


        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        etname = (EditText) findViewById(R.id.etname);
        etmobile = (EditText) findViewById(R.id.etmobile);

        etdob = (EditText) findViewById(R.id.etdob);
        etidproof = (EditText) findViewById(R.id.etidproof);
        etcardno = (EditText) findViewById(R.id.etcardno);
        etcvv = (EditText) findViewById(R.id.etcvv);

        etexpdate = (EditText) findViewById(R.id.etexpdate);
        etupi = (EditText) findViewById(R.id.etupi);

        btnSend = (Button) findViewById(R.id.btnNext);

        etmobile.setText(mobile= objTempStorage.getPhone());
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


    public void getData()
    {
        name=etname.getText().toString().trim();
        mobile=etmobile.getText().toString().trim();

        dob=etdob.getText().toString().trim();
        proof=etidproof.getText().toString().trim();

        cardno=etcardno.getText().toString().trim();

        cvv=etcvv.getText().toString().trim();

        expdate=etexpdate.getText().toString().trim();

        upi=etupi.getText().toString().trim();

        // String Type="Atm";
        /// Log.i("Data entry validation","success") ;

       mobile= objTempStorage.getPhone();


        List<NameValuePair> params1 = new ArrayList<NameValuePair>();
        params1.add(new BasicNameValuePair("name", name));
        params1.add(new BasicNameValuePair("mobile", mobile));
        params1.add(new BasicNameValuePair("dob", dob));
        params1.add(new BasicNameValuePair("proof", proof));
        params1.add(new BasicNameValuePair("cardno", cardno));
        params1.add(new BasicNameValuePair("cvv", cvv));
        params1.add(new BasicNameValuePair("expdate", expdate));
        params1.add(new BasicNameValuePair("upi", upi));

        json = HttpCommunication.makeHttpRequest(Constant.TEMP_CARD_DETAILS, "GET", params1,getApplicationContext());
        try {
            //  Log.i("Jsonconvert",getPostDataString(json));
            if (json != null) {

                String message = json.getString(TAG_MESSAGE);
                int success = json.getInt(TAG_SUCCESS);
                if(success==1)
                {
                    Toast.makeText(getApplicationContext(), "Data  Registered Succesfull", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(getApplicationContext(), AmountTransaction.class);
                   // i.putExtra("mobile",mobile);
                    startActivity(i);
                }
                if(success==0)
                {
                    Toast.makeText(getApplicationContext(), "Data  not Register", Toast.LENGTH_LONG).show();

                }
                Log.i("success status : " + success, message);
            }
        }catch (Exception e)
        {
            Toast.makeText(getApplicationContext(), "Erorr"+e.getMessage().toString(),
                    Toast.LENGTH_LONG).show();

            e.getMessage();
        }

    }

}
