package com.pens.it.d4b2018.clara_android.data.remotes;

import com.pens.it.d4b2018.clara_android.data.models.User;
import com.pens.it.d4b2018.clara_android.data.models.UserResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface APIServices {

    @POST("/api/login")
    @FormUrlEncoded
    Call<UserResponse> performLogin(@Body User user);

    @POST("/api/register/student")
    @FormUrlEncoded
    Call<UserResponse> performRegister(@Body User user);

}
