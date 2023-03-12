package com.bunnarak.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.bunnarak.notes.models.Note;

public class NoteActivity extends AppCompatActivity {

    private static final String TAG = "NoteActivity";

    //UI components
    private LinedEditText mLinedEditText;
    private EditText mEditTitle;
    private TextView mViewTitle;

    //vars
    private boolean mIsNewNote;
    private Note mInitialNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        mLinedEditText = findViewById(R.id.note_text);
        mEditTitle = findViewById(R.id.note_edit_title);
        mViewTitle = findViewById(R.id.note_text_title);

        if (getIncomingIntent()) {
            //if this is a new note, go to Edit Mode
            setNewNoteProperties();
        } else {
            //if this is not a new note, go to View Mode
            setNoteProperties();
        }

    }

    private boolean getIncomingIntent() {
        if (getIntent().hasExtra("selected_note")) {
            mInitialNote = getIntent().getParcelableExtra("selected_note");
            Log.d(TAG, "getIncomingIntent: " + mInitialNote.toString());
            mIsNewNote = false;
            return false;
        }
        mIsNewNote = true;
        return true;
    }

    private void setNoteProperties() {
        mViewTitle.setText(mInitialNote.getTitle());
        mEditTitle.setText(mInitialNote.getTitle());
        mLinedEditText.setText(mInitialNote.getContent());
    }

    private void setNewNoteProperties() {
        mViewTitle.setText("Note Title");
        mEditTitle.setText("Note Title");
    }
}