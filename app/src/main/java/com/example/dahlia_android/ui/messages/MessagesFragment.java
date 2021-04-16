package com.example.dahlia_android.ui.messages;

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
import com.example.dahlia_android.ui.recyclerview.AdapterTypeList;
import com.example.dahlia_android.ui.recyclerview.MainAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class MessagesFragment extends Fragment {

    private MessagesViewModel messagesViewModel;
    private RecyclerView rView;
    private LinearLayoutManager layoutManager;
    private MainAdapter messages_adapter;
    private AdapterTypeList adapterTypeList;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        messagesViewModel =
                new ViewModelProvider(this).get(MessagesViewModel.class);
        View root = inflater.inflate(R.layout.fragment_messages, container, false);
        FloatingActionButton messageFab = root.findViewById(R.id.message_fab);
        messageFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: Implement fab createNewBlankMsg
                Snackbar.make(view, "Starting message...", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        rView = root.findViewById(R.id.messages_recycler_view);
        layoutManager = new LinearLayoutManager(getActivity());
        messages_adapter = new MainAdapter(MainActivity._messageList, adapterTypeList); //TODO: Change or move to Data class somehow from MainActivity
        messages_adapter.notifyDataSetChanged();

        RecyclerView.ItemDecoration divider = new DividerItemDecoration(getContext(), layoutManager.getOrientation());
        rView.addItemDecoration(divider);
        rView.setLayoutManager(layoutManager);
        rView.scrollToPosition(0);
        rView.setAdapter(messages_adapter);
        return root;
    }
}