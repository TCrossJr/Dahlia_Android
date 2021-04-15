package com.example.dahlia_android.ui.recyclerview;

import java.util.ArrayList;

public class AdapterTypeList extends ArrayList{

    private ArrayList<AdapterType> adapterTypeList;

    public AdapterTypeList() {
        adapterTypeList = new ArrayList<>(0);
    }

    public ArrayList<AdapterType> getProfileCombined() {
        return adapterTypeList;
    }

    public AdapterType getObjectByPosition(int position) {
        return (AdapterType) this.adapterTypeList.get(position);
    }

    public void setProfileCombined(ArrayList<AdapterType> adapterTypeList) {
        this.adapterTypeList = adapterTypeList;
    }
}
