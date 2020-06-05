package com.example.mvparchitecturedemo.views.movie_list;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.mvparchitecturedemo.R;
import com.example.mvparchitecturedemo.adapters.MovieAdapter;
import com.example.mvparchitecturedemo.databinding.FragmentMovieListBinding;
import com.example.mvparchitecturedemo.models.Movie;
import com.example.mvparchitecturedemo.utils.GridSpacingItemDecoration;
import java.util.ArrayList;
import java.util.List;

import static com.example.mvparchitecturedemo.utils.EssentialMethods.hideView;
import static com.example.mvparchitecturedemo.utils.EssentialMethods.showShortToast;
import static com.example.mvparchitecturedemo.utils.EssentialMethods.showView;
import static com.example.mvparchitecturedemo.utils.GridSpacingItemDecoration.dpToPx;

public class MovieListFragment extends Fragment implements MovieListContract.View {

  private FragmentMovieListBinding mBinder;
  private MovieListPresenter movieListPresenter;
  private MovieAdapter movieAdapter;
  private GridLayoutManager layoutManager;
  private List<Movie> movieList;
  private int visibleItemCount, totalItemCount, firstVisibleItem, previousTotal = 0, pageNo = 1,
      visibleThreshold = 5;
  private boolean loading = true;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    mBinder = FragmentMovieListBinding.inflate(inflater, container, false);
    return mBinder.getRoot();
  }

  @Override public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    try {
      super.onViewCreated(view, savedInstanceState);

      Toolbar toolbar = getActivity().findViewById(R.id.tool_bar_movie_list);
      toolbar.setTitle(R.string.movies);

      initViews();

      setScrollListener();

      movieListPresenter = new MovieListPresenter(this);
      movieListPresenter.requestDataFromServer();
    } catch (NullPointerException e) {
      e.printStackTrace();
    }
  }

  private void initViews() {
    try {
      movieList = new ArrayList<>();
      movieAdapter = new MovieAdapter(getActivity(), movieList);

      layoutManager = new GridLayoutManager(getActivity(), 2);
      mBinder.recyclerViewMovieList.setLayoutManager(layoutManager);

      mBinder.recyclerViewMovieList.addItemDecoration(
          new GridSpacingItemDecoration(2, dpToPx(getActivity(), 10), true));
      mBinder.recyclerViewMovieList.setItemAnimator(new DefaultItemAnimator());

      mBinder.recyclerViewMovieList.setAdapter(movieAdapter);
    } catch (NullPointerException e) {
      e.printStackTrace();
    }
  }

  private void setScrollListener() {
    try {
      mBinder.recyclerViewMovieList.addOnScrollListener(new RecyclerView.OnScrollListener() {
        @Override public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
          super.onScrolled(recyclerView, dx, dy);

          visibleItemCount = mBinder.recyclerViewMovieList.getChildCount();
          totalItemCount = mBinder.recyclerViewMovieList.getChildCount();
          firstVisibleItem = layoutManager.findFirstVisibleItemPosition();

          if (loading) {
            if (totalItemCount > previousTotal) {
              loading = false;
              previousTotal = totalItemCount;
            }
          }

          if (!loading && (totalItemCount - visibleItemCount) <= (firstVisibleItem
              + visibleThreshold)) {
            movieListPresenter.getMoreData(pageNo);
            loading = true;
          }
        }
      });
    } catch (NullPointerException e) {
      e.printStackTrace();
    }
  }

  @Override public void showProgress() {
    showView(mBinder.progressBarMovieList);
  }

  @Override public void hideProgress() {
    hideView(mBinder.progressBarMovieList);
  }

  @Override public void setDataToRecyclerView(List<Movie> movieList) {
    try {
      this.movieList.addAll(movieList);
      movieAdapter.notifyDataSetChanged();

      pageNo++;
    } catch (NullPointerException e) {
      e.printStackTrace();
    }
  }

  @Override public void onResponseFail(Throwable t) {
    try {
      showShortToast(getActivity(), getString(R.string.error_getting_list));
    } catch (NullPointerException e) {
      e.printStackTrace();
    }
  }
}