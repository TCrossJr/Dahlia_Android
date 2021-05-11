package com.example.dahlia_android.ui.groups;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dahlia_android.R;
import com.example.dahlia_android.ui.recyclerview.MainAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class GroupsFragment extends Fragment {

    private GroupsViewModel groupsViewModel;
    private RecyclerView rView;
    private LinearLayoutManager layoutManager;
    private MainAdapter groups_adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_groups, container, false);
        groupsViewModel =
                new ViewModelProvider(this, new GroupsViewModelFactory())
                        .get(GroupsViewModel.class);
        FloatingActionButton groupFab = view.findViewById(R.id.group_fab);
        groupFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), CreateGroupActivity.class);
                startActivity(intent);
            }
        });
        rView = view.findViewById(R.id.groups_recycler_view);
        layoutManager = new LinearLayoutManager(getActivity());
        if(groupsViewModel.getGroups() == null) {
            groupsViewModel.loadGroups();
            groupsViewModel.getGroupsResult().observe(getViewLifecycleOwner(), new Observer<GroupsResult>() {
                @Override
                public void onChanged(GroupsResult groupsResult) {
                    if (groupsResult == null) {
                        return;
                    }
                    if (groupsResult.getError() != null) {
                        showGroupsFailed(groupsResult.getError());
                    }
                    if (groupsResult.getSuccess() != null) {
                        updateUiWithGroups(groupsResult.getSuccess());
                    }
                }
            });
        } else
            updateUI();
        return view;
    }

    private void updateUiWithGroups(GroupsView model) {
        updateUI();
    }

    private void updateUI() {
        groups_adapter = new MainAdapter(groupsViewModel.getGroups());
        groups_adapter.onAttachedToRecyclerView(rView);
        RecyclerView.ItemDecoration divider = new DividerItemDecoration(getContext(), layoutManager.getOrientation());
        rView.addItemDecoration(divider);
        rView.setLayoutManager(layoutManager);
        rView.scrollToPosition(0);
        groups_adapter.notifyDataSetChanged();
        rView.setAdapter(groups_adapter);
    }

    private void showGroupsFailed(Integer error) {
        Toast.makeText(getContext(), error, Toast.LENGTH_LONG).show();
    }
}