package com.example.myapplication.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.database.Repository;
import com.example.myapplication.entities.Term;

import java.util.Calendar;

public class updateTerm extends AppCompatActivity {

    Button btUpdate;
    EditText etTermName;
    EditText etStartDate;
    EditText etEndDate;
    DatePickerDialog.OnDateSetListener datePickerStart;
    DatePickerDialog.OnDateSetListener datePickerEnd;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_term);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorPrimary)));

        btUpdate = findViewById(R.id.btUpdateTerm);
        etTermName= findViewById(R.id.etTermName);
        etStartDate= findViewById(R.id.pkStartDate);
        etEndDate= findViewById(R.id.pkEndDate);
    }

    @Override
    protected void onStart() {
        super.onStart();

        int termID =getIntent().getIntExtra("termID",0);
        String terName = getIntent().getStringExtra("title");
        String start = getIntent().getStringExtra("startDate");
        String end = getIntent().getStringExtra("endDate");

        etTermName.setText(terName);
        etStartDate.setText(start);
        etEndDate.setText(end);

        etStartDate.setOnClickListener(v->
        {
            Calendar cal =  Calendar.getInstance();
            int year = cal.get(Calendar.YEAR);
            int month = cal.get(Calendar.MONTH);
            int data = cal.get(Calendar.DATE);

            DatePickerDialog dialog = new DatePickerDialog(updateTerm.this,
                    android.R.style.Theme_Holo_Light_Dialog_MinWidth, datePickerStart,
                    year, month, data);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();
        });
        datePickerStart = (view, year, month, dayOfMonth) -> {
            month++;
            String date = month + "/" + dayOfMonth + "/" + year;
            etStartDate.setText(date);
        };
        etEndDate.setOnClickListener(v->
        {
            Calendar cal =  Calendar.getInstance();
            int year = cal.get(Calendar.YEAR);
            int month = cal.get(Calendar.MONTH);
            int data = cal.get(Calendar.DATE);

            DatePickerDialog dialog = new DatePickerDialog(updateTerm.this,
                    android.R.style.Theme_Holo_Light_Dialog_MinWidth, datePickerEnd,
                    year, month, data);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();
        });
        datePickerEnd = (view, year, month, dayOfMonth) -> {
            month++;
            String date = month + "/" + dayOfMonth + "/" + year;
            etEndDate.setText(date);
        };

        btUpdate.setOnClickListener(v -> {
            String nameOfTerm = etTermName.getText().toString();
            String startOfTerm = etStartDate.getText().toString();
            String endOfTerm = etEndDate.getText().toString();
            Repository repository = new Repository(getApplication());
            repository.termDao.update(new Term(termID,nameOfTerm,startOfTerm,endOfTerm));
            this.finish();});
    }

    public boolean onOptionsItemSelected(MenuItem menuItem){
        if(menuItem.getItemId() ==  android.R.id.home){
            this.finish();
            Toast.makeText(this,"Back arrow",Toast.LENGTH_SHORT).show();
            return true;
        }
        return true;
    }
}