package com.nuc.postinfo.presenter;

import com.nuc.postinfo.model.Comment;
import com.nuc.postinfo.model.User;
import com.nuc.postinfo.services.QueryApi;
import com.nuc.postinfo.view.DetailActivity;

import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

;

/**
 * Created by mayurik on 01/05/2018.
 */

public class DetailPresenter {

    DetailActivity mView;
    QueryApi mFetchService;

    public DetailPresenter(DetailActivity activity, QueryApi FetchService) {

        mView = activity;
        mFetchService = FetchService;
    }



    public void loadComments() {

        mFetchService.getApi()
                .getComments(mView.getPostId())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Comment>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                    //TODO
                    }

                    @Override
                    public void onNext(List<Comment> comments) {
                        if(mView != null)
                            mView.displayComments(comments);
                    }
                });
    }

    public void loadUserName()
    {

        mFetchService.getApi()
                .getUserName(mView.getUserId())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<User>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<User> users)
                    {
                        if(mView != null)
                         mView.displayUserName(users);
                    }
                });
    }

}
