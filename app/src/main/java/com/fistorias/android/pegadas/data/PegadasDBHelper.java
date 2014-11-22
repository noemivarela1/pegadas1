package com.fistorias.android.pegadas.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.fistorias.android.pegadas.data.PegadasDBContract.Preguntas;
import com.fistorias.android.pegadas.data.PegadasDBContract.Respostas;
import com.fistorias.android.pegadas.Pregunta;

/**
 * Created by noemi on 22/11/2014.
 */
public class PegadasDBHelper extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Pegadas.db";
    public PegadasDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String  SQL_CREATE_PREGUNTAS_TABLE = "CREATE TABLE " + Preguntas.PREGUNTAS + "("
                + Preguntas._ID + " INTEGER PRIMARY KEY," + Preguntas.NUM_CASO + " INTEGER,"
                + Preguntas.NUM_PREGUNTA+ " INTEGER,"+Preguntas.COD_IDIOMA + " TEXT," + Preguntas.TIPO_PREGUNTA+" TEXT ,"
                + Preguntas.ENUNCIADO + " TEXT" + ")";
        String  SQL_CREATE_RESPOSTAS_TABLE = "CREATE TABLE " + Respostas.RESPOSTAS + "("
                + Respostas._ID + " INTEGER PRIMARY KEY," + Respostas.ID_PREGUNTA + " INTEGER,"
                + Respostas.RESPOSTA+ " TEXT,"+Respostas.E_RESPOSTA_CORRECTA + " INTEGER,"
                + Respostas.EXPLICACION + " TEXT" + ")";
        sqLiteDatabase.execSQL(SQL_CREATE_RESPOSTAS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {
        //sqLiteDatabase.execSQL(SQL_DELETE_ENTRIES);
        onCreate(sqLiteDatabase);

    }

    // Adding new contact
    void addPregunta(Pregunta pregunta) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Preguntas.NUM_CASO, pregunta.getNum_caso()); // Número de caso
        values.put(Preguntas.NUM_PREGUNTA, pregunta.getNum_pregunta()); // Número de pregunta en el caso
        values.put(Preguntas.COD_IDIOMA,pregunta.getCod_idioma());
        values.put(Preguntas.TIPO_PREGUNTA,pregunta.getTipo_pregunta());
        values.put(Preguntas.ENUNCIADO,pregunta.getEnunciado());
        // Inserting Row
        db.insert(Preguntas.PREGUNTAS, null, values);
        db.close(); // Closing database connection
    }
}
