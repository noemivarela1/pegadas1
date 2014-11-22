package com.fistorias.android.pegadas.data;

import android.provider.BaseColumns;

/**
 * Created by noemi on 22/11/2014.
 */
public final class PegadasDBContract {
    public static final  int    DATABASE_VERSION   = 1;
    public static final  String DATABASE_NAME      = "pegadas.db";
    //private static final String TEXT_TYPE          = " TEXT";
    //private static final String COMMA_SEP          = ",";

    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    private PegadasDBContract() {}

    public static abstract class Preguntas implements BaseColumns {
        public static final String PREGUNTAS       = "preguntas";
        public static final String ID = "id";
        public static final String ID_CASO = "id_caso";
        public static final String ID_PREGUNTA = "id_pregunta";
        public static final String TIPO_PREGUNTA="tipo_pregunta";
        public static final String COD_IDIOMA = "cod_idioma";
        public static final String ENUNCIADO = "enunciado";



        /*public static final String CREATE_TABLE = "CREATE TABLE " +
                TABLE_NAME + " (" +
                _ID + " INTEGER PRIMARY KEY," +
                COLUMN_NAME_COL1 + TEXT_TYPE + COMMA_SEP +
                COLUMN_NAME_COL2 + TEXT_TYPE + COMMA_SEP +
                COLUMN_NAME_COL3 + TEXT_TYPE + COMMA_SEP + " )";
        public static final String DELETE_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;*/
    }
}
