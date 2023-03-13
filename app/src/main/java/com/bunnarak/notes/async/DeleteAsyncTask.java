package com.bunnarak.notes.async;

import android.os.AsyncTask;
import android.util.Log;

import com.bunnarak.notes.models.Note;
import com.bunnarak.notes.persistence.NoteDAO;

public class DeleteAsyncTask extends AsyncTask<Note, Void, Void> {
    private static final String TAG = "InsertAsyncTask";
    private NoteDAO mNoteDAO;
    public DeleteAsyncTask(NoteDAO DAO) {
        mNoteDAO = DAO;
    }

    @Override
    protected Void doInBackground(Note... notes) {
        Log.d(TAG, "doInBackground: thread: " + Thread.currentThread().getName());
        mNoteDAO.delete(notes);
        return null;
    }
}
