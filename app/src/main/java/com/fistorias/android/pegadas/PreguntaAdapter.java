package com.fistorias.android.pegadas;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by noemi on 20/11/2014.
 */
public class PreguntaAdapter extends BaseAdapter {
    private Context mContext;
    public PreguntaAdapter(Context c) {
        mContext = c;
        Log.i("PreguntaAdapter","constructor del gridadapter");
    }
    public Object getItem(int position) {
        return null;
    }
    public int getCount() {
        return 1;
        //return mThumbIds.length;
    }
    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {

        View view;
        Log.i("PreguntaAdapter", "estoy en getView en el preguntaAdapter");
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {  // if it's not recycled, initialize some attributes
            Log.i("PreguntaAdapter","converView es null");
            // get layout from question_list.xml
            view = new View(mContext);
            view = (View) inflater.inflate(R.layout.question_list, null);

        } else {
            view = (View) convertView;
        }

        /*imageView.setImageResource(mThumbIds[position]);
        return imageView;*/
        // set values into textviews
        TextView preguntaView = (TextView) view.findViewById(R.id.textoPregunta);
        preguntaView.setText(R.string.pregunta1Enunciado);

        TextView textView = (TextView) view.findViewById(R.id.textoResposta1);
        textView.setText(R.string.pregunta1Resposta1);

        TextView resposta2View=(TextView)view.findViewById(R.id.textoResposta2);
        resposta2View.setText(R.string.pregunta1Resposta2);

        TextView resposta3View=(TextView)view.findViewById(R.id.textoResposta3);
        resposta3View.setText(R.string.pregunta1Resposta3);

        TextView resposta4View=(TextView)view.findViewById(R.id.textoResposta4);
        resposta4View.setText(R.string.pregunta1Resposta4);

        return view;
    }

}
/*
public class ImageAdapter extends BaseAdapter {
    private Context mContext;

    public ImageAdapter(Context c) {
        mContext = c;
    }

    public int getCount() {
        return mThumbIds.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {  // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
        } else {
            imageView = (ImageView) convertView;
        }

        imageView.setImageResource(mThumbIds[position]);
        return imageView;
    }

    // references to our images
  /*  private Integer[] mThumbIds = {
            R.drawable.sample_2, R.drawable.sample_3,
            R.drawable.sample_4, R.drawable.sample_5,
            R.drawable.sample_6, R.drawable.sample_7,
            R.drawable.sample_0, R.drawable.sample_1,
            R.drawable.sample_2, R.drawable.sample_3,
            R.drawable.sample_4, R.drawable.sample_5,
            R.drawable.sample_6, R.drawable.sample_7,
            R.drawable.sample_0, R.drawable.sample_1,
            R.drawable.sample_2, R.drawable.sample_3,
            R.drawable.sample_4, R.drawable.sample_5,
            R.drawable.sample_6, R.drawable.sample_7
    };
}*/
