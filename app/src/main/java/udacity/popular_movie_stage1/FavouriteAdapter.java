package udacity.popular_movie_stage1;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * Created by lenovo-pc on 7/18/2018.
 */

public class FavouriteAdapter extends RecyclerView.Adapter<FavouriteAdapter.MyViewHolder> {

    Context context;
    private List<FavouriteModel> cardModelList;

    public FavouriteAdapter(Context context, List<FavouriteModel> cardModelList) {
        this.context = context;
        this.cardModelList = cardModelList;
    }

    @Override
    public FavouriteAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final FavouriteModel favouriteModel = cardModelList.get(position);


        String image = favouriteModel.getImageUrl();
        Bitmap mIcon_val = null;

        URL newUrl = null;
        try {
            newUrl = new URL(image);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            mIcon_val = BitmapFactory.decodeStream(newUrl.openConnection().getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        holder.movieImg.setImageBitmap(mIcon_val);

        holder.title.setText(favouriteModel.getName());

    }

    @Override
    public int getItemCount() {

        return cardModelList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public ImageView movieImg;
        public CardView cardView;


        public MyViewHolder(View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.movieTitle);
            movieImg = (ImageView) itemView.findViewById(R.id.cardImg);
            cardView = (CardView) itemView.findViewById(R.id.card_view);


        }
    }
}
