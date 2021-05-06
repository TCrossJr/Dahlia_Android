package com.example.dahlia_android.ui.messages;

import com.example.dahlia_android.ui.friends.FriendsList;
import com.example.dahlia_android.ui.user.User;

import java.util.ArrayList;

/**
 * Class exposing authenticated friendsList details to the UI.
 */
class MessagesView {
    private Conversations _conversations;
    private Boolean _messageSent;
    //... other data fields that may be accessible to the UI

    MessagesView(Conversations conversations) {
        this._conversations = conversations;
    }

    MessagesView(ArrayList<Messages> conversations) {
        this._conversations = new Conversations(conversations);
    }

    public MessagesView(boolean booleanValue) {
        this._messageSent = booleanValue;
    }

    Conversations getConversations() {
        return _conversations;
    }

    public Boolean get_messageSent() {
        return _messageSent;
    }
}