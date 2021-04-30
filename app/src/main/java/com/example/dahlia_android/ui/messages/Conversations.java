package com.example.dahlia_android.ui.messages;

import java.util.ArrayList;

public class Conversations extends ArrayList {

    private ArrayList<Messages> conversations;

    public Conversations() {
        conversations = new ArrayList<>();
    }

    public Conversations(ArrayList<Messages> messages) {
        conversations = new ArrayList<>(messages);
    }

    public ArrayList<Messages> getConversations() {
        return conversations;
    }

    public Messages getMessages(int position) { return (Messages) this.get(position); }

    public void deleteConversation( Messages message ) { conversations.remove(message); }
}
