package com.example.dahlia_android.ui.groups;

import java.util.ArrayList;

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
