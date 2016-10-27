package com.marvel.gae;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {
    private HttpURLConnection urlConnection = null;

    private EditText eidText;
    private EditText enameText;
    private EditText output;
    private Handler hnd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button readButton = (Button)findViewById(R.id.read);
        Button writeButton = (Button)findViewById(R.id.write);
        eidText = (EditText) findViewById(R.id.empidText);
        enameText = (EditText) findViewById(R.id.empnameText);
        output = (EditText) findViewById(R.id.output);

        hnd =  new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message inputMessage) {
                Bundle b = inputMessage.getData();
                String s = b.getString("output","welcome");
                output.setText(s);
            }

        };


        readButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ReadDataTask readtask = new ReadDataTask();
                readtask.execute(new String[]{"http://grounded-plane-144407.appspot.com/"});
            }
        });

        writeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WriteDataTask writetask = new WriteDataTask();
                writetask.execute(new String[]{eidText.getText().toString(), enameText.getText().toString()});
            }
        });
    }

    private String getQuery(List<NameValuePair> params) throws UnsupportedEncodingException
    {
        StringBuilder result = new StringBuilder();
        boolean first = true;

        for (NameValuePair pair : params)
        {
            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(pair.getName(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(pair.getValue(), "UTF-8"));
        }

        return result.toString();
    }

    private class WriteDataTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
            byte [] bytes = new byte[1000];
            URL url = null;
            try {
                url = new URL("http://grounded-plane-144407.appspot.com/write");
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("POST");
                urlConnection.setDoInput(true);
                urlConnection.setDoOutput(true);

                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new NameValuePair("eid", urls[0]));
                params.add(new NameValuePair("ename", urls[1]));
                params.add(new NameValuePair("eaddress", "Test address122"));

                OutputStream os = urlConnection.getOutputStream();
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(os, "UTF-8"));
                writer.write(getQuery(params));
                writer.flush();
                writer.close();
                os.close();

                urlConnection.connect();

                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                in.read(bytes);
                Log.e("APP", "Message is " + new String(bytes));

            }
            catch (MalformedURLException e) {
                e.printStackTrace();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            finally {
                urlConnection.disconnect();
            }
                return null;
        }
    }

    private class ReadDataTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
            byte [] bytes = new byte[1000];
            URL url = null;
            try {
                url = new URL("https://grounded-plane-144407.appspot.com/");
                urlConnection = (HttpsURLConnection) url.openConnection();
                //urlConnection.setDoOutput(true); // To perform post
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                in.read(bytes);
                Log.e("APP", "Message is " + new String(bytes));
                Message m = new Message();
                Bundle b = new Bundle();
                b.putString("output",new String(bytes));
                m.setData(b);
                hnd.sendMessage(m);
            }
            catch (MalformedURLException e) {
                e.printStackTrace();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            finally {
                urlConnection.disconnect();
            }
            return null;
        }
    }
}
