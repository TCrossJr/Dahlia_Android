package com.example.dahlia_android.ui.friends;

import android.app.Activity;
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

    protected MainAdapter friends_adapter;
    protected LinearLayoutManager layoutManager;

    private RecyclerView rView;

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

        friendsViewModel.loadFriends();
        friendsViewModel.getFriendsResult().observe(getViewLifecycleOwner(), new Observer<FriendsResult>() {
            @Override
            public void onChanged(FriendsResult friendsResult) {
                if(friendsResult == null) {
                    return;
                }
                if(friendsResult.getError() != null) {
                    showFriendsFailed(friendsResult.getError());
                }
                if(friendsResult.getSuccess() != null) {
                    updateUiWithFriends(friendsResult.getSuccess());
                }
                getActivity().setResult(Activity.RESULT_OK);
            }
        });

        friends_adapter = new MainAdapter(friendsViewModel.getFriends());
        friends_adapter.onAttachedToRecyclerView(rView);
        RecyclerView.ItemDecoration divider = new DividerItemDecoration(getActivity(), layoutManager.getOrientation());
        rView.addItemDecoration(divider);
        rView.setLayoutManager(layoutManager);
        rView.scrollToPosition(0);
        friends_adapter.notifyDataSetChanged();
        rView.setAdapter(friends_adapter);
        return root;
    }

    private void updateUiWithFriends(FriendsView model) {
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
        Toast.makeText(getContext(), error, Toast.LENGTH_LONG).show();
    }
}
