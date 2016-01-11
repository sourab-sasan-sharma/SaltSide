package com.sourab.saltside.network;

import com.sourab.saltside.beans.UserContent;

import java.util.List;

import retrofit.Call;
import retrofit.http.GET;

/**
 * Created by Sourab Sharma (sourab.sharma@live.in)  on 1/9/2016.
 */
public class RetroServices {

    public interface UserContentAPI {
        @GET("/maclir/f715d78b49c3b4b3b77f/raw/8854ab2fe4cbe2a5919cea97d71b714ae5a4838d/items.json")
        Call<List<UserContent>> getUserContentList();
    }
}
