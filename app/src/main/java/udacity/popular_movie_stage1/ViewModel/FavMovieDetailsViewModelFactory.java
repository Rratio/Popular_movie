package udacity.popular_movie_stage1.ViewModel;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import udacity.popular_movie_stage1.DataBase;

/*
This ViewModel is used when MovieDetailsActivity is launced from FavoriteMovies Fragment.
*/
public class FavMovieDetailsViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private DataBase movieDatabase;
    private int movieID;

    public FavMovieDetailsViewModelFactory(DataBase movieDatabase, int movieID) {
        this.movieDatabase = movieDatabase;
        this.movieID = movieID;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new FavMovieDetailsViewModel(movieDatabase, movieID);
    }
}
