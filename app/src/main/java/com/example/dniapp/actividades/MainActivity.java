package com.example.dniapp.actividades;

import androidx.appcompat.app.AppCompatActivity;

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

public class MainActivity extends AppCompatActivity {

    public static final String TAG_APP = "DNI_APP";
    public static final String DNI_SAVE = "DNI";
    public static  String value = "dni";
    private RadioButton radioButtonSeleccionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.radioButtonSeleccionado = findViewById(R.id.radio1);
         // Normalmente las preferences se hacen en una clase aparte
        //recuperamos el fichero y el contenido de la palabra que hemos guardado
        SharedPreferences sp = getSharedPreferences(DNI_SAVE, MODE_PRIVATE);
            EditText textView = findViewById(R.id.dni);
            textView.setText(sp.getString(value, ""));

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
    }





    public void tocadoRadio(View view) {
        Log.d(TAG_APP, "Tocó RadioButton");
        this.radioButtonSeleccionado = (RadioButton)view;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
            // cogemos contenido de la caja y lo guardamos en un fichero llamado DNI_SAVE
            // con clave=value  y valor= codigo
            SharedPreferences sp = getSharedPreferences(DNI_SAVE, MODE_PRIVATE);
            Log.d("MIAPP", "el DNI no existe");
            EditText textView = findViewById(R.id.dni);
            String codigo = textView.getText().toString();
            SharedPreferences.Editor editor = sp.edit();
            editor.putString(value, codigo );
            editor.commit();
    }

    //TODO Guardar el dni introducido para que aparezca cuando vuelves a entrar
    // Al entrar de nuevo en la app -> usar shared preferences


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
