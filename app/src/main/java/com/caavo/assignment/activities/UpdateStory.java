package com.caavo.assignment.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.caavo.assignment.ApplicationLoader;
import com.caavo.assignment.R;

public class UpdateStory extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_story);

        final EditText text = (EditText)findViewById(R.id.content);


        findViewById(R.id.update).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ApplicationLoader loader = (ApplicationLoader) getApplication();
                loader.getDB().updateStory(getIntent().getStringExtra("story_id")
                                , getIntent().getStringExtra("user_id"), text.getText().toString()
                                , getIntent().getStringExtra("title"));
            }
        });

        text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(text.getText().toString().length() > 49) {
                    text.setError("Not more than 50");
                } else {
                    text.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
}
