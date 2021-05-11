package com.example.dahlia_android.ui.friends;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dahlia_android.R;
import com.example.dahlia_android.ui.recyclerview.MainAdapter;

public class FriendsFragment extends Fragment {

    private FriendsViewModel friendsViewModel;
    private RecyclerView rView;
    protected LinearLayoutManager layoutManager;
    protected MainAdapter friends_adapter;

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_friends, container, false);
        friendsViewModel =
                new ViewModelProvider(this, new FriendsViewModelFactory())
                        .get(FriendsViewModel.class);
        rView = root.findViewById(R.id.friendslist_recycler_view);
        layoutManager = new LinearLayoutManager(getActivity());

        if(friendsViewModel.getFriends() == null && friendsViewModel.getUser() != null) {
            friendsViewModel.loadFriends();
            friendsViewModel.getFriendsResult().observe(getViewLifecycleOwner(), new Observer<FriendsResult>() {
                @Override
                public void onChanged(FriendsResult friendsResult) {
                    if (friendsResult == null) {
                        return;
                    }
                    if (friendsResult.getError() != null) {
                        showFriendsFailed(friendsResult.getError());
                    }
                    if (friendsResult.getSuccess() != null) {
                        updateUiWithFriends(friendsResult.getSuccess());
                    }
                }
            });
        } else
            updateUI();
        return root;
    }

    private void updateUiWithFriends(FriendsView model) {
        // not using FriendsView model currently
        updateUI();
    }

    private void updateUI() {
        friends_adapter = new MainAdapter(friendsViewModel.getFriends());
        friends_adapter.onAttachedToRecyclerView(rView);
        RecyclerView.ItemDecoration divider = new DividerItemDecoration(getActivity(), layoutManager.getOrientation());
        rView.addItemDecoration(divider);
        rView.setLayoutManager(layoutManager);
        rView.scrollToPosition(0);
        friends_adapter.notifyDataSetChanged();
        rView.setAdapter(friends_adapter);
    }

    private void showFriendsFailed(@StringRes Integer error) {
        // TODO: Change how to display(or not) error
        Toast.makeText(getContext(), error, Toast.LENGTH_LONG).show();
    }
}
