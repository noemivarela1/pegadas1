package com.fistorias.android.pegadas.data;

import android.provider.BaseColumns;

/**
 * Created by noemi on 22/11/2014.
 */
public final class PegadasDBContract {
    //private static final String TEXT_TYPE          = " TEXT";
    //private static final String COMMA_SEP          = ",";

    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    private PegadasDBContract() {}

    public static abstract class Preguntas implements BaseColumns {
        public static final String PREGUNTAS = "preguntas";
        public static final String _ID = "id_pregunta";
        public static final String NUM_CASO = "num_caso";
        public static final String NUM_PREGUNTA = "num_pregunta";
        public static final String TIPO_PREGUNTA="tipo_pregunta";
        public static final String COD_IDIOMA = "cod_idioma";
        public static final String ENUNCIADO = "enunciado";
    }
    public static abstract class Respostas implements BaseColumns {
        public static final String RESPOSTAS ="respostas";
        public static final String _ID = "id_resposta";
        public static final String ID_PREGUNTA = "id_pregunta";
        public static final String RESPOSTA="resposta";
        public static final String E_RESPOSTA_CORRECTA = "e_resposta_correcta";
        public static final String EXPLICACION = "explicacion";
    }

}
