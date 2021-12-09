package test.connect.myapplication.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import test.connect.myapplication.model.User;

public class ApiClientFactory {

    static Retrofit apiClientSeed = null;

    static Retrofit GetApiClientSeed() {

        if (apiClientSeed == null) {
            apiClientSeed = new Retrofit.Builder()
                    .baseUrl("http://coms-309-004.cs.iastate.edu:8080/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return apiClientSeed;
    }


    public static PostApi GetPostApi(){
        return GetApiClientSeed().create(PostApi.class);
    }

    public static UserApi GetUserApi(){
        return GetApiClientSeed().create(UserApi.class);
    }
}
