package com.example.dahlia_android.ui.home;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dahlia_android.R;


public class HomeFeedListAdapter extends RecyclerView.Adapter<HomeFeedListAdapter.ViewHolder> {

    private static final String TAG = "HomeFeedAdapter";
    private static Feed _home_feed; // TODO: Put in HomeViewModel and use LiveData + Repository

    public HomeFeedListAdapter(Feed homeFeed) {
        _home_feed = homeFeed ;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_post, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d(TAG, "Post->" + position + "<-position");
        Post post = _home_feed.getPost(position);
        holder.setPostText(post.getPostText());
        holder.setPostMedia("");// TODO: Change/RMV
        holder.setPostProfileThumbnail("");// TODO: Change/RMV
        holder.setPostDate(post.getPostDate());
        holder.frameLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: Handle when user clicks post... Post(View)Fragment???
                /*
                TownDetailsFragment.currentTown = new Town(town);
                Intent intent = new Intent(v.getContext(),TownDetailsFragment.class);
                ContextCompat.startActivity(v.getContext(),intent, null);
                 */
            }
        });
    }

    @Override
    public int getItemCount() {
        return _home_feed.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final FrameLayout frameLayout;
        private final ImageView postProfileImageURL;
        private final TextView postText;
        private final TextView postDate;
        private final Button postReply;
        private final Button postLike;
        private final Button postMoreOptions;
        private ImageView postMedia; // TODO: Change for Video

        public ViewHolder(@NonNull View view) {
            super(view);
            frameLayout = view.findViewById(R.id.post_frame);

            postProfileImageURL = view.findViewById(R.id.post_profile_image);
            postText = view.findViewById(R.id.post_text);
            postDate = view.findViewById(R.id.post_date);
            postReply = view.findViewById(R.id.post_button_reply);
            postLike = view.findViewById(R.id.post_button_like);
            postMoreOptions = view.findViewById(R.id.post_button_more_options);
            postMedia = view.findViewById(R.id.post_media);
            postReply.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO: Implement Post reply button
                }
            });
            postLike.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO: Implement Post Liked
                }
            });
            postMoreOptions.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO: Implement Post more options button
                }
            });
        }

        public void setPostProfileThumbnail(String imageURL) {
            // TODO: Implement setting profile thumbnail of User on Post. Might need to change param
            postProfileImageURL.setImageResource(R.drawable.dahlia_logo_yellow_center_png); // TODO: CHANGE/RMV
        }

        public void setPostMedia(String mediaURL) {
            postMedia.setImageResource(R.drawable.dahlia_logo_yellow_center_png); // TODO: CHANGE/RMV
        }

        public void setPostText(String postText) {
            this.postText.setText(postText);
        }

        public void setPostDate(String postDate) {
            this.postDate.setText(postDate);
        }
    }
}
