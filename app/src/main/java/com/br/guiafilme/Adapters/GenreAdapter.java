package com.br.guiafilme.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.br.guiafilme.R;
import com.br.guiafilme.model.Genre;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GenreAdapter extends ArrayAdapter<Genre> {


    private Map<String, Bitmap> bitmaps = new HashMap<>();
    public GenreAdapter(@NonNull Context context, List<Genre> genres) {
        super(context,-1, genres);
    }

    private static class ViewHolder{
        TextView nameTextView;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Genre genre = (Genre) getItem(position);
        View raiz = null;
        ViewHolder viewHolder = null;
        Context context = getContext();
        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(context);
            //raiz = inflater.inflate(R.layout.list_item, parent, false);
            viewHolder = new ViewHolder();
            raiz.setTag(viewHolder);
        }
        else{
            raiz = convertView;
            viewHolder = (ViewHolder) raiz.getTag();
        }

        return raiz;
    }

    private class ImageGetter extends AsyncTask<String,Void,Bitmap>{
        private ImageView imageView;
        private Map<String,Bitmap> bitmaps;

        public ImageGetter(ImageView imageView, Map<String,Bitmap> bitmaps){
            this.imageView = imageView;
            this.bitmaps = bitmaps;
        }

        @Override
        protected Bitmap doInBackground(String... urls) {
            try{
                URL url = new URL(urls[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = connection.getInputStream();
                Bitmap figura = BitmapFactory.decodeStream(inputStream);
                bitmaps.put(urls[0], figura);
                return figura;
            }
            catch (IOException e){
                e.printStackTrace();
                return null;
            }
        }
    }
}
