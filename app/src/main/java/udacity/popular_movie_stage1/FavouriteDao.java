package udacity.popular_movie_stage1;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by lenovo-pc on 7/15/2018.
 */
@Dao
public interface FavouriteDao {

    // Movie Entries
    @Query("SELECT * FROM movies")
    LiveData<List<MovieEntry>> loadAllMovies();

    @Query("SELECT * FROM movies WHERE movie_id = :movieId")
    LiveData<MovieEntry> findMovieEntry(int movieId);



    @Query("SELECT * FROM Favourite where IsFavourite = :isFavourite")
    List<FavouriteModel> getAll(Boolean isFavourite);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(FavouriteModel products);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMovie(MovieEntry movieEntry);

    @Query("UPDATE Favourite SET IsFavourite = :isFavourite where id = :id")
    void update(int id, Boolean isFavourite);

    @Delete
    void deleteMovie(MovieEntry movieEntry);

    @Query("SELECT movie_id FROM movies")
    LiveData<List<Integer>> movieIDs();



    @Query("SELECT * FROM Favourite where id = :id")
    FavouriteModel getMovie(int id);

    // Casts
    @Query("SELECT * FROM casts WHERE movie_id = :movieId")
    LiveData<List<CastEntry>> loadAllCasts(int movieId);

    @Insert
    void insertCasts(List<CastEntry> castEntries);

    @Query("DELETE FROM casts WHERE movie_id = :movieId")
    void deleteCasts(int movieId);


    // Trailers
    @Query("SELECT * FROM trailers WHERE movie_id = :movieId")
    LiveData<List<TrailerEntry>> loadAllTrailers(int movieId);

    @Insert
    void insertTrailers(List<TrailerEntry> trailerEntries);

    @Query("DELETE FROM trailers WHERE movie_id = :movieId")
    void deleteTrailers(int movieId);

    //Reviews
    @Query("SELECT * FROM reviews where movie_id = :movieId ")
    LiveData<List<ReviewsEntry>> loadAllReviews(int movieId);

    @Insert
    void insertReviews(List<ReviewsEntry> reviewsEntry);

    @Query("DELETE FROM reviews WHERE movie_id = :movieId")
    void deleteReviews(int movieId);

}

