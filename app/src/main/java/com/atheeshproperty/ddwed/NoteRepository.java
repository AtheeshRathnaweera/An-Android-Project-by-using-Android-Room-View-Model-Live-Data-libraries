package com.atheeshproperty.ddwed;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class NoteRepository {

    private NoteDAO noteDAO;
    private LiveData<List<Note>> allNotes;

    public NoteRepository(Application application){
        NoteDatabase database = NoteDatabase.getInstance(application);
        noteDAO = database.noteDAO();
        allNotes = noteDAO.getAllNotes();

    }

    public void insert(Note note){
        new InsertNoteAsynchTask(noteDAO).execute(note);

    }

    public void update(Note note){
        new UpdateNoteAsynchTask(noteDAO).execute(note);

    }

    public void delete(Note note){
        new DeleteNoteAsynchTask(noteDAO).execute(note);

    }

    public void deleteAllNotes(){
        new DeleteAllNoteAsynchTask(noteDAO).execute();
    }

    public LiveData<List<Note>> getAllNotes() {
        return allNotes;
    }

    private static class InsertNoteAsynchTask extends AsyncTask<Note, Void, Void>{

        private NoteDAO noteDAO;

        private InsertNoteAsynchTask(NoteDAO noteDAO){
            this.noteDAO = noteDAO;

        }
        @Override
        protected Void doInBackground(Note... notes) {
            noteDAO.insert(notes[0]);
            return null;
        }
    }


    private static class UpdateNoteAsynchTask extends AsyncTask<Note, Void, Void>{

        private NoteDAO noteDAO;

        private UpdateNoteAsynchTask(NoteDAO noteDAO){
            this.noteDAO = noteDAO;

        }
        @Override
        protected Void doInBackground(Note... notes) {
            noteDAO.update(notes[0]);
            return null;
        }
    }

    private static class DeleteNoteAsynchTask extends AsyncTask<Note, Void, Void>{

        private NoteDAO noteDAO;

        private DeleteNoteAsynchTask(NoteDAO noteDAO){
            this.noteDAO = noteDAO;

        }
        @Override
        protected Void doInBackground(Note... notes) {
            noteDAO.delete(notes[0]);
            return null;
        }
    }

    private static class DeleteAllNoteAsynchTask extends AsyncTask<Void, Void, Void>{

        private NoteDAO noteDAO;

        private DeleteAllNoteAsynchTask(NoteDAO noteDAO){
            this.noteDAO = noteDAO;

        }
        @Override
        protected Void doInBackground(Void... notes) {
            noteDAO.deleteAllNotes();
            return null;
        }
    }
}
