package com.example.mvparchitecturedemo.views.movie_list;

import com.example.mvparchitecturedemo.models.Movie;
import java.util.List;

class MovieListPresenter
    implements MovieListContract.Presenter, MovieListContract.Model.OnFinishedListener {

  private MovieListContract.View movieListView;
  private MovieListContract.Model movieListModel;

  public MovieListPresenter(MovieListContract.View movieListView) {
    this.movieListView = movieListView;
    movieListModel = new MovieListModel();
  }

  @Override public void onFinished(List<Movie> movieList) {
    movieListView.setDataToRecyclerView(movieList);
    if (movieListView != null) {
      movieListView.hideProgress();
    }
  }

  @Override public void onFailure(Throwable t) {
    movieListView.onResponseFail(t);
    if (movieListView != null) {
      movieListView.hideProgress();
    }
  }

  @Override public void onDestroy() {
    this.movieListView = null;
  }

  @Override public void getMoreData(int pageNo) {
    if (movieListView != null) {
      movieListView.showProgress();
    }

    movieListModel.getModelList(this, pageNo);
  }

  @Override public void requestDataFromServer() {
    if (movieListModel != null) {
      movieListView.showProgress();
    }

    movieListModel.getModelList(this, 1);
  }
}
