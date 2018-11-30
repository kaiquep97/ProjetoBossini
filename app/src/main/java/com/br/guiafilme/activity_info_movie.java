package com.br.guiafilme;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.br.guiafilme.Adapters.MovieAdapter;
import com.br.guiafilme.Tasks.MoviesByGenreTask;
import com.br.guiafilme.model.Genre;
import com.br.guiafilme.model.Movie;

import java.util.ArrayList;

public class activity_info_movie extends AppCompatActivity {

    private Movie movie;
    private ArrayList<Movie> info_movie;
    private ListView infoMovieListView;
    private MovieAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_movie);

        infoMovieListView = findViewById(R.id.infoMovieListView);
        //adapter = new MovieAdapter();

        infoMovieListView.setAdapter(adapter);

        super.onResume();


    }
}
