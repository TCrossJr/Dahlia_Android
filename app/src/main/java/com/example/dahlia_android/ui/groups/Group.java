package com.example.dahlia_android.ui.groups;

import com.example.dahlia_android.ui.friends.FriendsList;
import com.example.dahlia_android.ui.messages.Message;
import com.example.dahlia_android.ui.user.User;

import java.util.ArrayList;

public class Group {
    private User group_creator;
    private FriendsList group_admins;
    private FriendsList group_moderators;
    private FriendsList group_members;
    private ArrayList<Message> group_chat;
}
