package com.android.fronc.project_07_01;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String SPName = "P0701";
    private static final String SPCounter = "SPCounter";

    private SharedPreferences sharedPreferences;
    private TextView tvCounter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvCounter = (TextView) findViewById(R.id.sp_counter);
        sharedPreferences = getSharedPreferences(SPName, Context.MODE_PRIVATE);

        if (sharedPreferences.contains(SPCounter)) {
            tvCounter.setText(sharedPreferences.getString(SPCounter, ""));
        }
    }

    private void init() {
    }

    public void add(View view) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(SPCounter, "test");
        editor.commit();
    }

    public void delete(View view) {
    }
}
