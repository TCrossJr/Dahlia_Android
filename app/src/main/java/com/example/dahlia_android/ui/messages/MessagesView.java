package com.example.dahlia_android.ui.messages;

import java.util.ArrayList;

/**
 * Class exposing authenticated messagesView details to the UI.
 */
class MessagesView {
    private Conversations _conversations;
    private Boolean _messageSent;
    //... other data fields that may be accessible to the UI

    MessagesView(ArrayList<Messages> conversations) {
        this._conversations = new Conversations(conversations);
    }
}