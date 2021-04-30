package com.example.dahlia_android.ui.messages;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Messages extends ArrayList {

    @SerializedName("conversation") private ArrayList<Message> messages;

    public Messages() {
        this.messages = new ArrayList<>();
    }

    public ArrayList<Message> getMessages() {
        return messages;
    }

    public Message getMessage(int position) { return (Message) this.get(position); }

    public void addMessage( Message message ) { messages.add(message); }

    public void deleteMessage( Message message ) { messages.remove(message); }

    public String getLastMessageText() {
        return "Last message sent in this conversation";
    }
}
