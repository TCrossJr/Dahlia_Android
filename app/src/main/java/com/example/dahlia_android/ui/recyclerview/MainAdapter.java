package com.example.dahlia_android.ui.recyclerview;

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
import com.example.dahlia_android.ui.groups.Group;
import com.example.dahlia_android.ui.home.Post;
import com.example.dahlia_android.ui.messages.Message;
import com.example.dahlia_android.ui.user.User;
import com.example.dahlia_android.ui.user.UserProfile;
import com.example.dahlia_android.ui.user.UserProfileActivity;

import java.util.ArrayList;


public class MainAdapter extends RecyclerView.Adapter {

    private static final String TAG = "MainAdapter";
    public static AdapterTypeList adapterList;
    private ArrayList<Object> dataList;
//    public static UserProfile currentProfile;

    public MainAdapter(AdapterTypeList list) {
        this.adapterList = list;
        this.dataList = new ArrayList<>(0);
    }

    public MainAdapter(ArrayList<Object> list) {
        this.adapterList = null;
        this.dataList = list;
    }

    public MainAdapter(ArrayList<Object> dataList, AdapterTypeList adapterTypeList) {
        this.adapterList =  adapterTypeList;
        this.dataList = dataList;
        convertAdapterType(dataList);
    }

    private void convertAdapterType(ArrayList<Object> typeList) {
        AdapterTypeList list = new AdapterTypeList();
        for( int i = 0; i < typeList.size(); i++ ) {
            Object obj = typeList.get(i);
            if( obj instanceof UserProfile ) {
                list.add(AdapterType.PROFILE_TYPE);
            }
            else if( obj instanceof Post ) {
                list.add(AdapterType.POST_TYPE);
            }
            else if( obj instanceof User ) {
                list.add(AdapterType.FRIEND_TYPE);
            }
            else if( obj instanceof Group ) {
                list.add(AdapterType.GROUP_TYPE);
            }
            else if( obj instanceof Message ) {
                list.add(AdapterType.MESSAGE_TYPE);
            }
        }
        adapterList = list;
    }

    public void setDataList(ArrayList<Object> dataList) {
        this.dataList = dataList;
    }

