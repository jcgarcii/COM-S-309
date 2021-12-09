package test.connect.myapplication.api;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import test.connect.myapplication.model.User;

public interface UserApi {

    @GET("user/all")
    Call<List<test.connect.myapplication.model.User>> GetAllUsers();

    @POST("user/post/{a}/{b}/{c}")
    Call<test.connect.myapplication.model.User> PostUserByPath(@Path("a") String loginname, @Path("b") String password, @Path("c") String displayname);

    @POST("trivia/post")
    Call<test.connect.myapplication.model.User> PostUserBody(@Body test.connect.myapplication.model.User newUser);
}
