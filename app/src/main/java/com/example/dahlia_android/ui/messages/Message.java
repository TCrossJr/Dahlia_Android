package com.example.dahlia_android.ui.messages;

import com.example.dahlia_android.ui.groups.Group;

public class Message {

    private Group messengers;
    private String messengerProfileImageURL;
    private String messageDisplayName;  // TODO: RMV???
    private String messageDate;
    private String messageText;
    private Messages replies;

    public Message(Group messengers, String messengerProfileImageURL, String messageDisplayName, String messageDate, String messageText, Messages replies) {
        this.messengers = messengers;
        this.messengerProfileImageURL = messengerProfileImageURL;
        this.messageDisplayName = messageDisplayName;
        this.messageDate = messageDate;
        this.messageText = messageText;
        this.replies = replies;
    }

    public Group getMessengers() {
        return messengers;
    }

    public String getMessengerProfileImageURL() {
        return messengerProfileImageURL;
    }

    public String getMessageDisplayName() {
        return messageDisplayName;
    }

    public String getMessageDate() {
        return messageDate;
    }

    public String getMessageText() {
        return messageText;
    }

    public Messages getReplies() {
        return replies;
    }

    public void setMessengers(Group messengers) {
        this.messengers = messengers;
    }

    public void setMessengerProfileImageURL(String messengerProfileImageURL) {
        this.messengerProfileImageURL = messengerProfileImageURL;
    }

    public void setMessageDisplayName(String messageDisplayName) {
        this.messageDisplayName = messageDisplayName;
    }

    public void setMessageDate(String messageDate) {
        this.messageDate = messageDate;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public void setReplies(Messages replies) {
        this.replies = replies;
    }
}
