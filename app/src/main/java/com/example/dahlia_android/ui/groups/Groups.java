package com.example.dahlia_android.ui.groups;

import com.example.dahlia_android.api.GroupsDeserializer;
import com.google.gson.annotations.JsonAdapter;

import java.util.ArrayList;

@JsonAdapter(GroupsDeserializer.class)
public class Groups extends ArrayList{

    private ArrayList<Group> groups;

    public Groups(ArrayList<Group> conversations) {
        this.groups = new ArrayList<>();
    }

    public Groups() {
        this.groups = new ArrayList<>();
    }

    public ArrayList<Group> getGroups() {
        return groups;
    }
}
