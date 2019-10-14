package com.example.dniapp.actividades;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dniapp.R;
import com.example.dniapp.beans.Dni;

import java.util.ArrayList;
import java.util.List;

public class AdapterDni extends RecyclerView.Adapter<DniViewHolder> {

    private List<Dni> datos;

    //Crear vista metiendo el Holder dentro.
    @NonNull
    @Override
    public DniViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        DniViewHolder dniViewHolder = null;

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View itemView = inflater.inflate(R.layout.lista_dni, parent, false );

        dniViewHolder = new DniViewHolder(itemView); //Creamos como un "hueco" nuevo para meter nuestros datos.

        return dniViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DniViewHolder holder, int position) {

        //Rellenamos la caja

        //Cojemos la posicion de mis datos. Los que estan en primero,segundo...

        Dni dni = datos.get(position);

        // y cargamos el Dni en el hueco.
        holder.cargarDniEnHolder(dni);


    }

    @Override
    public int getItemCount() {
        //Devolvemos la cantidad de datos que tenemos guardados. El "tamaño" de mis datos.
        return datos.size();
    }
    //Esto para¿?

    public AdapterDni (List<Dni> dnis) {
        this.datos = dnis;

    }
}
