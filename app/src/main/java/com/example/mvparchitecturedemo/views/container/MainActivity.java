package com.example.mvparchitecturedemo.views.container;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.example.mvparchitecturedemo.R;
import com.example.mvparchitecturedemo.databinding.ActivityMainBinding;
import com.example.mvparchitecturedemo.views.movie_list.MovieListFragment;

public class MainActivity extends AppCompatActivity {

  private ActivityMainBinding mBinder;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    try {
      super.onCreate(savedInstanceState);
      mBinder = ActivityMainBinding.inflate(getLayoutInflater());
      setContentView(mBinder.getRoot());

      initViews();
    } catch (NullPointerException e) {
      e.printStackTrace();
    }
  }

  private void initViews() {
    try {
      getSupportFragmentManager().beginTransaction()
          .replace(R.id.container, new MovieListFragment())
          .commit();
    } catch (NullPointerException e) {
      e.printStackTrace();
    }
  }
}