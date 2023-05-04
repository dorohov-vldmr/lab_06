package ru.rabiarill.lab_06;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity2 extends AppCompatActivity {

    EditText txt_title;
    EditText txt_content;

    int pos;

    @SuppressLint({"MissingInflatedId", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        txt_title = findViewById(R.id.txt_title_act2);
        txt_content = findViewById(R.id.txt_content_act2);

        Intent i = getIntent();
        pos = i.getIntExtra("my-note-index", -1);
        txt_title.setText(i.getStringExtra("my-note-title"));
        txt_content.setText(i.getStringExtra("my-note-content"));
    }

    public void on_save_click(View v){
        Intent i = new Intent();
        i.putExtra("my-note-index", pos); // prepare updated note data back to first activity
        i.putExtra("my-note-title", txt_title.getText () .toString());
        i.putExtra("my-note-content", txt_content.getText() .toString());

        setResult (RESULT_OK, i); // return to first activity
        finish();
    }

    public void on_cancel_click(View v) {
        finish();
    }
}