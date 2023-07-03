package com.example.myapplication.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.myapplication.entities.ClassInTerm;
import com.example.myapplication.entities.Term;

import java.util.List;

@Dao
public interface TermDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Term term);
    @Update
    void update(Term term);
    @Delete
    void delete(Term term);

    @Query("SELECT * FROM TERM ORDER BY termID ASC")
    List<Term> getAllTerm();

}
