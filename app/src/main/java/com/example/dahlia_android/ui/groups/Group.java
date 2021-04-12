package com.example.dahlia_android.ui.groups;

import com.example.dahlia_android.ui.friends.FriendsList;
import com.example.dahlia_android.ui.messages.Message;
import com.example.dahlia_android.ui.user.User;

import java.util.ArrayList;

public class Group {
    private User group_creator;
    private FriendsList group_admins;
    private FriendsList group_moderators;
    private String groupImageURL; // TODO: Change to Image???
    private String group_name;
    private String group_description;
    private FriendsList group_members;
    private ArrayList<Message> group_chat;

    public Group(User group_creator, FriendsList group_admins, FriendsList group_moderators,
                 String groupImageURL, String group_name, String group_description,
                 FriendsList group_members, ArrayList<Message> group_chat) {

        this.group_creator = group_creator;
        this.group_admins = group_admins;
        this.group_moderators = group_moderators;
        this.groupImageURL = groupImageURL;
        this.group_name = group_name;
        this.group_description = group_description;
        this.group_members = group_members;
        this.group_chat = group_chat;
    }

    // TODO: RMV
    public Group(String group_name, String group_description) {
        this.group_creator = null;
        this.group_admins = null;
        this.group_moderators = null;
        this.groupImageURL = "";
        this.group_name = group_name;
        this.group_description = group_description;
        this.group_members = null;
        this.group_chat = null;
    }

    public User getGroup_creator() {
        return group_creator;
    }

    public void setGroup_creator(User group_creator) {
        this.group_creator = group_creator;
    }

    public FriendsList getGroup_admins() {
        return group_admins;
    }

    public void setGroup_admins(FriendsList group_admins) {
        this.group_admins = group_admins;
    }

    public FriendsList getGroup_moderators() {
        return group_moderators;
    }

    public void setGroup_moderators(FriendsList group_moderators) {
        this.group_moderators = group_moderators;
    }

    public String getGroupImageURL() {
        return groupImageURL;
    }

    public void setGroupImageURL(String groupImageURL) {
        this.groupImageURL = groupImageURL;
    }

    public String getGroupName() {
        return group_name;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }

    public String getGroupDescription() {
        return group_description;
    }

    public void setGroup_description(String group_description) {
        this.group_description = group_description;
    }

    public FriendsList getGroup_members() {
        return group_members;
    }

    public void setGroup_members(FriendsList group_members) {
        this.group_members = group_members;
    }

    public ArrayList<Message> getGroup_chat() {
        return group_chat;
    }

    public void setGroup_chat(ArrayList<Message> group_chat) {
        this.group_chat = group_chat;
    }
}
