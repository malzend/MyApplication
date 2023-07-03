package com.example.myapplication.entities;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "class")
public class ClassInTerm {

    @PrimaryKey(autoGenerate = true)
    private int classID;
    @ColumnInfo(name = "Class_name ")
    private String name;
    @ColumnInfo (name = "Start_date ")
    private String startDate;
    @ColumnInfo (name = "End_date ")
    private String endDate;
    @ColumnInfo (name = "Instructor")
    private String instructor;
    @ColumnInfo (name = "start date ")
    private String phoneNumber;
    @ColumnInfo (name = "Email")
    private String email;
    @ColumnInfo (name = "TermID")
    private int termID;

    @ColumnInfo(name = "notes")
    private String notes;
    @ColumnInfo(name = "Statues")
    private String statues;



    public ClassInTerm(int classId,String name, String startDate, String endDate, String instructor,
                       String phoneNumber, String email, int termID, String statues, String notes) {
        this.classID = classId;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.instructor = instructor;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.termID = termID;
        this.statues = statues;
        this.notes = notes;
    }
    public ClassInTerm(int classId,String name, String startDate, String endDate, String instructor,
                       String phoneNumber, String email, String statues, String notes) {
        this.classID = classId;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.instructor = instructor;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.statues = statues;
        this.notes = notes;
    }
    public ClassInTerm( String name, String startDate, String endDate, String instructor,
                       String phoneNumber, String email,int termID, String statues, String notes) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.instructor = instructor;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.statues = statues;
        this.notes = notes;
        this.termID=termID;
    }
    public ClassInTerm(){

    }

    public ClassInTerm(int classID){
        this.classID = classID;

    }
    public int getTermID() {
        return termID;
    }

    public void setTermID(int termID) {
        this.termID = termID;
    }

    public String getName() {
        return name;
    }

    public int getClassID() {
        return classID;
    }

    public void setClassID(int classID) {
        this.classID = classID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getStatues() {return statues;}

    public void setStatues(String statues) {this.statues = statues;}


    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
