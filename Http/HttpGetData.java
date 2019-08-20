package app.com.imeitransaction.Http;

import android.content.Context;
import android.os.StrictMode;
import android.util.Log;
import android.widget.Toast;

import org.apache.commons.httpclient.HttpStatus;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class HttpGetData
{

    private static String json;
    private final Context context;

    static String responseString;

    static JSONObject jObj = null;

    public HttpGetData(Context context)
    {
        this.context=context;

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

    }

    public static JSONObject makeHttpRequest(String url, String method, Context context)
    {

        Log.d("json class", url + "," + method + "," );
        try
        {

                if (method == "GET")
            {
                // request method is GET
                HttpClient httpClient = new DefaultHttpClient();
                //String paramString = URLEncodedUtils.format(params, "utf-8");
               // url += "?" + paramString;
                Log.i("url", url);
                HttpGet httpGet = new HttpGet(url);
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                HttpResponse httpResponse = httpClient.execute(httpGet);
                int responce_code = httpResponse.getStatusLine()
                        .getStatusCode();

                Log.d("responce code", "" + responce_code);

                StatusLine statusLine = httpResponse.getStatusLine();

                if (statusLine.getStatusCode() == HttpStatus.SC_OK)
                {

                    Log.d("RESPONSE", "6");
                    httpResponse.getEntity().writeTo(out);

                    out.close();

                    responseString = out.toString();

                    Log.i("RESPONSE", "" + responseString);
                    // ..more logic
                }
                else
                {
                    Log.d("RESPONSE", "null pointer exception");
                    // Closes the connection.
                    httpResponse.getEntity().getContent().close();
                    throw new IOException(statusLine.getReasonPhrase());
                }

            }
        }
        catch (UnsupportedEncodingException e)
        {
            Toast.makeText(context, "Encode Exception "+e.getMessage().toString(),
                    Toast.LENGTH_LONG).show();

            e.printStackTrace();
        } catch (ClientProtocolException e)
        {
            Toast.makeText(context, "Protocol Exception "+e.getMessage().toString(),
                    Toast.LENGTH_LONG).show();

            e.printStackTrace();
        }
        catch (IOException e)
        {
            Toast.makeText(context, "Io Exception "+e.getMessage().toString(),
                    Toast.LENGTH_LONG).show();

            e.printStackTrace();
        }
        try {
            json = responseString.toString();

        }
        catch (Exception e)
        {
            Toast.makeText(context, "Error "+e.getMessage().toString(),
                    Toast.LENGTH_LONG).show();

            Log.e("Buffer Error", "Error converting result " + e.toString());
        }

        try {
            jObj = new JSONObject(json);

        } catch (JSONException e) {
            Toast.makeText(context, "JsonException "+e.getMessage().toString(),
                    Toast.LENGTH_LONG).show();

            Log.e("JSON Parser", "Error parsing data " + e.toString());
        }

        return jObj;
    }


}

