package com.fistorias.android.pegadas;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.fistorias.android.pegadas.data.PegadasDBHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;


public class MainActivity extends Activity {
    int numCasos=10;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GridView gridView = (GridView) findViewById(R.id.gridview);

        String[] valores=new String[numCasos];
        for (int i=0;i<numCasos;i++){
            String pregunta=this.getResources().getString(R.string.caso);
            valores[i]=pregunta+" "+(i+1);
        }

        // Define a new Adapter
        // First parameter - Context
        // Second parameter - Layout for the row
        // Third parameter - ID of the TextView to which the data is written
        // Forth - the Array of data

        ArrayAdapter<String> adapter =new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1,valores) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView txt=(TextView)view.findViewById(android.R.id.text1);
                txt.setGravity(Gravity.CENTER);

                int[] colores = getResources().getIntArray(R.array.azules);

                Log.i("MainActivity","colores[0]"+colores);
                int color=0x00FFFF;

                if (position%2==1 ){
                    color=colores[(position-1)/2];
                }else{
                    color=colores[(position)/2];
                }

                view.setBackgroundColor(color);
                int numFillo=parent.getChildCount();
                view.setTag(numFillo+1);
                Log.i("MainActivity","altura:"+view.getMinimumHeight());
                Log.i("MainActivity","número de hijos:"+parent.getChildCount());
                //int numFilas=(parent.getChildCount()+1)/2;
                int numFilas=5;
                Log.i("MainActivity","número de filas:"+numFilas);
                ViewGroup.LayoutParams params = view.getLayoutParams();
                params.height = (parent.getHeight()/numFilas) ;//-params.rightMargin - params.leftMargin;
                view.requestLayout();

                return view;
            }
        };

        // Assign adapter to ListView
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Toast.makeText(getApplicationContext(), ((TextView) v).getText(), Toast.LENGTH_SHORT).show();
                comezarCaso(v);
            }
        });


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
        }catch (JSONException jsone){
            jsone.printStackTrace();
        }
    }

    public void comezarCaso(final View view){
        // 1. Instantiate an AlertDialog.Builder with its constructor
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

        builder.setPositiveButton(R.string.cancelar, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Log.i("MainActivity","entra en comezar caso onClick botón positivo");
            }
        });

        builder.setNegativeButton(R.string.aceptar, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Log.i("MainActivity","entra en comezar caso onClick botón negativo id:"+id);
                Intent intent = new Intent(getApplicationContext(), OptionListActivity.class);
                view.getTag();
                intent.putExtra("num_pregunta",String.valueOf(view.getTag()));
                Log.i("MainActivity", "view.getTag():" + view.getTag());
                startActivity(intent);

            }
        });

        String text=getResources().getString(R.string.comezar);
        // 2. Chain together various setter methods to set the dialog characteristics
        builder.setMessage(text);
        String titulo=getResources().getString(R.string.comezar_title) +  " "+view.getTag();
        builder.setTitle(titulo);
        // 3. Get the AlertDialog from create()
        Log.i("MainActivity","crea el dialogo");
        AlertDialog dialog = builder.create();
        dialog.show();
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