    @Override
    public int getItemViewType(int position) {
        if( adapterList.get(position) instanceof UserProfile )
            return AdapterType.PROFILE_TYPE;
        else if( dataList.get(position) instanceof Post )
            return AdapterType.POST_TYPE;
        else if( dataList.get(position) instanceof User )
            return AdapterType.FRIEND_TYPE;
        else if( dataList.get(position) instanceof Group )
            return AdapterType.GROUP_TYPE;
        else if( dataList.get(position) instanceof Message)
            return AdapterType.MESSAGE_TYPE;
        else
            return -1;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if( viewType == AdapterType.PROFILE_TYPE ) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_profile, parent, false);
            return new ProfileViewHolder(view);
        }
        else if( viewType == AdapterType.POST_TYPE) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_post, parent, false);
            return new PostViewHolder(view);
        }
        else if( viewType == AdapterType.FRIEND_TYPE) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_friend, parent, false);
            return new FriendViewHolder(view);
        }
        else if( viewType == AdapterType.GROUP_TYPE) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_group, parent, false);
            return new GroupViewHolder(view);
        }
        else if( viewType == AdapterType.MESSAGE_TYPE) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_message, parent, false);
            return new MessageViewHolder(view);
        }
        else if( viewType == AdapterType.TAB_BUTTONS_TYPE ) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_message, parent, false);
            return new MessageViewHolder(view);
        }
        else
            return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if( holder instanceof ProfileViewHolder ) {
            Log.d(TAG, "MAIN_Profile_Item->" + position + "<-position");
            UserProfile profile = (UserProfile) dataList.get(position);

            ((ProfileViewHolder) holder).setUserProfileThumbnail("");
            ((ProfileViewHolder) holder).setProfileDisplayName(profile.getDisplayName());
            ((ProfileViewHolder) holder).setProfileUsername(profile.getUsername());
            ((ProfileViewHolder) holder).frameLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO: Handle when user clicks profile RecyclerView...

                }
            });
        }
        else if( holder instanceof PostViewHolder ) {
            Log.d(TAG, "Post->" + position + "<-position");
            Post post = (Post) dataList.get(position);
            ((PostViewHolder) holder).setPostText(post.getPostText());
            ((PostViewHolder) holder).setPostMedia("");// TODO: Change/RMV
            ((PostViewHolder) holder).setPostProfileThumbnail("");// TODO: Change/RMV
            ((PostViewHolder) holder).setPostDate(post.getPostDate());
            ((PostViewHolder) holder).frameLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO: Handle when user clicks post... Post(View)Fragment???

                }
            });
        }
        else if( holder instanceof FriendViewHolder ) {
            Log.d(TAG, "Friend->" + position + "<-position");
            User user = (User) dataList.get(position);

            ((FriendViewHolder) holder).setFriendsProfileThumbnail(""); // TODO: RMV/CHANGE
            ((FriendViewHolder) holder).setFriendsDisplayName(user);
            ((FriendViewHolder) holder).setFriendsDescription(user);
            ((FriendViewHolder) holder).frameLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    UserProfile selectedUser = new UserProfile(user.getUserProfile());
                    Intent intent = new Intent(v.getContext(), UserProfileActivity.class);
                    ContextCompat.startActivity(v.getContext(),intent,null);
                }
            });
        }
        else if( holder instanceof GroupViewHolder ) {
            Log.d(TAG, "Group->" + position + "<-position");
            Group group = (Group) dataList.get(position);
            ((GroupViewHolder) holder).setGroupThumbnail(""); // TODO: RMV
            ((GroupViewHolder) holder).setGroupName(group.getGroupName());
            ((GroupViewHolder) holder).setGroupDescription(group.getGroupDescription());
            ((GroupViewHolder) holder).frameLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO: Handle when user clicks Group...

                }
            });
        }
        else if( holder instanceof MessageViewHolder ) {
            Log.d(TAG, "Message->" + position + "<-position");
            Message msg = (Message) dataList.get(position);
            ((MessageViewHolder) holder).setMessageProfileImageURL(""); // TODO: CHANGE/RMV
            ((MessageViewHolder) holder).setMessagesDisplayName(msg.getMessageDisplayName());
            ((MessageViewHolder) holder).setMessageText(msg.getMessageText());
            ((MessageViewHolder) holder).frameLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                // TODO: Handle when user clicks message... Message (New v  iew) Fragment???

                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if( adapterList != null)
            return adapterList.size();
        else
            return 0;
    }

    public static class ProfileViewHolder extends RecyclerView.ViewHolder {
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

        public ProfileViewHolder(@NonNull View view) {
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

    public static class PostViewHolder extends RecyclerView.ViewHolder {
        private final FrameLayout frameLayout;
        private final ImageView postProfileImageURL;
        private final TextView postText;
        private final TextView postDate;
        private final Button postReply;
        private final Button postLike;
        private final Button postMoreOptions;
        private ImageView postMedia; // TODO: Change for Video

        public PostViewHolder(@NonNull View view) {
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

    public static class FriendViewHolder extends RecyclerView.ViewHolder {
        private final FrameLayout frameLayout;
        private final ImageView friendProfileImageURL;
        private final TextView friendDisplayName;
        private final TextView friendDescription;
        private final Button friendMessage;
        private final Button friendMoreOptions;

        public FriendViewHolder(@NonNull View view) {
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

    public static class GroupViewHolder extends RecyclerView.ViewHolder {
        private final FrameLayout frameLayout;
        private final ImageView groupProfileImageURL;
        private final TextView groupName;
        private final TextView groupDescription;
        private final Button groupMoreOptions;

        public GroupViewHolder(@NonNull View view) {
            super(view);
            frameLayout = view.findViewById(R.id.group_frame);
            groupProfileImageURL = view.findViewById(R.id.group_profile_image);
            groupName = view.findViewById(R.id.group_name);
            groupDescription = view.findViewById(R.id.group_description);
            groupMoreOptions = view.findViewById(R.id.group_more_options);
            groupMoreOptions.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO: Implement group more options
                }
            });
        }

        public void setGroupThumbnail(String imageURL) {
            // TODO: Implement setting profile thumbnail of group. Might need to change param
            groupProfileImageURL.setImageResource(R.drawable.dahlia_logo_yellow_center_png);
        }

        public void setGroupName(String groupName) {
            this.groupName.setText(groupName);
        }

        public void setGroupDescription(String groupDescription) {
            this.groupDescription.setText(groupDescription);
        }
    }

    public static class MessageViewHolder extends RecyclerView.ViewHolder {
        private final FrameLayout frameLayout;
        private final ImageView messageProfileImageURL;
        private final TextView messageDisplayName;
        private final TextView messageText;
        private Button messageMoreOptions;

        public MessageViewHolder(@NonNull View view) {
            super(view);
            frameLayout = view.findViewById(R.id.message_frame);
            messageProfileImageURL = view.findViewById(R.id.message_profile_image);
            messageDisplayName = view.findViewById(R.id.message_user_from);
            messageText = view.findViewById(R.id.message_text);
            messageMoreOptions = view.findViewById(R.id.message_more_options);
            messageMoreOptions.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO: More options button
                }
            });
        }

        public void setMessageProfileImageURL(String imageURL) {
            messageProfileImageURL.setImageResource(R.drawable.dahlia_logo_yellow_center_png); // TODO: CHANGE/RMV
        }

        public void setMessagesDisplayName(String displayName) {
            this.messageDisplayName.setText(displayName);
        }

        public void setMessageText(String messageText) {
            this.messageText.setText(messageText);
        }
    }
}
