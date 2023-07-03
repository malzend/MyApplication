package com.example.myapplication.database;

import android.app.Application;

import com.example.myapplication.dao.AssessmentDao;
import com.example.myapplication.dao.ClassDao;
import com.example.myapplication.dao.TermDao;
import com.example.myapplication.entities.Assessment;
import com.example.myapplication.entities.ClassInTerm;
import com.example.myapplication.entities.Term;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Repository {

    public  AssessmentDao assessmentDao;
    public  ClassDao classDao;
    public  TermDao termDao;

    private List<Assessment> allAssessment;
    private List<ClassInTerm> allClass;
    private List<Term> allTerm;

    private static  int NUMBER_OF_THREADS = 6;
    static final ExecutorService dataBaseExecuter = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public Repository(Application application){
        TermDataBase db = TermDataBase.getDataBase(application);
        assessmentDao = db.assessmentDao();
        classDao=  db.classDao();
        termDao = db.termDao();

    }

    public List<Assessment>getAllAssessment(){
        dataBaseExecuter.execute(()->{
          allAssessment=assessmentDao.getAllAssessment();
        });
        try {
        Thread.sleep(1000);}
        catch (InterruptedException e){
            e.printStackTrace();
        }
        return allAssessment;

    }

    public List<ClassInTerm>getAllClass(){
        dataBaseExecuter.execute(()->{
            allClass=classDao.getAllClass();
        });
        try {
            Thread.sleep(1000);}
        catch (InterruptedException e){
            e.printStackTrace();
        }
        return allClass;

    }

    public List<Term>getAllTerm(){
        dataBaseExecuter.execute(()-> allTerm=termDao.getAllTerm());
        try {
            Thread.sleep(1000);}
        catch (InterruptedException e){
            e.printStackTrace();
        }
        return allTerm;

    }
    /*public List<Term>getTableStatus() {
        dataBaseExecuter.execute(() -> allTerm = termDao.getTableStatues());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return allTerm;

    }*/


        public void insert(Assessment assessment){
        dataBaseExecuter.execute(()-> assessmentDao.insert(assessment));
        try {
            Thread.sleep(1000);}
        catch (InterruptedException e){
            e.printStackTrace();
        }
    }
    public void update(Assessment assessment){
        dataBaseExecuter.execute(()-> assessmentDao.update(assessment));
        try {
            Thread.sleep(1000);}
        catch (InterruptedException e){
            e.printStackTrace();
        }
    }
    public void delete(Assessment assessment){
        dataBaseExecuter.execute(()-> assessmentDao.delete(assessment));
        try {
            Thread.sleep(1000);}
        catch (InterruptedException e){
            e.printStackTrace();
        }
    }


    public void insert(ClassInTerm classInTerm){
        dataBaseExecuter.execute(()-> classDao.insert(classInTerm));
        try {
            Thread.sleep(1000);}
        catch (InterruptedException e){
            e.printStackTrace();
        }
    }
    public void update(ClassInTerm classInTerm){
        dataBaseExecuter.execute(()-> classDao.update(classInTerm));
        try {
            Thread.sleep(1000);}
        catch (InterruptedException e){
            e.printStackTrace();
        }
    }
    public void delete(Term term){
        dataBaseExecuter.execute(()-> termDao.delete(term));
        try {
            Thread.sleep(1000);}
        catch (InterruptedException e){
            e.printStackTrace();
        }
    }

   /* public void deleteAllTerm(){
        dataBaseExecuter.execute(()-> termDao.deleteALL());
        try {
            Thread.sleep(1000);}
        catch (InterruptedException e){
            e.printStackTrace();
        }
    }*/

    public void insert(Term term){
        dataBaseExecuter.execute(()-> termDao.insert(term));
        try {
            Thread.sleep(1000);}
        catch (InterruptedException e){
            e.printStackTrace();
        }
    }
    public void update(Term term){
        dataBaseExecuter.execute(()-> termDao.update(term));
        try {
            Thread.sleep(1000);}
        catch (InterruptedException e){
            e.printStackTrace();
        }
    }
    public void delete(ClassInTerm classInTerm){
        dataBaseExecuter.execute(()-> classDao.delete(classInTerm));
        try {
            Thread.sleep(1000);}
        catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
