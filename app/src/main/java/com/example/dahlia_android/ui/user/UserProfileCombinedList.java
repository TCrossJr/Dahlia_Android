package com.example.dahlia_android.ui.user;

import java.util.ArrayList;
import java.util.List;

public class UserProfileCombinedList extends ArrayList{

    private ArrayList<Object> profile_combined;

    public UserProfileCombinedList() {
        profile_combined = new ArrayList<>(0);
        profile_combined.add(new UserProfile("DisplayName","UserName"));
    }

    public UserProfileCombinedList(Object obj) {
        profile_combined = new ArrayList<Object>();
        profile_combined.add(obj);
    }

    public ArrayList<Object> getProfileCombined() {
        return profile_combined;
    }

    public Object getObjectByPosition(int position) {
        return (Object) this.profile_combined.get(position);
    }

    public void setProfileCombined(ArrayList<Object> profileCombined) {
        this.profile_combined = profileCombined;
    }
}
