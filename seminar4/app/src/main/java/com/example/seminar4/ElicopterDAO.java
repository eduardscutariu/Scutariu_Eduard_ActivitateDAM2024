package com.example.seminar4;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ElicopterDAO {

    @Insert
    void insert(Elicopter elicopter);

    @Update
    void update(Elicopter elicopter);

    @Delete
    void delete(Elicopter elicopter);

    @Query("SELECT * FROM TabelElicoptere")
    List<Elicopter> getAll();

    @Query("SELECT * FROM TabelElicoptere WHERE producator = :producator")
    Elicopter getByProducator(String producator);
}
