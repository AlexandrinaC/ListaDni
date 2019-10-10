package com.example.dniapp.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Clase para almacenar los preferences del mainActivity
 * @author RPC
 * @since 1.0
 * @see com.example.dniapp.actividades.MainActivity
 */
public class Preferencias {

    public static final String FICHERO_ULTIMO = "ultimo_dni";
    public static final String CLAVE_DNI = "ultimo";
    // TEner metodo por cada operacion



    /**
     * Este metodo guarda en el fichero de preferencias el ultimo dni introducio por el usuario
     * @param contexto el contexto de la aplicacion
     * @param dni el valor a guardar del dni
     */


    public static void guardarDNI(Context contexto , String dni){ // Necesitamos pasarle el contexto, para que sepa en que activity esta

        SharedPreferences sp = contexto.getSharedPreferences(FICHERO_ULTIMO, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(CLAVE_DNI, dni);
        editor.commit();
    }


    /**
     *
     * Obtiene el valor del ultimo dni almacenado en las preferencias
     * @param contexto
     * @return el ultimo dni almacenado y cadena vacia en caso de que no exista
     */
    public static String obtenerDni(Context contexto){
        String dni = "";
        SharedPreferences sp = contexto.getSharedPreferences(FICHERO_ULTIMO, Context.MODE_PRIVATE);
         dni = sp.getString(CLAVE_DNI, ""); // defValue es el valor por efecto si no se encuentra




        return dni;
    }
    // Los metodos estaticos son los que no cambian su funcionamiento dependiendo de la instancia
    //Siempre hace lo mismo a diferencia de getNumero, que devuelve distinto numero dependiendo del dni
    //que recibe.
}
