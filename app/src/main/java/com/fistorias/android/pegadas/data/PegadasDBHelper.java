package com.fistorias.android.pegadas.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.fistorias.android.pegadas.Pregunta;
import com.fistorias.android.pegadas.Resposta;
import com.fistorias.android.pegadas.data.PegadasDBContract.Preguntas;
import com.fistorias.android.pegadas.data.PegadasDBContract.Respostas;

/**
 * Created by noemi on 22/11/2014.
 */
public class PegadasDBHelper extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Pegadas.db";

    public PegadasDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.i("PegadasDBHelper","llamó al constructor");
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.i("PegadasDBHelper", "entra en onCreate");
        String  SQL_CREATE_PREGUNTAS_TABLE = "CREATE TABLE " + Preguntas.PREGUNTAS + "("
                + Preguntas._ID + " INTEGER PRIMARY KEY," + Preguntas.NUM_CASO + " INTEGER,"
                + Preguntas.NUM_PREGUNTA+ " INTEGER,"+Preguntas.COD_IDIOMA + " TEXT," + Preguntas.TIPO_PREGUNTA+" TEXT ,"
                + Preguntas.ENUNCIADO + " TEXT" + ")";
        String  SQL_CREATE_RESPOSTAS_TABLE = "CREATE TABLE " + Respostas.RESPOSTAS + "("
                + Respostas._ID + " INTEGER PRIMARY KEY," + Respostas.ID_PREGUNTA + " INTEGER,"
                + Respostas.RESPOSTA+ " TEXT,"+Respostas.E_RESPOSTA_CORRECTA + " INTEGER,"
                + Respostas.EXPLICACION + " TEXT" + ")";
        sqLiteDatabase.execSQL(SQL_CREATE_PREGUNTAS_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_RESPOSTAS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {
        //sqLiteDatabase.execSQL(SQL_DELETE_ENTRIES);
        onCreate(sqLiteDatabase);

    }

    // Añadir nueva pregunta
    public long addPregunta(Pregunta pregunta) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Preguntas.NUM_CASO, pregunta.getNum_caso()); // Número de caso
        values.put(Preguntas.NUM_PREGUNTA, pregunta.getNum_pregunta()); // Número de pregunta en el caso
        values.put(Preguntas.COD_IDIOMA,pregunta.getCod_idioma());
        values.put(Preguntas.TIPO_PREGUNTA,pregunta.getTipo_pregunta());
        values.put(Preguntas.ENUNCIADO,pregunta.getEnunciado());
        // Inserting Row
        long newRowId;
        newRowId=db.insert(Preguntas.PREGUNTAS, null, values);
        db.close(); // Closing database connection
        return newRowId;
    }
    // Añadir nueva pregunta
    public void addResposta(Resposta resposta) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Respostas.ID_PREGUNTA, resposta.getId_pregunta());
        values.put(Respostas.RESPOSTA, resposta.getResposta());
        values.put(Respostas.E_RESPOSTA_CORRECTA,resposta.getE_resposta_correcta());
        values.put(Respostas.EXPLICACION,resposta.getExplicacion());
        // Inserting Row
        long newRowId;
        newRowId=db.insert(Respostas.RESPOSTAS, null, values);
        db.close(); // Closing database connection
        //return newRowId;
    }
    public Cursor getPregunta(int num_caso,int num_pregunta){
        SQLiteDatabase db=this.getReadableDatabase();
        String[] campos = new String[] {Preguntas._ID, Preguntas.NUM_CASO,Preguntas.NUM_PREGUNTA,Preguntas.ENUNCIADO};
        String[] args = new String[] {String.valueOf(num_caso), String.valueOf(num_pregunta)};

        Cursor c = db.query(Preguntas.PREGUNTAS, campos, "num_caso=? and num_pregunta=?", args, null, null, null);
        return c;
    }
    public Cursor getRespuestas(int id_pregunta){
        SQLiteDatabase db=this.getReadableDatabase();
        String[] campos = new String[] {Respostas.RESPOSTA, Respostas.E_RESPOSTA_CORRECTA,Respostas.EXPLICACION};
        String[] args = new String[] {String.valueOf(id_pregunta)};

        Cursor c = db.query(Respostas.RESPOSTAS, campos, "id_pregunta=?", args, null, null, null);
        return c;
    }
}
