package com.example.myapplication.activities;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.database.Repository;
import com.example.myapplication.entities.ClassInTerm;
import com.example.myapplication.entities.Term;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class viewTerm extends AppCompatActivity {
    Button btAdd;
    Button btDelete;
    Button btUpdate;
    ListView lsClass;
    EditText etTermName;
    EditText etStartDate;
    EditText etEndDate;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_term);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorPrimary)));

        btAdd = findViewById(R.id.bt_add_class);
        btDelete = findViewById(R.id.bt_delete_class);
        lsClass = findViewById(R.id.lsClass);
        etTermName = findViewById(R.id.etTittle2);
        etStartDate = findViewById(R.id.etStartDate2);
        etEndDate = findViewById(R.id.etEndDate2);
        btUpdate = findViewById(R.id.bt_update_class);

        };

    @Override
    protected void onStart() {

        super.onStart();
        Toast.makeText(this, "OnStart TermView",Toast.LENGTH_SHORT).show();
        Repository repository = new Repository(getApplication());


        List<ClassInTerm> classList ;

        int termID = getIntent().getIntExtra("termID",100);
        String title = getIntent().getStringExtra("title");
        String start = getIntent().getStringExtra("startDate");
        String end = getIntent().getStringExtra("endDate");

        etTermName.setText(title);
        etStartDate.setText(start);
        etEndDate.setText(end);

        classList = repository.classDao.getAllClass();
        classList.removeIf(v-> v.getTermID() != termID);
        ArrayList<HashMap<String, Object>> List = new ArrayList<>();

        for (int i = 0; i < classList.size(); i++) {

                HashMap<String, Object> hashMap = new HashMap<>();
                hashMap.put("Title", classList.get(i).getName());
                hashMap.put("StartDate", classList.get(i).getStartDate());
                hashMap.put("EndDate", classList.get(i).getEndDate());
                List.add(hashMap);

        }

        String[] from = {"Title", "StartDate", "EndDate"};

        int[] to = {R.id.txList1, R.id.txList2, R.id.txList3};

        SimpleAdapter simpleAdapter = new SimpleAdapter(getApplicationContext(), List, R.layout.double_list_item, from, to);

        lsClass.setAdapter(simpleAdapter);


        lsClass.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(getApplicationContext(), viewClass.class);
            int i = (int) id;
            intent.putExtra("classID",classList.get(i).getClassID());
            intent.putExtra("termID",termID);
            intent.putExtra("title",classList.get(i).getName());
            intent.putExtra("startDate",classList.get(i).getStartDate());
            intent.putExtra("endDate",classList.get(i).getEndDate());
            intent.putExtra("statues",classList.get(i).getStatues());
            intent.putExtra("instructorName",classList.get(i).getInstructor());
            intent.putExtra("phoneNumber",classList.get(i).getPhoneNumber());
            intent.putExtra("email",classList.get(i).getEmail());
            intent.putExtra("notes",classList.get(i).getNotes());

            startActivity(intent);

        });
        btAdd.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), addClass.class);
            intent.putExtra("termID", termID);
            startActivity(intent);
        });

        btDelete.setOnClickListener(v -> {
           Intent intent = new Intent(getApplicationContext(), termList.class);
           boolean size =  classList.removeIf(t -> t.getTermID() == termID);
            if(size == true) {
                new AlertDialog.Builder(this)
                        .setTitle("Term has classes assigned to it ")
                        .setMessage("The term you are trying to delete has classes \n assigned to it.")
                        .setPositiveButton(android.R.string.ok, (dialog, which) -> {
                            // TODO Auto-generated method stub
                            dialog.dismiss();

                        })
                        .show()
                      ;
            } else if (size == false) {
                repository.delete(new Term(termID));
                startActivity(intent);
            }


        });

        btUpdate.setOnClickListener(v ->{
            Intent intent = new Intent(getApplicationContext(), updateTerm.class);
            repository.classDao.update(new ClassInTerm());
            intent.putExtra("termID",termID);
            intent.putExtra("title",title);
            intent.putExtra("startDate",start);
            intent.putExtra("endDate",end);

            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        Repository repository = new Repository(getApplication());
        List<ClassInTerm> classList ;
        List<Term> termList = repository.termDao.getAllTerm();

        int termID = getIntent().getIntExtra("termID",100);
        termList.removeIf(v->v.getTermID() != termID);
        String title = termList.get(0).getTermName();
        String start = termList.get(0).getStartDate();
        String end = termList.get(0).getEndDate();

        etTermName.setText(title);
        etStartDate.setText(start);
        etEndDate.setText(end);

        classList = repository.classDao.getAllClass();
        classList.removeIf(v-> v.getTermID() != termID);
        ArrayList<HashMap<String, Object>> List = new ArrayList<>();

        for (int i = 0; i < classList.size(); i++) {

            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("Title", classList.get(i).getName());
            hashMap.put("StartDate", classList.get(i).getStartDate());
            hashMap.put("EndDate", classList.get(i).getEndDate());
            List.add(hashMap);

        }

        String[] from = {"Title", "StartDate", "EndDate"};

        int[] to = {R.id.txList1, R.id.txList2, R.id.txList3};

        SimpleAdapter simpleAdapter = new SimpleAdapter(getApplicationContext(), List, R.layout.double_list_item, from, to);

        lsClass.setAdapter(simpleAdapter);

        lsClass.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(getApplicationContext(), viewClass.class);
            int i = (int) id;
            intent.putExtra("classID",classList.get(i).getClassID());
            intent.putExtra("termID",termID);
            intent.putExtra("title",classList.get(i).getName());
            intent.putExtra("startDate",classList.get(i).getStartDate());
            intent.putExtra("endDate",classList.get(i).getEndDate());
            intent.putExtra("statues",classList.get(i).getStatues());
            intent.putExtra("instructorName",classList.get(i).getInstructor());
            intent.putExtra("phoneNumber",classList.get(i).getPhoneNumber());
            intent.putExtra("email",classList.get(i).getEmail());
            intent.putExtra("notes",classList.get(i).getNotes());

            startActivity(intent);

        });
        btAdd.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), addClass.class);
            intent.putExtra("termID", termID);
            startActivity(intent);
        });

        btDelete.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), termList.class);
            Intent intent1 = new Intent(getApplicationContext(),viewTerm.class);
            boolean size =  classList.removeIf(t -> t.getTermID() == termID);
            if(size == true) {
                new AlertDialog.Builder(this)
                        .setTitle("Term has classes assigned to it ")
                        .setMessage("The term you are trying to delete has classes \n assigned to it.")
                        .setPositiveButton(android.R.string.ok, (dialog, which) -> {
                            // TODO Auto-generated method stub
                            intent.putExtra("termID",termID) ;
                            intent.putExtra("title", title);
                            intent.putExtra("startDate", start);
                            intent.putExtra("endDate", end);
                            startActivity(intent1);
                            dialog.dismiss();

                        })
                        .show()
                ;
            } else if (size == false) {
                repository.delete(new Term(termID));
                startActivity(intent);
            }

        });

        btUpdate.setOnClickListener(v ->{
            Intent intent = new Intent(getApplicationContext(), updateTerm.class);
            repository.classDao.update(new ClassInTerm());
            intent.putExtra("termID",termID);
            intent.putExtra("title",title);
            intent.putExtra("startDate",start);
            intent.putExtra("endDate",end);

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
