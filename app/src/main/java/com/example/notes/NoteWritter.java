package com.example.notes;

import android.content.Context;
import android.content.Intent;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

public class NoteWritter {
    Note note;
    int fileCounter;
    public NoteWritter(Note note) {
        this.note = note;
    }
    public void saveNote(Context context, String nazwaPliku, String tresc)
    {
        try (FileOutputStream outputStream = context.openFileOutput(nazwaPliku, Context.MODE_PRIVATE)) {
        outputStream.write(tresc.getBytes());
        outputStream.close();
        System.out.println("Notatka została zapisana do pliku.");
        File plik = new File(context.getFilesDir(), nazwaPliku);
        String sciezka = plik.getAbsolutePath();
        System.out.println("Plik znajduje się w lokalizacji: " + sciezka);
        File katalog = new File(String.valueOf(context.getFilesDir()));
        File [] files = katalog.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isFile()) {
                        fileCounter++;
                    }
                }
            }
            System.out.println(fileCounter + " jest plikow");
    } catch (IOException e) {
        e.printStackTrace();
    }
    }
}
