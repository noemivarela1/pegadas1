package com.fistorias.android.pegadas;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.fistorias.android.pegadas.data.PegadasDBHelper;

/**
 * Created by noemi on 16/11/2014.
 */
public class OptionListActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.option_list);

        ActionBar actionBar = getActionBar();
        actionBar.setTitle(R.string.pregunta1);

        /*PegadasDBHelper mDbHelper = new PegadasDBHelper(this);

        Log.d("Insert: ", "Inserting ..");
        long rowIdPregunta=mDbHelper.addPregunta(new Pregunta(1,1,"GL","PR","PreguntaX"));
        mDbHelper.addResposta(new Resposta(rowIdPregunta,"resposta1",0,"incorrecto1"));
        mDbHelper.addResposta(new Resposta(rowIdPregunta,"resposta2",0,"incorrecto2"));
        mDbHelper.addResposta(new Resposta(rowIdPregunta,"resposta3",0,"correcto"));
        mDbHelper.addResposta(new Resposta(rowIdPregunta,"resposta4",0,"incorrecto4"));*/
        PegadasDBHelper mDbHelper = new PegadasDBHelper(this);
        Cursor cursor=mDbHelper.getPregunta(1,1);

        //Nos aseguramos de que existe al menos un registro
        String enunciado="";
        int id_pregunta=0;
        if (cursor.moveToFirst()) {
            //Recorremos el cursor hasta que no haya más registros
            do {
                enunciado= cursor.getString(3);
                id_pregunta=cursor.getInt(0);
                Log.i("OptionListActivity","enunciado:"+enunciado);
            } while(cursor.moveToNext());
        }
        cursor.close();

        TextView preguntaView = (TextView) findViewById(R.id.textoPregunta);
        preguntaView.setText(enunciado);
        Cursor cursor1=mDbHelper.getRespuestas(id_pregunta);
        int capacidade=cursor1.getCount();
        final TextView[] textView=new TextView[capacidade];
        String resposta="";
        //int id_pregunta=0;
        int j=0;
        if (cursor1.moveToFirst()) {
            //Recorremos el cursor hasta que no haya más registros
            do {
                j=j+1;
                resposta= cursor1.getString(0);
                Log.i("OptionListActivity","resposta:"+resposta);
                String idRespostas="textoResposta"+String.valueOf(j);
                int id = this.getResources().getIdentifier(idRespostas, "id", this.getPackageName());
                textView[j-1]=(TextView)findViewById(id);
                textView[j-1].setText(resposta);

                //textView[j-1].setTag(1,j-1);
                int correcta=cursor1.getInt(1);
                String explicacion=cursor1.getString(2);
                String[] parametros={String.valueOf(j),String.valueOf(correcta),explicacion,resposta};
                textView[j-1].setTag(parametros);
                textView[j-1].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.i("OptionListActivity","click en un textView");

                        Context context = getApplicationContext();
                        String[] parametros=(String[])v.getTag();
                        //Integer integer1=(Integer)v.getTag(1);
                        //int i=integer1.intValue();
                        //parametros[0]++;
                        //i++;
                        Log.i("OptionListActivity","i:"+parametros[0]);
                        //Integer correcta1=(Integer)v.getTag(2);
                        //String solucion="pregunta1Solucion"+i;
                        //Log.i("OptionListActivity","solucion:"+solucion);
                        //int idSol=context.getResources().getIdentifier(solucion, "string", context.getPackageName());
                        //Log.i("OptionListActivity","idSol:"+idSol);
                        //CharSequence sol=getText(idSol);

                        Log.i("OptionListActivity","correcta1:"+parametros[1]);
                        String text=parametros[2];
                        if (parametros[1].equals("1")){
                            text =getResources().getString(R.string.correcto)+"\n\n"+text;
                        }else{
                            text=getResources().getString(R.string.incorrecto)+"\n\n"+text;
                        }
                        //int duration = Toast.LENGTH_LONG;
                        //Toast toast=Toast.makeText(context,text,duration);
                        //toast.show();

                        // 1. Instantiate an AlertDialog.Builder with its constructor
                        AlertDialog.Builder builder = new AlertDialog.Builder(OptionListActivity.this);

                        builder.setNeutralButton(R.string.aceptar, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Log.i("OptionListActivity","entra en onClick de Dialog");
                            }
                        });


                        // 2. Chain together various setter methods to set the dialog characteristics
                        builder.setMessage(text);
                                builder.setTitle(parametros[3]);
                            /*dialog.setButton(R.string.aceptar, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // here you can add functions
                            }
                        });*/

                        // 3. Get the AlertDialog from create()
                        Log.i("OptionListActivity","crea el dialogo");
                        AlertDialog dialog = builder.create();
                        //dialog.show();
                        dialog.show();

                    }
                });

            } while(cursor1.moveToNext());
        }
        cursor1.close();


        //preguntaView.setText(R.string.pregunta1Enunciado);

        /*Log.i("OptionListActivity", "utiliza un array");


        int capacidade=4;
        String[] idRespostas=new String[capacidade];
        String[] respostas=new String[capacidade];
        final TextView[] textView=new TextView[capacidade];
        for (int j=1;j<=capacidade;j++){
            idRespostas[j-1]="textoResposta"+String.valueOf(j);
            respostas[j-1]="pregunta1Resposta"+String.valueOf(j);
            Log.i("OptionListActivity","idRespostas[j]"+idRespostas[j-1]);
            Log.i("OptionListActivity","respostas[j]"+respostas[j-1]);
            int id = this.getResources().getIdentifier(idRespostas[j-1], "id", this.getPackageName());
            Log.i("OptionListActivity","id:"+id);
            int idResp=this.getResources().getIdentifier(respostas[j-1], "string", this.getPackageName());
            Log.i("OptionListActivity","idResp:"+idResp);
            textView[j-1]=(TextView)findViewById(id);
            textView[j-1].setText(idResp);
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
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast=Toast.makeText(context,text,duration);
                    toast.show();
                }
            });
        }*/
        actionBar.setDisplayShowTitleEnabled(true);
    }
}
