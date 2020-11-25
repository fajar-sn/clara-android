package com.pens.it.d4b2018.clara_android.data.remotes;

import com.pens.it.d4b2018.clara_android.data.models.LoginRequest;
import com.pens.it.d4b2018.clara_android.data.models.User;
import com.pens.it.d4b2018.clara_android.data.models.LoginResponse;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;

public interface APIService {

    @POST(APIUtils.LOGIN_URL)
    Call<LoginResponse> performLogin(@Body LoginRequest loginRequest);

    @POST(APIUtils.REGISTER_URL)
    Call<User> performRegister(@Body User user);

}
