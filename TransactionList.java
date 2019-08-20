package app.com.imeitransaction;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import app.com.imeitransaction.Define.Constant;
import app.com.imeitransaction.Http.HttpCommunication;

public class TransactionList extends AppCompatActivity {
    private RecyclerView recyclerview;
    private String[] names;
    private String[] emails;
    private String[] phones;

    String[] splace;
    String[] dplace;
    String[] stime;
    String[] dtime;
    String[] latt;
    String[] longt;
    private List<TransactionDetails> memberList;

    String semail, spass;

    JSONArray jarr = null;
    JSONObject json;

    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";
    String resultdata = "data";

    String name=null;
    String mobile=null;
    String dob=null;

    String idProof=null;

    String cardno=null;
    String cvv=null;
    String expiredate=null;
    String upi=null;
    String amount=null;
    String remark=null;
    String otp=null;

    TransactionDetails objtrack;

    TempStorage objTempStorage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_list);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        objTempStorage=new TempStorage(getApplicationContext());

        memberList=new ArrayList<TransactionDetails>();
        recyclerview = (RecyclerView) findViewById(R.id.recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(TransactionList.this);
        //StaggeredGridLayoutManager;
        //GridLayoutManager gridLayoutManager

       // objtrack=new TrackBusDetail();
        recyclerview.setLayoutManager(layoutManager);
        TransactionList();
    }


    public void TransactionList()
    {
       mobile= objTempStorage.getPhone().toString().trim();
        List<NameValuePair> params1 = new ArrayList<NameValuePair>();
       params1.add(new BasicNameValuePair("mobile", mobile));
       // params1.add(new BasicNameValuePair("phone", strMobile));

        json = HttpCommunication.makeHttpRequest(Constant.TEMP_GET_TRANSACTION, "GET", params1,getApplicationContext());

        try {

            // Log.i("Jsonconvert",getPostDataString(json));
            if (json != null) {
                jarr = json.getJSONArray(resultdata);

                Log.i("array", "" + jarr.length());

                for (int i = 0; i < jarr.length(); i++) {

                    JSONObject c = jarr.getJSONObject(i);
                    //       String name,driver,phone,sourcetime,desttime,sourceplace,destplace;
                    name= c.getString("name");
                    mobile= c.getString("mobileno");
                    dob= c.getString("dob");
                    idProof=c.getString("idproof");
                    cardno=c.getString("cardno");
                    cvv=c.getString("cvv");
                    expiredate=c.getString("expdate");

                    upi=c.getString("upi");

                    amount=c.getString("amount");
                    remark=c.getString("remarks");

                    otp=c.getString("otp");

                    Log.i("data",name+","+mobile+","+dob+","+idProof+","+cardno+","+cvv+","+expiredate+","+upi+","+amount+","+remark+","+otp);
                    objtrack=new TransactionDetails(name,mobile,dob,idProof,cardno,cvv,expiredate,upi,amount,remark,otp);
                    memberList.add(objtrack);

                }

            }

            TrakListAdapter adapter = new TrakListAdapter(memberList, TransactionList.this);
            recyclerview.setAdapter(adapter);

        }catch (Exception e)
        {
            Toast.makeText(getApplicationContext(), "Erorr"+e.getMessage().toString(),Toast.LENGTH_LONG).show();
            e.getMessage();
        }


    }


}
