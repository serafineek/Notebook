package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.File;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ArrayList<Button> buttons = new ArrayList<Button>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        printNotes();
        openNote();
        Button createNote = findViewById(R.id.createNoteBtn);
        createNote.setOnClickListener(view -> {
            Intent noteActivity = new Intent(this, NoteActivity.class);
            startActivity(noteActivity);
        });
    }
    @SuppressLint("ResourceAsColor")
    public void printNotes() {
        LinearLayout notesContainer = findViewById(R.id.noteContainer);
        //Print Notes method
        File directory = new File(String.valueOf(this.getFilesDir()));
        File [] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    //Main Container
                    CardView cardView = new CardView(this);
                    cardView.setRadius(15);
                    LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    params1.setMargins(20,20,20,20);
                    cardView.setLayoutParams(params1);
                    notesContainer.addView(cardView);
                    //NoteContainer
                    RelativeLayout noteContainer = new RelativeLayout(this);
                    RelativeLayout.LayoutParams params2 = new RelativeLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, (int) (100 * getResources().getDisplayMetrics().density + 0.5f));
                    noteContainer.setLayoutParams(params2);
                    noteContainer.setBackgroundColor(getResources().getColor(R.color.noteCard));
                    noteContainer.setPadding(5,5,5,5);
                    cardView.addView(noteContainer);
                    //Note Title TextView
                    TextView noteTitle = new TextView(this);
                    LinearLayout.LayoutParams params3 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT);
                    params3.setMargins(60,0,0,0);
                    noteTitle.setLayoutParams(params3);
                    noteTitle.setTextSize(22);
                    //Set note title from file
                    noteTitle.setText(file.getName().toString());
                    noteTitle.setTypeface(Typeface.DEFAULT_BOLD);
                    noteTitle.setTextColor(getResources().getColor(R.color.white));
                    noteTitle.setGravity(Gravity.CENTER);
                    noteContainer.addView(noteTitle);
                    //Button Edit
                    Button button = new Button(this);
                    RelativeLayout.LayoutParams params4 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                    params4.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
                    params4.addRule(RelativeLayout.CENTER_IN_PARENT);
                    params4.setMargins(0,0,40,0);
                    int padding15dp = (int) (15*getResources().getDisplayMetrics().density+0.5f);
                    button.setPaddingRelative(padding15dp,padding15dp,padding15dp,padding15dp);
                    button.setText("EDYTUJ");
                    button.setTag(file.getName());
                    button.setTextSize(20);
                    button.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.backgroundColor)));
                    button.setLayoutParams(params4);
                    buttons.add(button);
                    noteContainer.addView(button);
                }
            }
        }
    }
    public void openNote() {
        for (Button btn:buttons) {
            btn.setOnClickListener(view -> {
                System.out.println(btn.getTag());
                Intent editNoteActivity = new Intent(this,EditNoteActivity.class);
                editNoteActivity.putExtra("tag",btn.getTag().toString());
                startActivity(editNoteActivity);
            });
        }
    }
}