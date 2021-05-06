package com.example.dahlia_android.ui.messages;

import com.google.gson.annotations.SerializedName;

public class RawMessage {

    @SerializedName("message_creator_id") private int messageCreator;
    @SerializedName("message_receiver_id") private int messageReceiver;
    @SerializedName("message_text") private String messageText;
    @SerializedName("message_media") private String messageMedia;

    public RawMessage(int messageCreator, int messageReceiver, String messageText, String messageMedia) {
        this.messageCreator = messageCreator;
        this.messageReceiver = messageReceiver;
        this.messageText = messageText;
        this.messageMedia = messageMedia;
    }

    public RawMessage() {
        this.messageCreator = -1;
        this.messageReceiver = -1;
        this.messageText = "";
        this.messageMedia = "";
    }

    public RawMessage(RawMessage msg ) {
        this.messageCreator = msg.messageCreator;
        this.messageReceiver = msg.messageReceiver;
        this.messageText = msg.messageText;
        this.messageMedia = msg.messageMedia;
    }
/*
    public RawMessage(LinkedTreeMap msg) {
        double creator = Double.parseDouble(String.valueOf(msg.get("message_creator_id")));
        this.messageCreator = (int) creator;
        double receiver = Double.parseDouble(String.valueOf(msg.get("message_receiver_id")));
        this.messageReceiver = (int) receiver;
        this.messageText = (String) msg.get("message_text");
        this.messageMedia = (String) msg.get("message_media");
    }*/

    public int getMessageCreator() {
        return messageCreator;
    }

    public int getMessageReceiver() {
        return messageReceiver;
    }

    public String getMessageText() {
        return messageText;
    }

    public String getMessageMedia() {
        return messageMedia;
    }
}
