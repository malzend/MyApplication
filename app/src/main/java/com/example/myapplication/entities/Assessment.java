package com.example.myapplication.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "assessment")
public class Assessment {
    @PrimaryKey(autoGenerate = true)
    private int assessmentID;
    @ColumnInfo (name = "Assessment_name")
    private String assessment;
    @ColumnInfo (name = "Start_date ")
    private String startDateAssessment;
    @ColumnInfo (name = "End_date ")
    private String endDateAssessment;
    @ColumnInfo( name ="Assessment_Type")
    private  String assessmentType;
    @ColumnInfo(name ="ClassID")
    private int classID;

    public Assessment(String assessment,
                      String startDateAssessment, String endDateAssessment, String assessmentType, int classID) {
        this.assessment = assessment;
        this.startDateAssessment = startDateAssessment;
        this.endDateAssessment = endDateAssessment;
        this.classID = classID;
        this.assessmentType = assessmentType;

    }
    public Assessment(int assessmentID, String assessment,
                      String startDateAssessment, String endDateAssessment, String assessmentType) {
        this.assessment = assessment;
        this.startDateAssessment = startDateAssessment;
        this.endDateAssessment = endDateAssessment;
        this.assessmentType = assessmentType;
        this.assessmentID = assessmentID;

    }
    public Assessment(int assessmentID, String assessment,
                      String startDateAssessment, String endDateAssessment, int classID, String assessmentType) {
        this.assessmentID = assessmentID;
        this.assessment = assessment;
        this.startDateAssessment = startDateAssessment;
        this.endDateAssessment = endDateAssessment;
        this.classID = classID;
        this.assessmentType = assessmentType;
    }
    public Assessment(int assessmentID){
        this.assessmentID = assessmentID;
    }
 public Assessment(){

 }

    public String getAssessmentType() {
        return assessmentType;
    }

    public void setAssessmentType(String assessmentType) {
        this.assessmentType = assessmentType;
    }

    public int getClassID() {
        return classID;
    }

    public void setClassID(int classID) {
        this.classID = classID;
    }

    public int getAssessmentID() {
        return assessmentID;
    }

    public void setAssessmentID(int assessmentID) {
        this.assessmentID = assessmentID;
    }

    public String getAssessment() {
        return assessment;
    }

    public void setAssessment(String assessment) {
        this.assessment = assessment;
    }

    public String getStartDateAssessment() {
        return startDateAssessment;
    }

    public void setStartDateAssessment(String startDateAssessment) {
        this.startDateAssessment = startDateAssessment;
    }

    public String getEndDateAssessment() {
        return endDateAssessment;
    }

    public void setEndDateAssessment(String endDateAssessment) {
        this.endDateAssessment = endDateAssessment;
    }
}
