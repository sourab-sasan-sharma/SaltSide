package com.sourab.saltside.network;

import android.content.Context;

import com.sourab.saltside.CONSTANTS.CONSTANTS;
import com.sourab.saltside.beans.UserContent;

import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by Sourab Sharma (sourab.sharma@live.in)  on 1/9/2016.
 */
public class RetroWrapper {

    Context context = null;
    Retrofit retrofit = null;
    Object listener = null;

    public RetroWrapper(Context context, Object listener) {
        this.listener = listener;
        this.context = context;
        retrofit = new Retrofit.Builder()
                .baseUrl(CONSTANTS.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public void getUserContentList() {
        // prepare call in Retrofit 2.0
        RetroServices.UserContentAPI userContentAPI = retrofit.create(RetroServices.UserContentAPI.class);
        Call<List<UserContent>> call = userContentAPI.getUserContentList();
        //asynchronous call
        call.enqueue((Callback<List<UserContent>>) listener);
    }
}
