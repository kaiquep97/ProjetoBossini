package com.br.guiafilme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.br.guiafilme.Adapters.MovieAdapter;
import com.br.guiafilme.Tasks.MoviesByGenreTask;
import com.br.guiafilme.model.Genre;
import com.br.guiafilme.model.Movie;

import java.util.ArrayList;

public class MoviesActivity extends AppCompatActivity {

    private Genre genre;
    private ArrayList<Movie> movies;
    private ListView movieListView;
    private MovieAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);
        movieListView = findViewById(R.id.movieListView);
        movies = new ArrayList<>();
        adapter = new MovieAdapter(this,movies);

        movieListView.setAdapter(adapter);

        Intent intent = getIntent();
        genre = (Genre) intent.getSerializableExtra("genre");

    }

    @Override
    protected void onResume() {
        super.onResume();

        MoviesByGenreTask task = new MoviesByGenreTask(this, genre.getId(), movies, adapter);
        task.execute();
    }
}
