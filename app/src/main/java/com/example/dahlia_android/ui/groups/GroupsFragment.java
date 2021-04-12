package com.example.dahlia_android.ui.groups;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dahlia_android.MainActivity;
import com.example.dahlia_android.R;
import com.example.dahlia_android.ui.messages.MessagesListAdapter;

public class GroupsFragment extends Fragment {

    private GroupsViewModel groupsViewModel;
    private RecyclerView rView;
    private LinearLayoutManager layoutManager;
    private GroupsListAdapter groups_adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        groupsViewModel =
                new ViewModelProvider(this).get(GroupsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_groups, container, false);
        rView = root.findViewById(R.id.groups_recycler_view);
        layoutManager = new LinearLayoutManager(getActivity());
        groups_adapter = new GroupsListAdapter(MainActivity._groupsList); //TODO: Change or move to Data class somehow from MainActivity
        groups_adapter.notifyDataSetChanged();

        RecyclerView.ItemDecoration divider = new DividerItemDecoration(getContext(), layoutManager.getOrientation());
        rView.addItemDecoration(divider);
        rView.setLayoutManager(layoutManager);
        rView.scrollToPosition(0);
        rView.setAdapter(groups_adapter);
        return root;
    }
}