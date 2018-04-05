package com.caavo.assignment.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.caavo.assignment.R;
import com.caavo.assignment.activities.UpdateStory;
import com.caavo.assignment.models.Story;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by santo on 2/4/18.
 */

public class StoryListAdapter extends RecyclerView.Adapter<StoryListAdapter.ViewHolder> {

    private Context context;
    private List<Story> list;
    private String user_id;

    public StoryListAdapter(Context context, String user_id) {
        this.context = context;
        list = new ArrayList<>();
        this.user_id = user_id;
    }

    public void updateList(List<Story> list) {
        this.list.clear();
        this.list.addAll(list);
    }

    @Override
    public StoryListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater
                    .from(parent.getContext())
                    .inflate(R.layout.story_row, parent, false));
    }

    @Override
    public void onBindViewHolder(StoryListAdapter.ViewHolder holder, int position) {
        final Story story = list.get(position);
        if (story != null) {
            holder.title.setText(story.getTitle());
            holder.content.setText(story.getContent());
            holder.layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    context.startActivity(new Intent(context, UpdateStory.class)
                            .putExtra("story_id", story.getId())
                            .putExtra("user_id", user_id)
                            .putExtra("title", story.getTitle()));
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView title, content;
        private LinearLayout layout;
        public ViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            content = (TextView) itemView.findViewById(R.id.content);
            layout = (LinearLayout) itemView.findViewById(R.id.layout);
        }
    }
}
