package com.example.frontend.api;

import com.example.frontend.model.FriendRequest;
import com.example.frontend.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Body;

/**
 * API connection to database for User objects
 */
public interface UserApi {

    //Returns the entire database of users
    @GET("user/all")
    Call<List<User>> GetAllUsers();

    //Returns the list of friends based on a user id
    @GET("user/getFriends/{id}")
    Call<List<User>> getFriendsById(@Path("id") long id);

    //Returns a list of friend requests based on a user id
    @GET("user/getRequests/{id}")
    Call<List<FriendRequest>> getRequestsById(@Path("id") long id);

    //Gets a particular user based on the user id
    @GET("user/getByID/{id}")Call<User> getUserById(@Path("id") long id);

    //gets a freind request based on id
    @GET("friendRequest/getByID/{id}") Call<FriendRequest> getRequest(@Path("id") long id);

    @POST("user/post/{u}/{p}/{d}")
    Call<com.example.frontend.model.User> PostUserByPath(@Path("u") java.lang.String loginname, @Path("p") java.lang.String password, @Path("d") java.lang.String displayname);

    //makes friend requests based on two user ids NOTE: user from and user to are both numbers
    @POST("friendRequest/post/{userTo}/{userFrom}")
    Call<Boolean> PostFriendRequestByPath(@Path("userTo") long userTo, @Path("userFrom") long userFrom);

    @POST("friendRequest/deny/{requestId}")
    void DenyFriendRequestByPath(@Path("requestId") long requestId);

    @POST("friendRequest/accept/{requestId}")
    Call<String> AcceptFriendRequestByPath(@Path("requestId") long requestId);

    @DELETE("user/delete/{id}")
    void DeleteById(@Path("id") long id);



}
