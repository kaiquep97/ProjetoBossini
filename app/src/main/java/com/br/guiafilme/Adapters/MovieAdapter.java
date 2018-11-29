package com.br.guiafilme.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.br.guiafilme.R;
import com.br.guiafilme.model.Genre;
import com.br.guiafilme.model.Movie;

import java.util.List;

public class MovieAdapter extends ArrayAdapter<Movie> {

    public MovieAdapter(@NonNull Context context, List<Movie> resource) {
        super(context, -1,resource);
    }

    private static  class ViewHolder{
        TextView titleTextView;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Movie movie = (Movie) getItem(position);
        View raiz = null;
        ViewHolder viewHolder = null;
        Context context = getContext();
        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(context);
            raiz = inflater.inflate(R.layout.list_item, parent, false);
            viewHolder = new ViewHolder();
            raiz.setTag(viewHolder);
            viewHolder.titleTextView = raiz.findViewById(R.id.txtGeneroView);
        }
        else{
            raiz = convertView;
            viewHolder = (ViewHolder) raiz.getTag();
        }

        viewHolder.titleTextView.setText(movie.getTitle());

        return raiz;
    }
}
