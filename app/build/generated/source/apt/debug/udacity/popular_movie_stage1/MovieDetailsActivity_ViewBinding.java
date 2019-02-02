// Generated code from Butter Knife. Do not modify!
package udacity.popular_movie_stage1;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MovieDetailsActivity_ViewBinding implements Unbinder {
  private MovieDetailsActivity target;

  private View view2131296419;

  @UiThread
  public MovieDetailsActivity_ViewBinding(MovieDetailsActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public MovieDetailsActivity_ViewBinding(final MovieDetailsActivity target, View source) {
    this.target = target;

    View view;
    target.textView_movieTitle = Utils.findRequiredViewAsType(source, R.id.tv_movie_title, "field 'textView_movieTitle'", TextView.class);
    target.textView_movie_language = Utils.findRequiredViewAsType(source, R.id.tv_movieLanguage, "field 'textView_movie_language'", TextView.class);
    target.textView_generes = Utils.findRequiredViewAsType(source, R.id.tv_genres, "field 'textView_generes'", TextView.class);
    target.textView_releaseDate = Utils.findRequiredViewAsType(source, R.id.tv_releaseDate, "field 'textView_releaseDate'", TextView.class);
    target.textView_tagline = Utils.findRequiredViewAsType(source, R.id.tv_tagline, "field 'textView_tagline'", TextView.class);
    target.textView_description = Utils.findRequiredViewAsType(source, R.id.tv_description, "field 'textView_description'", TextView.class);
    target.textView_rating = Utils.findRequiredViewAsType(source, R.id.tv_rating, "field 'textView_rating'", TextView.class);
    target.imageView_backdrop = Utils.findRequiredViewAsType(source, R.id.img_backDrop, "field 'imageView_backdrop'", ImageView.class);
    target.imageView_poster = Utils.findRequiredViewAsType(source, R.id.img_poster, "field 'imageView_poster'", ImageView.class);
    view = Utils.findRequiredView(source, R.id.img_btn_back, "field 'imageButton_back' and method 'onClick'");
    target.imageButton_back = Utils.castView(view, R.id.img_btn_back, "field 'imageButton_back'", ImageButton.class);
    view2131296419 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick();
      }
    });
    target.progressBar = Utils.findRequiredViewAsType(source, R.id.progressBar_movieDetails, "field 'progressBar'", ProgressBar.class);
    target.imageButton_favorite = Utils.findRequiredViewAsType(source, R.id.img_btn_favorite, "field 'imageButton_favorite'", ImageButton.class);
    target.recyclerView_casts = Utils.findRequiredViewAsType(source, R.id.recyclerView_casts, "field 'recyclerView_casts'", RecyclerView.class);
    target.recyclerView_trailers = Utils.findRequiredViewAsType(source, R.id.recyclerView_trailers, "field 'recyclerView_trailers'", RecyclerView.class);
    target.recyclerView_reviews = Utils.findRequiredViewAsType(source, R.id.recyclerView_reviews, "field 'recyclerView_reviews'", RecyclerView.class);
    target.textView_total_reviews = Utils.findRequiredViewAsType(source, R.id.tv_total_reviews, "field 'textView_total_reviews'", TextView.class);
    target.imageButton_more_reviews = Utils.findRequiredViewAsType(source, R.id.img_btn_more_reviews, "field 'imageButton_more_reviews'", ImageButton.class);
    target.cardView_reviews = Utils.findRequiredViewAsType(source, R.id.cv_reviews, "field 'cardView_reviews'", CardView.class);
    target.progressBarCasts = Utils.findRequiredViewAsType(source, R.id.progressBar_casts, "field 'progressBarCasts'", ProgressBar.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    MovieDetailsActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.textView_movieTitle = null;
    target.textView_movie_language = null;
    target.textView_generes = null;
    target.textView_releaseDate = null;
    target.textView_tagline = null;
    target.textView_description = null;
    target.textView_rating = null;
    target.imageView_backdrop = null;
    target.imageView_poster = null;
    target.imageButton_back = null;
    target.progressBar = null;
    target.imageButton_favorite = null;
    target.recyclerView_casts = null;
    target.recyclerView_trailers = null;
    target.recyclerView_reviews = null;
    target.textView_total_reviews = null;
    target.imageButton_more_reviews = null;
    target.cardView_reviews = null;
    target.progressBarCasts = null;

    view2131296419.setOnClickListener(null);
    view2131296419 = null;
  }
}
