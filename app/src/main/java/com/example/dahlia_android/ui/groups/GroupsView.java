package com.example.dahlia_android.ui.groups;

import java.util.ArrayList;

/**
 * Class exposing authenticated friendsList details to the UI.
 */
class GroupsView {
    private Groups _groupConversations;
    private Boolean _messageSent;
    //... other data fields that may be accessible to the UI

    GroupsView(Groups conversations) {
        this._groupConversations = conversations;
    }

    GroupsView(ArrayList<Group> conversations) {
        this._groupConversations = new Groups(conversations);
    }

    public GroupsView(boolean booleanValue) {
        this._messageSent = booleanValue;
    }

    Groups getGroups() {
        return _groupConversations;
    }

    public Boolean get_messageSent() {
        return _messageSent;
    }
}