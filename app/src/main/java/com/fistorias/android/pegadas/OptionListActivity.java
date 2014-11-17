package com.fistorias.android.pegadas;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;

/**
 * Created by noemi on 16/11/2014.
 */
public class OptionListActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.option_list);

        ActionBar actionBar = getActionBar();
        actionBar.setTitle("Pregunta 1");
        actionBar.setDisplayShowTitleEnabled(true);



    }
}
