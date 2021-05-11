package com.example.dahlia_android.ui.groups;

import com.example.dahlia_android.ui.friends.FriendsList;
import com.example.dahlia_android.ui.messages.Messages;
import com.example.dahlia_android.ui.user.User;
import com.google.gson.annotations.SerializedName;
import com.google.gson.internal.LinkedTreeMap;

public class Group {
    @SerializedName("id") private int group_ID;
    @SerializedName("date_created") private String dateCreated;
    @SerializedName("group_creator") private User group_creator;
    private FriendsList group_admins;
    private FriendsList group_moderators;
    private String groupImageURL;
    @SerializedName("group_name") private String group_name;
    private String group_description;
    @SerializedName("group_users") private FriendsList group_members;
    @SerializedName("group_chat") private Messages group_chat;

    public Group(User group_creator, FriendsList group_admins, FriendsList group_moderators,
                 String groupImageURL, String group_name, String group_description,
                 FriendsList group_members, Messages group_chat) {

        this.group_creator = group_creator;
        this.group_admins = group_admins;
        this.group_moderators = group_moderators;
        this.groupImageURL = groupImageURL;
        this.group_name = group_name;
        this.group_description = group_description;
        this.group_members = group_members;
        this.group_chat = group_chat;
    }

    // TODO: RMV???
    public Group() {
        this.group_creator = null;
        this.group_admins = null;
        this.group_moderators = null;
        this.groupImageURL = "";
        this.group_name = null;
        this.group_description = null;
        this.group_members = null;
        this.group_chat = null;
    }


    public Group(LinkedTreeMap group) {
        double id = Double.parseDouble(String.valueOf(group.get("id")));
        this.group_ID = (int) id;
        this.dateCreated = (String) group.get("date_created");
        LinkedTreeMap usr = (LinkedTreeMap) group.get("group_creator");
        User user = new User(usr);
        this.group_creator = user;
//        this.group_creator = (User) group.get("group_creator");
        this.group_name = (String) group.get("group_name");
        this.group_members = (FriendsList) group.get("group_users");
        this.group_chat = (Messages) group.get("group_chat");
    }

    public Group(int groupID, String date_created, User groupCreator, String group_name, FriendsList users, Messages chat) {
        this.group_ID = groupID;
        this.dateCreated = date_created;
        this.group_creator = groupCreator;
        this.group_name = group_name;
        this.group_members = users;
        this.group_chat = chat;
    }

    public int getGroupID() {
        return this.group_ID;
    }

    public User getGroupCreator() {
        return group_creator;
    }

    public FriendsList getGroupAdmins() {
        return group_admins;
    }

    public FriendsList getGroupModerators() {
        return group_moderators;
    }

    public String getGroupImageURL() {
        return groupImageURL;
    }

    public String getGroupName() {
        return group_name;
    }

    public String getGroupDescription() {
        return group_description;
    }

    public FriendsList getGroupMembers() {
        return group_members;
    }

    public Messages getGroupChat() {
        return group_chat;
    }
}
