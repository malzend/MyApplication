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
import com.example.myapplication.entities.ClassInTerm;

import java.util.Calendar;

public class addClass extends AppCompatActivity {

    Button btAddClass;
    EditText className;
    EditText etStartDate;
    EditText instructor;
    EditText phoneNumber;
    EditText etEmail;
    EditText etEndDate;
    EditText notes;
    RadioButton rbProgress;
    RadioButton rbDrop;
    RadioButton rbPlaneToTake;
    RadioButton rbCompleted;
    DatePickerDialog.OnDateSetListener datePickerStart;

    DatePickerDialog.OnDateSetListener datePickerEnd;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_class);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorPrimary)));

        btAddClass = findViewById(R.id.btUpdateAssessment2);
        etEndDate = findViewById(R.id.etEndDateAssessment2);
        className = findViewById(R.id.etAssessmentTitleUpdate2);
        etStartDate = findViewById(R.id.etStartDateAssessment2);
        etEndDate = findViewById(R.id.etEndDateAssessment2);
        instructor = findViewById(R.id.etInstructorNameUpdate);
        phoneNumber = findViewById(R.id.etPhoneUpdate);
        etEmail = findViewById(R.id.etEmailUpdate);
        notes = findViewById(R.id.etNotesInAdd);

    }

    @Override
    protected void onStart() {
        super.onStart();
        int termID = getIntent().getIntExtra("termID",100);



        etStartDate.setOnClickListener(v -> {
                    Calendar cal = Calendar.getInstance();
                    int year = cal.get(Calendar.YEAR);
                    int month = cal.get(Calendar.MONTH);
                    int data = cal.get(Calendar.DATE);

                    DatePickerDialog dialog = new DatePickerDialog(addClass.this,
                            android.R.style.Theme_Holo_Light_Dialog_MinWidth, datePickerStart,
                            year, month, data);
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    dialog.show();
                }

        );
        datePickerStart = (view, year, month, dayOfMonth) -> {
            month++;
            String date = month + "/" + dayOfMonth + "/" + year;
            etStartDate.setText(date);
        };

        etEndDate.setOnClickListener(v -> {
                    Calendar cal = Calendar.getInstance();
                    int year = cal.get(Calendar.YEAR);
                    int month = cal.get(Calendar.MONTH);
                    int data = cal.get(Calendar.DATE);

                    DatePickerDialog dialog = new DatePickerDialog(addClass.this,
                            android.R.style.Theme_Holo_Light_Dialog_MinWidth, datePickerEnd,
                            year, month, data);
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    dialog.show();
                }

        );

        datePickerEnd = (view, year, month, dayOfMonth) -> {
            month++;
            String date = month + "/" + dayOfMonth + "/" + year;
            etEndDate.setText(date);
        };

        btAddClass.setOnClickListener(v->
        {
            String radioSelection = "" ;

            String clName = className.getText().toString();
            String start =  etStartDate.getText().toString();
            String end = etEndDate.getText().toString();
            String inName = instructor.getText().toString();
            String phNumber = phoneNumber.getText().toString();
            String email = etEmail.getText().toString();
            String optionalNotes = notes.getText().toString();

            try {
                radioSelection =  getRadioButtonSelection();
            }
            catch (NullPointerException e){
                e.printStackTrace();
            }

            Repository repository = new Repository(getApplication());

            repository.classDao.insert(new ClassInTerm(clName,start,end, inName,phNumber, email,termID ,radioSelection,optionalNotes));
            this.finish();


        });
    }

    public String getRadioButtonSelection(){
        rbCompleted = findViewById(R.id.rbCompletedUpdate);
        rbDrop = findViewById(R.id.rbDropedUpdate);
        rbProgress = findViewById(R.id.rbPerformanceAssessment2);
        rbProgress = findViewById(R.id.rbObjectiveAssessment2);
        if (rbCompleted.isChecked() == true){
            return "Completed";
        } else if (rbProgress.isChecked() == true){
            return  "In progress";
        } else if(rbDrop.isChecked() == true){
            return "Dropped";
        } else if (rbPlaneToTake.isChecked() == true) {
            return "Plane to take";
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