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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dahlia_android.ApplicationUser;
import com.example.dahlia_android.R;
import com.example.dahlia_android.api.APIClient;
import com.example.dahlia_android.api.APIServiceInterface;
import com.example.dahlia_android.api.RVService;
import com.example.dahlia_android.data.DataRepository;
import com.example.dahlia_android.data.DataSource;
import com.example.dahlia_android.ui.friends.FriendsViewModel;
import com.example.dahlia_android.ui.groups.Group;
import com.example.dahlia_android.ui.home.Post;
import com.example.dahlia_android.ui.messages.CreateMessageActivity;
import com.example.dahlia_android.ui.messages.Message;
import com.example.dahlia_android.ui.messages.Messages;
import com.example.dahlia_android.ui.user.User;
import com.example.dahlia_android.ui.user.UserProfile;
import com.example.dahlia_android.ui.user.UserProfileActivity;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Response;


public class MainAdapter extends RecyclerView.Adapter {

    private static final String TAG = "MainAdapter";
    // TODO: RMV hardcoded USER_ID, TOKEN, MSG_ID, etc...
    private static final int MY_USER_ID = 148;
    public static final String TOKEN = "4c82f31197fb2300a90b13de623c8d335854037a";
    public static final int MSG_ID = -1;
    public static final int FRIEND_ID = -1;
    private static final int POST_ID = -1;
    private static final int GROUP_USER_ID = -1;
    private ArrayList<Object> dataList;
    public static AdapterTypeList adapterList;

    private APIServiceInterface apiInterface;
    private RVService rvService;

    public MainAdapter(ArrayList<Object> dataList) {
        this.dataList = dataList;
        this.adapterList =  convertAdapterType(dataList);
        this.rvService = new RVService() {
            @Override
            public void AddItem(Object item) {
                dataList.add(item);
                adapterList.add(item);
                notifyDataSetChanged();
            }

            @Override
            public void removeItem(int position) {
                dataList.remove(position);
                adapterList.remove(position);
                notifyDataSetChanged();
            }
        };
    }

