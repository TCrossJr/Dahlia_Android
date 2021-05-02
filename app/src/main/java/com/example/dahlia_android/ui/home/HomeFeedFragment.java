package com.example.dahlia_android.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dahlia_android.MainActivity;
import com.example.dahlia_android.R;
import com.example.dahlia_android.ui.recyclerview.MainAdapter;

import java.util.ArrayList;

public class HomeFeedFragment extends Fragment {

    private HomeFeedViewModel homeViewModel;

    protected MainAdapter feed_adapter;
    protected LinearLayoutManager layoutManager;

    private RecyclerView rView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
//        homeViewModel =
//                new ViewModelProvider(this).get(HomeFeedViewModel.class);
        homeViewModel =
                new ViewModelProvider(this, new HomeFeedViewModelFactory())
                        .get(HomeFeedViewModel.class);
        
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        rView = root.findViewById(R.id.feed_recycler_view);
        layoutManager = new LinearLayoutManager(getActivity());
        feed_adapter = new MainAdapter((ArrayList<Object>) MainActivity._homeFeed);
        feed_adapter.notifyDataSetChanged();
        RecyclerView.ItemDecoration divider = new DividerItemDecoration(getContext(), layoutManager.getOrientation());
        rView.addItemDecoration(divider);
        rView.setLayoutManager(layoutManager);
        rView.scrollToPosition(0);
        rView.setAdapter(feed_adapter);
        return root;
    }
}