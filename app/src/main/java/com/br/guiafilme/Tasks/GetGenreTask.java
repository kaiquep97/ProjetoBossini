package com.br.guiafilme.Tasks;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.br.guiafilme.Adapters.GenreAdapter;
import com.br.guiafilme.R;
import com.br.guiafilme.Web.WebClient;
import com.br.guiafilme.model.Genre;
import com.br.guiafilme.model.GenreList;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;


public class GetGenreTask extends AsyncTask<Void,Void,String> {

    private final ArrayList<Genre> genres;
    private final Context context;
    private final GenreAdapter adapter;
    private ProgressDialog dialog;

    public GetGenreTask(Context context, ArrayList<Genre> genres, GenreAdapter adapter){
        this.genres = genres;
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

    @Override
    protected void onPostExecute(String json) {
        dialog.dismiss();
        Gson gson = new Gson();

        if(!json.isEmpty()) {
            GenreList list = gson.fromJson(json, GenreList.class);

            genres.clear();
            genres.addAll(list.getGenres());

            adapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void onPreExecute() {
        dialog = ProgressDialog.show(context , "Aguarde", "Buscando Generos...", true,true);
    }

    private String CreateURL(Context context) {
        String baseUrl = context.getString(R.string.API_URL);
        String url_key = context.getString(R.string.API_KEY);
        String language = context.getString(R.string.language);

        return baseUrl + "genre/movie/list?api_key="+url_key+"&language="+language;
    }
}
