package com.example.myapplication.activities;

import static com.example.myapplication.R.*;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.media.tv.TvContract;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.database.Repository;
import com.example.myapplication.entities.Assessment;
import com.example.myapplication.entities.ClassInTerm;
import com.example.myapplication.entities.Term;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {
   public static int numAlert;
    Button btWelcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(color.colorPrimary)));
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_main);


        btWelcome = findViewById(id.btWelcome);
        btWelcome.setBackgroundColor(getResources().getColor(color.colorSecondary));
        btWelcome.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), termList.class);
            startActivity(intent);
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);

        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item){
        Toast toast ;
        if (item.getItemId() == id.addSampleData){
               Repository repository = new Repository(getApplication());
               repository.termDao.insert(new Term(1,"Term1","2022/12/01","2023/06/01"));
               repository.termDao.insert(new Term(2,"Term 2" , "2020/12/30" , "2022/06/20"));
               repository.classDao.insert(new ClassInTerm(4,"Math 255","08/02/2023","10/02/2023","Ali","000-000-0000i","aaa@gmail.com",1,"Completed", "This class is important "));
               repository.classDao.insert(new ClassInTerm(5,"science","08/02/2023","10/02/2023","Ali","000-000-0000i","aaa@gmail.com",1,"Completed","Completed course"));
               repository.classDao.insert(new ClassInTerm(6,"DB 105","08/02/2023","10/02/2023","Ali","000-000-0000i","aaa@gmail.com",2,"Completed", "Completed course"));
               repository.classDao.insert(new ClassInTerm(7,"CS 220","08/02/2023","10/02/2023","Ali","000-000-0000i","aaa@gmail.com",2,"Completed","completed course"));
               repository.assessmentDao.insert(new Assessment(1,"math 255","02/20/2024","02/21/2024",4,"Objective assessment"));
               repository.assessmentDao.insert(new Assessment(2,"DB 105","02/20/2024", "02/21/2024",6,"Performance assessment"));
           toast =  Toast.makeText(this,"New Data was added",Toast.LENGTH_SHORT);
           toast.show();

           return true;

       }
        toast = Toast.makeText(this,"No data is added",Toast.LENGTH_SHORT);
        toast.show();

        return super.onOptionsItemSelected(item);
    }
    }
