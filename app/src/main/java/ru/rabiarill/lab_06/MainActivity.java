package ru.rabiarill.lab_06;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    ArrayAdapter<Note> adp;
    int sel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adp = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);

        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) ListView lst = findViewById(R.id.note_list);
        lst.setAdapter (adp);

        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                sel = i;
            }
        });
    }

    public void on_new_click(View view){

        Note n = new Note(); // create new note
        n.title = "New note";
        n. content = "Some content";

        adp.add(n); // add to list
        int pos = adp.getPosition(n); // get it's position (array element index)

        Intent i = new Intent (this, MainActivity2.class);
        i.putExtra("my-note-index", pos); // share note data with new activity
        i.putExtra("my-note-title", n.title);
        i.putExtra("my-note-content", n.content);

        startActivityForResult(i, 12345); // show note editing activity
    }

    public void on_edit_click(View view){
        try {
            Note n = adp.getItem(sel);
            Intent i = new Intent (this, MainActivity2.class);
            i.putExtra("my-note-index", sel); // share note data with new activity
            i.putExtra("my-note-title", n.title);
            i.putExtra("my-note-content", n.content);

            startActivityForResult(i, 12345);

        }catch (Exception e){

        }
    }

    public void on_delete_click(View view){
        try {
            Note noteToDel = adp.getItem(sel);
            adp.remove(noteToDel);
        }catch (Exception ignored){

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (data != null) { // user pressed "save" button
            int pos = data.getIntExtra("my-note-index",  -1);
            String title = data.getStringExtra("my-note-title");
            String content = data.getStringExtra("my-note-content");

            Note n = adp.getItem(pos) ;
            n.title = title;
            n. content = content;

            adp.notifyDataSetChanged(); // update List box
        }

        super.onActivityResult (requestCode, resultCode, data);
    }

}