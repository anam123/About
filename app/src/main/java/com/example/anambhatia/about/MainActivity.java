package com.example.anambhatia.about;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;

import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends Activity {


    TextView outputText;
    ProgressBar d;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent=getIntent();
        String uri=intent.getStringExtra("URL");
        outputText=(TextView)findViewById(R.id.textView);
        d=(ProgressBar) findViewById(R.id.progressBar);
        new ServletPostAsyncTask().execute(uri);
    }

    public void back(View v)
    {
        Intent intent = new Intent(this,intro.class);
        this.startActivity(intent);
    }

    private class ServletPostAsyncTask extends AsyncTask<String, Void, String> {
        private Context context;
        private String jsonResult;
        URL url;
        @Override
        protected String doInBackground(String... params) {

            String uri=params[0];
            Log.d("url",uri);
            try {
                // Set up the request
                 url = new URL(uri);
              URLConnection connection = (URLConnection) url.openConnection();
                connection.setConnectTimeout(5000);
                connection.setReadTimeout(5000);
                BufferedReader inputStream = new BufferedReader(new InputStreamReader(
                        connection.getInputStream()));

                StringBuilder sBuilder = new StringBuilder();

                String line = null;
                while ((line = inputStream.readLine()) != null) {
                    sBuilder.append(line + "\n");
                }

                inputStream.close();
                jsonResult = sBuilder.toString();
                // read the JSON results into a string
                System.out.print("data: "+jsonResult);

            }
            catch (IOException e)
            {

            }
            return jsonResult;
        }


        @Override
        protected void onPostExecute(String result) {


            Document a=Jsoup.parse(result);
            String b=a.text();
            if (url.toString().equals("https://iiitd.ac.in/about")) {
                String[] arr = b.split("2009-2010");
                outputText.setText(arr[1]);
            }

            else
            {
                outputText.setText(b);
            }

            Log.d("data",b);
            d.setVisibility(View.GONE);




        }
    }


}
