package com.atheeshproperty.ddwed;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface NoteDAO {//This Dao is for "note" object
                          // This is an interface( Also can be an abstract class ).
                          // Because DAO(Data Access Object) only contains methods which doesnt have bodies

    @Insert
    void insert(Note note);

    @Update
    void update(Note note);

    @Delete
    void delete(Note note);

    @Query("DELETE FROM note_table")//Delete all data of note_table. We use @Query to define a database operation by ourselves
    void deleteAllNotes();

    @Query("SELECT * FROM note_table ORDER BY priority DESC")
    LiveData<List<Note>> getAllNotes();

}
