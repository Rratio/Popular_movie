package udacity.popular_movie_stage1;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;


@Entity(tableName = "Favourite")
public class FavouriteModel {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "uid")
    private int uid;

    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "vote_average")
    private String vote_average;

    @ColumnInfo(name = "title")
    private String name;

    @ColumnInfo(name = "overview")
    private String overview;

    @ColumnInfo(name = "release_date")
    private String release_date;

    @ColumnInfo(name = "IsFavourite")
    private Boolean isFavourite;

    @ColumnInfo(name = "image_url")
    private String imageUrl;




    public FavouriteModel(@NonNull int uid, int id, String vote_average, String name, String overview, String release_date, Boolean isFavourite, String imageUrl) {
        this.uid = uid;
        this.id = id;
        this.vote_average = vote_average;
        this.name = name;
        this.overview = overview;
        this.release_date = release_date;
        this.isFavourite = isFavourite;
        this.imageUrl = imageUrl;
    }

    @Ignore
    public FavouriteModel(int id, String vote_average, String name, String overview, String release_date, Boolean isFavourite, String imageUrl) {
        this.id = id;
        this.vote_average = vote_average;
        this.name = name;
        this.overview = overview;
        this.release_date = release_date;
        this.isFavourite = isFavourite;
        this.imageUrl = imageUrl;
    }

    public Boolean getIsFavourite() {

        return isFavourite;
    }

    public void setIsFavourite(Boolean isFavourite) {
        this.isFavourite = isFavourite;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getVote_average() {
        return vote_average;
    }

    public void setVote_average(String vote_average) {
        this.vote_average = vote_average;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }


}

