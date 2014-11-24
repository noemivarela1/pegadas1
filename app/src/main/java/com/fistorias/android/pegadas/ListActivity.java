package com.fistorias.android.pegadas;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Actividad para la lista de preguntas
 */
public class ListActivity extends Activity {
    ListView listView ;
    int numMaxPreguntasCaso=10;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("ListActivity", "entra en onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.questions);

        listView = (ListView) findViewById(R.id.lista);

        String[] valores=new String[numMaxPreguntasCaso];
        for (int i=0;i<numMaxPreguntasCaso;i++){
            String pregunta=this.getResources().getString(R.string.pregunta);
            valores[i]=pregunta+" "+(i+1);
        }

        // Define a new Adapter
        // First parameter - Context
        // Second parameter - Layout for the row
        // Third parameter - ID of the TextView to which the data is written
        // Forth - the Array of data

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, valores);


        // Assign adapter to ListView
        listView.setAdapter(adapter);

        // ListView Item Click Listener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // ListView Clicked item index
                int itemPosition     = position;

                // ListView Clicked item value
                String  itemValue    = (String) listView.getItemAtPosition(position);

                // Show Alert
                Toast.makeText(getApplicationContext(),
                        "Position :" + itemPosition + "  ListItem : " + itemValue, Toast.LENGTH_LONG)
                        .show();

            }

        });


    }
}
