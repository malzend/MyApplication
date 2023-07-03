package com.example.myapplication.activities;
import static java.util.Locale.*;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.ColorDrawable;
import android.net.MailTo;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.database.Repository;
import com.example.myapplication.entities.Assessment;
import com.example.myapplication.entities.ClassInTerm;
import com.example.myapplication.entities.Term;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class viewClass extends AppCompatActivity {
    private static final int REQUEST_SEND_SMS =0 ;

    EditText clName;
    EditText startDate;
    EditText endDate;
    EditText instructorName;
    EditText phoneNumber;
    EditText email;
    EditText note;
    EditText statues;
    Button btAddAssessment;
    Button btUpdateClass;
    Button btSms;
    Button btEmail;
    Button btDeleteClass;
    ListView lsAssessment;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_class);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorPrimary)));

        clName = findViewById(R.id.etClassTittle);
        startDate = findViewById(R.id.etClassStartDate);
        endDate = findViewById(R.id.etClassEndDate);
        btAddAssessment = findViewById(R.id.bt_add_Assessment);
        btUpdateClass = findViewById(R.id.bt_update_class);
        btDeleteClass = findViewById(R.id.bt_delete_class);
        instructorName = findViewById(R.id.etInstructorName);
        phoneNumber = findViewById(R.id.etPhoneNumber);
        email = findViewById(R.id.etaEmailViewClass);
        note = findViewById(R.id.etNotes);
        statues = findViewById(R.id.etClassStatues);
        lsAssessment = findViewById(R.id.lsAssessment);
        btEmail= findViewById(R.id.email);
        btSms = findViewById(R.id.sms);

        if ((ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_SMS) +
                ContextCompat.checkSelfPermission(this, android.Manifest.permission.SEND_SMS))
                != PackageManager.PERMISSION_GRANTED) {



            if (ActivityCompat.shouldShowRequestPermissionRationale(this,"Manifest.permission.READ_SMS") ||
                    ActivityCompat.shouldShowRequestPermissionRationale(this,"Manifest.permission.READ_SMS")) {



            } else {

                ActivityCompat.requestPermissions(this,
                        new String[]{"Manifest.permission.READ_SMS, Manifest.permission.SEND_SMS"},
                        REQUEST_SEND_SMS);

            }
        }

