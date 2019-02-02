package udacity.popular_movie_stage1;

import android.arch.lifecycle.ComputableLiveData;
import android.arch.lifecycle.LiveData;
import android.arch.persistence.db.SupportSQLiteStatement;
import android.arch.persistence.room.EntityDeletionOrUpdateAdapter;
import android.arch.persistence.room.EntityInsertionAdapter;
import android.arch.persistence.room.InvalidationTracker.Observer;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.RoomSQLiteQuery;
import android.arch.persistence.room.SharedSQLiteStatement;
import android.database.Cursor;
import android.support.annotation.NonNull;
import java.lang.Boolean;
import java.lang.Integer;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@SuppressWarnings("unchecked")
public class FavouriteDao_Impl implements FavouriteDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfFavouriteModel;

  private final EntityInsertionAdapter __insertionAdapterOfMovieEntry;

  private final EntityInsertionAdapter __insertionAdapterOfCastEntry;

  private final EntityInsertionAdapter __insertionAdapterOfTrailerEntry;

  private final EntityInsertionAdapter __insertionAdapterOfReviewsEntry;

  private final EntityDeletionOrUpdateAdapter __deletionAdapterOfMovieEntry;

  private final SharedSQLiteStatement __preparedStmtOfUpdate;

  private final SharedSQLiteStatement __preparedStmtOfDeleteCasts;

  private final SharedSQLiteStatement __preparedStmtOfDeleteTrailers;

  private final SharedSQLiteStatement __preparedStmtOfDeleteReviews;

  public FavouriteDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfFavouriteModel = new EntityInsertionAdapter<FavouriteModel>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `Favourite`(`uid`,`id`,`vote_average`,`title`,`overview`,`release_date`,`IsFavourite`,`image_url`) VALUES (nullif(?, 0),?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, FavouriteModel value) {
        stmt.bindLong(1, value.getUid());
        stmt.bindLong(2, value.getId());
        if (value.getVote_average() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getVote_average());
        }
        if (value.getName() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getName());
        }
        if (value.getOverview() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getOverview());
        }
        if (value.getRelease_date() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getRelease_date());
        }
        final Integer _tmp;
        _tmp = value.getIsFavourite() == null ? null : (value.getIsFavourite() ? 1 : 0);
        if (_tmp == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindLong(7, _tmp);
        }
        if (value.getImageUrl() == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, value.getImageUrl());
        }
      }
    };
    this.__insertionAdapterOfMovieEntry = new EntityInsertionAdapter<MovieEntry>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `movies`(`id`,`movie_id`,`movie_title`,`language`,`release_date`,`overView`,`vote_avg`,`poster`,`back_drop`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, MovieEntry value) {
        stmt.bindLong(1, value.getId());
        stmt.bindLong(2, value.getMovieId());
        if (value.getMovieTitle() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getMovieTitle());
        }
        if (value.getMovie_language() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getMovie_language());
        }
        if (value.getReleaseDate() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getReleaseDate());
        }
        if (value.getOverView() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getOverView());
        }
        stmt.bindDouble(7, value.getVoteAverage());
        if (value.getPoster() == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindBlob(8, value.getPoster());
        }
        if (value.getBackDrop() == null) {
          stmt.bindNull(9);
        } else {
          stmt.bindBlob(9, value.getBackDrop());
        }
      }
    };
    this.__insertionAdapterOfCastEntry = new EntityInsertionAdapter<CastEntry>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `casts`(`id`,`movie_id`,`name`,`character`,`gender`,`order`,`profile_pic`) VALUES (nullif(?, 0),?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, CastEntry value) {
        stmt.bindLong(1, value.getId());
        stmt.bindLong(2, value.getMovieId());
        if (value.getName() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getName());
        }
        if (value.getCharacter() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getCharacter());
        }
        stmt.bindLong(5, value.getGender());
        stmt.bindLong(6, value.getOrder());
        if (value.getProfilePic() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindBlob(7, value.getProfilePic());
        }
      }
    };
    this.__insertionAdapterOfTrailerEntry = new EntityInsertionAdapter<TrailerEntry>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `trailers`(`id`,`movie_id`,`video_id`,`key`,`name`,`site`,`type`,`video_thumbnail`) VALUES (nullif(?, 0),?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, TrailerEntry value) {
        stmt.bindLong(1, value.getId());
        stmt.bindLong(2, value.getMovieId());
        if (value.getVideoId() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getVideoId());
        }
        if (value.getKey() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getKey());
        }
        if (value.getName() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getName());
        }
        if (value.getSite() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getSite());
        }
        if (value.getType() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getType());
        }
        if (value.getVideoThumbnail() == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindBlob(8, value.getVideoThumbnail());
        }
      }
    };
    this.__insertionAdapterOfReviewsEntry = new EntityInsertionAdapter<ReviewsEntry>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `reviews`(`id`,`movie_id`,`author`,`content`,`review_url`) VALUES (nullif(?, 0),?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, ReviewsEntry value) {
        stmt.bindLong(1, value.getId());
        stmt.bindLong(2, value.getMovieID());
        if (value.getAuthor() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getAuthor());
        }
        if (value.getContent() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getContent());
        }
        if (value.getReviewURL() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getReviewURL());
        }
      }
    };
    this.__deletionAdapterOfMovieEntry = new EntityDeletionOrUpdateAdapter<MovieEntry>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `movies` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, MovieEntry value) {
        stmt.bindLong(1, value.getId());
      }
    };
    this.__preparedStmtOfUpdate = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "UPDATE Favourite SET IsFavourite = ? where id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteCasts = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM casts WHERE movie_id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteTrailers = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM trailers WHERE movie_id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteReviews = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM reviews WHERE movie_id = ?";
        return _query;
      }
    };
  }

  @Override
  public void insertAll(FavouriteModel products) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfFavouriteModel.insert(products);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void insertMovie(MovieEntry movieEntry) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfMovieEntry.insert(movieEntry);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void insertCasts(List<CastEntry> castEntries) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfCastEntry.insert(castEntries);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void insertTrailers(List<TrailerEntry> trailerEntries) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfTrailerEntry.insert(trailerEntries);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void insertReviews(List<ReviewsEntry> reviewsEntry) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfReviewsEntry.insert(reviewsEntry);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteMovie(MovieEntry movieEntry) {
    __db.beginTransaction();
    try {
      __deletionAdapterOfMovieEntry.handle(movieEntry);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update(int id, Boolean isFavourite) {
    final SupportSQLiteStatement _stmt = __preparedStmtOfUpdate.acquire();
    __db.beginTransaction();
    try {
      int _argIndex = 1;
      final Integer _tmp;
      _tmp = isFavourite == null ? null : (isFavourite ? 1 : 0);
      if (_tmp == null) {
        _stmt.bindNull(_argIndex);
      } else {
        _stmt.bindLong(_argIndex, _tmp);
      }
      _argIndex = 2;
      _stmt.bindLong(_argIndex, id);
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfUpdate.release(_stmt);
    }
  }

  @Override
  public void deleteCasts(int movieId) {
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteCasts.acquire();
    __db.beginTransaction();
    try {
      int _argIndex = 1;
      _stmt.bindLong(_argIndex, movieId);
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteCasts.release(_stmt);
    }
  }

  @Override
  public void deleteTrailers(int movieId) {
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteTrailers.acquire();
    __db.beginTransaction();
    try {
      int _argIndex = 1;
      _stmt.bindLong(_argIndex, movieId);
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteTrailers.release(_stmt);
    }
  }

  @Override
  public void deleteReviews(int movieId) {
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteReviews.acquire();
    __db.beginTransaction();
    try {
      int _argIndex = 1;
      _stmt.bindLong(_argIndex, movieId);
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteReviews.release(_stmt);
    }
  }

  @Override
  public LiveData<List<MovieEntry>> loadAllMovies() {
    final String _sql = "SELECT * FROM movies";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return new ComputableLiveData<List<MovieEntry>>() {
      private Observer _observer;

      @Override
      protected List<MovieEntry> compute() {
        if (_observer == null) {
          _observer = new Observer("movies") {
            @Override
            public void onInvalidated(@NonNull Set<String> tables) {
              invalidate();
            }
          };
          __db.getInvalidationTracker().addWeakObserver(_observer);
        }
        final Cursor _cursor = __db.query(_statement);
        try {
          final int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("id");
          final int _cursorIndexOfMovieId = _cursor.getColumnIndexOrThrow("movie_id");
          final int _cursorIndexOfMovieTitle = _cursor.getColumnIndexOrThrow("movie_title");
          final int _cursorIndexOfMovieLanguage = _cursor.getColumnIndexOrThrow("language");
          final int _cursorIndexOfReleaseDate = _cursor.getColumnIndexOrThrow("release_date");
          final int _cursorIndexOfOverView = _cursor.getColumnIndexOrThrow("overView");
          final int _cursorIndexOfVoteAverage = _cursor.getColumnIndexOrThrow("vote_avg");
          final int _cursorIndexOfPoster = _cursor.getColumnIndexOrThrow("poster");
          final int _cursorIndexOfBackDrop = _cursor.getColumnIndexOrThrow("back_drop");
          final List<MovieEntry> _result = new ArrayList<MovieEntry>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final MovieEntry _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final int _tmpMovieId;
            _tmpMovieId = _cursor.getInt(_cursorIndexOfMovieId);
            final String _tmpMovieTitle;
            _tmpMovieTitle = _cursor.getString(_cursorIndexOfMovieTitle);
            final String _tmpMovie_language;
            _tmpMovie_language = _cursor.getString(_cursorIndexOfMovieLanguage);
            final String _tmpReleaseDate;
            _tmpReleaseDate = _cursor.getString(_cursorIndexOfReleaseDate);
            final String _tmpOverView;
            _tmpOverView = _cursor.getString(_cursorIndexOfOverView);
            final double _tmpVoteAverage;
            _tmpVoteAverage = _cursor.getDouble(_cursorIndexOfVoteAverage);
            final byte[] _tmpPoster;
            _tmpPoster = _cursor.getBlob(_cursorIndexOfPoster);
            final byte[] _tmpBackDrop;
            _tmpBackDrop = _cursor.getBlob(_cursorIndexOfBackDrop);
            _item = new MovieEntry(_tmpId,_tmpMovieId,_tmpMovieTitle,_tmpMovie_language,_tmpReleaseDate,_tmpOverView,_tmpVoteAverage,_tmpPoster,_tmpBackDrop);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    }.getLiveData();
  }

  @Override
  public LiveData<MovieEntry> findMovieEntry(int movieId) {
    final String _sql = "SELECT * FROM movies WHERE movie_id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, movieId);
    return new ComputableLiveData<MovieEntry>() {
      private Observer _observer;

      @Override
      protected MovieEntry compute() {
        if (_observer == null) {
          _observer = new Observer("movies") {
            @Override
            public void onInvalidated(@NonNull Set<String> tables) {
              invalidate();
            }
          };
          __db.getInvalidationTracker().addWeakObserver(_observer);
        }
        final Cursor _cursor = __db.query(_statement);
        try {
          final int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("id");
          final int _cursorIndexOfMovieId = _cursor.getColumnIndexOrThrow("movie_id");
          final int _cursorIndexOfMovieTitle = _cursor.getColumnIndexOrThrow("movie_title");
          final int _cursorIndexOfMovieLanguage = _cursor.getColumnIndexOrThrow("language");
          final int _cursorIndexOfReleaseDate = _cursor.getColumnIndexOrThrow("release_date");
          final int _cursorIndexOfOverView = _cursor.getColumnIndexOrThrow("overView");
          final int _cursorIndexOfVoteAverage = _cursor.getColumnIndexOrThrow("vote_avg");
          final int _cursorIndexOfPoster = _cursor.getColumnIndexOrThrow("poster");
          final int _cursorIndexOfBackDrop = _cursor.getColumnIndexOrThrow("back_drop");
          final MovieEntry _result;
          if(_cursor.moveToFirst()) {
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final int _tmpMovieId;
            _tmpMovieId = _cursor.getInt(_cursorIndexOfMovieId);
            final String _tmpMovieTitle;
            _tmpMovieTitle = _cursor.getString(_cursorIndexOfMovieTitle);
            final String _tmpMovie_language;
            _tmpMovie_language = _cursor.getString(_cursorIndexOfMovieLanguage);
            final String _tmpReleaseDate;
            _tmpReleaseDate = _cursor.getString(_cursorIndexOfReleaseDate);
            final String _tmpOverView;
            _tmpOverView = _cursor.getString(_cursorIndexOfOverView);
            final double _tmpVoteAverage;
            _tmpVoteAverage = _cursor.getDouble(_cursorIndexOfVoteAverage);
            final byte[] _tmpPoster;
            _tmpPoster = _cursor.getBlob(_cursorIndexOfPoster);
            final byte[] _tmpBackDrop;
            _tmpBackDrop = _cursor.getBlob(_cursorIndexOfBackDrop);
            _result = new MovieEntry(_tmpId,_tmpMovieId,_tmpMovieTitle,_tmpMovie_language,_tmpReleaseDate,_tmpOverView,_tmpVoteAverage,_tmpPoster,_tmpBackDrop);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    }.getLiveData();
  }

  @Override
  public List<FavouriteModel> getAll(Boolean isFavourite) {
    final String _sql = "SELECT * FROM Favourite where IsFavourite = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    final Integer _tmp;
    _tmp = isFavourite == null ? null : (isFavourite ? 1 : 0);
    if (_tmp == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindLong(_argIndex, _tmp);
    }
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfUid = _cursor.getColumnIndexOrThrow("uid");
      final int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("id");
      final int _cursorIndexOfVoteAverage = _cursor.getColumnIndexOrThrow("vote_average");
      final int _cursorIndexOfName = _cursor.getColumnIndexOrThrow("title");
      final int _cursorIndexOfOverview = _cursor.getColumnIndexOrThrow("overview");
      final int _cursorIndexOfReleaseDate = _cursor.getColumnIndexOrThrow("release_date");
      final int _cursorIndexOfIsFavourite = _cursor.getColumnIndexOrThrow("IsFavourite");
      final int _cursorIndexOfImageUrl = _cursor.getColumnIndexOrThrow("image_url");
      final List<FavouriteModel> _result = new ArrayList<FavouriteModel>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final FavouriteModel _item;
        final int _tmpUid;
        _tmpUid = _cursor.getInt(_cursorIndexOfUid);
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        final String _tmpVote_average;
        _tmpVote_average = _cursor.getString(_cursorIndexOfVoteAverage);
        final String _tmpName;
        _tmpName = _cursor.getString(_cursorIndexOfName);
        final String _tmpOverview;
        _tmpOverview = _cursor.getString(_cursorIndexOfOverview);
        final String _tmpRelease_date;
        _tmpRelease_date = _cursor.getString(_cursorIndexOfReleaseDate);
        final Boolean _tmpIsFavourite;
        final Integer _tmp_1;
        if (_cursor.isNull(_cursorIndexOfIsFavourite)) {
          _tmp_1 = null;
        } else {
          _tmp_1 = _cursor.getInt(_cursorIndexOfIsFavourite);
        }
        _tmpIsFavourite = _tmp_1 == null ? null : _tmp_1 != 0;
        final String _tmpImageUrl;
        _tmpImageUrl = _cursor.getString(_cursorIndexOfImageUrl);
        _item = new FavouriteModel(_tmpUid,_tmpId,_tmpVote_average,_tmpName,_tmpOverview,_tmpRelease_date,_tmpIsFavourite,_tmpImageUrl);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public LiveData<List<Integer>> movieIDs() {
    final String _sql = "SELECT movie_id FROM movies";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return new ComputableLiveData<List<Integer>>() {
      private Observer _observer;

      @Override
      protected List<Integer> compute() {
        if (_observer == null) {
          _observer = new Observer("movies") {
            @Override
            public void onInvalidated(@NonNull Set<String> tables) {
              invalidate();
            }
          };
          __db.getInvalidationTracker().addWeakObserver(_observer);
        }
        final Cursor _cursor = __db.query(_statement);
        try {
          final List<Integer> _result = new ArrayList<Integer>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final Integer _item;
            if (_cursor.isNull(0)) {
              _item = null;
            } else {
              _item = _cursor.getInt(0);
            }
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    }.getLiveData();
  }

  @Override
  public FavouriteModel getMovie(int id) {
    final String _sql = "SELECT * FROM Favourite where id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfUid = _cursor.getColumnIndexOrThrow("uid");
      final int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("id");
      final int _cursorIndexOfVoteAverage = _cursor.getColumnIndexOrThrow("vote_average");
      final int _cursorIndexOfName = _cursor.getColumnIndexOrThrow("title");
      final int _cursorIndexOfOverview = _cursor.getColumnIndexOrThrow("overview");
      final int _cursorIndexOfReleaseDate = _cursor.getColumnIndexOrThrow("release_date");
      final int _cursorIndexOfIsFavourite = _cursor.getColumnIndexOrThrow("IsFavourite");
      final int _cursorIndexOfImageUrl = _cursor.getColumnIndexOrThrow("image_url");
      final FavouriteModel _result;
      if(_cursor.moveToFirst()) {
        final int _tmpUid;
        _tmpUid = _cursor.getInt(_cursorIndexOfUid);
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        final String _tmpVote_average;
        _tmpVote_average = _cursor.getString(_cursorIndexOfVoteAverage);
        final String _tmpName;
        _tmpName = _cursor.getString(_cursorIndexOfName);
        final String _tmpOverview;
        _tmpOverview = _cursor.getString(_cursorIndexOfOverview);
        final String _tmpRelease_date;
        _tmpRelease_date = _cursor.getString(_cursorIndexOfReleaseDate);
        final Boolean _tmpIsFavourite;
        final Integer _tmp;
        if (_cursor.isNull(_cursorIndexOfIsFavourite)) {
          _tmp = null;
        } else {
          _tmp = _cursor.getInt(_cursorIndexOfIsFavourite);
        }
        _tmpIsFavourite = _tmp == null ? null : _tmp != 0;
        final String _tmpImageUrl;
        _tmpImageUrl = _cursor.getString(_cursorIndexOfImageUrl);
        _result = new FavouriteModel(_tmpUid,_tmpId,_tmpVote_average,_tmpName,_tmpOverview,_tmpRelease_date,_tmpIsFavourite,_tmpImageUrl);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public LiveData<List<CastEntry>> loadAllCasts(int movieId) {
    final String _sql = "SELECT * FROM casts WHERE movie_id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, movieId);
    return new ComputableLiveData<List<CastEntry>>() {
      private Observer _observer;

      @Override
      protected List<CastEntry> compute() {
        if (_observer == null) {
          _observer = new Observer("casts") {
            @Override
            public void onInvalidated(@NonNull Set<String> tables) {
              invalidate();
            }
          };
          __db.getInvalidationTracker().addWeakObserver(_observer);
        }
        final Cursor _cursor = __db.query(_statement);
        try {
          final int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("id");
          final int _cursorIndexOfMovieId = _cursor.getColumnIndexOrThrow("movie_id");
          final int _cursorIndexOfName = _cursor.getColumnIndexOrThrow("name");
          final int _cursorIndexOfCharacter = _cursor.getColumnIndexOrThrow("character");
          final int _cursorIndexOfGender = _cursor.getColumnIndexOrThrow("gender");
          final int _cursorIndexOfOrder = _cursor.getColumnIndexOrThrow("order");
          final int _cursorIndexOfProfilePic = _cursor.getColumnIndexOrThrow("profile_pic");
          final List<CastEntry> _result = new ArrayList<CastEntry>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final CastEntry _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final int _tmpMovieId;
            _tmpMovieId = _cursor.getInt(_cursorIndexOfMovieId);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final String _tmpCharacter;
            _tmpCharacter = _cursor.getString(_cursorIndexOfCharacter);
            final int _tmpGender;
            _tmpGender = _cursor.getInt(_cursorIndexOfGender);
            final int _tmpOrder;
            _tmpOrder = _cursor.getInt(_cursorIndexOfOrder);
            final byte[] _tmpProfilePic;
            _tmpProfilePic = _cursor.getBlob(_cursorIndexOfProfilePic);
            _item = new CastEntry(_tmpId,_tmpMovieId,_tmpName,_tmpCharacter,_tmpGender,_tmpOrder,_tmpProfilePic);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    }.getLiveData();
  }

  @Override
  public LiveData<List<TrailerEntry>> loadAllTrailers(int movieId) {
    final String _sql = "SELECT * FROM trailers WHERE movie_id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, movieId);
    return new ComputableLiveData<List<TrailerEntry>>() {
      private Observer _observer;

      @Override
      protected List<TrailerEntry> compute() {
        if (_observer == null) {
          _observer = new Observer("trailers") {
            @Override
            public void onInvalidated(@NonNull Set<String> tables) {
              invalidate();
            }
          };
          __db.getInvalidationTracker().addWeakObserver(_observer);
        }
        final Cursor _cursor = __db.query(_statement);
        try {
          final int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("id");
          final int _cursorIndexOfMovieId = _cursor.getColumnIndexOrThrow("movie_id");
          final int _cursorIndexOfVideoId = _cursor.getColumnIndexOrThrow("video_id");
          final int _cursorIndexOfKey = _cursor.getColumnIndexOrThrow("key");
          final int _cursorIndexOfName = _cursor.getColumnIndexOrThrow("name");
          final int _cursorIndexOfSite = _cursor.getColumnIndexOrThrow("site");
          final int _cursorIndexOfType = _cursor.getColumnIndexOrThrow("type");
          final int _cursorIndexOfVideoThumbnail = _cursor.getColumnIndexOrThrow("video_thumbnail");
          final List<TrailerEntry> _result = new ArrayList<TrailerEntry>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final TrailerEntry _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final int _tmpMovieId;
            _tmpMovieId = _cursor.getInt(_cursorIndexOfMovieId);
            final String _tmpVideoId;
            _tmpVideoId = _cursor.getString(_cursorIndexOfVideoId);
            final String _tmpKey;
            _tmpKey = _cursor.getString(_cursorIndexOfKey);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final String _tmpSite;
            _tmpSite = _cursor.getString(_cursorIndexOfSite);
            final String _tmpType;
            _tmpType = _cursor.getString(_cursorIndexOfType);
            final byte[] _tmpVideoThumbnail;
            _tmpVideoThumbnail = _cursor.getBlob(_cursorIndexOfVideoThumbnail);
            _item = new TrailerEntry(_tmpId,_tmpMovieId,_tmpVideoId,_tmpKey,_tmpName,_tmpSite,_tmpType,_tmpVideoThumbnail);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    }.getLiveData();
  }

  @Override
  public LiveData<List<ReviewsEntry>> loadAllReviews(int movieId) {
    final String _sql = "SELECT * FROM reviews where movie_id = ? ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, movieId);
    return new ComputableLiveData<List<ReviewsEntry>>() {
      private Observer _observer;

      @Override
      protected List<ReviewsEntry> compute() {
        if (_observer == null) {
          _observer = new Observer("reviews") {
            @Override
            public void onInvalidated(@NonNull Set<String> tables) {
              invalidate();
            }
          };
          __db.getInvalidationTracker().addWeakObserver(_observer);
        }
        final Cursor _cursor = __db.query(_statement);
        try {
          final int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("id");
          final int _cursorIndexOfMovieID = _cursor.getColumnIndexOrThrow("movie_id");
          final int _cursorIndexOfAuthor = _cursor.getColumnIndexOrThrow("author");
          final int _cursorIndexOfContent = _cursor.getColumnIndexOrThrow("content");
          final int _cursorIndexOfReviewURL = _cursor.getColumnIndexOrThrow("review_url");
          final List<ReviewsEntry> _result = new ArrayList<ReviewsEntry>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final ReviewsEntry _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final int _tmpMovieID;
            _tmpMovieID = _cursor.getInt(_cursorIndexOfMovieID);
            final String _tmpAuthor;
            _tmpAuthor = _cursor.getString(_cursorIndexOfAuthor);
            final String _tmpContent;
            _tmpContent = _cursor.getString(_cursorIndexOfContent);
            final String _tmpReviewURL;
            _tmpReviewURL = _cursor.getString(_cursorIndexOfReviewURL);
            _item = new ReviewsEntry(_tmpId,_tmpMovieID,_tmpAuthor,_tmpContent,_tmpReviewURL);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    }.getLiveData();
  }
}
