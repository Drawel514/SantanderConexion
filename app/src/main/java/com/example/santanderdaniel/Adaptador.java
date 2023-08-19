package com.example.santanderdaniel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;

public class Adaptador extends RecyclerView.Adapter<Adaptador.ViewHolder> {
    JSONArray jsonArray;
    Context context;
    public Adaptador (Context context,JSONArray jsonArray ){
        this.context = context;
        this.jsonArray=jsonArray;
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        //atributos
        ImageView imagen;
        TextView tvnombre,tvvrUnitario;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //relacion de los atributos con la vista
            imagen=itemView.findViewById(R.id.imageView);
            tvnombre=itemView.findViewById(R.id.tvNombre);
            tvvrUnitario=itemView.findViewById(R.id.tvApellido);
        }
    }
    @NonNull
    @Override
    public Adaptador.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lista_usuarios,null);
        return new ViewHolder(vista);
    }
    @Override
    public void onBindViewHolder(@NonNull Adaptador.ViewHolder holder, int position) {
        //colocar los datos del json a cada elemento o vista
        try {
            holder.tvnombre.setText(jsonArray.getJSONObject(position).get("nombre").toString());
            holder.tvvrUnitario.setText(jsonArray.getJSONObject(position).get("vrUnitario").toString());

            String url=jsonArray.getJSONObject(position).get("imagen").toString();

            Picasso.with(this.context).load(url).into(holder.imagen);
        }catch (JSONException e){
            //error no se pudo obtener el json
            throw new RuntimeException(e);
        }
    }
    @Override
    public int getItemCount() {
        return jsonArray.length();
    }
}
