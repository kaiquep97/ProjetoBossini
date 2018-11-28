package com.br.guiafilme;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.br.guiafilme.Tasks.GetGenreTask;
import com.br.guiafilme.Tasks.MoviesByGenreTask;
import com.br.guiafilme.model.Genre;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Genre> genresList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //AQUI FAZER IMPLEMENTAÇAO DO ADAPTER PARA A LISTVIEW
        genresList = new ArrayList<>();

    }

    @Override
    protected void onResume() {
        super.onResume();

        GetGenreTask task = new GetGenreTask(this,genresList);
        //MoviesByGenreTask task = new MoviesByGenreTask(this,14);
        task.execute();
    }
}
