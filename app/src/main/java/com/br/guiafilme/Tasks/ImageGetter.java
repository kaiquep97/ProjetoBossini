package com.br.guiafilme.Tasks;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ImageGetter extends AsyncTask<String, Void, Bitmap> {

    private ImageView imageView;

    public ImageGetter (ImageView imageView){
        this.imageView = imageView;
    }

    @Override
    protected Bitmap doInBackground(String... urlS) {
        try{
            URL url = new URL(urlS[0]);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = connection.getInputStream();
            Bitmap figura = BitmapFactory.decodeStream(inputStream);
            return figura;
        }
        catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }


    @Override
    protected void onPostExecute(Bitmap figura) {
        if(figura != null) {
            imageView.setImageBitmap(figura);
        }
    }
}
