package com.example.myapplication.activities;

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

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.database.Repository;
import com.example.myapplication.entities.Term;

import java.util.Calendar;

public class addTerm extends AppCompatActivity {
    EditText etStartDate;
    EditText etEndDate;
    EditText termName;
    DatePickerDialog.OnDateSetListener datePickerStart;
    DatePickerDialog.OnDateSetListener datePickerEnd;
    Button btAddT;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_term);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorPrimary)));

        etStartDate = findViewById(R.id.pkStartDate);
        etEndDate = findViewById(R.id.pkEndDate);
        btAddT = findViewById(R.id.btUpdateTerm);
        termName = findViewById(R.id.etTermName);



    }

    @Override
    protected void onStart() {
        super.onStart();
        etStartDate.setOnClickListener(v -> {
                    Calendar  cal= Calendar.getInstance();
                    int year = cal.get(Calendar.YEAR);
                    int month = cal.get(Calendar.MONTH);
                    int data = cal.get(Calendar.DATE);

                    DatePickerDialog dialog = new DatePickerDialog(addTerm.this,
                            android.R.style.Theme_Holo_Light_Dialog_MinWidth, datePickerStart,
                            year,month,data);
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    dialog.show();
                }

        );
        datePickerStart = (view, year, month, dayOfMonth) -> {
            month++;
            String date = month  + "/" + dayOfMonth + "/" + year;
            etStartDate.setText(date);
        };

        etEndDate.setOnClickListener(v -> {
                    Calendar  cal= Calendar.getInstance();
                    int year = cal.get(Calendar.YEAR);
                    int month = cal.get(Calendar.MONTH);
                    int data = cal.get(Calendar.DATE);

                    DatePickerDialog dialog = new DatePickerDialog(addTerm.this,
                            android.R.style.Theme_Holo_Light_Dialog_MinWidth, datePickerEnd,
                            year,month,data);
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    dialog.show();
                }

        );
        datePickerEnd = (view, year, month, dayOfMonth) -> {
            month++;
            String date = month  + "/" + dayOfMonth + "/" + year;
            etEndDate.setText(date);
        };

        btAddT.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(),
                    termList.class);

            String name = termName.getText().toString().trim();
            String dateStart = etStartDate.getText().toString().trim();
            String dateEnd = etEndDate.getText().toString().trim();
            Repository repository = new Repository(getApplication());
            repository.termDao.insert(new Term(name,dateStart,dateEnd));
            startActivity(intent);

        });
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
