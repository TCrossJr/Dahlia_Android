package com.example.dahlia_android.ui.home;

import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class HomeFeedAdapter extends RecyclerView.Adapter<HomeFeedAdapter.ViewHolder> {

    private static final String TAG = "HomeFeedAdapter";
    private static HomeFeed home_feed;

    public HomeFeedAdapter(HomeFeed feed) { home_feed = feed; }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private FrameLayout frameLayout;
//        private TextView townName;
//        private TextView countyName;

        public ViewHolder(@NonNull View view) {
            super(view);
//            frameLayout = view.findViewById(R.id.town_frame);
//            townName = view.findViewById(R.id.list_item_town);
//            countyName = view.findViewById(R.id.list_item_county);
        }

//        public void setTownName(String town) {
//            this.townName.setText(town);
//        }
//
//        public void setCountyName(String county) {
//            this.countyName.setText(county);
//        }
    }
}
