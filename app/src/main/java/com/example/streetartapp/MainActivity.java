package com.example.streetartapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.AsyncQueryHandler;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText art_name, art_style, art_period, date,art_summary, address, category_name;
    Button btn_addart, btn_view_art;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        art_name = findViewById(R.id.art_name);
        art_style = findViewById(R.id.art_style);
        art_period = findViewById(R.id.art_period);
        date = findViewById(R.id.date);
        art_summary = findViewById(R.id.summary);
        address = findViewById(R.id.address);
        category_name = findViewById(R.id.category_name);
        btn_addart = findViewById(R.id.btn_addart);
        btn_view_art = findViewById(R.id.btn_view_art);

        btn_addart.setOnClickListener(this);
        btn_view_art.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if (view == btn_addart) {

            String name = art_name.getText().toString().trim();
            String style = art_style.getText().toString().trim();
            String period = art_period.getText().toString().trim();
            String cdate = date.getText().toString().trim();
            String summary = art_summary.getText().toString().trim();
            String add = address.getText().toString().trim();
            String cname = category_name.getText().toString().trim();

            if (TextUtils.isEmpty(name) ||
                    TextUtils.isEmpty(name) ||
                    TextUtils.isEmpty(style) ||
                    TextUtils.isEmpty(period) ||
                    TextUtils.isEmpty(cdate) ||
                    TextUtils.isEmpty(summary) ||
                    TextUtils.isEmpty(add) ||
                    TextUtils.isEmpty(cname)) {
                Toast.makeText(getApplicationContext(), "Fill Allm The Details!!!Pleasee", Toast.LENGTH_LONG).show();
            } else {
                new MyTask(getApplicationContext(), name, style, period, cdate, summary, add, cname).execute();
            }
        } else if (view == btn_view_art) {
            startActivity(new Intent(MainActivity.this, ListOfArt.class));
        }
    }

    private class MyTask extends AsyncTask<Void, Void, String> {
        String message;
        String name, style, period, cdate, summary, add, cname;
        Context context;

        public MyTask(Context context, String name, String style, String period, String cdate, String summary, String add, String cname) {
            this.context = context;
            this.name = name;
            this.style = style;
            this.period = period;
            this.cdate = cdate;
            this.summary = summary;
            this.add = add;
            this.cname = cname;

        }


        @Override
        protected String doInBackground(Void... voids) {

            URL url = null;
            try {
               // http://192.168.2.23:8080/StreetArtGallery/streetart/database/AddArt&fyi&griffiti&4&19-06-19&motivation&1655,mtl&paint

                url = new URL("http://192.168.3.102:8080/StreetArtGallery/streetart/database/AddArt&" + name + "&" + style + "&" + period + "&" + cdate + "&" + summary+ "&" + add + "&" + cname );

                HttpURLConnection client = null;

                client = (HttpURLConnection) url.openConnection();

                client.setRequestMethod("GET");

                int responseCode = client.getResponseCode();

                System.out.println("\n Sending 'GET' request to URL : " + url);

                System.out.println("Response Code : " + responseCode);

                InputStreamReader myInput = new InputStreamReader(client.getInputStream());

                BufferedReader in = new BufferedReader(myInput);
                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                System.out.println(response.toString());

                message = response.toString();

            } catch (MalformedURLException e) {
                e.printStackTrace();

            } catch (IOException e) {
                e.printStackTrace();
            }
            return message;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            parseJsonData(result);
        }

        private void parseJsonData(String jsonResponse) {
            try {
                JSONObject responseObj = new JSONObject(jsonResponse);
                Toast.makeText(context, responseObj.getString("message"), Toast.LENGTH_LONG).show();
                if (responseObj.getString("Status").equals("OK")) {
                    startActivity(new Intent(MainActivity.this, ListOfArt.class));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
