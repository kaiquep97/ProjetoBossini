package com.br.guiafilme.Tasks;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.br.guiafilme.Adapters.MovieAdapter;
import com.br.guiafilme.R;
import com.br.guiafilme.Web.WebClient;
import com.br.guiafilme.model.Genre;

import java.util.ArrayList;

public class GetInfoMovies extends AsyncTask<Void,Void,String>
{
    private final Integer id = null;
    private final Context context;
    private final MovieAdapter adapter;
    private ProgressDialog dialog;

    public GetInfoMovies(Context context, Integer id, MovieAdapter adapter){
     //   this.id = id;
        this.context= context;
        this.adapter = adapter;
    }

    @Override
    protected String doInBackground(Void... voids) {
        String endpoint = CreateURL(context);
        WebClient client = new WebClient();

        String response = client.Get(context,endpoint);
        return response;
    }

    private String CreateURL(Context context) {
        String baseUrl = context.getString(R.string.API_URL);
        String url_key = context.getString(R.string.API_KEY);
        String id_movie = context.getString(R.string.ID_MOVIE);
        return baseUrl + "/movie/" + id_movie + "?api_key=" + url_key;
    }
}
