package udacity.popular_movie_stage1;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.util.Log;

/**
 * Created by lenovo-pc on 7/15/2018.
 */

    @Database(entities = {MovieEntry.class,CastEntry.class,TrailerEntry.class, ReviewsEntry.class,FavouriteModel.class}, version = 5, exportSchema = false)
    public abstract class DataBase extends RoomDatabase {

    private static final String LOG_TAG = DataBase.class.getSimpleName();
    private static final Object LOCK = new Object();
    private static final String DATABASE_NAME = "favourite list";
    static DataBase isInstance;


    public static DataBase getIsInstance(Context context){
        if(isInstance==null ){
            synchronized(LOCK){
                Log.d(LOG_TAG,"Creating new database instance");
                isInstance = Room.databaseBuilder(context.getApplicationContext(),DataBase.class,DataBase.DATABASE_NAME)
                        .allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build();
            }
        }
        Log.d(LOG_TAG,"getting the new instance");
        return isInstance;
    }

    public abstract FavouriteDao userDao();

}