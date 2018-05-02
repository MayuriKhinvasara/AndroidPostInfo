package com.nuc.postinfo.presenter;

import com.nuc.postinfo.model.Post;
import com.nuc.postinfo.services.QueryApi;
import com.nuc.postinfo.view.PostsActivity;

import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by mayurik on 01/05/2018.
 */

public class ListPresenter {

    PostsActivity mView;
    QueryApi mQueryApi;

    public ListPresenter(PostsActivity view, QueryApi api) {

        mView = view;
        mQueryApi = api;
    }

    public void loadPosts() {

        mQueryApi.getApi()
                .getPosts()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Post>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<Post> posts) {

                        if(mView != null)
                              mView.displayPosts(posts);
                    }
                });
    }
}
