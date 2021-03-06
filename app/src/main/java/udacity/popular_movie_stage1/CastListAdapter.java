package udacity.popular_movie_stage1;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.List;

import udacity.popular_movie_stage1.Utils.NetworkUtils;

public class CastListAdapter extends RecyclerView.Adapter<CastListAdapter.CastViewHolder>{

    private ArrayList<CastDetails> castDetails;
    private int totalCasts;
    private Context context;
    private boolean isFromFav;

    public CastListAdapter(ArrayList<CastDetails> castDetails, int totalCasts, boolean isFromFav){
        this.castDetails = castDetails;
        this.totalCasts = totalCasts;
        this.isFromFav = isFromFav;
    }
    @NonNull
    @Override
    public CastViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        int layoutID = R.layout.recyclerview_cast_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(layoutID , parent , false);


        return new CastViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CastViewHolder holder, int position) {

            if (castDetails.size() > 0) {

                holder.textView_name.setText(castDetails.get(position).getName());
                holder.textView_character.setText(castDetails.get(position).getCharacter());

                if (isFromFav){

//                    RequestOptions options = new RequestOptions()
//                            .error(R.drawable.error_bk)
//                            .diskCacheStrategy(DiskCacheStrategy.ALL)
//                            .priority(Priority.HIGH);

                    Glide.with(context)
                            .load(castDetails.get(position).getProfilePic())
                            .into(holder.imageView_cast);

                }else {
                    String path = castDetails.get(position).getProfile_path();
                    String url = NetworkUtils.buildURL_for_Image(path, false).toString();

//                    RequestOptions options = new RequestOptions()
//                            .error(R.drawable.error_bk)
//                            .diskCacheStrategy(DiskCacheStrategy.ALL)
//                            .priority(Priority.HIGH);

                    Glide.with(context)
                            .load(url)
                            .into(holder.imageView_cast);
                }
            }
    }

    @Override
    public int getItemCount() {
        return totalCasts;
    }

    class CastViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView_cast;
        TextView textView_name, textView_character;

        CastViewHolder(View itemView) {
            super(itemView);
            imageView_cast = itemView.findViewById(R.id.recyclerview_castlist_imageView);
            textView_name  = itemView.findViewById(R.id.recyclerview_castlist_textView_name);
            textView_character  = itemView.findViewById(R.id.recyclerview_castlist_textView_character);

            // Cache is enabled so as to obtain Bitmap later while saving in Database
            imageView_cast.setDrawingCacheEnabled(true);

        }
    }
}
