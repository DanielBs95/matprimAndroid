package com.daniel.appmatematicas.util;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.daniel.appmatematicas.R;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyviewHolder> {

    Context context;
    List<Reporte> movieList;

    public RecyclerAdapter(Context context, List<Reporte> movieList) {
        this.context = context;
        this.movieList = movieList;
    }

    public void setMovieList(List<Reporte> movieList) {
        this.movieList = movieList;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerAdapter.MyviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.album_card,parent,false);
        return new MyviewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerAdapter.MyviewHolder holder, int position) {
        holder.tvMovieName.setText("Usuario: "+movieList.get(position).getNombre());
        holder.tvNota.setText("Nota: "+movieList.get(position).getNota());

        String satifaccion[] = movieList.get(position).getNota().split("Satisfacción");
        String motivacion[] = movieList.get(position).getNota().split("Motivacion:");
        String nota[] = movieList.get(position).getNota().split("");

        holder.tvMotivacion.setText(satifaccion[0]);
        holder.tvSatisfaccion.setText(motivacion[0]);

    }

    @Override
    public int getItemCount() {
        if(movieList != null){
            return movieList.size();
        }
        return 0;
    }

    public class MyviewHolder extends RecyclerView.ViewHolder {
        TextView tvMovieName;
        TextView tvNota;
        TextView tvMotivacion;
        TextView tvSatisfaccion;

        public MyviewHolder(View itemView) {
            super(itemView);
            tvMovieName = (TextView)itemView.findViewById(R.id.nombre_resultado);
            tvNota = (TextView)itemView.findViewById(R.id.nota_resultado);
            tvMotivacion = (TextView)itemView.findViewById(R.id.motivacion_resultado);
            tvSatisfaccion = (TextView)itemView.findViewById(R.id.satisfaccion_resultado);
        }
    }
}