    private AdapterTypeList convertAdapterType(ArrayList<Object> typeList) {
        if( typeList == null )
            return new AdapterTypeList();
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
                if(((Message) obj).getMessageCreator() == ApplicationUser.getCurrentUser().getUserID()) {
                    list.add(AdapterType.MESSAGE_TO_TYPE);
                } else
                    list.add(AdapterType.MESSAGE_FROM_TYPE);
            }
            else if( obj instanceof Messages) {
                list.add(AdapterType.MESSAGES_TYPE);
            }
        }
        return list;
    }

    public ArrayList<Object> getDataList() {
        return dataList;
    }

    public void setDataList(ArrayList<Object> dataList) {
        this.dataList = dataList;
    }

    @Override
    public int getItemViewType(int position) {
        if( dataList.get(position) instanceof UserProfile )
            return AdapterType.PROFILE_TYPE;
        else if( dataList.get(position) instanceof Post )
            return AdapterType.POST_TYPE;
        else if( dataList.get(position) instanceof User )
            return AdapterType.FRIEND_TYPE;
        else if( dataList.get(position) instanceof Group )
            return AdapterType.GROUP_TYPE;
        else if( dataList.get(position) instanceof Messages)
            return AdapterType.MESSAGES_TYPE;
        else if( dataList.get(position) instanceof Message) {
            if( ((Message) dataList.get(position)).getMessageCreator() == ApplicationUser.getCurrentUser().getUserID())
                return AdapterType.MESSAGE_TO_TYPE;
            else
                return AdapterType.MESSAGE_FROM_TYPE;
        }
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
        else if( viewType == AdapterType.MESSAGES_TYPE) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_messages, parent, false);
            return new MessagesViewHolder(view);
        }
        else if( viewType == AdapterType.MESSAGE_TO_TYPE) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_message_to, parent, false);
            return new MessageToViewHolder(view);
        }
        else if( viewType == AdapterType.MESSAGE_FROM_TYPE) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_message_from, parent, false);
            return new MessageFromViewHolder(view);
        }
        else if( viewType == AdapterType.TAB_BUTTONS_TYPE ) {
            // TODO: Change to Buttons type RecyclerView item, create row layout. Not used currently
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_messages, parent, false);
            return new MessagesViewHolder(view);
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
            ((PostViewHolder) holder).setPostObject(post, rvService);
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
            ((FriendViewHolder) holder).setFriendObject(user, rvService);
            ((FriendViewHolder) holder).setFriendsProfileThumbnail(""); // TODO: RMV/CHANGE
            ((FriendViewHolder) holder).setFriendsDisplayName(user.getUsername());
            ((FriendViewHolder) holder).setFriendsDescription(String.valueOf(user.getUserID())); // TODO: Change to description not UserID
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
            ((GroupViewHolder) holder).setGroupObject(group, rvService);
            ((GroupViewHolder) holder).setGroupThumbnail(""); // TODO: RMV
            ((GroupViewHolder) holder).setGroupName(group.getGroupName());
            ((GroupViewHolder) holder).setGroupDescription(group.getGroupDescription());
            ((GroupViewHolder) holder).frameLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO: Handle when user clicks Group... display Group Chat

                }
            });
        }
        else if( holder instanceof MessagesViewHolder) {
            Log.d(TAG, "Messages->" + position + "<-position");
            FriendsViewModel data = new FriendsViewModel(DataRepository.getInstance(new DataSource()));
            int myID = 0;
            try {
                myID = data.getMyID();
            } catch (NullPointerException e) {
                // do nothing
                return;
            }
            Messages msg = (Messages) dataList.get(position);
            User friend;
            if(msg.getMessage(position).getMessageReceiver() != myID)
                friend = data.getFriend(msg.getMessage(position).getMessageReceiver());
            else
                friend = data.getFriend(msg.getMessage(position).getMessageCreator());

            ((MessagesViewHolder) holder).setMessagesObject(msg, rvService);
            ((MessagesViewHolder) holder).setMessagesProfileImageURL(""); // TODO: CHANGE
            ((MessagesViewHolder) holder).setMessagesDisplayName(friend.getUsername());
            ((MessagesViewHolder) holder).setMessagesText(msg.getLastMessageText());
            ((MessagesViewHolder) holder).frameLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO: Handle when user clicks message... Open Conversation

                }
            });
        }
        else if( holder instanceof MessageToViewHolder ) {
            Log.d(TAG, "MessageTo->" + position + "<-position");
            Message msg = (Message) dataList.get(position);
            ((MessageToViewHolder) holder).setMessageToObject(msg, rvService);
            ((MessageToViewHolder) holder).setMessageToDate(msg.getMessageDate());
            ((MessageToViewHolder) holder).setMessageToUserText(msg.getMessageText());
            // TODO: Change to long press
            ((MessageToViewHolder) holder).frameLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO: Handle when user long presses message -> remove_message

                }
            });
        }
        else if( holder instanceof MessageFromViewHolder ) {
            Log.d(TAG, "MessageFrom->" + position + "<-position");
            Message msg = (Message) dataList.get(position);
            ((MessageFromViewHolder) holder).setMessageFromObject(msg, rvService);
            ((MessageFromViewHolder) holder).setMessageFromFriendProfileImageURL(""); // TODO: Implement
            ((MessageFromViewHolder) holder).setMessageFromDate(msg.getMessageDate());
            ((MessageFromViewHolder) holder).setMessageFromFriendText(msg.getMessageText());
            // TODO: Change to long press
            ((MessageFromViewHolder) holder).frameLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO: Handle when user long presses message -> remove_message

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
                    menu.getMenuInflater().inflate(R.menu.more_options_profile, menu.getMenu());
                    menu.show();
                    menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            switch (item.getItemId()) {
                                case R.id.menu_message_profile:
                                    // TODO: Go to MSG. Should create a message to user being currently viewed
//                                    Intent intent = new Intent(item.getMenuInfo(),)
                                    break;
                                case R.id.menu_invite_to_group_friends:
                                    // TODO: Invite to group
                                    break;
                                case R.id.menu_block_user_profile:
                                    DialogInterface.OnClickListener dialog = new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            switch(which) {
                                                case DialogInterface.BUTTON_POSITIVE:
                                                    // Yes Remove Friend
                                                    //TODO: Implement block user/friend
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
            // TODO: Implement setting profile thumbnail of User on Profile. Might need to change param
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

        private Post current_Post;
        private APIServiceInterface apiInterface;
        private RVService rvService;

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
                    PopupMenu menu = new PopupMenu(v.getContext(), postMoreOptions);
                    menu.getMenuInflater().inflate(R.menu.more_options_post, menu.getMenu());
                    menu.show();
                    menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            switch (item.getItemId()) {
                                case R.id.menu_reply_post:
                                    // TODO: reply to Post
                                    break;
                                case R.id.menu_like_post:
                                    // TODO: like Post
                                    break;
                                case R.id.menu_user_profile_post:
                                    // TODO: Go to profile of Post user
                                    break;
                                case R.id.menu_invite_to_group_post:
                                    // TODO: Invite Post user to Group
                                    break;
                                case R.id.remove_post:
                                    // TODO: Prompt user if they want to remove post
                                    DialogInterface.OnClickListener dialog = new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            switch(which) {
                                                case DialogInterface.BUTTON_POSITIVE:
                                                    // Yes, Remove Post
                                                    removePost(current_Post, rvService);
                                                    Log.d(TAG, "removePost: Post removed." );
                                                    break;
                                                case DialogInterface.BUTTON_NEGATIVE:
                                                    // No Don't
                                                    dialog.cancel();
                                                    break;

                                            }
                                        }
                                    };
                                    AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                                    builder.setMessage("Are you sure you want to delete post?")
                                            .setPositiveButton("Yes", dialog)
                                            .setNegativeButton("No", dialog).show();
                            }
                            return true;
                        }
                    });
                }
            });
        }

        private void removePost(Post currentPost, RVService rvService) {
            try {
                apiInterface = APIClient.getClient().create(APIServiceInterface.class);
//                Call<Void> removeCall = apiInterface.removePost(TOKEN, currentPost.getPostID()); // TODO: hardcoded token
                Call<Void> removeCall = apiInterface.removePost(TOKEN, POST_ID, MY_USER_ID);
                Response<Void> response = removeCall.execute();
                Log.d(TAG, "removePost: " + response.message() );
                rvService.removeItem(getAdapterPosition());
            } catch (IOException e) {
                e.printStackTrace();
            }
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

        public void setPostObject(Post post, RVService service) {
            this.current_Post = post;
            this.rvService = service;
        }
    }

    public static class FriendViewHolder extends RecyclerView.ViewHolder {
        private final FrameLayout frameLayout;
        private final ImageView friendProfileImageURL;
        private final TextView friendDisplayName;
        private final TextView friendDescription;
        private final Button friendMessage;
        private final Button friendMoreOptions;

        private User current_Friend;
        private APIServiceInterface apiInterface;
        private RVService rvService;

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
                    menu.getMenuInflater().inflate(R.menu.more_options_friends, menu.getMenu());
                    menu.show();
                    menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            switch (item.getItemId()) {
                                case R.id.menu_message_friends:
                                    // TODO: Go to MSG
//                                    Intent intent = new Intent(item.getMenuInfo(),)
                                    break;
                                case R.id.menu_user_profile_friends:
                                    // TODO: Go to Users profile
                                    break;
                                case R.id.menu_invite_to_group_friends:
                                    // TODO: Go to Users profile
                                    break;
                                case R.id.remove_friend:
                                    // TODO: Prompt user if they want to remove friend
                                    DialogInterface.OnClickListener dialog = new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            switch(which) {
                                                case DialogInterface.BUTTON_POSITIVE:
                                                    // Yes, Remove Friend
                                                    removeFriend(current_Friend, rvService);
                                                    Log.d(TAG, "removeFriend: Friend removed." );
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

        private void removeFriend(User currentFriend, RVService rvService) {
            try {
                apiInterface = APIClient.getClient().create(APIServiceInterface.class);
//                Call<Void> removeCall = apiInterface.removeFriend(TOKEN, currentFriend.getUserID());
                Call<Void> removeCall = apiInterface.removeFriend(TOKEN, currentFriend.getUserID(), MY_USER_ID); // TODO: hardcoded token
                Response<Void> response = removeCall.execute();
                Log.d(TAG, "removeFriend: " + response.message() );
                rvService.removeItem(getAdapterPosition());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void setFriendsProfileThumbnail(String imageURL) {
            // TODO: Implement setting profile thumbnail of friend on friendslist. Might need to change param
            friendProfileImageURL.setImageResource(R.drawable.dahlia_logo_yellow_center_png); // TODO: CHANGE/RMV
        }

        public void setFriendsDisplayName(String displayName) {
            this.friendDisplayName.setText(displayName);
        }

        public void setFriendsDescription(String description) {
            // TODO: Change to User Description or First and Last name
            this.friendDescription.setText(description);
        }

        public void setFriendObject(User user, RVService service) {
            this.current_Friend = user;
            this.rvService = service;
        }
    }

    public static class GroupViewHolder extends RecyclerView.ViewHolder {
        private final FrameLayout frameLayout;
        private final ImageView groupProfileImageURL;
        private final TextView groupName;
        private final TextView groupDescription;
        private final Button groupMoreOptions;

        private Group current_Group;
        private APIServiceInterface apiInterface;
        private RVService rvService;

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
                    PopupMenu menu = new PopupMenu(v.getContext(), groupMoreOptions);
                    menu.getMenuInflater().inflate(R.menu.more_options_groups, menu.getMenu());
                    menu.show();
                    menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            switch (item.getItemId()) {
                                case R.id.menu_message_groups:
                                    // TODO: Go to MSG // Change to Create Message
//                                    Intent intent = new Intent(item.getMenuInfo(),)
                                    break;
                                case R.id.menu_invite_to_group_groups:
                                    // TODO: Go to Users profile
                                    break;
                                case R.id.leave_group:
                                    DialogInterface.OnClickListener dialog = new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            switch(which) {
                                                case DialogInterface.BUTTON_POSITIVE:
                                                    // Yes Remove Group
                                                    removeGroup(current_Group, rvService);
                                                    Log.d(TAG, "removeGroup: Group removed." );
                                                    break;
                                                case DialogInterface.BUTTON_NEGATIVE:
                                                    // No Don't
                                                    dialog.cancel();
                                                    break;

                                            }
                                        }
                                    };
                                    AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                                    builder.setMessage("Are you sure you want to leave group?")
                                            .setPositiveButton("Yes", dialog)
                                            .setNegativeButton("No", dialog).show();
                            }
                            return true;
                        }
                    });
                }
            });
        }

        private void removeGroup(Group currentGroup, RVService rvService) {
            try {
                apiInterface = APIClient.getClient().create(APIServiceInterface.class);
//                Call<Void> removeCall = apiInterface.removeGroupUser(TOKEN, currentGroup.getGroupID(), GROUP_USER_ID , MY_USER_ID); // TODO: hardcoded token
                Call<Void> removeCall = apiInterface.removeGroupUser(TOKEN, currentGroup.getGroupID(), MY_USER_ID); // TODO: hardcoded token
                Response<Void> response = removeCall.execute();
                Log.d(TAG, "removeGroup: " + response.message() );
                rvService.removeItem(getAdapterPosition());
            } catch (IOException e) {
                e.printStackTrace();
            }
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
        public void setGroupObject(Group group, RVService service) {
            this.current_Group = group;
            this.rvService = service;
        }
    }

    public static class MessagesViewHolder extends RecyclerView.ViewHolder {
        private final FrameLayout frameLayout;
        private final ImageView messageProfileImageURL;
        private final TextView messageDisplayName;
        private final TextView messageText;
        private Button messageMoreOptions;

        private Messages current_messages;
        private APIServiceInterface apiInterface;
        private RVService rvService;

        public MessagesViewHolder(@NonNull View view) {
            super(view);
            frameLayout = view.findViewById(R.id.message_frame);
            messageProfileImageURL = view.findViewById(R.id.message_profile_image);
            messageDisplayName = view.findViewById(R.id.message_user_from);
            messageText = view.findViewById(R.id.message_text);
            messageMoreOptions = view.findViewById(R.id.message_more_options);

            messageMoreOptions.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO: Implement message more options
                    PopupMenu menu = new PopupMenu(v.getContext(), messageMoreOptions);
                    menu.getMenuInflater().inflate(R.menu.more_options_messages, menu.getMenu());
                    menu.show();
                    menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            switch (item.getItemId()) {
                                case R.id.menu_reply_message:
                                    // TODO: Go to MSG // Change to Create Message
//                                    Intent intent = new Intent(item.getMenuInfo(),)
                                    break;
                                case R.id.menu_invite_to_group_messages:
                                    // TODO: Go to Users profile
                                    break;
                                case R.id.remove_message:
                                    DialogInterface.OnClickListener dialog = new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            switch(which) {
                                                case DialogInterface.BUTTON_POSITIVE:
                                                    // Yes, Remove Message
                                                    removeConversation(current_messages, rvService);
                                                    Log.d(TAG, "removeConversation: Conversation removed." );
                                                    break;
                                                case DialogInterface.BUTTON_NEGATIVE:
                                                    // No Don't
                                                    dialog.cancel();
                                                    break;

                                            }
                                        }
                                    };
                                    AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                                    builder.setMessage("Are you sure you want to delete message?")
                                            .setPositiveButton("Yes", dialog)
                                            .setNegativeButton("No", dialog).show();
                            }
                            return true;
                        }
                    });
                }
            });
        }

        public void removeConversation(Messages currentMessage, RVService rvService) {
            try {
                apiInterface = APIClient.getClient().create(APIServiceInterface.class);
//                Call<Void> removeCall = apiInterface.removeMessage(TOKEN, currentMessage.getMessageID());
                Call<Void> removeCall = apiInterface.removeMessages(TOKEN, MSG_ID,FRIEND_ID); // TODO: Hardcoded
                Response<Void> response = removeCall.execute();
                Log.d(TAG, "removeConversation: " + response.message() );
                rvService.removeItem(getAdapterPosition());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void setMessagesProfileImageURL(String imageURL) {
            messageProfileImageURL.setImageResource(R.drawable.dahlia_logo_yellow_center_png); // TODO: CHANGE/RMV
        }

        public void setMessagesDisplayName(String displayName) {
            this.messageDisplayName.setText(displayName);
        }

        public void setMessagesText(String messageText) {
            this.messageText.setText(messageText);
        }

        public void setMessagesObject(Messages currentMessages, RVService service) {
            this.current_messages = currentMessages;
            this.rvService = service;
        }
    }

    public static class MessageToViewHolder extends RecyclerView.ViewHolder {
        private final FrameLayout frameLayout;
        private final TextView conversationUserText;
        private final TextView conversationDateTo;

        private Message current_message_to;
        private APIServiceInterface apiInterface;
        private RVService rvService;

        public MessageToViewHolder(@NonNull View view) {
            super(view);
            frameLayout = view.findViewById(R.id.conversation_frame_to);
            conversationUserText = view.findViewById(R.id.conversation_text_user);
            conversationDateTo = view.findViewById(R.id.conversation_date_to);
        }

        public void setMessageToUserText(String messageText) {
            this.conversationUserText.setText(messageText);
        }

        public void setMessageToDate(String messageText) {
            this.conversationDateTo.setText(messageText);
        }

        public void setMessageToObject(Message currentMessage, RVService service) {
            this.current_message_to = currentMessage;
            this.rvService = service;
        }
    }

    public static class MessageFromViewHolder extends RecyclerView.ViewHolder {
        private final FrameLayout frameLayout;
        private final ImageView conversationFriendProfileImageURL;
        private final TextView conversationFriendText;
        private final TextView conversationDateFrom;

        private Message current_message_from;
        private APIServiceInterface apiInterface;
        private RVService rvService;

        public MessageFromViewHolder(@NonNull View view) {
            super(view);
            frameLayout = view.findViewById(R.id.conversation_frame_from);
            conversationFriendProfileImageURL = view.findViewById(R.id.conversation_friend_profile_image);
            conversationFriendText = view.findViewById(R.id.conversation_text_friend);
            conversationDateFrom = view.findViewById(R.id.conversation_date_from);
        }

        public void setMessageFromFriendProfileImageURL (String imageURL){
            conversationFriendProfileImageURL.setImageResource(R.drawable.dahlia_logo_yellow_center_png); // TODO: CHANGE/RMV
        }

        public void setMessageFromFriendText (String messageText){
            this.conversationFriendText.setText(messageText);
        }

        public void setMessageFromDate (String messageText){
            this.conversationDateFrom.setText(messageText);
        }

        public void setMessageFromObject (Message currentMessage, RVService service){
            this.current_message_from = currentMessage;
            this.rvService = service;
        }
    }
}

