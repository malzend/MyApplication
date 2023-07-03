package com.example.myapplication.database;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.myapplication.dao.AssessmentDao;
import com.example.myapplication.dao.ClassDao;
import com.example.myapplication.dao.TermDao;
import com.example.myapplication.entities.Assessment;
import com.example.myapplication.entities.ClassInTerm;
import com.example.myapplication.entities.Term;

@Database(entities = {Term.class, ClassInTerm.class, Assessment.class}, version  = 2
        , exportSchema = false)
public abstract  class TermDataBase extends RoomDatabase {
    public abstract AssessmentDao assessmentDao();
    public abstract ClassDao classDao();
    public abstract TermDao termDao();

    private static volatile TermDataBase INSTANCE;

    static TermDataBase getDataBase(final Context context){
        if(INSTANCE== null){
            synchronized (TermDataBase.class){
                if (INSTANCE==null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),TermDataBase.class,"MyTermDataBase.db").fallbackToDestructiveMigration()
                            .allowMainThreadQueries().build();
                }
            }
        }
        return INSTANCE;
    }
}
