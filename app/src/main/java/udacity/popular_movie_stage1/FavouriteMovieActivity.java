package udacity.popular_movie_stage1;

import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class FavouriteMovieActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    Toolbar toolbar;
    private FavouriteAdapter adapter;
    List<FavouriteModel> favouriteModels = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite_movie);
        DataBase mdb = DataBase.getIsInstance(getApplicationContext());

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Favourite Movies");
        setSupportActionBar(toolbar);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        favouriteModels = mdb.userDao().getAll(true);
        adapter = new FavouriteAdapter(getApplicationContext(), favouriteModels);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 2);
        recyclerView.setLayoutManager(layoutManager);
        ViewCompat.setNestedScrollingEnabled(recyclerView, true);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {
        private int count, space;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int count, int space, boolean includeEdge) {
            this.count = count;
            this.space = space;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view);
            int column = position % count;

            if (includeEdge) {
                outRect.left = space - column * space / count;
                outRect.right = (column + 1) * space / count;

                if (position < count) {
                    outRect.top = space;
                }
                outRect.bottom = space;
            } else {
                outRect.left = column * space / count;
                outRect.right = space - (column + 1) * space / count;

                if (position > count) {
                    outRect.top = space;
                }
            }

        }
    }

    private int dpToPx(int i) {
        Resources res = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, i, res.getDisplayMetrics()));
    }


}
