package com.example.hebatsitubondo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class Kalender extends AppCompatActivity {

    DatePicker datePicker;
    TextView textView;
    Button tampilkan;
    int month;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kalender);

        Toolbar toolbark = (Toolbar)findViewById(R.id.toolbark);
        setSupportActionBar(toolbark);
        getSupportActionBar().setTitle("Kalender");

        datePicker=(DatePicker)findViewById(R.id.datePicker);
        textView=(TextView)findViewById(R.id.txt1);
        textView.setText("Display Date");
        tampilkan= (Button)findViewById(R.id.button);
        textView.setText(currentDate());
        tampilkan.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                textView.setText(currentDate());
            }
        });
    }

    public String currentDate(){
        StringBuilder mcurrentDate = new StringBuilder();
        month = datePicker.getMonth() +1;
        mcurrentDate.append("Date : " +month + "/" + datePicker.getDayOfMonth() + "/" + datePicker.getYear());
        return mcurrentDate.toString();
    }
}
