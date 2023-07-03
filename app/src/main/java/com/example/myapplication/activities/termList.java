package com.example.myapplication.activities;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.database.Repository;
import com.example.myapplication.entities.Term;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class termList extends AppCompatActivity {
    ListView lsTerm;
    Button btAddTerm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.term_list);
        Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorPrimary)));

        lsTerm = findViewById(R.id.lsTerm);
        lsTerm.setClickable(true);
        btAddTerm = findViewById(R.id.btAddTerm);

    }

    @Override
    protected void onStart() {
        super.onStart();

        lsTerm = findViewById(R.id.lsTerm);
        lsTerm.setClickable(true);
        btAddTerm = findViewById(R.id.btAddTerm);

        List<Term> termList;

        Repository repository = new Repository(getApplication());
        termList = repository.termDao.getAllTerm();

        ArrayList<HashMap<String, Object>> List = new ArrayList<>();

        for (int i = 0; i < termList.size(); i++) {

            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("Title", termList.get(i).getTermName());
            hashMap.put("StartDate", termList.get(i).getStartDate());
            hashMap.put("EndDate", termList.get(i).getEndDate());

            List.add(hashMap);
        }

        String[] from = {"Title", "StartDate", "EndDate"};

        int[] to = {R.id.txList1, R.id.txList2, R.id.txList3};

        SimpleAdapter simpleAdapter = new SimpleAdapter(getApplicationContext(), List, R.layout.double_list_item, from, to);

        lsTerm.setAdapter(simpleAdapter);


        btAddTerm = findViewById(R.id.btAddTerm);
        btAddTerm.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), addTerm.class);
            startActivity(intent);
        });

        lsTerm.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(getApplicationContext(), viewTerm.class);
            int i = (int) id;
            intent.putExtra("termID", termList.get(i).getTermID());
            intent.putExtra("title", termList.get(i).getTermName());
            intent.putExtra("startDate", termList.get(i).getStartDate());
            intent.putExtra("endDate", termList.get(i).getEndDate());
            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        lsTerm = findViewById(R.id.lsTerm);
        lsTerm.setClickable(true);
        btAddTerm = findViewById(R.id.btAddTerm);

        List<Term> termList;

        Repository repository = new Repository(getApplication());
        termList = repository.termDao.getAllTerm();

        ArrayList<HashMap<String, Object>> List = new ArrayList<>();

        for (int i = 0; i < termList.size(); i++) {

            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("Title", termList.get(i).getTermName());
            hashMap.put("StartDate", termList.get(i).getStartDate());
            hashMap.put("EndDate", termList.get(i).getEndDate());

            List.add(hashMap);
        }

        String[] from = {"Title", "StartDate", "EndDate"};

        int[] to = {R.id.txList1, R.id.txList2, R.id.txList3};

        SimpleAdapter simpleAdapter = new SimpleAdapter(getApplicationContext(), List, R.layout.double_list_item, from, to);

        lsTerm.setAdapter(simpleAdapter);


        btAddTerm = findViewById(R.id.btAddTerm);
        btAddTerm.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), addTerm.class);
            startActivity(intent);
        });

        lsTerm.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(getApplicationContext(), viewTerm.class);
            int i = (int) id;
            intent.putExtra("termID", termList.get(i).getTermID());
            intent.putExtra("title", termList.get(i).getTermName());
            intent.putExtra("startDate", termList.get(i).getStartDate());
            intent.putExtra("endDate", termList.get(i).getEndDate());
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