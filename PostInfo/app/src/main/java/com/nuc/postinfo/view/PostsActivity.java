package com.nuc.postinfo.view;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.nuc.postinfo.R;
import com.nuc.postinfo.adapter.PostsAdapter;
import com.nuc.postinfo.adapter.RVEmptyObserver;
import com.nuc.postinfo.model.Post;
import com.nuc.postinfo.presenter.ListPresenter;
import com.nuc.postinfo.services.QueryApi;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class PostsActivity extends AppCompatActivity {

    @BindView(R.id.posts_recycler_view)
    RecyclerView mRecycleViewPosts;
    @BindView(R.id.emptyView)
    TextView emptyView;

    PostsAdapter mPostsAdapter;
    ListPresenter mListPresenter;
    LinearLayoutManager linearLayoutManager;
    QueryApi mQueryApi;
    ArrayList<Post> dummyPosts = new ArrayList<Post>();
    private Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posts_list);
        unbinder = ButterKnife.bind(this);

        initializeRecyclerView();

        mQueryApi = new QueryApi();
        mListPresenter = new ListPresenter(PostsActivity.this, mQueryApi);
        mListPresenter.loadPosts();

    }

    private void initializeRecyclerView() {

        linearLayoutManager = new LinearLayoutManager(this);
        mRecycleViewPosts.setLayoutManager(linearLayoutManager);
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

    @Override
    public void onDestroy() {
        super.onDestroy();

        // unbind the view to free some memory
        unbinder.unbind();
    }

}
