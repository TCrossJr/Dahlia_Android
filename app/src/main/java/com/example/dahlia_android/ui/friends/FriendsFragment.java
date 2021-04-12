package com.example.dahlia_android.ui.friends;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dahlia_android.MainActivity;
import com.example.dahlia_android.R;
import com.example.dahlia_android.ui.home.HomeFeedAdapter;
import com.example.dahlia_android.ui.user.User;

public class FriendsFragment extends Fragment {

    private FriendsViewModel friendsViewModel;

    protected FriendsListAdapter friends_adapter;
    protected LinearLayoutManager layoutManager;

    private RecyclerView rView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        friendsViewModel =
                new ViewModelProvider(this).get(FriendsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_friends, container, false);
        // TODO: Change to FriendsActivity???

        rView = root.findViewById(R.id.friendslist_recycler_view);
        layoutManager = new LinearLayoutManager(getActivity());
        friends_adapter = new FriendsListAdapter(MainActivity._friendsList); //TODO: Change or move to Data class somehow from MainActivity
        friends_adapter.notifyDataSetChanged();

        RecyclerView.ItemDecoration divider = new DividerItemDecoration(getContext(), layoutManager.getOrientation());
        rView.addItemDecoration(divider);
        rView.setLayoutManager(layoutManager);
        rView.scrollToPosition(0);
        rView.setAdapter(friends_adapter);
        return root;
    }
}
