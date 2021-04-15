package com.example.dahlia_android.ui.user;

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
import com.example.dahlia_android.ui.friends.FriendsList;
import com.example.dahlia_android.ui.friends.FriendsListAdapter;
import com.example.dahlia_android.ui.groups.Groups;
import com.example.dahlia_android.ui.groups.GroupsListAdapter;
import com.example.dahlia_android.ui.home.Feed;
import com.example.dahlia_android.ui.home.HomeFeedListAdapter;
import com.example.dahlia_android.ui.messages.Messages;
import com.example.dahlia_android.ui.messages.MessagesListAdapter;


public class UserProfileDataAdapter extends RecyclerView.Adapter<UserProfileDataAdapter.ViewHolder> {

    public interface AdapterType{
        int PROFILE_TYPE = 1;
        int HOME_FEED_TYPE = 1;
        int FRIENDS_LIST_TYPE = 2;
        int GROUPS_TYPE = 3;
        int MESSAGES_TYPE = 4;
    }

    private static final String TAG = "FriendsAdapter";
    private static UserProfileCombinedList user_profile; // TODO: Put in UserProfile???ViewModel and use LiveData + Repository
    public static UserProfile currentProfile;

    public UserProfileDataAdapter(UserProfileCombinedList profileCombined) {
        this.user_profile = profileCombined;
    }

    @Override
    public int getItemViewType(int position) {
        if( user_profile.get(position) instanceof UserProfile )
            return AdapterType.PROFILE_TYPE;
        else if( user_profile.get(position) instanceof Feed )
            return AdapterType.HOME_FEED_TYPE;
        else if( user_profile.get(position) instanceof FriendsList )
            return AdapterType.FRIENDS_LIST_TYPE;
        else if( user_profile.get(position) instanceof Groups )
            return AdapterType.GROUPS_TYPE;
        else if( user_profile.get(position) instanceof Messages)
            return AdapterType.MESSAGES_TYPE;
        else
            return -1;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
/*        if( viewType == AdapterType.PROFILE_TYPE ) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_profile, parent, false);
            return new UserProfileDataAdapter.ViewHolder(view);
        }
        else if( viewType == AdapterType.HOME_FEED_TYPE ) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_post, parent, false);
            return new HomeFeedListAdapter.ViewHolder(view);
        }
        else if( viewType == AdapterType.FRIENDS_LIST_TYPE ) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_friend, parent, false);
            return new FriendsListAdapter.ViewHolder(view);
        }
        else if( viewType == AdapterType.GROUPS_TYPE ) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_group, parent, false);
            return new GroupsListAdapter.ViewHolder(view);
        }
        else if( viewType == AdapterType.MESSAGES_TYPE ) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_message, parent, false);
            return new MessagesListAdapter.ViewHolder(view);
        }
        else
            return null;*/
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_profile, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d(TAG, "ProfileItem->" + position + "<-position");
        UserProfile profile = (UserProfile) user_profile.get(position);

        holder.setUserProfileThumbnail("");
        holder.setProfileDisplayName(profile.getDisplayName());
        holder.setProfileUsername(profile.getUsername());
        holder.frameLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                 TODO: Handle when user clicks profile RecyclerView...
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
        return user_profile.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final FrameLayout frameLayout;
        private final ImageView profileBannerURL;
        private final ImageView profileThumbnailURL;
        private final TextView profileDisplayName;
        private final TextView profileUsername;
        private final TextView profileLocation;
        private final TextView profileDateCreated;
        private final TextView profileFollowerCount;
        private final TextView profileFollowingCount;
        private final Button profileMessage;
        private final Button profileMoreOptions;
        private final Button profileAddFriend;

        public ViewHolder(@NonNull View view) {
            super(view);
            frameLayout = view.findViewById(R.id.profile_frame);
            profileBannerURL = view.findViewById(R.id.profile_banner);
            profileThumbnailURL = view.findViewById(R.id.profile_thumbnail);
            profileDisplayName = view.findViewById(R.id.profile_displayname);
            profileUsername = view.findViewById(R.id.profile_username);
            profileLocation = view.findViewById(R.id.profile_location);
            profileDateCreated = view.findViewById(R.id.profile_date_created);
            profileFollowerCount = view.findViewById(R.id.profile_follower_count);
            profileFollowingCount = view.findViewById(R.id.profile_following_count);

            profileMessage = view.findViewById(R.id.profile_message);
            profileMoreOptions = view.findViewById(R.id.profile_more_options);
            profileAddFriend = view.findViewById(R.id.profile_add_friend);
            profileMessage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO: implement message friend
                }
            });
            profileMoreOptions.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PopupMenu menu = new PopupMenu(v.getContext(), profileMoreOptions);
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
            profileAddFriend.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO: Implement Following button

                }
            });
        }

        public void setUserProfileThumbnail(String imageURL) {
            // TODO: Implement setting profile thumbnail of friend on friendslist. Might need to change param
            profileThumbnailURL.setImageResource(R.drawable.dahlia_logo_yellow_center_png); // TODO: CHANGE/RMV
        }

        public void setProfileDisplayName(String friendDisplayName) {
            this.profileDisplayName.setText(friendDisplayName);
        }

        public void setProfileUsername(String friendsDescription) {
            this.profileUsername.setText(friendsDescription);
        }
    }
}
