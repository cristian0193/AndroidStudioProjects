package com.example.crodriguez.proyectofinalquestions.vista.adaptadores;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.crodriguez.proyectofinalquestions.R;
import com.example.crodriguez.proyectofinalquestions.modelo.PersonajeVo;
import com.example.crodriguez.proyectofinalquestions.modelo.Pregunta;
import com.example.crodriguez.proyectofinalquestions.vista.utilidades.Utilidades;

import java.util.ArrayList;

public class AdaptadorRecyclerView
        extends RecyclerView.Adapter<AdaptadorRecyclerView.PersonajesViewHolder>
        implements View.OnClickListener {

    ArrayList<Pregunta> listaPregunta;
    private View.OnClickListener listener;

    public AdaptadorRecyclerView(ArrayList<Pregunta> listaPregunta) {
        this.listaPregunta = listaPregunta;
    }

    @Override
    public PersonajesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list,null,false);
        RecyclerView.LayoutParams layParams = new RecyclerView.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(layParams);

        view.setOnClickListener(this);

        return new PersonajesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PersonajesViewHolder holder, int position) {
        holder.txtCategoria.setText(listaPregunta.get(position).getCategoria());
        
        if (Utilidades.PORTRAIT==true){
            holder.txtInformacion.setText(listaPregunta.get(position).getDescripcion_pregunta());
            holder.txtFecha.setText(listaPregunta.get(position).getFecha());
        }

        holder.foto.setImageResource(listaPregunta.get(position).getImagenId());
    }

    @Override
    public int getItemCount() {
        return listaPregunta.size();
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener=listener;
    }

    @Override
    public void onClick(View view) {
        if (listener!=null){
            listener.onClick(view);
        }
    }

    public class PersonajesViewHolder extends RecyclerView.ViewHolder {
        TextView txtCategoria,txtInformacion,txtFecha;
        ImageView foto;

        public PersonajesViewHolder(View itemView) {
            super(itemView);
            txtCategoria= (TextView) itemView.findViewById(R.id.idNombre);
            if (Utilidades.PORTRAIT==true){
                txtInformacion= (TextView) itemView.findViewById(R.id.idInfo);
                txtFecha = (TextView) itemView.findViewById(R.id.idFecha);
            }

            foto= (ImageView) itemView.findViewById(R.id.idImagen);
        }
    }
}
