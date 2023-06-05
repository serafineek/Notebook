package com.example.notes;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class EditNoteActivity extends AppCompatActivity {
    Button deleteBtn;
    Button saveNote;
    EditText title;
    EditText textarea;
    Intent intent = getIntent();
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.editnote_activity);

        deleteBtn = findViewById(R.id.deleteButton);
        deleteBtn.setOnClickListener(view -> {
            deleteNote();
        });
        try {
            fillNoteData();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        saveNote = findViewById(R.id.saveNote);
        saveNote.setOnClickListener(view -> {
            editNote();
        });
    }
    public void fillNoteData() throws FileNotFoundException {
        String text  = "";
        File directory = new File(String.valueOf(this.getFilesDir()));
        File [] files = directory.listFiles();
        for(File file:files) {
            if(file.getName().equals(getIntent().getStringExtra("tag"))) {
                title = findViewById(R.id.noteTitle);
                title.setText(file.getName());
                Scanner scanner = new Scanner(file);
                while (scanner.hasNextLine()) {
                    String linia = scanner.nextLine();
                    text += linia + "\n";
                }
                scanner.close();
                textarea = findViewById(R.id.textarea);
                textarea.setText(text);
            }
        }
    }
    public void deleteNote() {
        File directory = new File(String.valueOf(this.getFilesDir()));
        File [] files = directory.listFiles();
        for(File file:files) {
            if(file.getName().equals(getIntent().getStringExtra("tag"))) {
                file.delete();
                System.out.println("usunieto notateczke");
            }
        }
        Intent mainActivity = new Intent(this,MainActivity.class);
        startActivity(mainActivity);
    }
    public void editNote() {
        File directory = new File(String.valueOf(this.getFilesDir()));
        File [] files = directory.listFiles();
        for(File file:files) {
            if(file.getName().equals(getIntent().getStringExtra("tag"))) {
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                    writer.write(String.valueOf(textarea.getText()));
                    writer.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        Intent mainActivity = new Intent(this,MainActivity.class);
        startActivity(mainActivity);
    }
}
