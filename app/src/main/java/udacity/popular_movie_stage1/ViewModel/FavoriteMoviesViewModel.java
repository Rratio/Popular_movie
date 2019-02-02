package udacity.popular_movie_stage1.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.util.Log;



import java.util.List;

import udacity.popular_movie_stage1.DataBase;
import udacity.popular_movie_stage1.MovieEntry;

public class FavoriteMoviesViewModel extends AndroidViewModel{

    private LiveData<List<MovieEntry>> movieEntries;

    public FavoriteMoviesViewModel(@NonNull Application application) {
        super(application);
        Log.d("Querying from Database","FavoriteMoviesViewModel");
        DataBase movieDatabase = DataBase.getIsInstance(this.getApplication());
        movieEntries = movieDatabase.userDao().loadAllMovies();
    }


    public LiveData<List<MovieEntry>> getMovieEntries() {
        return movieEntries;
    }
}
