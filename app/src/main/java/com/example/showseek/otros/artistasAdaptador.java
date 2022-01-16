package com.example.showseek.otros;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.showseek.R;
import com.example.showseek.estructures.nonLineal.ColaPrioridad;
import com.example.showseek.objects.Artista;

public class artistasAdaptador extends RecyclerView.Adapter<artistasAdaptador.ViewHolder>{

    //Se debe habilitar la puesta de imagenes en la linea 65
    // cuando sea parametrizada el uri la imagen en firebase
    //luego de ponerla en el storage

    //Atributos
    private ColaPrioridad<Artista> queue;
    private Context context;
    private LayoutInflater mInflater;
    private int size;

    public artistasAdaptador(ColaPrioridad queue, Context context, int size) {
        this.queue = queue;
        this.context = context;
        this.size = size;
        this.mInflater = LayoutInflater.from(context);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView foto;
        private TextView nombre, tipo, genero;
        private RatingBar rating;

        public ViewHolder(@NonNull View itemView){
            super(itemView);

            foto = itemView.findViewById(R.id.imagen_artista);
            nombre = itemView.findViewById(R.id.nombreArtistico);
            tipo = itemView.findViewById(R.id.tipoAgrupacion);
            genero = itemView.findViewById(R.id.generoMusical);
            rating = itemView.findViewById(R.id.ratingBar);

        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.artista_busqueda_recyclerview,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Artista ar = queue.dequeue();
        holder.nombre.setText(ar.getNombreArtistico());
        holder.tipo.setText(ar.getTipoAgrupacion());
        holder.genero.setText(ar.getGeneroMusical());
        holder.rating.setRating(ar.getRating());
        //Glide.with(context).load(ar.getFotoPerfil().centerCrop().into(holder.foto);
        String prueba = ar.getNombreArtistico();
        String rating = Float.toString(ar.getRating());
        Log.d("Nombre Artístico: ",prueba);
        Log.d("Rating: ",rating);
    }

    @Override
    public int getItemCount() {
        return queue.getSize();
    }
}
