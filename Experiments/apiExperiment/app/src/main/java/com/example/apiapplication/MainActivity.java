package com.example.apiapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.widget.TextView;
import android.os.Bundle;

import com.android.volley.Response;
import com.google.gson.annotations.SerializedName;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView apiText1 = findViewById(R.id.main_activity_textBox);

        Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

        PosterApi apiClient = retrofit.create(PosterApi.class);

        apiClient.getFirstPost().enqueue(new Callback<Poster>(){

            @Override
            public void onResponse(Call<Poster> call, retrofit2.Response<Poster> response) {
                apiText1.setText(response.body().getBodyText());
            }

            @Override
            public void onFailure(Call<Poster> call, Throwable t){
                // Use to log failures using throwable t
            }
        });
    }
}

interface PosterApi{

    @GET("posts/1")
    Call<Poster> getFirstPost();

}

class Poster{
    private int userId;
    private int id;
    private String title;
    @SerializedName("body")
    private String bodyText;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBodyText() {
        return bodyText;
    }

    public void setBodyText(String bodyText) {
        this.bodyText = bodyText;
    }
}