package com.example.dniapp.actividades;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dniapp.R;
import com.example.dniapp.beans.Dni;

public class DniViewHolder extends RecyclerView.ViewHolder{

    private TextView text_view_numero;
    private TextView text_view_letra;

    public DniViewHolder(@NonNull View itemView) {
        super(itemView);
        text_view_numero = (TextView)itemView.findViewById(R.id.numero_dni);
        text_view_letra = (TextView)itemView.findViewById(R.id.letra_dni);

    }

    public void cargarDniEnHolder (Dni dni) {
        text_view_numero.setText(dni.getNumero());
        text_view_letra.setText(dni.getLetra());

    }
}
