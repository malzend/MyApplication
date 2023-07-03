package com.example.myapplication.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Term")
public class Term {
    @PrimaryKey(autoGenerate = true)
    private  int termID;
    @ColumnInfo(name = "Term_Name")
    private String termName;
    @ColumnInfo(name = "Start_date")
    private String startDate;
    @ColumnInfo(name = "End_date")
    private String EndDate;

    public Term(int termID , String termName, String startDate, String endDate) {
        this.termID = termID;
        this.termName = termName;
        this.startDate = startDate;
        EndDate = endDate;
    }

    public Term(String termName, String startDate, String endDate) {
        this.termName = termName;
        this.startDate = startDate;
        EndDate = endDate;
    }

    public Term() {

    }
    public Term(int termID) {
        this.termID = termID;
    }
    public int getTermID() {return termID;}

    public void setTermID(int termID) {this.termID = termID;}
    public String getTermName() {
        return termName;
    }

    public void setTermName(String termName) {
        this.termName = termName;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(String endDate) {
        EndDate = endDate;
    }

    public String getEndDate() {
        return EndDate;
    }
}
