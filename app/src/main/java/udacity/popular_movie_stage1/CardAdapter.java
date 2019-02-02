package udacity.popular_movie_stage1;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;


public class CardAdapter extends RecyclerView.Adapter<CardAdapter.MyViewHolder> {

    private Context context;
    private List<CardModel> cardModelList;

    public CardAdapter(Context context, List<CardModel> CardModels) {
        this.context = context;
        this.cardModelList = CardModels;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card, parent, false);

        return new MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final CardModel model = cardModelList.get(position);

        Picasso.with(context).load(model.getPoster_path()).into(holder.movieImg);

        holder.title.setText(model.getOriginal_title());
        holder.release.setText("Release Date: " + model.getRelease_date());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent sand = new Intent(context, DetailActivity.class);
                sand.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                sand.putExtra("id", "" + model.getId());
                sand.putExtra("title", " " + model.getOriginal_title());
                sand.putExtra("poster_path", " " + model.getPoster_path());
                sand.putExtra("backdrop_path", "" + model.getBackdrop_path());
                sand.putExtra("release_date", " " + model.getRelease_date());
                sand.putExtra("overview", " " + model.getOverview());
                sand.putExtra("vote_average", " " + model.getVote_average());
                context.startActivity(sand);

                Log.e("backdrop", ":::::" + model.getBackdrop_path());

            }
        });

    }

    @Override
    public int getItemCount() {

        return cardModelList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, release;
        public ImageView movieImg;
        public CardView cardView;


        public MyViewHolder(View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.movieTitle);
            movieImg = (ImageView) itemView.findViewById(R.id.cardImg);
            release = (TextView) itemView.findViewById(R.id.exp);


            cardView = (CardView) itemView.findViewById(R.id.card_view);


        }
    }


}
