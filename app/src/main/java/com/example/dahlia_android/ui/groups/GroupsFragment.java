package com.example.dahlia_android.ui.groups;

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

import com.example.dahlia_android.MainActivity;
import com.example.dahlia_android.R;
import com.example.dahlia_android.ui.recyclerview.MainAdapter;

public class GroupsFragment extends Fragment {

    private GroupsViewModel groupsViewModel;
    private RecyclerView rView;
    private LinearLayoutManager layoutManager;
    private MainAdapter groups_adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_groups, container, false);
        groupsViewModel =
                new ViewModelProvider(this, new GroupsViewModelFactory())
                        .get(GroupsViewModel.class);
        rView = root.findViewById(R.id.groups_recycler_view);
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
        return root;
    }

    private void updateUiWithGroups(GroupsView model) {
        updateUI();
    }

    private void updateUI() {
        groups_adapter = new MainAdapter(MainActivity._groupsList); //TODO: Move to ViewModel/DataSource/DataRepository
        groups_adapter.onAttachedToRecyclerView(rView);
        RecyclerView.ItemDecoration divider = new DividerItemDecoration(getContext(), layoutManager.getOrientation());
        rView.addItemDecoration(divider);
        rView.setLayoutManager(layoutManager);
        rView.scrollToPosition(0);
        groups_adapter.notifyDataSetChanged();
        rView.setAdapter(groups_adapter);
    }

    private void showGroupsFailed(Integer error) {
        // TODO: Change how to display(or not) error
        Toast.makeText(getContext(), error, Toast.LENGTH_LONG).show();
    }
}