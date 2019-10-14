package com.example.dniapp.actividades;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.dniapp.R;
import com.example.dniapp.beans.Dni;
import com.example.dniapp.beans.DniX;
import com.example.dniapp.beans.DniY;
import com.example.dniapp.beans.DniZ;
import com.example.dniapp.util.Preferencias;
import com.google.gson.Gson;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String TAG_APP = "DNI_APP";
    private RadioButton radioButtonSeleccionado;
    public static int numDNIs = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.radioButtonSeleccionado = findViewById(R.id.radio1);
         // Normalmente las preferences se hacen en una clase aparte
        //recuperamos el fichero y el contenido de la palabra que hemos guardado



       // Dni dni = new Dni(123123123, 'X');
        // Serializar de objeto DNI a String

        /*Gson gson = new Gson();
        String dni_json = gson.toJson(dni);
        Log.d("MIAPP", "dni en json es"+ dni_json);

        //Deserializar de String a objeto de java
        Dni dni2 = gson.fromJson(dni_json, Dni.class);

        */
            numDNIs = Preferencias.obtenerNum(this);
            EditText textView = findViewById(R.id.dni);
            String string = "";

            string = Preferencias.obtenerDni(this, numDNIs);
            textView.setText(string);  // asignamos ese valor al EditText del layout

    }


    private Dni crearObjetoDni (int num_dni)
    {
        Dni dni = null;

        switch (this.radioButtonSeleccionado.getId())
        {
            case R.id.radio1: dni = new Dni(num_dni);//nacional
                break;
            case R.id.radio2: dni = new DniX(num_dni);//X
                break;
            case R.id.radio3: dni = new DniY(num_dni);//Y
                break;
            case R.id.radio4: dni = new DniZ(num_dni);//Z
                break;
        }

        return dni;
    }

    public void calcularLetraDni(View view) {
        Log.d(TAG_APP, "Ha tocado el botón de calcular");


        Gson gson = new Gson();
        //TODO
        //1 obtener el número de dni introducido por el usario
        EditText caja_dni = findViewById(R.id.dni);
        String sdni = caja_dni.getText().toString();
        //2 Casting de String a Int //NO ESTOY VALIDANDO LA ENTRADA
        int num_dni = Integer.parseInt(sdni);
        //2.1 Creo el objeto DNI nuevo
        Dni dni = crearObjetoDni(num_dni);
        //calcular letra
        char letra_dni = dni.calculaLetra();
        Log.d(TAG_APP, "Letra calculada = "+letra_dni);
        //4 Lanzo la actividad del resultado pasándole la letra
        Intent intent = new Intent(this, AnimacionLetraActivity.class);
        intent.putExtra("LETRA", letra_dni);
        startActivity(intent);


        // poner letra al dni, para que no quede vacia
        dni.setLetra(letra_dni);
        String json_dni = gson.toJson(dni);
        // borrar contenido de la caja y guardar vacio si ya se ha calculado
        caja_dni.setText("");
        ++numDNIs;
        Preferencias.guardarNum(this, numDNIs);
        Preferencias.guardarDNI(this, numDNIs, json_dni);
    }




    public void tocadoRadio(View view) {
        Log.d(TAG_APP, "Tocó RadioButton");
        this.radioButtonSeleccionado = (RadioButton)view;
    }

    @Override
    public void onBackPressed() {
        EditText textView = findViewById(R.id.dni);
        String codigo = textView.getText().toString();


        mostrarDialogoSalir();
        elegirGuardar( codigo);
        Log.d("MIAPP", "el usuario selecciona slair o no");
        //super.onBackPressed();
            // cogemos contenido de la caja y lo guardamos en un fichero llamado DNI_SAVE
            // con clave=value  y valor= codigo
            Log.d("MIAPP", "el DNI no existe");
            Preferencias.guardarNum(this, numDNIs);
            Preferencias.guardarDNI(this,numDNIs, codigo);

    }


    //TODO Guardar el dni introducido para que aparezca cuando vuelves a entrar
    // Al entrar de nuevo en la app -> usar shared preferences




    private void mostrarDialogoSalir ()
    {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("¿Está seguro de que desea salir?");

        builder.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Log.d(TAG_APP, "Tocó SÍ");
                dialog.dismiss();
                MainActivity.this.finish(); // el this se usa para referirse al listener, para poder hacer finish desde aqui en el activity.
                // Y debemos poner el nombre de la clase que contiene este dialogo
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User cancelled the dialog
                Log.d(TAG_APP, "Tocó NO");
                dialog.cancel();
            }
        });

         AlertDialog dialog = builder.create();
         dialog.show();
    }


    private void elegirGuardar (final String dni) // mejor hacer un metodo para obtener DNI, esta solucion no es muy ortodoxa
    {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("¿Quieres guardar el dni?");

        builder.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Log.d(TAG_APP, "Tocó SÍ");
                dialog.dismiss();
                Preferencias.guardarDNI(MainActivity.this,numDNIs, dni);
                MainActivity.this.finish();// el this se usa para referirse al listener, para poder hacer finish desde aqui en el activity.
                // Y debemos poner el nombre de la clase que contiene este dialogo
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User cancelled the dialog
                Log.d(TAG_APP, "Tocó NO");
                dialog.cancel();
                Preferencias.guardarDNI(MainActivity.this,numDNIs, "");
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();







    }

   // public void onBackPressed


    /*private char calcularLetra (int numero_dni)
    {
        char letra_calculada =  ' ';
        int resto = -1;

            resto = (numero_dni%SECUENCIA_LETRAS_DNI.length());
            letra_calculada = SECUENCIA_LETRAS_DNI.charAt(resto);

        return letra_calculada;
    }*/
}
