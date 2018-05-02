package com.nuc.postinfo.services;

import java.util.List;

import com.nuc.postinfo.model.Comment;
import com.nuc.postinfo.model.Post;
import com.nuc.postinfo.model.User;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;
import rx.Observable;
/**
 * Created by mayurik on 01/05/2018.
 */

public class QueryApi {

    private static final String BASE_SERVER_URL = "http://jsonplaceholder.typicode.com";
    private QueryApiInterface mQueryApi;

    public QueryApi() {


        RequestInterceptor requestInterceptor = new RequestInterceptor() {
            @Override
            public void intercept(RequestFacade request) {
                request.addHeader("Accept", "application/json");
            }
        };

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(BASE_SERVER_URL)
                .setRequestInterceptor(requestInterceptor)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();

        mQueryApi = restAdapter.create(QueryApiInterface.class);
    }

    public QueryApiInterface getApi() {

        return mQueryApi;
    }




    public interface QueryApiInterface {

        @GET("/posts")
        public Observable<List<Post>>
        getPosts();


        @GET("/posts/{id}")
        public Observable<Post>
        getPost(@Path("id") int postId);

        @GET("/comments")
        public Observable<List<Comment>>
        getComments(@Query("postId") int postId);

        @GET("/posts/{userId}")
        public Observable<Post>
        getUser(@Path("userId") int id);


        @GET("/users")
        public Observable<List<User>>
        getUserName(@Query("id") int id);

       /* @POST("/posts")
        public Observable<Post>
        postPost(Post post);*/
    }
}
