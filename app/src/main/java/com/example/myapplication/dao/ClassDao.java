package com.example.myapplication.dao;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.myapplication.entities.ClassInTerm;

import java.util.List;

@Dao
public interface ClassDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(ClassInTerm classInTerm);
    @Update
    void update(ClassInTerm classInterm);
    @Delete
    void delete(ClassInTerm classInTerm);

    @Query("SELECT * FROM CLASS ORDER BY classID ASC")
    List<ClassInTerm> getAllClass();

    @Query("SELECT * FROM CLASS WHERE termID= :termID ORDER BY classID ASC")
    List<ClassInTerm> getALLAssociatedPart(int termID);

    //@Query("SELECT (SELECT COUNT(*) FROM Term) == 0")
   // List<ClassInTerm> getTableStatues(int termID);

}
