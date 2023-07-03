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
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.database.Repository;
import com.example.myapplication.entities.ClassInTerm;

import java.util.Calendar;

public class updateClass extends AppCompatActivity {

    Button btUpdateClass;
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
    Button updateClass;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_class);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorPrimary)));


        btUpdateClass = findViewById(R.id.btUpdateAssessment2);
        className = findViewById(R.id.etCalssTitleUpdate);
        etStartDate = findViewById(R.id.etStartDateAssessment2);
        etEndDate = findViewById(R.id.etEndDateAssessment2);
        instructor = findViewById(R.id.etInstructorNameUpdate);
        phoneNumber = findViewById(R.id.etPhoneUpdate);
        etEmail = findViewById(R.id.etEmailUpdate);
        notes = findViewById(R.id.etNotesUpdate);
        rbCompleted = findViewById(R.id.rbCompletedUpdate);
        rbPlaneToTake = findViewById(R.id.rbObjectiveAssessment2);
        rbDrop = findViewById(R.id.rbDropedUpdate);
        rbProgress = findViewById(R.id.rbPerformanceAssessment2);


    }


    @Override
    protected void onStart() {
        super.onStart();

        int classID = getIntent().getIntExtra("classID",100);
        int termID = getIntent().getIntExtra("termID",0);
        String classN = getIntent().getStringExtra("title");
        String startDate = getIntent().getStringExtra("startDate");
        String endDate = getIntent().getStringExtra("endDate");
        String statuesClass = getIntent().getStringExtra("statues");
        String instructorR = getIntent().getStringExtra("instructorName");
        String phone = getIntent().getStringExtra("phoneNumber");
        String emailInstructor= getIntent().getStringExtra("email");
        String note = getIntent().getStringExtra("note");

        className.setText(classN);
        etStartDate.setText(startDate);
        etEndDate.setText(endDate);
        instructor.setText(instructorR);
        phoneNumber.setText(phone);
        etEmail.setText(emailInstructor);
        notes.setText(note);

        if(statuesClass.equals("Completed")){
            rbCompleted.setChecked(true);
        }else if(statuesClass.equals("In progress")){
            rbProgress.setChecked(true);
        }else if(statuesClass.equals("Dropped")){
            rbDrop.setChecked(true);
        }else if(statuesClass.equals("Plane to take")){
            rbPlaneToTake.setChecked(true);
        }
        etStartDate.setOnClickListener(v -> {
                    Calendar cal = Calendar.getInstance();
                    int year = cal.get(Calendar.YEAR);
                    int month = cal.get(Calendar.MONTH);
                    int data = cal.get(Calendar.DATE);

                    DatePickerDialog dialog = new DatePickerDialog(updateClass.this,
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

                    DatePickerDialog dialog = new DatePickerDialog(updateClass.this,
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


        btUpdateClass.setOnClickListener(v ->{
            String radioSelection = "";

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

            repository.classDao.update(new ClassInTerm(classID,clName,start,end, inName,phNumber, email,termID, radioSelection,optionalNotes));
            this.finish();});

    }

    public String getRadioButtonSelection(){

        rbCompleted = findViewById(R.id.rbCompletedUpdate);
        rbDrop = findViewById(R.id.rbDropedUpdate);
        rbProgress = findViewById(R.id.rbPerformanceAssessment2);
        rbPlaneToTake = findViewById(R.id.rbObjectiveAssessment2);

        if (rbCompleted.isChecked() == true){
            return "Completed";
        } else if (rbProgress.isChecked() == true){
            return  "In progress";
        } else if(rbDrop.isChecked() == true){
            return "Dropped";
        } else if (rbPlaneToTake.isChecked() == true) {
            return "Plane to take";
        }
        return null;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem){
        if(menuItem.getItemId() ==  android.R.id.home){
            this.finish();
            Toast.makeText(this,"Back arrow",Toast.LENGTH_SHORT).show();
            return true;
        }
        return true;
    }
    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        startActivity(new Intent(getApplicationContext(), viewTerm.class));
        finish();

    }
}