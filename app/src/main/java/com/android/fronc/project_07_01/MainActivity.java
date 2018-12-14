package com.android.fronc.project_07_01;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private static final String SPName = "P0701";
    private static final String SPCounter = "SPCounter";

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private TextView tvCounter;
    private ListView lvDateClickList;
    private ArrayList<String> dateClickListItems;
    private ArrayAdapter<String> adapter;
    private Date date;
    private SimpleDateFormat dateFormat;

    private int counter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {
        lvDateClickList = (ListView) findViewById(R.id.dateClickList);
        tvCounter = (TextView) findViewById(R.id.sp_counter);
        sharedPreferences = getSharedPreferences(SPName, Context.MODE_PRIVATE);
        dateClickListItems = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(
                getApplicationContext(), android.R.layout.simple_list_item_1, dateClickListItems
        );
        lvDateClickList.setAdapter(adapter);
        counter = 0;

        if (sharedPreferences.contains(SPCounter)) {
            counter = sharedPreferences.getInt(SPCounter, 0);
            tvCounter.setText(String.valueOf(counter));

            for (int i = 1; i <= counter; i++) {
                dateClickListItems.add(sharedPreferences.getString(String.valueOf(i), ""));
            }
        }
    }

    public void add(View view) {
        counter++;
        date = new Date();
        dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        editor = sharedPreferences.edit();
        tvCounter.setText(String.valueOf(counter));
        dateClickListItems.add(dateFormat.format(date));
        adapter.notifyDataSetChanged();
        editor.putString(String.valueOf(counter), dateFormat.format(date));
        editor.putInt(SPCounter, counter);
        editor.apply();
        editor.commit();
    }

    public void delete(View view) {
        tvCounter.setText(String.valueOf(0));
        counter = 0;
        dateClickListItems.clear();
        adapter.notifyDataSetChanged();
        editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        editor.commit();
    }
}
