package com.caavo.assignment.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.caavo.assignment.ApplicationLoader;
import com.caavo.assignment.R;
import com.caavo.assignment.database.BaseDatabase;

import java.util.UUID;

public class CreateStory extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_story);

        final EditText title = (EditText)findViewById(R.id.title);
        final EditText content = (EditText)findViewById(R.id.content);

        findViewById(R.id.create_story).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(title.getText().toString().isEmpty()) {
                    title.setError("Title can't be empty");
                    return;
                }

                if(content.getText().toString().isEmpty()) {
                    content.setError("Title can't be empty");
                    return;
                }

                ApplicationLoader app = (ApplicationLoader)getApplication();
                BaseDatabase db = app.getDB();
                db.insertStory(UUID.randomUUID().toString(), getIntent().getStringExtra("id")
                                , title.getText().toString()
                                , content.getText().toString());
            }
        });
    }
}
