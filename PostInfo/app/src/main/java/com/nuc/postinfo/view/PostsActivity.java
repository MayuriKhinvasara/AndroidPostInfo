package com.nuc.postinfo.view;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import com.nuc.postinfo.R;
import com.nuc.postinfo.adapter.PostsAdapter;
import com.nuc.postinfo.adapter.RVEmptyObserver;
import com.nuc.postinfo.model.Post;
import com.nuc.postinfo.presenter.ListPresenter;
import com.nuc.postinfo.services.QueryApi;

public class PostsActivity extends AppCompatActivity {


    RecyclerView mRecycleViewPosts;
    PostsAdapter mPostsAdapter;
    ListPresenter mListPresenter;
    LinearLayoutManager linearLayoutManager;
    QueryApi mQueryApi;
    ArrayList<Post> dummyPosts = new ArrayList<Post>();
    private TextView emptyView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posts_list);

        initializeRecyclerView();

        mQueryApi = new QueryApi();
        mListPresenter = new ListPresenter(PostsActivity.this, mQueryApi);
        mListPresenter.loadPosts();

    }

    private void initializeRecyclerView() {
        mRecycleViewPosts = (RecyclerView) findViewById(R.id.posts_recycler_view);
        emptyView = (TextView) findViewById(R.id.emptyView);
        linearLayoutManager = new LinearLayoutManager(this);
        mRecycleViewPosts.setLayoutManager(linearLayoutManager);

        //ArrayList<Post> dummyPosts= createDummyData();
        mPostsAdapter = new PostsAdapter(dummyPosts);

        mRecycleViewPosts.setAdapter(mPostsAdapter);

        //set emptyview
        mPostsAdapter.registerAdapterDataObserver(new RVEmptyObserver(mRecycleViewPosts, emptyView));

        //list item divider

        DividerItemDecoration divider = new
                DividerItemDecoration(mRecycleViewPosts.getContext(),
                DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(getBaseContext(),
                R.drawable.list_divider));
        mRecycleViewPosts.addItemDecoration(divider);
    }

    public void displayPosts(List<Post> posts) {

        mPostsAdapter.addAll((ArrayList<Post>) posts);
        mPostsAdapter.notifyDataSetChanged();
    }

  /*  @NonNull
    private ArrayList<Post> createDummyData() {

        Post post = new Post();
        post.body = "body1";
        post.title = "title 1";
        dummyPosts.add(post);
        post = new Post();

        post.body = "body2";
        post.title = "title 2";
        dummyPosts.add(post);
        post = new Post();


        post.body = "body3";
        post.title = "title 3";
        dummyPosts.add(post);

        Log.d(Utility.LOG_TAG, " dummypost count = " + Integer.toString(dummyPosts.size()));
        return dummyPosts;
    }*/



}
