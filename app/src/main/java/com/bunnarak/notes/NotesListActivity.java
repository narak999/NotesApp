package com.bunnarak.notes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.bunnarak.notes.adapters.NotesRecyclerAdapter;
import com.bunnarak.notes.models.Note;
import com.bunnarak.notes.util.VerticalSpacingItemDecorator;

import java.util.ArrayList;

public class NotesListActivity extends AppCompatActivity implements NotesRecyclerAdapter.OnNoteListener {
    private static final String TAG = "NotesListActivity";

    //UI components
    private RecyclerView mRecyclerView;

    //vars
    private ArrayList<Note> mNotes = new ArrayList<>();
    private NotesRecyclerAdapter mNoteRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.recyclerView);
        initRecyclerView();
        insertFakeNotes();

        setSupportActionBar((Toolbar)findViewById(R.id.notes_toolbar));
        setTitle("Notes");
    }

    private void insertFakeNotes() {
        for (int i = 0; i < 1000; i++) {
            Note note = new Note();
            note.setTitle("title #: " + i);
            note.setContent("content #: " + i);
            note.setTimeStamp("Jan 2019");
            mNotes.add(note);
        }
        mNoteRecyclerAdapter.notifyDataSetChanged();
    }

    private void initRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        VerticalSpacingItemDecorator itemDecorator = new VerticalSpacingItemDecorator(10);
        mRecyclerView.addItemDecoration(itemDecorator);
        mNoteRecyclerAdapter = new NotesRecyclerAdapter(mNotes, this);
        mRecyclerView.setAdapter(mNoteRecyclerAdapter);
    }

    @Override
    public void onNoteClick(int position) {
        Log.d(TAG, "onNoteClick: clicked!" + position);
        Intent intent = new Intent(this, NoteActivity.class);
        intent.putExtra("selected_note", mNotes.get(position));
        startActivity(intent);
    }
}