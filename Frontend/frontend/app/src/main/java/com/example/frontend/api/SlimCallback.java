package com.example.frontend.api;


import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Callback Class that generates a callback attached to a lambdaInterface to run
 * on callback trigger
 * @param <T>
 */
public class SlimCallback<T> implements Callback<T> {

    LambdaInterface<T> lambdaInterface;

    String logTag;


    /**
     * Constructor for a Callback function
     * @param lambdaInterface Lambda interface to execute on callback
     */
    public SlimCallback(LambdaInterface<T> lambdaInterface){
        this.lambdaInterface = lambdaInterface;
    }

    /**
     * Constructor for a Callback function using a custom tag
     * @param lambdaInterface Lambda interface to execute on callback
     * @param customTag tag to reference callback
     */
    public SlimCallback(LambdaInterface<T> lambdaInterface, String customTag){
        this.lambdaInterface = lambdaInterface;
        this.logTag = customTag;
    }



    @Override
    /**
     * Execution of callback on response trigger
     */
    public void onResponse(Call<T> call, Response<T> response) {

        if (response.isSuccessful()){
            lambdaInterface.doSomething(response.body());
        }
        else{
            Log.d(logTag, "Code:  "+response.code() + "    Msg:  "+response.message());
        }

    }

    @Override
    /**
     * Failure response of callback on response trigger
     */
    public void onFailure(Call<T> call, Throwable t) {

        Log.d(logTag, "Thrown:  "+t.getMessage());

    }
}


