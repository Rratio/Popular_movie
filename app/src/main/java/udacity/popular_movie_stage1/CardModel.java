package udacity.popular_movie_stage1;


public class CardModel {

    private int vote_average,popularity, id;
    private String title, poster_path, overview,release_date, author, content, type,key,backdrop_path;
    private Boolean isFavourite;

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public String setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
        return backdrop_path;
    }

    public Boolean getFavourite() {
        return isFavourite;
    }

    public void setFavourite(Boolean favourite) {
        isFavourite = favourite;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }


    public String getAuthor() {
        return author;
    }

    public void setAuthor(String name) {
        this.author = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public int setId(int id) {
        this.id = id;
        return id;
    }

    public int getVote_average() {
        return vote_average;
    }

    public int setVote_average(int vote_average) {
        this.vote_average = vote_average;
        return vote_average;
    }

    public String getRelease_date() {
        return release_date;
    }

    public String setRelease_date(String release_date) {
        this.release_date = release_date;
        return release_date;
    }

    public int getPopularity() {
        return popularity;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }

    public String getOriginal_title() {
        return title;
    }

    public String setOriginal_title(String original_title) {
        this.title = original_title;
        return original_title;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public String setPoster_path(String poster_path) {
        this.poster_path = poster_path;
        return poster_path;
    }

    public String getOverview() {
        return overview;
    }

    public String setOverview(String overview) {
        this.overview = overview;
        return overview;
    }
}

