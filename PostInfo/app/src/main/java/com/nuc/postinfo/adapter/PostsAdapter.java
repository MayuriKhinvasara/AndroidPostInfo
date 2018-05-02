package com.nuc.postinfo.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nuc.postinfo.R;
import com.nuc.postinfo.model.Post;
import com.nuc.postinfo.util.Utility;
import com.nuc.postinfo.view.DetailActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by mayurik on 01/05/2018.
 */

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.ViewHolder> {
    private ArrayList<Post> values;
    private PostsViewWrapper wrapperView;

    public PostsAdapter(ArrayList<Post> myDataset) {

        values = myDataset;
        Log.d(Utility.LOG_TAG, " Total Data count = " +
                Integer.toString(values.size()));
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case

        public View layout;

        public ViewHolder(View v) {
            super(v);
            layout = v;
            wrapperView = new PostsViewWrapper();
        }
    }

    public void add(int position, Post post) {

        values.add(position, post);
        notifyItemInserted(position);
    }

    public void addAll(ArrayList<Post> myDataset) {

        values = myDataset;
        Log.d(Utility.LOG_TAG, "New Data Total Data count = " + Integer.toString(values.size()));
    }

    public void remove(int position) {
        values.remove(position);
        notifyItemRemoved(position);
    }

    // Create new views (invoked by the layout manager)
    @Override
    public PostsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.recyleview_item_post, parent, false);

        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        final Post post = values.get(position);

        if (post != null) {
          //  wrapperView = new PostsViewWrapper();
            ButterKnife.bind(wrapperView, holder.layout);
            wrapperView.load(post);
            holder.layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    launchDetailActivity(v.getContext(), post, position);
                }
            });
        }
    }


    class PostsViewWrapper {
        @BindView(R.id.textViewItemPostTitle)
        TextView txtTitle;

        @BindView(R.id.textViewItemPostBody)
        TextView txtBody;
        // public ImageView imageView;


        public void load(Post post) {
            Log.d(Utility.LOG_TAG, " POST = " + post.title + " Email = " + post.email);
            txtTitle.setText(" Title: " + post.title);
            txtBody.setText(" Body: " + post.body);
        }

    }

    private void launchDetailActivity(Context context, Post post, int position) {
        Log.d(Utility.LOG_TAG, " POST ID Clicked = " + post.id + "  title = " + post.title + " position = " + position);

        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra(Utility.POST_TITLE, post.title);
        intent.putExtra(Utility.POST_BODY, post.body);
        intent.putExtra(Utility.POST_ID, post.id);
        intent.putExtra(Utility.USER_ID, post.userId);

        context.startActivity(intent);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return values.size();
    }

}
