package com.fistorias.android.pegadas;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.fistorias.android.pegadas.data.PegadasDBHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by noemi on 29/11/2014.
 */
public class CargaPreguntasBD extends AsyncTask<Void, Void, Void> {
    private final Context mContext;

    public CargaPreguntasBD(Context context) {
        Log.i("CargaPreguntasBD", "Crea el objeto CargaPreguntasBD");
        mContext = context;
        //mForecastAdapter = forecastAdapter;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        //inserción de datos en la base de datos
        Log.i("CargaPreguntasBD","entra en doInBackground");
        PegadasDBHelper mDbHelper = new PegadasDBHelper(mContext);
        int num_preguntas=mDbHelper.getNumPreguntas();
        Log.i("MainActivity", "num_preguntas:" + num_preguntas);
        if (num_preguntas>0) {
            Log.i("MainActivity","Base de datos creada");
        } else {
            try {
                Log.i("MainActivity","Base de datos non creada");
                String resultadoJSON = cargaPreguntasJSON();
                //Log.i("MainActivity", "resultadoJSON:" + resultadoJSON);
                JSONObject jObject = new JSONObject(resultadoJSON);
                JSONObject caso = jObject.getJSONObject("caso");
                int num_caso = caso.getInt("num_caso");
                Log.i("MainActivity", "num_caso:" + num_caso);
                //Log.i("MainActivity", "enunciado:" + caso);
                JSONObject pregunta = caso.getJSONObject("pregunta");
                int num_pregunta = pregunta.getInt("num_pregunta");
                String cod_idioma = pregunta.getString("cod_idioma");
                String tipo_pregunta = pregunta.getString("tipo_pregunta");

                String enunciado = pregunta.getString("enunciado");
                //Log.i("MainActivity", "enunciado:" + enunciado);
                ArrayList list = new ArrayList();
                JSONArray jArray = caso.getJSONArray("respostas");
                for (int i = 0; i < jArray.length(); i++) {
                    try {
                        JSONObject oneObject = jArray.getJSONObject(i);
                        // Pulling items from the array
                        String texto_resposta = oneObject.getString("texto_resposta");
                        int e_resposta_correcta = oneObject.getInt("e_resposta_correcta");
                        String explicacion = oneObject.getString("explicacion");

                        HashMap map = new HashMap();

                        map.put("texto_resposta", texto_resposta);
                        map.put("e_resposta_correcta", e_resposta_correcta);
                        map.put("explicacion", explicacion);

                        list.add(map);
                    } catch (JSONException e) {
                        // Oops
                    }
                }


                //PegadasDBHelper mDbHelper = new PegadasDBHelper(this);

                Log.d("Insert: ", "Inserting ...in the database");
                long rowIdPregunta = mDbHelper.addPregunta(new Pregunta(num_caso, num_pregunta, cod_idioma, tipo_pregunta, enunciado));
                for (int i = 0; i < jArray.length(); i++) {
                    HashMap map = (HashMap) list.get(i);
                    mDbHelper.addResposta(new Resposta(rowIdPregunta, (String) map.get("texto_resposta"), (Integer) map.get("e_resposta_correcta"), (String) map.get("explicacion")));
                }
                //int num_preguntas = mDbHelper.getNumPreguntas();
                Log.i("MainActivity", "num_preguntas:" + num_preguntas);
                //Toast.makeText(getApplicationContext(),"número", Toast.LENGTH_SHORT).show();

            } catch (JSONException jsone) {
                jsone.printStackTrace();
            }
        }

        return null;
    }
    public String cargaPreguntasJSON(){
        Log.i("MainActivity","entra en cargaPreguntaJSON");
        String json=null;

        try {
            InputStream fichero=mContext.getResources().openRawResource(R.raw.datos);//getAssets().open(R.raw.datos);
            int size = fichero.available();
            byte[] buffer = new byte[size];
            fichero.read(buffer);
            fichero.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json;
    }
}
