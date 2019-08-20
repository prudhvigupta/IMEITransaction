package app.com.imeitransaction;

import android.Manifest;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.StrictMode;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
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
import java.util.Random;

import app.com.imeitransaction.Define.Constant;
import app.com.imeitransaction.Http.HttpCommunication;
import app.com.imeitransaction.Http.HttpGetData;
import android.telephony.SmsManager;


public class OtpMenu extends AppCompatActivity implements View.OnClickListener {
    TextView etimei, etiotp;
    EditText etrotp;
    Button btnSend;

    String emi, otp, mobile;
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

    Random rand;

    int n;

    TempStorage objTempStorage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_menu);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        objTempStorage=new TempStorage(getApplicationContext());

        imei = (TextView) findViewById(R.id.etimei);
        etiotp = (TextView) findViewById(R.id.etiotp);
        etrotp = (EditText) findViewById(R.id.etrotp);

        mobile = objTempStorage.getPhone();

      //  mobile="9003814210";
        String otp= getOtp(mobile);


      if(otp!=null) {

          etiotp.setText(otp);

          etrotp.setText(otp);
      }
      else
      {

          etiotp.setText("Not Generate otp");
      }

        rand = new Random();

        n = rand.nextInt(123456) + 4;

        Log.d("checkrand", String.valueOf(n));


        //if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //getDeviceId() is Deprecated so for android O we can use getImei() method
          //  if (ActivityCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {

                //Intent intent = new Intent(getApplicationContext(), OtpMenu.class);
               /// PendingIntent pi = PendingIntent.getActivity(getApplicationContext(), 0, intent, 0);
                //SmsManager sms = SmsManager.getDefault();
                //sms.sendTextMessage(mobile, null, String.valueOf(n), pi, null);

          //  }

       // }
        // mobile=Intent.getIntent("")

        btnSend = (Button) findViewById(R.id.btnNext);
        askForPermission(Manifest.permission.READ_PHONE_STATE, PHONESTATS);


        try {
            btnSend.setOnClickListener(this);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void askForPermission(String permission, Integer requestCode) {
        if (ContextCompat.checkSelfPermission(OtpMenu.this, permission) != PackageManager.PERMISSION_GRANTED) {

            // Should show an explanation
            if (ActivityCompat.shouldShowRequestPermissionRationale(OtpMenu.this, permission)) {

                ActivityCompat.requestPermissions(OtpMenu.this, new String[]{permission}, requestCode);

            } else {

                ActivityCompat.requestPermissions(OtpMenu.this, new String[]{permission}, requestCode);
            }
        } else {
            imeis = getImeiNumber();
            imei.setText(imeis);

            getClientPhoneNumber();
            androidid = getAndroidId();
            Toast.makeText(this, permission + " is already granted.", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1: {

                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    imeis = getImeiNumber();
                    imei.setText(imeis);

                    getClientPhoneNumber();
                    androidid = getAndroidId();

                } else {

                    Toast.makeText(OtpMenu.this, "You have Denied the Permission", Toast.LENGTH_SHORT).show();
                }
                return;
            }
        }
    }


    private void getClientPhoneNumber() {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    subInfoList = subscriptionManager.getActiveSubscriptionInfoList();

                    return;
                }
            }
            //check whether the phone is of Multi sim or Not
            if (subInfoList.size() > 1) {
                isMultiSimEnabled = true;
            }
            for (SubscriptionInfo subscriptionInfo : subInfoList)
            //add all sim number into arraylist
            {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
                    numbers.add(subscriptionInfo.getNumber());
                }
            }
            Log.e(TAG, "Sim 1:- " + numbers.get(0));
            Log.e(TAG, "Sim 2:- " + numbers.get(1));
        } catch (Exception e) {
            Log.d(TAG, e.toString());
        }


    }

    private String getAndroidId() {

        androidid = Settings.Secure.getString(getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);

        Log.e("TAG", Settings.Secure.getString(getApplicationContext().getContentResolver(), Settings.Secure.ALLOWED_GEOLOCATION_ORIGINS));
        Log.e("TAG", Settings.Secure.getString(getApplicationContext().getContentResolver(), Settings.Secure.DEFAULT_INPUT_METHOD));

        return androidid;
    }


    private String getImeiNumber() {

        String strdev = null;
        final TelephonyManager telephonyManager = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //getDeviceId() is Deprecated so for android O we can use getImei() method
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.

                Log.i("getDevice", telephonyManager.getImei());

                strdev = telephonyManager.getImei();

                return telephonyManager.getImei();

            }
        } else {

            Log.i("getDevice", telephonyManager.getDeviceId());
            strdev = telephonyManager.getDeviceId();


            return telephonyManager.getDeviceId();
        }
        return strdev;
    }


    @Override
    public void onClick(View v) {
        getData();
    }


    public void getData() {
        emi = imei.getText().toString().trim();
        otp = etrotp.getText().toString().trim();

        // String Type="Atm";
        /// Log.i("Data entry validation","success") ;

        List<NameValuePair> params1 = new ArrayList<NameValuePair>();
        params1.add(new BasicNameValuePair("emi", emi));
        params1.add(new BasicNameValuePair("otp", otp));
        params1.add(new BasicNameValuePair("mobile", mobile));


        json = HttpCommunication.makeHttpRequest(Constant.TEMP_OTP_REGISTER, "GET", params1, getApplicationContext());
        try {
            //  Log.i("Jsonconvert",getPostDataString(json));
            if (json != null) {

                String message = json.getString(TAG_MESSAGE);
                int success = json.getInt(TAG_SUCCESS);
                if (success == 1) {
                   // Toast.makeText(getApplicationContext(), "Payment Registered Succesfull", Toast.LENGTH_LONG).show();

                    Intent i = new Intent(getApplicationContext(), Payment.class);
                    startActivity(i);
                }
                if (success == 0) {
                    Toast.makeText(getApplicationContext(), "Payment  not Register", Toast.LENGTH_LONG).show();

                }
                Log.i("success status : " + success, message);
            }
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Erorr" + e.getMessage().toString(), Toast.LENGTH_LONG).show();

            e.getMessage();
        }

    }


    public String getOtp(String mobile) {

        String otp=null;

        List<NameValuePair> params1 = new ArrayList<NameValuePair>();
        params1.add(new BasicNameValuePair("mobile", mobile));


        json = HttpCommunication.makeHttpRequest(Constant.TEMP_GET_OTP, "GET", params1, getApplicationContext());
        try {
            //  Log.i("Jsonconvert",getPostDataString(json));
            if (json != null) {

                String message = json.getString(TAG_MESSAGE);
                int success = json.getInt(TAG_SUCCESS);
                if (success == 1) {
                    Toast.makeText(getApplicationContext(), "IMEI  Registered Succesfull", Toast.LENGTH_LONG).show();

                    //Intent i = new Intent(getApplicationContext(), OtpMenu.class);
                    // startActivity(i);

                    otp=message;
                }
                if (success == 0) {
                    Toast.makeText(getApplicationContext(), "IMEI  not Register", Toast.LENGTH_LONG).show();

                }
                Log.i("success status : " + success, message);
            }
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Erorr" + e.getMessage().toString(), Toast.LENGTH_LONG).show();

            e.getMessage();
        }
return otp;
    }
}