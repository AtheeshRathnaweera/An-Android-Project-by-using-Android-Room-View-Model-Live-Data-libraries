package com.atheeshproperty.ddwed;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {Note.class}, version = 1)
public abstract class NoteDatabase extends RoomDatabase {

    private static NoteDatabase instance;

    public abstract NoteDAO noteDAO();

    public static synchronized NoteDatabase getInstance(Context context){//Create an instance and return
        if(instance == null){//Instantiate if instance is null
            instance = Room.databaseBuilder(context.getApplicationContext(),NoteDatabase.class,"note_database").fallbackToDestructiveMigration().build();
        }
        return instance;
    }
}
