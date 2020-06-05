package com.example.mvparchitecturedemo.views.movie_list;

import com.example.mvparchitecturedemo.models.Movie;
import java.util.List;

public interface MovieListContract {
  interface Model {
    interface OnFinishedListener {
      void onFinished(List<Movie> movieList);

      void onFailure(Throwable t);
    }

    void getModelList(OnFinishedListener onFinishedListener, int pageNo);
  }

  interface View {
    void showProgress();

    void hideProgress();

    void setDataToRecyclerView(List<Movie> movieList);

    void onResponseFail(Throwable t);
  }

  interface Presenter {
    void onDestroy();

    void getMoreData(int pageNo);

    void requestDataFromServer();
  }
}
