package com.example.dniapp.actividades;

import android.app.AppComponentFactory;
import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dniapp.R;
import com.example.dniapp.beans.Dni;
import com.example.dniapp.util.Preferencias;

import java.util.ArrayList;
import java.util.List;

public class ListaDni extends AppCompatActivity {

    private RecyclerView recView;

    private List<Dni> datos;

    private AdapterDni adaptador;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.recycler_view);

        datos = Preferencias.cargarFicheroDni(this);

        recView = (RecyclerView) findViewById(R.id.RecView);

        //Creamos el adapter
        adaptador = new AdapterDni(datos);

        //Decimos al recycleview cual es el adapter
        recView.setAdapter(adaptador);

        recView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));

        //Ponemos el ITEM decorativo
        recView.addItemDecoration(
                new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }
}

