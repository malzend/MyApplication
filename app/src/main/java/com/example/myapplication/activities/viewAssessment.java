package com.example.myapplication.activities;

import static com.example.myapplication.R.id.etAssessmentTypeView;
import static com.example.myapplication.R.id.startDate;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.database.Repository;
import com.example.myapplication.entities.Assessment;
import com.example.myapplication.entities.ClassInTerm;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class viewAssessment extends AppCompatActivity {

    EditText etTitle;
    EditText etStartDate;
    EditText eteEndDate;
    EditText etType;
    Button btUpdate;
    Button btDelete;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_assessment);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorPrimary)));


        etTitle = findViewById(R.id.etAssessmentTitleUpdate2);
        etStartDate = findViewById(R.id.etStartDateAssessment2);
        eteEndDate = findViewById(R.id.etEndDateAssessment2);
        etType = findViewById(R.id.etAssessmentTypeView);
        btUpdate = findViewById(R.id.btUpdateAssessment);
        btDelete = findViewById(R.id.btDeleteAssessment);
    }

    @Override
    protected void onStart() {
        super.onStart();
        int assessmentID = getIntent().getIntExtra("assessmentID",0);
        int classID = getIntent().getIntExtra("classID",0);
        String assessment = getIntent().getStringExtra("assessment");
        String start = getIntent().getStringExtra("start");
        String end = getIntent().getStringExtra("end");
        String type = getIntent().getStringExtra("type");

        etTitle.setText(assessment);
        etStartDate.setText(start);
        eteEndDate.setText(end);
        etType.setText(type);

        btUpdate.setOnClickListener(v->{
            Intent intent = new Intent(getApplicationContext(), updateAssessment.class);
            intent.putExtra("assessmentID",assessmentID);
            intent.putExtra("classID",classID);
            intent.putExtra("assessment",assessment);
            intent.putExtra("start",start);
            intent.putExtra("end",end);
            intent.putExtra("type",type);
            startActivity(intent);
        });
        btDelete.setOnClickListener(v ->{

            Repository repository = new Repository(getApplication());

            repository.assessmentDao.delete(new Assessment(assessmentID));
            this.finish();

        });

    }

    @Override
    protected void onResume() {
        super.onResume();

        Repository repository = new Repository(getApplication());
        List<Assessment> assessmentList = repository.assessmentDao.getAllAssessment();

        int classID = getIntent().getIntExtra("classID",0);
        int assessmentID = getIntent().getIntExtra("assessmentID",1);
        assessmentList.removeIf(v->v.getAssessmentID() != assessmentID);
        String assessment = assessmentList.get(0).getAssessment();
        String start = assessmentList.get(0).getStartDateAssessment();
        String end = assessmentList.get(0).getEndDateAssessment();
        String type = assessmentList.get(0).getAssessmentType();

        etTitle.setText(assessment);
        etStartDate.setText(start);
        eteEndDate.setText(end);
        etType.setText(type);

        btUpdate.setOnClickListener(v->{
            Intent intent = new Intent(getApplicationContext(), updateAssessment.class);
            intent.putExtra("assessmentID",assessmentID);
            intent.putExtra("classID",classID);
            intent.putExtra("assessment",assessment);
            intent.putExtra("start",start);
            intent.putExtra("end",end);
            intent.putExtra("type",type);
            startActivity(intent);
        });
        btDelete.setOnClickListener(v ->{

            repository.assessmentDao.delete(new Assessment(assessmentID));
            this.finish();

        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_view_class,menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem){
        if(menuItem.getItemId() ==  android.R.id.home){
            this.finish();
            Toast.makeText(this,"Back arrow",Toast.LENGTH_SHORT).show();
            return true;
        }
        if(menuItem.getItemId() == R.id.ntStart){

            String star = etStartDate.getText().toString();
            String assessmentName = etTitle.getText().toString();
            String myForMate = "MM/dd/yy";
            SimpleDateFormat sdf = new SimpleDateFormat(myForMate, Locale.US);

            Date myDate = null;
            try {
                myDate = sdf.parse(star);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }

            Long trigger = myDate.getTime() ;

            Intent intent= new Intent(viewAssessment.this, notification.class);
            intent.putExtra("key",  "Assessment" + assessmentName + " will start today");
            PendingIntent sender =PendingIntent.getBroadcast (viewAssessment.this, ++MainActivity.numAlert, intent,PendingIntent.FLAG_UPDATE_CURRENT);
            AlarmManager alarmManager =(AlarmManager)getSystemService(Context.ALARM_SERVICE);
            alarmManager.set(AlarmManager. RTC_WAKEUP, trigger, sender);
            return true;
        }
        else if(menuItem.getItemId() == R.id.ntEnd){

            String star = eteEndDate.getText().toString();
            String assessmentName = etTitle.getText().toString();
            String myForMate = "MM/dd/yy";
            SimpleDateFormat sdf = new SimpleDateFormat(myForMate, Locale.US);

            Date myDate = null;
            try {
                myDate = sdf.parse(star);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }

            Long trigger = myDate.getTime() ;

            Intent intent= new Intent(viewAssessment.this, notification.class);
            intent.putExtra("key",  "Assessment" + assessmentName + " will end today");
            PendingIntent sender =PendingIntent.getBroadcast (viewAssessment.this, ++MainActivity.numAlert, intent,PendingIntent.FLAG_UPDATE_CURRENT);
            AlarmManager alarmManager =(AlarmManager)getSystemService(Context.ALARM_SERVICE);

            alarmManager.set(AlarmManager. RTC_WAKEUP, trigger, sender);
            return true;
        }
        return true;
    }

}