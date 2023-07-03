package com.example.myapplication.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.database.Repository;
import com.example.myapplication.entities.Assessment;

import java.util.Calendar;

public class addAssessment extends AppCompatActivity {


    EditText assessmentTitle;
    EditText startDate;
    EditText endDate;
    RadioButton objectiveAssessment;
    RadioButton performanceAssessment;
    Button addAssessment;
    DatePickerDialog.OnDateSetListener datePickerStart;

    DatePickerDialog.OnDateSetListener datePickerEnd;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_assessment);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorPrimary)));

        assessmentTitle = findViewById(R.id.etAssessmentTitleUpdate2);
        startDate = findViewById(R.id.etStartDateAssessment2);
        endDate = findViewById(R.id.etEndDateAssessment2);
        objectiveAssessment = findViewById(R.id.rbObjectiveAssessment2);
        performanceAssessment = findViewById(R.id.rbPerformanceAssessment2);
        addAssessment= findViewById(R.id.btUpdateAssessment2);

    }

    @Override
    protected void onStart() {
        super.onStart();

        startDate.setOnClickListener(v -> {
                    Calendar cal = Calendar.getInstance();
                    int year = cal.get(Calendar.YEAR);
                    int month = cal.get(Calendar.MONTH);
                    int data = cal.get(Calendar.DATE);

                    DatePickerDialog dialog = new DatePickerDialog(addAssessment.this,
                            android.R.style.Theme_Holo_Light_Dialog_MinWidth, datePickerStart,
                            year, month, data);
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    dialog.show();
                }

        );
        datePickerStart = (view, year, month, dayOfMonth) -> {
            month++;
            String date = month + "/" + dayOfMonth + "/" + year;
            startDate.setText(date);
        };

        endDate.setOnClickListener(v -> {
                    Calendar cal = Calendar.getInstance();
                    int year = cal.get(Calendar.YEAR);
                    int month = cal.get(Calendar.MONTH);
                    int data = cal.get(Calendar.DATE);

                    DatePickerDialog dialog = new DatePickerDialog(addAssessment.this,
                            android.R.style.Theme_Holo_Light_Dialog_MinWidth, datePickerEnd,
                            year, month, data);
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    dialog.show();
                }

        );

        datePickerEnd = (view, year, month, dayOfMonth) -> {
            month++;
            String date = month + "/" + dayOfMonth + "/" + year;
            endDate.setText(date);
        };
        addAssessment.setOnClickListener(v -> {

            String assessmentName = assessmentTitle.getText().toString();
            String start = startDate.getText().toString();
            String end = endDate.getText().toString();
            String assessmentType = getAssessmentType();
            int classID = getIntent().getIntExtra("classID",0);


            Repository repository= new Repository(getApplication());

            repository.assessmentDao.insert(new Assessment(assessmentName,start,end,assessmentType,classID));
            this.finish();
        });


    }

    public String getAssessmentType(){
        if(objectiveAssessment.isChecked()){
            return "Objective assessment";
        }else if(performanceAssessment.isChecked()){
            return "Performance assessment";

        }
        return "";
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