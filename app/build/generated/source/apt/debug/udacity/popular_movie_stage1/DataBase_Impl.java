package udacity.popular_movie_stage1;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.db.SupportSQLiteOpenHelper.Callback;
import android.arch.persistence.db.SupportSQLiteOpenHelper.Configuration;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.RoomOpenHelper;
import android.arch.persistence.room.RoomOpenHelper.Delegate;
import android.arch.persistence.room.util.TableInfo;
import android.arch.persistence.room.util.TableInfo.Column;
import android.arch.persistence.room.util.TableInfo.ForeignKey;
import android.arch.persistence.room.util.TableInfo.Index;
import java.lang.IllegalStateException;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;
import java.util.HashSet;

@SuppressWarnings("unchecked")
public class DataBase_Impl extends DataBase {
  private volatile FavouriteDao _favouriteDao;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(5) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `movies` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `movie_id` INTEGER NOT NULL, `movie_title` TEXT, `language` TEXT, `release_date` TEXT, `overView` TEXT, `vote_avg` REAL NOT NULL, `poster` BLOB, `back_drop` BLOB)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `casts` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `movie_id` INTEGER NOT NULL, `name` TEXT, `character` TEXT, `gender` INTEGER NOT NULL, `order` INTEGER NOT NULL, `profile_pic` BLOB)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `trailers` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `movie_id` INTEGER NOT NULL, `video_id` TEXT, `key` TEXT, `name` TEXT, `site` TEXT, `type` TEXT, `video_thumbnail` BLOB)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `reviews` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `movie_id` INTEGER NOT NULL, `author` TEXT, `content` TEXT, `review_url` TEXT)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `Favourite` (`uid` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `id` INTEGER NOT NULL, `vote_average` TEXT, `title` TEXT, `overview` TEXT, `release_date` TEXT, `IsFavourite` INTEGER, `image_url` TEXT)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, \"fd219abcecef2376f435bf3d16fdb6ff\")");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `movies`");
        _db.execSQL("DROP TABLE IF EXISTS `casts`");
        _db.execSQL("DROP TABLE IF EXISTS `trailers`");
        _db.execSQL("DROP TABLE IF EXISTS `reviews`");
        _db.execSQL("DROP TABLE IF EXISTS `Favourite`");
      }

      @Override
      protected void onCreate(SupportSQLiteDatabase _db) {
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onCreate(_db);
          }
        }
      }

      @Override
      public void onOpen(SupportSQLiteDatabase _db) {
        mDatabase = _db;
        internalInitInvalidationTracker(_db);
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onOpen(_db);
          }
        }
      }

      @Override
      protected void validateMigration(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsMovies = new HashMap<String, TableInfo.Column>(9);
        _columnsMovies.put("id", new TableInfo.Column("id", "INTEGER", true, 1));
        _columnsMovies.put("movie_id", new TableInfo.Column("movie_id", "INTEGER", true, 0));
        _columnsMovies.put("movie_title", new TableInfo.Column("movie_title", "TEXT", false, 0));
        _columnsMovies.put("language", new TableInfo.Column("language", "TEXT", false, 0));
        _columnsMovies.put("release_date", new TableInfo.Column("release_date", "TEXT", false, 0));
        _columnsMovies.put("overView", new TableInfo.Column("overView", "TEXT", false, 0));
        _columnsMovies.put("vote_avg", new TableInfo.Column("vote_avg", "REAL", true, 0));
        _columnsMovies.put("poster", new TableInfo.Column("poster", "BLOB", false, 0));
        _columnsMovies.put("back_drop", new TableInfo.Column("back_drop", "BLOB", false, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysMovies = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesMovies = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoMovies = new TableInfo("movies", _columnsMovies, _foreignKeysMovies, _indicesMovies);
        final TableInfo _existingMovies = TableInfo.read(_db, "movies");
        if (! _infoMovies.equals(_existingMovies)) {
          throw new IllegalStateException("Migration didn't properly handle movies(udacity.popular_movie_stage1.MovieEntry).\n"
                  + " Expected:\n" + _infoMovies + "\n"
                  + " Found:\n" + _existingMovies);
        }
        final HashMap<String, TableInfo.Column> _columnsCasts = new HashMap<String, TableInfo.Column>(7);
        _columnsCasts.put("id", new TableInfo.Column("id", "INTEGER", true, 1));
        _columnsCasts.put("movie_id", new TableInfo.Column("movie_id", "INTEGER", true, 0));
        _columnsCasts.put("name", new TableInfo.Column("name", "TEXT", false, 0));
        _columnsCasts.put("character", new TableInfo.Column("character", "TEXT", false, 0));
        _columnsCasts.put("gender", new TableInfo.Column("gender", "INTEGER", true, 0));
        _columnsCasts.put("order", new TableInfo.Column("order", "INTEGER", true, 0));
        _columnsCasts.put("profile_pic", new TableInfo.Column("profile_pic", "BLOB", false, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysCasts = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesCasts = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoCasts = new TableInfo("casts", _columnsCasts, _foreignKeysCasts, _indicesCasts);
        final TableInfo _existingCasts = TableInfo.read(_db, "casts");
        if (! _infoCasts.equals(_existingCasts)) {
          throw new IllegalStateException("Migration didn't properly handle casts(udacity.popular_movie_stage1.CastEntry).\n"
                  + " Expected:\n" + _infoCasts + "\n"
                  + " Found:\n" + _existingCasts);
        }
        final HashMap<String, TableInfo.Column> _columnsTrailers = new HashMap<String, TableInfo.Column>(8);
        _columnsTrailers.put("id", new TableInfo.Column("id", "INTEGER", true, 1));
        _columnsTrailers.put("movie_id", new TableInfo.Column("movie_id", "INTEGER", true, 0));
        _columnsTrailers.put("video_id", new TableInfo.Column("video_id", "TEXT", false, 0));
        _columnsTrailers.put("key", new TableInfo.Column("key", "TEXT", false, 0));
        _columnsTrailers.put("name", new TableInfo.Column("name", "TEXT", false, 0));
        _columnsTrailers.put("site", new TableInfo.Column("site", "TEXT", false, 0));
        _columnsTrailers.put("type", new TableInfo.Column("type", "TEXT", false, 0));
        _columnsTrailers.put("video_thumbnail", new TableInfo.Column("video_thumbnail", "BLOB", false, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysTrailers = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesTrailers = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoTrailers = new TableInfo("trailers", _columnsTrailers, _foreignKeysTrailers, _indicesTrailers);
        final TableInfo _existingTrailers = TableInfo.read(_db, "trailers");
        if (! _infoTrailers.equals(_existingTrailers)) {
          throw new IllegalStateException("Migration didn't properly handle trailers(udacity.popular_movie_stage1.TrailerEntry).\n"
                  + " Expected:\n" + _infoTrailers + "\n"
                  + " Found:\n" + _existingTrailers);
        }
        final HashMap<String, TableInfo.Column> _columnsReviews = new HashMap<String, TableInfo.Column>(5);
        _columnsReviews.put("id", new TableInfo.Column("id", "INTEGER", true, 1));
        _columnsReviews.put("movie_id", new TableInfo.Column("movie_id", "INTEGER", true, 0));
        _columnsReviews.put("author", new TableInfo.Column("author", "TEXT", false, 0));
        _columnsReviews.put("content", new TableInfo.Column("content", "TEXT", false, 0));
        _columnsReviews.put("review_url", new TableInfo.Column("review_url", "TEXT", false, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysReviews = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesReviews = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoReviews = new TableInfo("reviews", _columnsReviews, _foreignKeysReviews, _indicesReviews);
        final TableInfo _existingReviews = TableInfo.read(_db, "reviews");
        if (! _infoReviews.equals(_existingReviews)) {
          throw new IllegalStateException("Migration didn't properly handle reviews(udacity.popular_movie_stage1.ReviewsEntry).\n"
                  + " Expected:\n" + _infoReviews + "\n"
                  + " Found:\n" + _existingReviews);
        }
        final HashMap<String, TableInfo.Column> _columnsFavourite = new HashMap<String, TableInfo.Column>(8);
        _columnsFavourite.put("uid", new TableInfo.Column("uid", "INTEGER", true, 1));
        _columnsFavourite.put("id", new TableInfo.Column("id", "INTEGER", true, 0));
        _columnsFavourite.put("vote_average", new TableInfo.Column("vote_average", "TEXT", false, 0));
        _columnsFavourite.put("title", new TableInfo.Column("title", "TEXT", false, 0));
        _columnsFavourite.put("overview", new TableInfo.Column("overview", "TEXT", false, 0));
        _columnsFavourite.put("release_date", new TableInfo.Column("release_date", "TEXT", false, 0));
        _columnsFavourite.put("IsFavourite", new TableInfo.Column("IsFavourite", "INTEGER", false, 0));
        _columnsFavourite.put("image_url", new TableInfo.Column("image_url", "TEXT", false, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysFavourite = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesFavourite = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoFavourite = new TableInfo("Favourite", _columnsFavourite, _foreignKeysFavourite, _indicesFavourite);
        final TableInfo _existingFavourite = TableInfo.read(_db, "Favourite");
        if (! _infoFavourite.equals(_existingFavourite)) {
          throw new IllegalStateException("Migration didn't properly handle Favourite(udacity.popular_movie_stage1.FavouriteModel).\n"
                  + " Expected:\n" + _infoFavourite + "\n"
                  + " Found:\n" + _existingFavourite);
        }
      }
    }, "fd219abcecef2376f435bf3d16fdb6ff", "725b90a46a6e34eec3d2e16c1d517f87");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    return new InvalidationTracker(this, "movies","casts","trailers","reviews","Favourite");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `movies`");
      _db.execSQL("DELETE FROM `casts`");
      _db.execSQL("DELETE FROM `trailers`");
      _db.execSQL("DELETE FROM `reviews`");
      _db.execSQL("DELETE FROM `Favourite`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  public FavouriteDao userDao() {
    if (_favouriteDao != null) {
      return _favouriteDao;
    } else {
      synchronized(this) {
        if(_favouriteDao == null) {
          _favouriteDao = new FavouriteDao_Impl(this);
        }
        return _favouriteDao;
      }
    }
  }
}
