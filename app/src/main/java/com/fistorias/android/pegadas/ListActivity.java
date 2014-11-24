package com.fistorias.android.pegadas;

import android.app.Activity;
import android.os.Bundle;

/**
 * Actividad para la lista de preguntas
 */
public class ListActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.questions);
    }
}
