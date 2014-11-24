package com.fistorias.android.pegadas;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.fistorias.android.pegadas.data.PegadasDBHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            String resultadoJSON=cargaPreguntasJSON();
            Log.i("MainActivity","resultadoJSON:"+resultadoJSON);
            JSONObject jObject = new JSONObject(resultadoJSON);
            JSONObject caso=jObject.getJSONObject("caso");
            //Pregunta pregunta=(Pregunta)jObject.get("pregunta");
            int num_caso=caso.getInt("num_caso");
            Log.i("MainActivity", "num_caso:" + num_caso);
            //Log.i("MainActivity", "enunciado:" + caso);
            JSONObject pregunta = caso.getJSONObject("pregunta");
            int num_pregunta=pregunta.getInt("num_pregunta");
            String cod_idioma=pregunta.getString("cod_idioma");
            String tipo_pregunta=pregunta.getString("tipo_pregunta");

            String enunciado=pregunta.getString("enunciado");
            Log.i("MainActivity","enunciado:"+enunciado);
            ArrayList list = new ArrayList();
            JSONArray jArray = caso.getJSONArray("respostas");
            for (int i=0; i < jArray.length(); i++)
            {
                try {
                    JSONObject oneObject = jArray.getJSONObject(i);
                    // Pulling items from the array
                    String texto_resposta = oneObject.getString("texto_resposta");
                    int e_resposta_correcta = oneObject.getInt("e_resposta_correcta");
                    String explicacion=oneObject.getString("explicacion");

                    HashMap map = new HashMap();

                    map.put("texto_resposta", texto_resposta);
                    map.put("e_resposta_correcta", e_resposta_correcta);
                    map.put("explicacion", explicacion);

                    list.add(map);
                } catch (JSONException e) {
                    // Oops
                }
            }



            PegadasDBHelper mDbHelper = new PegadasDBHelper(this);

            Log.d("Insert: ", "Inserting ..");
            long rowIdPregunta=mDbHelper.addPregunta(new Pregunta(num_caso,num_pregunta,cod_idioma,tipo_pregunta,enunciado));
            for (int i=0; i < jArray.length(); i++){
                HashMap map=(HashMap) list.get(i);
                mDbHelper.addResposta(new Resposta(rowIdPregunta,(String)map.get("texto_resposta"),(Integer)map.get("e_resposta_correcta"),(String)map.get("explicacion")));
            }
            /*mDbHelper.addResposta(new Resposta(rowIdPregunta,jArray[0],0,"incorrecto1"));
            mDbHelper.addResposta(new Resposta(rowIdPregunta,"resposta2",0,"incorrecto2"));
            mDbHelper.addResposta(new Resposta(rowIdPregunta,"resposta3",0,"correcto"));
            mDbHelper.addResposta(new Resposta(rowIdPregunta,"resposta4",0,"incorrecto4"));*/
        }catch (JSONException jsone){
            jsone.printStackTrace();
        }
    }

    /*public void doQuestion (View view){
         Intent intent = new Intent(this, OptionListActivity.class);
        startActivity(intent);
    }*/

    public void showQuestions (View view){
        Intent intent = new Intent(this, ListActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public String cargaPreguntasJSON(){
        Log.i("MainActivity","entra en cargaPreguntaJSON");
        String json=null;

        try {
            InputStream fichero=getResources().openRawResource(R.raw.datos);//getAssets().open(R.raw.datos);
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
// Caso compuesto de pistas. Cada pista compuesta de preguntas y respuestas