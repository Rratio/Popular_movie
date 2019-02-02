package udacity.popular_movie_stage1;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class AppExecutors {

    // For Singleton instantiation
    private static final Object LOCK = new Object();
    private static AppExecutors appExecutors;
    private final Executor executorForDatabase;

    public AppExecutors(Executor executorForDatabase) {
        this.executorForDatabase = executorForDatabase;
    }

    public static AppExecutors getInstance(){
        if (appExecutors == null){
            synchronized (LOCK){
                appExecutors = new AppExecutors(Executors.newSingleThreadExecutor());
            }
        }
        return appExecutors;
    }

    public Executor executorForDatabase(){
        return executorForDatabase;
    }
}
