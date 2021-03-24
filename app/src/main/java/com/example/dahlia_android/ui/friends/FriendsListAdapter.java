package com.example.dahlia_android.ui.friends;

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
import com.example.dahlia_android.ui.home.Post;

import java.util.List;


public class FriendsListAdapter extends RecyclerView.Adapter<FriendsListAdapter.ViewHolder> {

    private static final String TAG = "FriendsAdapter";
    private static FriendsList friends_list;

    public FriendsListAdapter(FriendsList friendsList) {
        friends_list = friendsList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_friend, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d(TAG, "Post->" + position + "<-position");
        Friend friend = friends_list.getFriend(position);

        holder.setFriendsDisplayName(friend.getFriendDisplayName());
        holder.setFriendsUsername(friend.getFriendDisplayName());
        holder.frameLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                 TODO: Handle when user clicks post... Post(View)Fragment???
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
        return friends_list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final FrameLayout frameLayout;
        private final ImageView friendProfileImageURL;
        private final TextView friendDisplayName;
        private final TextView friendUsername;
        private final Button friendMessage;
        private final Button friendMoreOptions;

        public ViewHolder(@NonNull View view) {
            super(view);
            frameLayout = view.findViewById(R.id.group_frame);

            friendProfileImageURL = view.findViewById(R.id.friend_profile_image);
            friendDisplayName = view.findViewById(R.id.friend_display_name);
            friendUsername = view.findViewById(R.id.friend_username);
            friendMessage = view.findViewById(R.id.friend_message);
            friendMoreOptions = view.findViewById(R.id.friend_more_options);
        }

        public void setFriendsDisplayName(String friendDisplayName) {
            this.friendDisplayName.setText(friendDisplayName);
        }

        public void setFriendsUsername(String friendsUsername) {
            this.friendUsername.setText(friendsUsername);
        }
    }
}
