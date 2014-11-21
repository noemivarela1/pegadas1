package com.fistorias.android.pegadas;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by noemi on 16/11/2014.
 */
public class OptionListActivity extends Activity {
    static final String[] respostas = new String[] {
            "resposta1", "resposta2", "resposta3", "resposta4"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.option_list);

        ActionBar actionBar = getActionBar();
        actionBar.setTitle(R.string.pregunta1);

        TextView preguntaView = (TextView) findViewById(R.id.textoPregunta);
        preguntaView.setText(R.string.pregunta1Enunciado);

        Log.i("OptionListActivity", "entra en el adapter del grid");

        int capacidade=4;
        String[] idRespostas=new String[capacidade];
        String[] respostas=new String[capacidade];
        final TextView[] textView=new TextView[capacidade];
        for (int j=1;j<=capacidade;j++){
            idRespostas[j-1]="textoResposta"+String.valueOf(j);
            respostas[j-1]="pregunta1Resposta"+String.valueOf(j);
            Log.i("OptionListActivity","idRespostas[j]"+idRespostas[j-1]);
            Log.i("OptionListActivity","respostas[j]"+respostas[j-1]);
            //int id = this.getResources().getIdentifier(idRespostas[j-1], "id", this.getPackageName());
            int id = this.getResources().getIdentifier(idRespostas[j-1], "id", this.getPackageName());
            Log.i("OptionListActivity","id:"+id);
            int idResp=this.getResources().getIdentifier(respostas[j-1], "string", this.getPackageName());
            Log.i("OptionListActivity","idResp:"+idResp);
            //int id=getResId(respostas[j], getApplicationContext(), String.class);
            textView[j-1]=(TextView)findViewById(id);
            textView[j-1].setText(idResp);
            //textView[j-1].setText(respostas[j-1]);
            //textView[j-1].setOnClickListener(textClick);
            textView[j-1].setTag(j-1);

            textView[j-1].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i("OptionListActivity","click en un textView");

                    Context context = getApplicationContext();

                    Integer integer1=(Integer)v.getTag();
                    int i=integer1.intValue();
                    i++;
                    Log.i("OptionListActivity","i:"+i);
                    String solucion="pregunta1Solucion"+i;
                    Log.i("OptionListActivity","solucion:"+solucion);
                    int idSol=context.getResources().getIdentifier(solucion, "string", context.getPackageName());
                    Log.i("OptionListActivity","idSol:"+idSol);
                    CharSequence sol=getText(idSol);
                    Log.i("OptionListActivity","sol:"+sol);
                    CharSequence text;
                    if (sol.toString().equals("1")){
                        text ="Correcto!";
                    }else{
                        text="Incorrecto!";
                    }
                    //CharSequence text = textView[i].getText().toString();
                    //CharSequence text="Prueba de toast";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast=Toast.makeText(context,text,duration);
                    toast.show();


                    //Toast.makeText(getApplicationContext(), "" + textView[v.getId()].getText(), Toast.LENGTH_SHORT).show();


                }
            });
            //hButtonStop.setOnClickListener(mButtonStopListener);
        }

/*
        TextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Toast.makeText(HelloGridView.this, "" + position, Toast.LENGTH_SHORT).show();
            }
        });*/

        /*
        textView[j].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"textview person clicked",Toast.LENGTH_LONG).show();
            }
        });*/

        actionBar.setDisplayShowTitleEnabled(true);



    }
    //View.OnClickListener mButtonStartListener = new OnClickListener() {
    /*View.OnClickListener textClick = new View.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            v.setBackgroundColor(-16711681);
            Toast.makeText(getApplicationContext(), ((TextView)v).getText().toString(), Toast.LENGTH_SHORT);
        }
    };*/
    /*
    public static int getResId(String variableName, Context context, Class<?> c) {

        try {
            Field idField = c.getDeclaredField(variableName);
            return idField.getInt(idField);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }*/
}
