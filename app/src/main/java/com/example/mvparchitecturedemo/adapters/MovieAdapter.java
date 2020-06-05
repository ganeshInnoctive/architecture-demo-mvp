package com.example.mvparchitecturedemo.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.example.mvparchitecturedemo.R;
import com.example.mvparchitecturedemo.databinding.ItemRowMovieBinding;
import com.example.mvparchitecturedemo.models.Movie;
import java.util.List;

import static com.example.mvparchitecturedemo.utils.Configs.IMAGE_BASE_URL;
import static com.example.mvparchitecturedemo.utils.EssentialMethods.hideView;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

  private Context context;
  private List<Movie> movieList;
  private ItemRowMovieBinding mBinder;

  public MovieAdapter(Context context, List<Movie> movieList) {
    this.context = context;
    this.movieList = movieList;
  }

  @NonNull @Override public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    mBinder = ItemRowMovieBinding.inflate(LayoutInflater.from(context), parent, false);
    return new ViewHolder(mBinder);
  }

  @Override public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    try {
      holder.setDataToViews(movieList.get(position));
    } catch (NullPointerException e) {
      e.printStackTrace();
    }
  }

  @Override public long getItemId(int position) {
    return position;
  }

  @Override public int getItemCount() {
    return movieList.size();
  }

  @Override public int getItemViewType(int position) {
    return position;
  }

  public class ViewHolder extends RecyclerView.ViewHolder {
    private ItemRowMovieBinding itemRowMovieBinder;

    public ViewHolder(ItemRowMovieBinding itemRowMovieBinder) {
      super(itemRowMovieBinder.getRoot());
      this.itemRowMovieBinder = itemRowMovieBinder;
    }

    void setDataToViews(Movie movie) {
      try {
        Glide.with(context)
            .load(IMAGE_BASE_URL + movie.getThumbPath())
            .listener(
                new RequestListener<Drawable>() {
                  @Override public boolean onLoadFailed(@Nullable GlideException e, Object model,
                      Target<Drawable> target, boolean isFirstResource) {
                    hideView(itemRowMovieBinder.progressBarMovieThumbnail);
                    return false;
                  }

                  @Override
                  public boolean onResourceReady(Drawable resource, Object model,
                      Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                    hideView(itemRowMovieBinder.progressBarMovieThumbnail);
                    return false;
                  }
                })
            .apply(new RequestOptions().placeholder(R.drawable.ic_error_grey_24)
                .error(R.drawable.ic_error_grey_24))
            .into(itemRowMovieBinder.imageViewMovieThumbnail);

        itemRowMovieBinder.textViewMovieTitle.setText(movie.getTitle());
        itemRowMovieBinder.textViewMovieRating.setText(String.valueOf(movie.getRating()));
        itemRowMovieBinder.textViewMovieReleaseDate.setText(movie.getReleaseDate());
      } catch (NullPointerException e) {
        e.printStackTrace();
      }
    }
  }
}
