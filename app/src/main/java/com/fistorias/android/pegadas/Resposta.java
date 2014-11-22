package com.fistorias.android.pegadas;

/**
 * Created by noemi on 22/11/2014.
 */
public class Resposta {
    //int id_respuesta;
    long id_pregunta;
    String resposta;
    int e_resposta_correcta;
    String explicacion;

    public Resposta(long id_pregunta, String resposta, int e_resposta_correcta, String explicacion){
        this.id_pregunta=id_pregunta;
        this.resposta=resposta;
        this.e_resposta_correcta=e_resposta_correcta;
        this.explicacion=explicacion;
    }

    public long getId_pregunta() {
        return id_pregunta;
    }

    public String getResposta() {
        return resposta;
    }

    public int getE_resposta_correcta() {
        return e_resposta_correcta;
    }

    public String getExplicacion() {
        return explicacion;
    }
}
