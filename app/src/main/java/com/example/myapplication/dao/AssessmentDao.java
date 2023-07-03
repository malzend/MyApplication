package com.example.myapplication.dao;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.myapplication.entities.Assessment;
import com.example.myapplication.entities.ClassInTerm;

import java.util.List;

@Dao
public interface AssessmentDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Assessment assessment);

    @Update
    void update(Assessment assessment);

    @Delete
    void delete(Assessment assessment);

    @Query("SELECT * FROM ASSESSMENT ORDER BY assessmentID ASC")
    List<Assessment> getAllAssessment();

    @Query("SELECT * FROM ASSESSMENT WHERE classID= :classID ORDER BY assessmentID ASC")
    List<Assessment> getAssociatedAssessment(int classID);

 //   @Query("SELECT (SELECT COUNT(*) FROM Term) == 0")
   // List<ClassInTerm> getTableStatues(int termID);
}

