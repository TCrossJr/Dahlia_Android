package com.example.dahlia_android.ui.friends;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dahlia_android.R;
import com.example.dahlia_android.ui.user.User;
import com.example.dahlia_android.ui.user.UserProfile;
import com.example.dahlia_android.ui.user.UserProfileActivity;


public class FriendsListAdapter extends RecyclerView.Adapter<FriendsListAdapter.ViewHolder> {

    private static final String TAG = "FriendsAdapter";
    private static FriendsList friends_list; // TODO: Put in FriendsViewModel and use LiveData + Repository
    public static UserProfile selectedUser;

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
        Log.d(TAG, "Friend->" + position + "<-position");
        User user = friends_list.getFriend(position);

        holder.setFriendsProfileThumbnail(""); // TODO: RMV/CHANGE
        holder.setFriendsDisplayName(user);
        holder.setFriendsDescription(user);
        holder.frameLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedUser = new UserProfile(user.getUserProfile());
                Intent intent = new Intent(v.getContext(), UserProfileActivity.class);
                ContextCompat.startActivity(v.getContext(),intent,null);
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
        private final TextView friendDescription;
        private final Button friendMessage;
        private final Button friendMoreOptions;

        public ViewHolder(@NonNull View view) {
            super(view);
            frameLayout = view.findViewById(R.id.friend_frame);
            friendProfileImageURL = view.findViewById(R.id.friend_profile_image);
            friendDisplayName = view.findViewById(R.id.friend_display_name);
            friendDescription = view.findViewById(R.id.friend_description);
            friendMessage = view.findViewById(R.id.friend_message);
            friendMoreOptions = view.findViewById(R.id.friend_more_options);
            friendMessage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO: implement message friend
                }
            });
            friendMoreOptions.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PopupMenu menu = new PopupMenu(v.getContext(), friendMoreOptions);
                    menu.getMenuInflater().inflate(R.menu.more_options, menu.getMenu());
                    menu.show();
                    menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            // TODO: implement item clicks
                            switch (item.getItemId()) {
                                case R.id.nav_messages:
                                    // TODO: Go to MSG
//                                    Intent intent = new Intent(item.getMenuInfo(),)
                                    break;
                                case R.id.nav_user_profile:
                                    // TODO: Go to Users profile
                                    break;
                                case R.id.nav_invite_to_group:
                                    // TODO: Go to Users profile
                                    break;
                                case R.id.nav_remove_friend:
                                    // TODO: Prompt user if they want to remove friend
                                    DialogInterface.OnClickListener dialog = new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            switch(which) {
                                                case DialogInterface.BUTTON_POSITIVE:
                                                    // Yes Remove Friend
                                                    //TODO: Implement remove friend
                                                    break;
                                                case DialogInterface.BUTTON_NEGATIVE:
                                                    // No Don't
                                                    dialog.cancel();
                                                    break;

                                            }
                                        }
                                    };
                                    AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                                    builder.setMessage("Are you sure you want to remove friend?")
                                            .setPositiveButton("Yes", dialog)
                                            .setNegativeButton("No", dialog).show();
                            }
                            return true;
                        }
                    });
                }
            });
        }

        public void setFriendsProfileThumbnail(String imageURL) {
            // TODO: Implement setting profile thumbnail of friend on friendslist. Might need to change param
            friendProfileImageURL.setImageResource(R.drawable.dahlia_logo_yellow_center_png); // TODO: CHANGE/RMV
        }

        public void setFriendsDisplayName(User selectedUser) {
//            this.friendDisplayName.setText(selectedUser);
            this.friendDisplayName.setText("Display Name");
        }

        public void setFriendsDescription(User selectedUser) {
//            this.friendDescription.setText(selectedUser);
            this.friendDescription.setText("User Description");
        }
    }
}
