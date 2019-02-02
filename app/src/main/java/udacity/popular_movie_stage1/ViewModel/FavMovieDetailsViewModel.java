package udacity.popular_movie_stage1.ViewModel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import java.util.List;

import udacity.popular_movie_stage1.CastEntry;
import udacity.popular_movie_stage1.DataBase;
import udacity.popular_movie_stage1.MovieEntry;
import udacity.popular_movie_stage1.ReviewsEntry;
import udacity.popular_movie_stage1.TrailerEntry;
    /*
        This ViewModel is used when MovieDetailsActivity is launced from FavoriteMovies Fragment.
    */

public class FavMovieDetailsViewModel extends ViewModel {

    private LiveData<MovieEntry> movieEntry;
    private LiveData<List<CastEntry>> castEntries;
    private LiveData<List<ReviewsEntry>> reviewsEntries;
    private LiveData<List<TrailerEntry>> trailerEntries;

    FavMovieDetailsViewModel(DataBase movieDatabase, int movieID) {
        movieEntry = movieDatabase.userDao().findMovieEntry(movieID);
        castEntries = movieDatabase.userDao().loadAllCasts(movieID);
        reviewsEntries = movieDatabase.userDao().loadAllReviews(movieID);
        trailerEntries = movieDatabase.userDao().loadAllTrailers(movieID);
        Log.d("Querying from Database", "FavMovieDetailsViewModel");

    }

    public LiveData<MovieEntry> getMovieEntry() {
        return movieEntry;
    }

    public LiveData<List<CastEntry>> getCastEntries() {
        return castEntries;
    }
    public LiveData<List<ReviewsEntry>> getReviewsEntries() {
        return reviewsEntries;
    }

    public LiveData<List<TrailerEntry>> getTrailerEntries() {
        return trailerEntries;
    }

}
