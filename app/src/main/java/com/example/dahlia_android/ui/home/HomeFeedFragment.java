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

import com.example.dahlia_android.R;
import com.example.dahlia_android.ui.recyclerview.MainAdapter;

public class HomeFeedFragment extends Fragment {

    private HomeFeedViewModel homeViewModel;

    protected MainAdapter feed_adapter;
    protected LinearLayoutManager layoutManager;

    private RecyclerView rView;

    public static HomeFeedFragment newInstance() {
        return new HomeFeedFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_homefeed, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        homeViewModel =
                new ViewModelProvider(this, new HomeFeedViewModelFactory())
                        .get(HomeFeedViewModel.class);

        rView = view.findViewById(R.id.feed_recycler_view);
        layoutManager = new LinearLayoutManager(getActivity());
        feed_adapter = new MainAdapter(homeViewModel.getFeed());
        feed_adapter.notifyDataSetChanged();
        RecyclerView.ItemDecoration divider = new DividerItemDecoration(getContext(), layoutManager.getOrientation());
        rView.addItemDecoration(divider);
        rView.setLayoutManager(layoutManager);
        rView.scrollToPosition(0);
        rView.setAdapter(feed_adapter);
    }
}