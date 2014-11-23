package com.fistorias.android.pegadas;

/**
 * Created by noemi on 22/11/2014.
 */
public class Pregunta {
    //int id_pregunta;
    int num_caso;
    int num_pregunta;
    String cod_idioma;
    String tipo_pregunta;
    String enunciado;

    public Pregunta(int num_caso,int num_pregunta,String cod_idioma,String tipo_pregunta,String enunciado){
        this.num_caso=num_caso;
        this.num_pregunta=num_pregunta;
        this.cod_idioma=cod_idioma;
        this.tipo_pregunta=tipo_pregunta;
        this.enunciado=enunciado;
    }
    public int getNum_caso() {
        return num_caso;
    }
    public int getNum_pregunta() {
        return num_pregunta;
    }

    public String getCod_idioma() {
        return cod_idioma;
    }

    public String getEnunciado() {
        return enunciado;
    }

    public String getTipo_pregunta() {
        return tipo_pregunta;
    }
}
