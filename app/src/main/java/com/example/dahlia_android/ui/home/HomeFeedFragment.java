package com.example.dahlia_android.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dahlia_android.R;
import com.example.dahlia_android.ui.messages.CreateMessageActivity;
import com.example.dahlia_android.ui.recyclerview.MainAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

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
        View view = inflater.inflate(R.layout.fragment_homefeed, container, false);
        homeViewModel =
                new ViewModelProvider(this, new HomeFeedViewModelFactory())
                        .get(HomeFeedViewModel.class);

        FloatingActionButton postFab = view.findViewById(R.id.homefeed_fab);
        postFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: Implement fab createNewPost
                Snackbar.make(view, "Starting post...", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Intent intent = new Intent(getContext(), CreatePostActivity.class);
                startActivity(intent);
            }
        });

        rView = view.findViewById(R.id.feed_recycler_view);
        layoutManager = new LinearLayoutManager(getActivity());

//        if(homeViewModel.getFeed() == null && homeViewModel.getUser() != null) {
        if(homeViewModel.getFeed() == null) {
            homeViewModel.loadFeed();
            homeViewModel.getHomeFeedResult().observe(getViewLifecycleOwner(), new Observer<HomeFeedResult>() {
                @Override
                public void onChanged(HomeFeedResult homeFeedResult) {
                    if (homeFeedResult == null) {
                        return;
                    }
                    if (homeFeedResult.getError() != null) {
                        showFriendsFailed(homeFeedResult.getError());
                    }
                    if (homeFeedResult.getSuccess() != null) {
                        updateUiWithFriends(homeFeedResult.getSuccess());
                    }
                }
            });
        } else
            updateUI();
        return view;
    }

    private void updateUiWithFriends(HomeFeedView model) {
        // not using HomeFeedView model currently
        updateUI();
    }

    private void updateUI() {
        feed_adapter = new MainAdapter(homeViewModel.getFeed());
        feed_adapter.onAttachedToRecyclerView(rView);
        RecyclerView.ItemDecoration divider = new DividerItemDecoration(getContext(), layoutManager.getOrientation());
        rView.addItemDecoration(divider);
        rView.setLayoutManager(layoutManager);
        rView.scrollToPosition(0);
        feed_adapter.notifyDataSetChanged();
        rView.setAdapter(feed_adapter);
    }

    private void showFriendsFailed(Integer error) {
        // TODO: Change how to display(or not) error
        Toast.makeText(getContext(), error, Toast.LENGTH_LONG).show();
    }
}