package com.br.guiafilme.Web;

import android.content.Context;

import com.br.guiafilme.R;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.lang.String;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class WebClient {
    public String post(Context context, String endpoint, String json){

        try {
            URL url = new URL(endpoint);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("Content-type","application/json");
            connection.setDoOutput(true);

            PrintStream stream = new PrintStream(connection.getOutputStream());
            stream.println(json);

            Scanner scanner = new Scanner(connection.getInputStream());
            String response = scanner.next();

            return  response;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  null;
    }

    public String Get(Context context, String endpoint){

        HttpURLConnection connection = null;
        try {
            URL url = new URL(endpoint);
            connection = (HttpURLConnection) url.openConnection();

            if(connection.getResponseCode() == HttpURLConnection.HTTP_OK){
                StringBuilder builder = new StringBuilder();

                try(BufferedReader reader = new BufferedReader((new InputStreamReader(connection.getInputStream())))){
                    String line;
                    while((line = reader.readLine()) !=null){
                        builder.append(line);
                    }
                }

                return builder.toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private String GetURL(Context context, String method) {
        String baseUrl = context.getString(R.string.API_URL);
        String url_key = context.getString(R.string.API_KEY);
        return baseUrl + method + "?api_key="+url_key;
    }
}
