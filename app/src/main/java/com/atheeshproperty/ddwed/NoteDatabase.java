package com.atheeshproperty.ddwed;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.view.View;

@Database(entities = {Note.class}, version = 1)
public abstract class NoteDatabase extends RoomDatabase {

    private static NoteDatabase instance;

    public abstract NoteDAO noteDAO();

    public static synchronized NoteDatabase getInstance(Context context){//Create an instance and return
        if(instance == null){//Instantiate if instance is null
            instance = Room.databaseBuilder(context.getApplicationContext(),NoteDatabase.class,"note_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallBack)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallBack = new RoomDatabase.Callback(){

        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDBAsynchTask(instance).execute();
        }
    };

    private static class PopulateDBAsynchTask extends AsyncTask<Void, Void, Void>{

        private NoteDAO noteDAO;

        public PopulateDBAsynchTask(NoteDatabase db) {
            noteDAO = db.noteDAO();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            noteDAO.insert(new Note("Title 1","Description 1",1));
            noteDAO.insert(new Note("Title 2","Description 2",2));
            noteDAO.insert(new Note("Title 3","Description 3",3));
            return null;
        }
    }
}
