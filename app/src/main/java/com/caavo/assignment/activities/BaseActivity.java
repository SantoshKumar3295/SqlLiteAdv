package com.caavo.assignment.activities;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.caavo.assignment.Adapter.StoryListAdapter;
import com.caavo.assignment.ApplicationLoader;
import com.caavo.assignment.R;
import com.caavo.assignment.database.BaseDatabase;
import com.caavo.assignment.models.Story;

import java.util.ArrayList;
import java.util.List;

public class BaseActivity extends AppCompatActivity {
    private String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        try {
            Intent intent = getIntent();
            id = intent.getStringExtra("id");


            RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
            LinearLayoutManager manager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(manager);
            StoryListAdapter listAdapter = new StoryListAdapter(this, id);
            recyclerView.setAdapter(listAdapter);


            ApplicationLoader loader = (ApplicationLoader) getApplication();
            Cursor rs= loader.getDB().getAllStory();
            List<Story> list = new ArrayList<>();
            if(rs !=null) {

                for(rs.moveToFirst(); !rs.isAfterLast(); rs.moveToNext()) {
                    Story story = new Story();
                    story.setTitle(rs.getString(rs.getColumnIndex(BaseDatabase.STORY_TITLE_COLUMN)));
                    story.setContent(rs.getString(rs.getColumnIndex(BaseDatabase.STORY_CONTENT_COLUMN)));
                    story.setId(rs.getString(rs.getColumnIndex(BaseDatabase.STORY_ID_COLUMN)));
                    boolean flag = false;
                    for(Story x: list) {
                        if(x.getId().equals(story.getId())) {
                            x.setContent(x.getContent()+story.getContent());
                            flag=true;
                        }
                    }
                    if(!flag)
                    list.add(story);
                }

            }
            Log.d("tag", "some");

            listAdapter.updateList(list);
            FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(BaseActivity.this, CreateStory.class).putExtra("id",id));
                }
            });
        } catch (Exception e) {

        }
    }
}
