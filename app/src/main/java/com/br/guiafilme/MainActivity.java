package com.br.guiafilme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.br.guiafilme.Adapters.GenreAdapter;
import com.br.guiafilme.Tasks.GetGenreTask;
import com.br.guiafilme.Tasks.MoviesByGenreTask;
import com.br.guiafilme.model.Genre;

import java.io.Serializable;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Genre> genresList;
    private GenreAdapter adapter;
    private ListView genreListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        genreListView = findViewById(R.id.generoListView);
        genresList = new ArrayList<>();
        adapter = new GenreAdapter(this,genresList);
        genreListView.setAdapter(adapter);

        genreListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Genre genre = (Genre) genreListView.getItemAtPosition(position);

                Intent intentToMoviesByGenre = new Intent(MainActivity.this, MoviesActivity.class);
                intentToMoviesByGenre.putExtra("genre", (Serializable) genre);
                startActivity(intentToMoviesByGenre);
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();

        GetGenreTask task = new GetGenreTask(this,genresList,adapter);
        task.execute();
    }
}
