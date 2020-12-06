package com.pens.it.d4b2018.clara_android.backup.data.remotes;

import com.pens.it.d4b2018.clara_android.backup.data.models.LoginRequest;
import com.pens.it.d4b2018.clara_android.backup.data.models.LoginResponse;
import com.pens.it.d4b2018.clara_android.backup.data.models.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface APIService {

    @POST(APIUtils.LOGIN_URL)
    Call<LoginResponse> performLogin(@Body LoginRequest loginRequest);

    @POST(APIUtils.REGISTER_URL)
    Call<User> performRegister(@Body User user);

}
