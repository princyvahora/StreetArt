package com.example.streetartapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class ListOfArt extends AppCompatActivity {

    ArrayList<Art_data> artDataArrayList;
    RecyclerView recyclerView;
    RecycleAdapter recycleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_art);
        recyclerView = findViewById(R.id.list_art);

        artDataArrayList = new ArrayList<Art_data>();
        recycleAdapter = new RecycleAdapter(artDataArrayList,getApplicationContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(recycleAdapter);
        loadData();

    }

    public void loadData() {
        MyTask process = new MyTask();
        process.execute();
    }


    private class MyTask extends AsyncTask<Void, Void, String> {
        String message;

        @Override
        protected String doInBackground(Void... voids) {

            URL url = null;
            try {

                url = new URL("http://192.168.3.102:8080/StreetArtGallery/streetart/database/ArtList");

                HttpURLConnection client = null;

                client = (HttpURLConnection) url.openConnection();

                client.setRequestMethod("GET");

                int responseCode = client.getResponseCode();

                System.out.println("\n Sending 'GET' request to URL : " + url);

                System.out.println("Response Code : " + responseCode);

                InputStreamReader myInput= new InputStreamReader(client.getInputStream());

                BufferedReader in = new BufferedReader(myInput);
                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                //print result
                System.out.println(response.toString());

                message = response.toString();
            }
            catch (MalformedURLException e) {
                e.printStackTrace();
            }

            catch (IOException e) {
                e.printStackTrace();
            }

            return message;

        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            parseJsonData(result);
        }
    }

    private void parseJsonData(String jsonResponse) {
        try
        {
            JSONObject responseObj = new JSONObject(jsonResponse);
            JSONArray jsonArray = responseObj.getJSONArray("ArtList");
            for(int i=0;i<jsonArray.length();i++)
            {

                JSONObject jsonObject = jsonArray.getJSONObject(i);

                Art_data item = new Art_data(jsonObject.getInt("ARTID"),
                        jsonObject.getString("NAMEOFART"),
                        jsonObject.getString("STYLEOFART"),
                        jsonObject.getInt("PERIODOFTIME"),
                        jsonObject.getString("CREATIONDATE"),
                        jsonObject.getString("SUMMARY"),
                        jsonObject.getString("ADDRESS"),
                        jsonObject.getString("CATEGORYNAME"));
                artDataArrayList.add(item);
                recycleAdapter.notifyDataSetChanged();
            }

        }
        catch (JSONException e) {

            e.printStackTrace();
        }
    }
    }




