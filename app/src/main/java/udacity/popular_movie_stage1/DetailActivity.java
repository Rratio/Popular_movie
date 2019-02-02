package udacity.popular_movie_stage1;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class DetailActivity extends AppCompatActivity {

    TextView overview, vote_average, release_date, author, content;
    private CardAdapter adapter;
    private ReviewAdapter reviewAdapter;
    ImageView poster, fullImage;
    Bitmap mIcon_val;
    Toolbar toolbar;
    private ImageView button;
    String Name, Overview, Vote, Release, img, id, key, full;
    ToggleButton toggleButton;
    CardModel card;
    private FavouriteModel movieById;
    DataBase mdb;
    RecyclerView recyclerView;
    private List<CardModel> modelList = new ArrayList<CardModel>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        adapter = new CardAdapter(getApplicationContext(), modelList);
        reviewAdapter = new ReviewAdapter(getApplicationContext(), modelList);

        recyclerView = (RecyclerView) findViewById(R.id.review2);
        overview = (TextView) findViewById(R.id.overview);
        vote_average = (TextView) findViewById(R.id.movieTitle);
        release_date = (TextView) findViewById(R.id.exp);
        poster = (ImageView) findViewById(R.id.cardImg);
        fullImage = (ImageView) findViewById(R.id.fullImage);
        button = (ImageView) findViewById(R.id.card);
        author = (TextView) findViewById(R.id.author);
        content = (TextView) findViewById(R.id.content);

        id = getIntent().getStringExtra("id");
        Name = getIntent().getStringExtra("title");
        img = getIntent().getStringExtra("poster_path");
        full = getIntent().getStringExtra("backdrop_path");
        Release = getIntent().getStringExtra("release_date");
        Vote = getIntent().getStringExtra("vote_average");
        Overview = getIntent().getStringExtra("overview");
        toggleButton = (ToggleButton) findViewById(R.id.myToggleButton);


        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(Name);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        mdb = DataBase.getIsInstance(getApplicationContext());


        movieById = mdb.userDao().getMovie(id);
        if (movieById != null)
            updateUI(movieById.getIsFavourite());
        else
            updateUI(false);
        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isFavourite) {

                if (movieById == null) {
                    FavouriteModel favouriteModel = new FavouriteModel(id, Vote, Name, Overview, Release, isFavourite, img);
                    mdb.userDao().insertAll(favouriteModel);
                    updateUI(true);
                } else {
                    mdb.userDao().update(Integer.parseInt(id), !movieById.getIsFavourite());
                    updateUI(!movieById.getIsFavourite());
                }

            }
        });

        getData();
        getReview();

        URL newUrl = null;
        try {
            newUrl = new URL(img);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            mIcon_val = BitmapFactory.decodeStream(newUrl.openConnection().getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }


        poster.setImageBitmap(mIcon_val);
        fullImage.setImageBitmap(mIcon_val);
        vote_average.setText("Rating :- " + Vote + "/10");
        release_date.setText("Date :- " + Release);
        overview.setText(Overview);

        button.setImageBitmap(mIcon_val);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                watchYoutubeVideo(getApplicationContext());
            }
        });
    }


    private void updateUI(boolean isFavorite) {
        if (!isFavorite) {
            toggleButton.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_favorite_border_black_24dp));

        } else {
            toggleButton.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_favorite_black_24dp));

        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // todo: goto back activity from here

                Intent intent = new Intent(this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void getData() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, "https://api.themoviedb.org/3/movie/"+id+"/videos?api_key=3a19341c015b47b8cb85017aaca0675d&language=en-US",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        modelList.clear();
                        try {
                            JSONObject object = new JSONObject(response);

                            JSONArray jsonArray = object.getJSONArray("results");

                            for (int i = 0; i < jsonArray.length(); i++) {

                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                CardModel card = new CardModel();
                                for (int j = 0; j < jsonObject.length(); j++) {

                                    card.setKey(jsonObject.getString("key"));

                                    key = card.getKey();
                                }
                                Log.e("KEY", "::::::" + key);
                                modelList.add(card);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        adapter.notifyDataSetChanged();

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Log.e("ERROR", ">>>>>>" + error.getMessage());
                        Toast.makeText(getApplicationContext(), "Time Out" + error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }


    public void getReview() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, "https://api.themoviedb.org/3/movie/" + id + "/reviews?api_key=3a19341c015b47b8cb85017aaca0675d&language=en-US&page=1",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        modelList.clear();
                        try {
                            JSONObject object = new JSONObject(response);

                            JSONArray jsonArray = object.getJSONArray("results");

                            for (int i = 0; i < jsonArray.length(); i++) {

                                JSONObject jsonObject = jsonArray.getJSONObject(i);

                                card = new CardModel();


                                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
                                recyclerView.setLayoutManager(layoutManager);
                                recyclerView.setAdapter(reviewAdapter);

                                card.setAuthor(jsonObject.getString("author"));
                                card.setContent(jsonObject.getString("content"));

                                modelList.add(card);
                                reviewAdapter.notifyDataSetChanged();
                            }
                        } catch (Exception e) {
                            Toast.makeText(getApplicationContext(), "Don't have any Review", Toast.LENGTH_LONG).show();
                            e.printStackTrace();
                        }

                        adapter.notifyDataSetChanged();

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Log.e("ERROR", ">>>>>>" + error.getMessage());
                        Toast.makeText(getApplicationContext(), "Time Out" + error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }


    public void watchYoutubeVideo(Context context) {
        try {
            getIntent().addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=" + key)));

        } catch (android.content.ActivityNotFoundException anfe) {
            viewInBrowser(context, "https://www.youtube.com/watch?v=" + key);
        }
    }

    public static void viewInBrowser(Context context, String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        if (null != intent.resolveActivity(context.getPackageManager())) {
            context.startActivity(intent);
        }
    }

}

