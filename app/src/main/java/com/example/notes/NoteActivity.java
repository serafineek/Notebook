package com.example.notes;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

public class NoteActivity extends AppCompatActivity {
    Button menuBtn;
    Button newnoteBtn;
    EditText noteTitle;
    EditText textArea;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note_activity);
        getSupportActionBar().hide();
        newnoteBtn = findViewById(R.id.newNote);
        menuBtn = findViewById(R.id.menuButton);
        noteTitle = findViewById(R.id.noteTitle);
        textArea = findViewById(R.id.textarea);
        menuBtn.setOnClickListener(view -> {
            Intent activityMain = new Intent(this,MainActivity.class);
            startActivity(activityMain);
        });
        newnoteBtn.setOnClickListener(view -> {
            Note mynote = new Note(noteTitle.getText().toString(),textArea.getText().toString());
            NoteWritter writter = new NoteWritter(mynote);
            writter.saveNote(this,mynote.getTitle(),mynote.getText());
            Intent activityMain = new Intent(this,MainActivity.class);
            startActivity(activityMain);
        });
    }
}