else {}}



    @Override
    protected void onStart() {

        super.onStart();

        int classID = getIntent().getIntExtra("classID",100);
        int termID = getIntent().getIntExtra("termID",10);
        String className = getIntent().getStringExtra("title");
        String start = getIntent().getStringExtra("startDate");
        String end = getIntent().getStringExtra("endDate");
        String statuesClass = getIntent().getStringExtra("statues");
        String instructor = getIntent().getStringExtra("instructorName");
        String phone = getIntent().getStringExtra("phoneNumber");
        String emailInstructor= getIntent().getStringExtra("email");
        String notes = getIntent().getStringExtra("notes");

        clName.setText(className);
        startDate.setText(start);
        endDate.setText(end);
        instructorName.setText(instructor);
        phoneNumber.setText(phone);
        email.setText(emailInstructor);
        statues.setText(statuesClass);
        note.setText(notes);
        Repository repository = new Repository(getApplication());


        List<Assessment> assessmentList;

        assessmentList = repository.assessmentDao.getAllAssessment();
        assessmentList.removeIf(v-> v.getClassID() != classID);

        ArrayList<HashMap<String, Object>> List = new ArrayList<>();


        for(int i = 0; assessmentList.size() > i;i++){
            HashMap<String,Object> hashMap = new HashMap<>();
            hashMap.put("Title", assessmentList.get(i).getAssessment());
            hashMap.put("StartDate", assessmentList.get(i).getStartDateAssessment());
            hashMap.put("EndDate",assessmentList.get(i).getEndDateAssessment());

            List.add(hashMap);
        }

        String[] from = {"Title","StartDate","EndDate"};

        int[] to ={R.id.txList1,R.id.txList2,R.id.txList3};

        SimpleAdapter simpleAdapter = new SimpleAdapter(getApplicationContext(), List, R.layout.double_list_item, from, to);

        lsAssessment.setAdapter(simpleAdapter);


        lsAssessment.setOnItemClickListener((parent, view, position, id) -> {

            int i = (int) id;
            Intent intent = new Intent(getApplicationContext(), viewAssessment.class);
            intent.putExtra("classID", classID);
            intent.putExtra("assessmentID",assessmentList.get(i).getAssessmentID());
            intent.putExtra("assessment",assessmentList.get(i).getAssessment());
            intent.putExtra("start",assessmentList.get(i).getStartDateAssessment());
            intent.putExtra("end",assessmentList.get(i).getEndDateAssessment());
            intent.putExtra("type",assessmentList.get(i).getAssessmentType());
            startActivity(intent);
        });
        btAddAssessment.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), addAssessment.class);
            intent.putExtra("classID",classID);
            startActivity(intent);
        });
        btUpdateClass.setOnClickListener(v->{

            Intent intent = new Intent(getApplicationContext(), updateClass.class);
            intent.putExtra("classID",classID);
            intent.putExtra("termID",termID);
            intent.putExtra("title",className);
            intent.putExtra("startDate",start);
            intent.putExtra("endDate",end);
            intent.putExtra("statues",statuesClass);
            intent.putExtra("instructorName",instructor);
            intent.putExtra("phoneNumber",phone);
            intent.putExtra("email",emailInstructor);
            intent.putExtra("note",notes);

            startActivity(intent);
        });

        btDeleteClass.setOnClickListener(v->{

            repository.classDao.delete(new ClassInTerm(classID));
            this.finish();
        });

    }

    protected void onResume() {
        super.onResume();

        Repository repository = new Repository(getApplication());
        List<ClassInTerm> classList = repository.classDao.getAllClass();

        int classID = getIntent().getIntExtra("classID",100);
        classList.removeIf(v->v.getClassID() != classID);
        int termID = classList.get(0).getTermID();
        String className = classList.get(0).getName();
        String start = classList.get(0).getStartDate();
        String end = classList.get(0).getEndDate();
        String statuesClass = classList.get(0).getStatues();
        String instructor = classList.get(0).getInstructor();
        String phone =  classList.get(0).getPhoneNumber();
        String emailInstructor= classList.get(0).getEmail();
        String notes = classList.get(0).getNotes();

        clName.setText(className);
        startDate.setText(start);
        endDate.setText(end);
        instructorName.setText(instructor);
        phoneNumber.setText(phone);
        email.setText(emailInstructor);
        statues.setText(statuesClass);
        note.setText(notes);

        List<Assessment> assessmentList;

        assessmentList = repository.assessmentDao.getAllAssessment();
        assessmentList.removeIf(v-> v.getClassID() != classID);


        ArrayList<HashMap<String, Object>> List = new ArrayList<>();


        for(int i = 0; assessmentList.size() > i;i++){
            HashMap<String,Object> hashMap = new HashMap<>();
            hashMap.put("Title", assessmentList.get(i).getAssessment());
            hashMap.put("StartDate", assessmentList.get(i).getStartDateAssessment());
            hashMap.put("EndDate",assessmentList.get(i).getEndDateAssessment());

            List.add(hashMap);
        }

        String[] from = {"Title","StartDate","EndDate"};

        int[] to ={R.id.txList1,R.id.txList2,R.id.txList3};

        SimpleAdapter simpleAdapter = new SimpleAdapter(getApplicationContext(), List, R.layout.double_list_item, from, to);

        lsAssessment.setAdapter(simpleAdapter);

        if(assessmentList.size()>0){
            lsAssessment.setVisibility(View.VISIBLE);
        }
        else{
            lsAssessment.setVisibility(View.GONE);
        }

        lsAssessment.setOnItemClickListener((parent, view, position, id) -> {

            int i = (int) id;
            Intent intent = new Intent(getApplicationContext(), viewAssessment.class);
            intent.putExtra("classID", classID);
            intent.putExtra("assessmentID",assessmentList.get(i).getAssessmentID());
            intent.putExtra("assessment",assessmentList.get(i).getAssessment());
            intent.putExtra("start",assessmentList.get(i).getStartDateAssessment());
            intent.putExtra("end",assessmentList.get(i).getEndDateAssessment());
            intent.putExtra("type",assessmentList.get(i).getAssessmentType());
            startActivity(intent);
        });

        btAddAssessment.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), addAssessment.class);
            intent.putExtra("classID",classID);
            startActivity(intent);
        });

        btUpdateClass.setOnClickListener(v->{

            Intent intent = new Intent(getApplicationContext(), updateClass.class);
            intent.putExtra("classID",classID);
            intent.putExtra("termID",termID);
            intent.putExtra("title",className);
            intent.putExtra("startDate",start);
            intent.putExtra("endDate",end);
            intent.putExtra("statues",statuesClass);
            intent.putExtra("instructorName",instructor);
            intent.putExtra("phoneNumber",phone);
            intent.putExtra("email",emailInstructor);
            intent.putExtra("note",notes);

            startActivity(intent);
        });

        btDeleteClass.setOnClickListener(v->{

            repository.classDao.delete(new ClassInTerm(classID));
            this.finish();
        });
        btSms.setOnClickListener(v->{
            try{
                String phoneNu = phoneNumber.getText().toString();
                String sms = note.getText().toString();
                SmsManager smsNote = SmsManager.getSmsManagerForSubscriptionId(SmsManager.getDefaultSmsSubscriptionId());
                smsNote.sendTextMessage(phoneNu,null,sms,null,null);
                Toast.makeText(getApplicationContext(),"SMS sent",Toast.LENGTH_SHORT).show();
            }
            catch (Exception e){
                Toast.makeText(getApplicationContext(),"SMS not sent",Toast.LENGTH_SHORT).show();
                e.printStackTrace();

            } });

        btEmail.setOnClickListener(v->{

                String emailReceiver = email.getText().toString();
                String emailClName = clName.getText().toString();
                String noteBody = note.getText().toString();
                Intent intent = new Intent(Intent.ACTION_SEND);

                intent.setData(Uri.parse("mailto:"));
                intent.setType("message/rfc822");
                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{emailReceiver});
                intent.putExtra(Intent.EXTRA_SUBJECT,"Class Note for : "+ emailClName);
                intent.putExtra(Intent.EXTRA_TEXT,noteBody);
                try{


                startActivity(Intent.createChooser(intent,"Choose an Email to client: "));
                finish();
                    Log.i("Finished sending email .......","");
                Toast.makeText(getApplicationContext(),"Email successfully sent",Toast.LENGTH_LONG).show();
            }
            catch (android.content.ActivityNotFoundException ex){
                Toast.makeText(getApplicationContext(),"Email was not successfully sent",Toast.LENGTH_LONG).show();
                ex.printStackTrace();

            }


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

          String star = startDate.getText().toString();
          String className = clName.getText().toString();

          String myForMate = "MM/dd/yy";
          SimpleDateFormat sdf = new SimpleDateFormat(myForMate, Locale.US);

            Date myDate = null;
            try {
                myDate = sdf.parse(star);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }

          Long trigger = myDate.getTime() ;

          Intent intent= new Intent(viewClass.this, notification.class);
          intent.putExtra("key", "Class " + className + " will start today");
          PendingIntent sender =PendingIntent.getBroadcast (viewClass.this, ++MainActivity.numAlert, intent,PendingIntent.FLAG_UPDATE_CURRENT);
          AlarmManager alarmManager =(AlarmManager)getSystemService(Context.ALARM_SERVICE);
          alarmManager.set(AlarmManager. RTC_WAKEUP, trigger, sender);
            return true;
        }
        else if(menuItem.getItemId() == R.id.ntEnd){

            String star = endDate.getText().toString();
            String className = clName.getText().toString();
            String myForMate = "MM/dd/yy";
            SimpleDateFormat sdf = new SimpleDateFormat(myForMate, Locale.US);

            Date myDate = null;
            try {
                myDate = sdf.parse(star);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }

            Long trigger = myDate.getTime() ;

            Intent intent= new Intent(viewClass.this, notification.class);
            intent.putExtra("key",  "Class " + className + " will end today");
            PendingIntent sender =PendingIntent.getBroadcast (viewClass.this, ++MainActivity.numAlert, intent,PendingIntent.FLAG_UPDATE_CURRENT);
            AlarmManager alarmManager =(AlarmManager)getSystemService(Context.ALARM_SERVICE);

            alarmManager.set(AlarmManager. RTC_WAKEUP, trigger, sender);
            return true;
        }
        return true;
    }
}