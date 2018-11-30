package com.br.guiafilme;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.br.guiafilme.Tasks.ImageGetter;
import com.br.guiafilme.model.Movie;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class MovieDetailActivity extends AppCompatActivity {

    private TextView txtDescricao;
    private ImageView imgPoster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        Intent intent = getIntent();
        Movie movie = (Movie) intent.getSerializableExtra("movie");

        txtDescricao = findViewById(R.id.txtDescricao);
        imgPoster = findViewById(R.id.imgPoster);
        setTitle(movie.getTitle());
        txtDescricao.setText(movie.getOverview());

        if(!movie.getBackdrop_path().isEmpty()){
            String imgUrl = getText(R.string.IMG_URL)+ movie.getBackdrop_path();
            ImageGetter imageGetter = new ImageGetter(imgPoster);
            imageGetter.execute(imgUrl);
        }
    }
}
