package com.example.dahlia_android.ui.groups;

import com.example.dahlia_android.ui.messages.Message;

import java.util.ArrayList;

public class Groups extends ArrayList{

    private ArrayList<Group> groups;

    public Groups() {
        this.groups = new ArrayList<>();
    }

    public ArrayList<Group> getGroups() {
        return groups;
    }

    public Group getGroup(int position) { return (Group) this.get(position); }

    public void addGroup( Group group ) { groups.add(group); }

    public void deleteGroup( Group group ) { groups.remove(group); }
}
