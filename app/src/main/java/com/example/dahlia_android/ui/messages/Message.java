package com.example.dahlia_android.ui.messages;

import com.google.gson.annotations.SerializedName;
import com.google.gson.internal.LinkedTreeMap;

public class Message {

    @SerializedName("id") private int messageID;
    @SerializedName("date_created") private String messageDate;
    @SerializedName("message_creator_id") private int messageCreator;
    @SerializedName("message_receiver_id") private int messageReceiver;
    @SerializedName("message_text") private String messageText;
    @SerializedName("message_media") private String messageMedia;

    public Message(int messageID, String messageDate, int messageCreator, int messageReceiver, String messageText, String messageMedia) {
        this.messageID = messageID;
        this.messageDate = messageDate;
        this.messageCreator = messageCreator;
        this.messageReceiver = messageReceiver;
        this.messageText = messageText;
        this.messageMedia = messageMedia;
    }

    public Message() {
        this.messageID = -1;
        this.messageDate = "";
        this.messageCreator = -1;
        this.messageReceiver = -1;
        this.messageText = "";
        this.messageMedia = "";
    }

    public Message( Message msg ) {
        this.messageID = msg.messageID;
        this.messageDate = msg.messageDate;
        this.messageCreator = msg.messageCreator;
        this.messageReceiver = msg.messageReceiver;
        this.messageText = msg.messageText;
        this.messageMedia = msg.messageMedia;
    }

    public Message(LinkedTreeMap msg) {
        double id = Double.parseDouble(String.valueOf(msg.get("id")));
        this.messageID = (int) id;
        this.messageDate = (String) msg.get("date_created");
        double creator = Double.parseDouble(String.valueOf(msg.get("message_creator_id")));
        this.messageCreator = (int) creator;
        double receiver = Double.parseDouble(String.valueOf(msg.get("message_receiver_id")));
        this.messageReceiver = (int) receiver;
        this.messageText = (String) msg.get("message_text");
        this.messageMedia = (String) msg.get("message_media");
    }

    public int getMessageID() {
        return messageID;
    }

    public String getMessageDate() {
        return messageDate;
    }

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
