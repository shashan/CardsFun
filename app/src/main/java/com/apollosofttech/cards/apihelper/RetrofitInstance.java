package com.apollosofttech.cards.apihelper;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import static com.apollosofttech.cards.app.Constans.URL;

/**
 * Created by ANDROID on 12-Jun-18.
 */

public class RetrofitInstance {

    private static Retrofit retrofit = null;

    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {

            retrofit = new Retrofit.Builder()
                    .baseUrl(URL)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
//                    .client(getClient().build())
                    .build();
        }
        return retrofit;
    }


}
