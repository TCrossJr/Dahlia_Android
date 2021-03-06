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

    @Override
    public void onStart() {
        super.onStart();
//        updateUI(true);
        reloadUI();
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
                Snackbar.make(view, "Starting post...", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Intent intent = new Intent(getContext(), CreatePostActivity.class);
                startActivity(intent);
            }
        });

        rView = view.findViewById(R.id.feed_recycler_view);
        layoutManager = new LinearLayoutManager(getActivity());
        reloadUI();
        return view;
    }

    void reloadUI() {
        if(homeViewModel.getFeed() == null && homeViewModel.getUser() != null) {
            homeViewModel.loadFeed();
            homeViewModel.getFeedResult().observe(getViewLifecycleOwner(), new Observer<HomeFeedResult>() {
                @Override
                public void onChanged(HomeFeedResult homeFeedResult) {
                    if (homeFeedResult == null) {
                        return;
                    }
                    if (homeFeedResult.getError() != null) {
                        showFriendsFailed(homeFeedResult.getError());
                    }
                    if (homeFeedResult.getSuccess() != null) {
                        updateUiWithPosts(homeFeedResult.getSuccess());
                    }
                }
            });
        } else
            updateUI(false);
    }

    private void updateUiWithPosts(HomeFeedView model) {
        // not using HomeFeedView model currently
        updateUI(false);
    }

    void updateUI(Boolean reload) {
        if(homeViewModel.getPost()!=null) {
            homeViewModel.getFeed().add(0,homeViewModel.getPost());
            homeViewModel.setPost(null);
        }
        feed_adapter = new MainAdapter(homeViewModel.getFeed());
        feed_adapter.onAttachedToRecyclerView(rView);
        RecyclerView.ItemDecoration divider = new DividerItemDecoration(getContext(), layoutManager.getOrientation());
        rView.addItemDecoration(divider);
        rView.setLayoutManager(layoutManager);
        rView.scrollToPosition(0);
        feed_adapter.notifyDataSetChanged();
        rView.setAdapter(feed_adapter);
        if(reload) {
            reloadUI();
        }
    }

    private void showFriendsFailed(Integer error) {
        Toast.makeText(getContext(), error, Toast.LENGTH_LONG).show();
    }
}