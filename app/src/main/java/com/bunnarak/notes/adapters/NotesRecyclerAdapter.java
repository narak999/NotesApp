package com.bunnarak.notes.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bunnarak.notes.R;
import com.bunnarak.notes.models.Note;
import com.bunnarak.notes.util.Utility;

import java.util.ArrayList;


public class NotesRecyclerAdapter extends RecyclerView.Adapter<NotesRecyclerAdapter.ViewHolder> {
    private static final String TAG = "NotesRecyclerAdapter";
    private ArrayList<Note> mNotes = new ArrayList<Note>();
    private OnNoteListener mOnNoteListener;

    public NotesRecyclerAdapter(ArrayList<Note> notes, OnNoteListener onNoteListener) {
        this.mNotes = notes;
        this.mOnNoteListener = onNoteListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_note_list_item, parent, false);
        return new ViewHolder(view, mOnNoteListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        try {
            String month = mNotes.get(position).getTimeStamp().substring(0, 2);
            month = Utility.getMonthFromNumber(month);
            String year = mNotes.get(position).getTimeStamp().substring(3);
            String timestamp = month + " " + year;
            holder.timeStamp.setText(timestamp);
            holder.title.setText(mNotes.get(position).getTitle());
        } catch (NullPointerException e) {
            Log.e(TAG, "onBindViewHolder: NullPointerException: " + e.getMessage());
        }
    }

    @Override
    public int getItemCount() {
        return mNotes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView title, timeStamp;
        OnNoteListener onNoteListener;

        public ViewHolder(@NonNull View itemView, OnNoteListener onNoteListener) {
            super(itemView);
            title = itemView.findViewById(R.id.note_title);
            timeStamp = itemView.findViewById(R.id.note_timestamp);
            this.onNoteListener = onNoteListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onNoteListener.onNoteClick(getAbsoluteAdapterPosition());
        }
    }

    public interface OnNoteListener {
        void onNoteClick(int position);

    }


}
