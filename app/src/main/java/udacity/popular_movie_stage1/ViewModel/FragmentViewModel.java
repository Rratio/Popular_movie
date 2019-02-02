package udacity.popular_movie_stage1.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.util.Log;


import java.util.List;

import udacity.popular_movie_stage1.DataBase;

public class FragmentViewModel extends AndroidViewModel {

    private LiveData<List<Integer>> movieIDs;

    public FragmentViewModel(@NonNull Application application) {
        super(application);
        Log.d("Querying from Database","FragmentViewModel");
        DataBase movieDatabase = DataBase.getIsInstance(this.getApplication());
        movieIDs = movieDatabase.userDao().movieIDs();
    }

    public LiveData<List<Integer>> getMovieIDs() {
        return movieIDs;
    }
}
