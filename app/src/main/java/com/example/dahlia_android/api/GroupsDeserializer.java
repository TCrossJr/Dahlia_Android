package com.example.dahlia_android.api;

import com.example.dahlia_android.ui.friends.FriendsList;
import com.example.dahlia_android.ui.groups.Group;
import com.example.dahlia_android.ui.groups.Groups;
import com.example.dahlia_android.ui.messages.Messages;
import com.example.dahlia_android.ui.user.User;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class GroupsDeserializer implements JsonDeserializer<Groups> {
    @Override
    public Groups deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonArray jsonArray = json.getAsJsonArray();
        Groups groups = new Groups();
        for(int i = 0; i < jsonArray.size(); i++) {
            JsonObject jsonObject = jsonArray.get(i).getAsJsonObject();
            JsonObject creatorObject = jsonObject.getAsJsonObject("group_creator");
            User groupCreator = new User(
                    creatorObject.get("id").getAsInt(),
                    creatorObject.get("username").getAsString(),
                    creatorObject.get("email").getAsString(),
                    creatorObject.get("first_name").getAsString(),
                    creatorObject.get("last_name").getAsString(),
                    creatorObject.get("agency").getAsString()
            );
            JsonObject userObject = jsonObject.get("group_users").getAsJsonObject();
            Type fLType = new TypeToken<User>(){}.getType();
            User u = new Gson().fromJson(userObject, fLType);
            FriendsList users = new FriendsList();
            users.add(u);

            JsonElement rawChatArray = jsonObject.get("group_chat");
            JsonArray chatArray = null;
            if(!rawChatArray.isJsonNull()){
                chatArray = jsonObject.get("group_chat").getAsJsonArray();
            }
            Type messagesType = new TypeToken<Messages>() {
            }.getType();
            Messages chat = new Gson().fromJson(chatArray, messagesType);
            Group tmp = new Group(
                    jsonObject.get("id").getAsInt(),
                    jsonObject.get("date_created").getAsString(),
                    groupCreator,
                    jsonObject.get("group_name").getAsString(),
                    users,
                    chat
            );
            groups.add(tmp);
        }
        return groups;
    }
}
