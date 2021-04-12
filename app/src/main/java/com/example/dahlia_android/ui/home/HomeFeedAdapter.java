package com.example.dahlia_android.ui.home;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dahlia_android.R;
import com.example.dahlia_android.ui.user.User;

import java.util.List;


public class HomeFeedAdapter extends RecyclerView.Adapter<HomeFeedAdapter.ViewHolder> {

    private static final String TAG = "HomeFeedAdapter";
    private static HomeFeed home_feed;

    public HomeFeedAdapter(HomeFeed feed) {
        home_feed = feed;
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
        Post post = home_feed.getPost(position);

//        holder.setProfileImageURL(post.getProfileImageURL());
//        holder.setPostDate(post.getPostDate());
        holder.setPostText(post.getPostText());
//        holder.setPostMedia(post.getMediaURL());
//        holder.setPostLikedBy(post.getPostLikedBy());
//        holder.frameLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                 TODO: Handle when user clicks post... Post(View)Fragment???
                /*
                TownDetailsFragment.currentTown = new Town(town);
                Intent intent = new Intent(v.getContext(),TownDetailsFragment.class);
                ContextCompat.startActivity(v.getContext(),intent, null);
                 */

//            }
//        });

//        holder.setReplies(post.getReplies());
//        holder.setLikes(post.getLikes());
    }

    @Override
    public int getItemCount() {
        return home_feed.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private FrameLayout frameLayout;
        private ImageView profileImageURL;
        private TextView postDate;
        private TextView postLikedBy;
        private TextView postText;
        private ImageView postMedia;
        private Button replies;
        private Button likes;

        public ViewHolder(@NonNull View view) {
            super(view);
            frameLayout = view.findViewById(R.id.post_frame);

//            profileImageURL = view.findViewById(R.id.post_profile_image);
//            postDate = view.findViewById(R.id.post_date);
            postText = view.findViewById(R.id.post_text);
//            postMedia = view.findViewById(R.id.post_media);
//            postLikedBy = view.findViewById(R.id.post_liked);
//            replies = view.findViewById(R.id.post_button_reply);
//            likes = view.findViewById(R.id.post_button_like);
        }

        public void setProfileImageURL(String profileImageURL) {
            // set profile image from post here
        }

        public void setPostDate(String postDate) {
            this.postDate.setText(postDate);
        }

        public void setPostLikedBy(List<User> postLikedBy) {
//            Add likedBy List into Strings displayed on post
//            this.postLikedBy = postLikedBy;
        }

        public void setPostText(String postText) {
            this.postText.setText(postText);
        }

        public void setPostMedia(String postMedia) {
            // set image media here
//            this.postMedia = postMedia;
        }
/*
        public void setReplies(Button replies) {
            this.replies = replies;
        }

        public void setLikes(Button likes) {
            this.likes.setText(likes);
        }
*/
    }
}
