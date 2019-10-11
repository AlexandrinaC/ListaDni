package com.example.dniapp.util;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.dniapp.beans.Dni;
import com.google.gson.Gson;

/**
 * Clase para almacenar los preferences del mainActivity
 * @author RPC
 * @since 1.0
 * @see com.example.dniapp.actividades.MainActivity
 */
public class Preferencias {

    public static final String FICHERO_ULTIMO = "ultimo_dni";
    public static final String FICHERO_CONTADOR = "contador";
    public static final String CONTADOR = "contador";

    // TEner metodo por cada operacion



    /**
     * Este metodo guarda en el fichero de preferencias el ultimo dni introducio por el usuario
     * @param contexto el contexto de la aplicacion
     * @param dni el valor a guardar del dni
     */


    public static void guardarDNI(Context contexto, int num, String dni){ // Necesitamos pasarle el contexto, para que sepa en que activity esta

        SharedPreferences sp = contexto.getSharedPreferences(FICHERO_ULTIMO, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(String.valueOf(num), dni);
        editor.commit();
    }


    /**
     *
     * Obtiene el valor del ultimo dni almacenado en las preferencias
     * @param contexto
     * @return el ultimo dni almacenado y cadena vacia en caso de que no exista
     */
    public static String obtenerDni(Context contexto, int numero){
        String dni = "";
        SharedPreferences sp = contexto.getSharedPreferences(FICHERO_ULTIMO, Context.MODE_PRIVATE);
        dni = sp.getString(String.valueOf(numero), "");
        Gson gson = new Gson();
        Dni dniST = gson.fromJson(dni ,Dni.class);

        if (dniST == null){
            return "";
        }
        else {
            int i = dniST.getNumero();
            char letra = dniST.getLetra();
            dni = (i + letra + "");
        }
      return dni;
    }


    // guardo el numero del contador
    public static void guardarNum(Context contexto, int numero){
        SharedPreferences sp = contexto.getSharedPreferences(FICHERO_CONTADOR, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(CONTADOR, numero);
        editor.commit();
    }

    // Metodo para obtener el contador del fichero
    public static int obtenerNum(Context contexto){
        int numero = 0;
        SharedPreferences ps = contexto.getSharedPreferences(FICHERO_CONTADOR, Context.MODE_PRIVATE);
        numero = ps.getInt(CONTADOR,  0);

        return numero;
    }
    // Los metodos estaticos son los que no cambian su funcionamiento dependiendo de la instancia
    //Siempre hace lo mismo a diferencia de getNumero, que devuelve distinto numero dependiendo del dni
    //que recibe.
}
