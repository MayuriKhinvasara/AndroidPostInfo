package com.nuc.postinfo.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.nuc.postinfo.R;
import com.nuc.postinfo.model.Comment;
import com.nuc.postinfo.model.User;
import com.nuc.postinfo.presenter.DetailPresenter;
import com.nuc.postinfo.services.QueryApi;
import com.nuc.postinfo.util.Utility;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class DetailActivity extends AppCompatActivity {

    DetailPresenter mDetailPresenter;
    QueryApi queryApi;
    LinearLayoutManager linearLayoutManager;
    ArrayList<Comment> dummyComments = new ArrayList<Comment>();

    protected int mPostId;

    @BindView(R.id.textViewDetailTitle)
    TextView mtvTitle;

    @BindView(R.id.textViewDetailUserName)
    TextView mtvUsername;

    @BindView(R.id.textViewDetailBody)
   TextView mtvmBody;

    @BindView(R.id.textViewDetailCount)
     TextView mCommentCount;

    @BindView(R.id.imageViewItemDetail)
    ImageView imageView;

    protected int mUserId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ButterKnife.bind(this);


        //for back button
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        mPostId = getIntent().getIntExtra(Utility.POST_ID, 0);
        String mBody = getIntent().getStringExtra(Utility.POST_BODY);
        String mTitle = getIntent().getStringExtra(Utility.POST_TITLE);
        mUserId = getIntent().getIntExtra(Utility.USER_ID, 0);

        Log.d(Utility.LOG_TAG, " ***************  RECEIVED THRU INTENT  =  post.id= " + mPostId + "  userid = " + mUserId);
        //"   email = "+ post.email+ "  comments.name= "+post.name);

        queryApi = new QueryApi();
        mDetailPresenter = new DetailPresenter(this, queryApi);
        mDetailPresenter.loadComments();
        mDetailPresenter.loadUserName();
        // mDetailPresenter.loadPost();


        mtvTitle.setText("Title :  " + mTitle);
        mtvmBody.setText("Body : " + mBody);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public int getPostId() {

        Log.d(Utility.LOG_TAG, " postid returned in view.getpostid =" + mPostId);
        return mPostId;
    }


    public int getUserId() {
        Log.d(Utility.LOG_TAG, " userid returned in view.getUSERid =" + mUserId);
        return mUserId;
    }

    public void displayComments(List<Comment> comments) {
        Log.d(Utility.LOG_TAG, " displayComments COUNT =  " + comments.size() + "  comments = " + comments.get(0));
        mCommentCount.setText("Total Comments :  " + comments.size());
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public void displayUserName(List<User> users) {

        ArrayList<User> u1 = (ArrayList<User>) users;
        if (users.size() > 0) {

            User currentUser = (u1.get(0));
            Log.d(Utility.LOG_TAG, " displayUSERNAME SIZE=  " + users.size() + "  currentUser.getUserName()  : USERNAME = " + currentUser.getusername());
            String email = currentUser.email;
            mtvUsername.setText("By Username : " + currentUser.username + " , email : " + email + " . name : " + currentUser.name );
            Log.d(Utility.LOG_TAG, "\n\n\t Username = " + currentUser.username + " \t email = " + email + " name = " + currentUser.name + "  id = " + currentUser.id);
            if(email !=null)
                loadImage(email);
        }

    }

    public void loadImage(String emailId)
    {
    if(imageView!=null)
         Glide
                .with(this)
                .load(Utility.BASE_IMAGE_URL+emailId)
                 .thumbnail(0.5f)
                .into(imageView);
    }
}

