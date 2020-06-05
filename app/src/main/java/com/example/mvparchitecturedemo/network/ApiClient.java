package com.example.mvparchitecturedemo.network;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.mvparchitecturedemo.utils.Configs.BASE_URL;

public class ApiClient {
  private static Retrofit retrofit = null;

  private static Retrofit getClient() {
    HttpLoggingInterceptor logging = new HttpLoggingInterceptor();

    logging.setLevel(HttpLoggingInterceptor.Level.BODY);

    OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    httpClient.addInterceptor(logging);

    if (retrofit == null) {
      retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
          .addConverterFactory(GsonConverterFactory.create())
          .client(httpClient.build())
          .build();
    }
    return retrofit;
  }

  public static ApiInterface getApiInterface() {
    return getClient().create(ApiInterface.class);
  }
}
