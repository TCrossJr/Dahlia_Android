package com.example.dahlia_android.ui.recyclerview;

import java.util.ArrayList;

public class AdapterTypeList extends ArrayList{

    private ArrayList<AdapterType> adapterTypeList;

    public AdapterTypeList() {
        this.adapterTypeList = new ArrayList<>(0);
    }

    public ArrayList<AdapterType> getAdapterTypeList() {
        return adapterTypeList;
    }

    public void setAdapterTypeList(ArrayList<AdapterType> adapterTypeList) {
        this.adapterTypeList = adapterTypeList;
    }
}
