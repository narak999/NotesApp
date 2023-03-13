package com.bunnarak.notes.persistence;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.bunnarak.notes.async.DeleteAsyncTask;
import com.bunnarak.notes.async.InsertAsyncTask;
import com.bunnarak.notes.async.UpdateAsyncTask;
import com.bunnarak.notes.models.Note;

import java.util.List;

public class NoteRepository {

    private NoteDatabase mNoteDatabase;

    public NoteRepository(Context context) {
        mNoteDatabase = NoteDatabase.getInstance(context);
    }

    public void insertNoteTask(Note note) {
        new InsertAsyncTask(mNoteDatabase.getNoteDAO()).execute(note);
    }

    public void updateNote(Note note) {
        new UpdateAsyncTask(mNoteDatabase.getNoteDAO()).execute(note);
    }

    public LiveData<List<Note>> retrieveNotesTask() {
        return mNoteDatabase.getNoteDAO().getNotes();
    }

    public void deleteNote(Note note) {
        new DeleteAsyncTask(mNoteDatabase.getNoteDAO()).execute(note);
    }
}
