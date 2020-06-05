package com.example.mvparchitecturedemo.views.movie_list;

import com.example.mvparchitecturedemo.models.MovieListResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.mvparchitecturedemo.network.ApiClient.getApiInterface;
import static com.example.mvparchitecturedemo.utils.Configs.API_KEY;

public class MovieListModel implements MovieListContract.Model {

  @Override public void getModelList(final OnFinishedListener onFinishedListener, int pageNo) {
    try {
      getApiInterface().getPopularMovies(API_KEY, pageNo)
          .enqueue(new Callback<MovieListResponse>() {
            @Override
            public void onResponse(Call<MovieListResponse> call,
                Response<MovieListResponse> response) {
              if (response != null && response.code() == 200) {
                onFinishedListener.onFinished(response.body().getResults());
              }
            }

            @Override public void onFailure(Call<MovieListResponse> call, Throwable t) {
              onFinishedListener.onFailure(t);
            }
          });
    } catch (NullPointerException e) {
      e.printStackTrace();
    }
  }
}
