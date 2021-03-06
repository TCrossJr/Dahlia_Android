package com.example.dahlia_android.ui.messages;

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
import com.example.dahlia_android.ui.recyclerview.AdapterTypeList;
import com.example.dahlia_android.ui.recyclerview.MainAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class MessagesFragment extends Fragment {

    private MessagesViewModel messagesViewModel;
    private RecyclerView rView;
    private LinearLayoutManager layoutManager;
    private MainAdapter messages_adapter;
    private AdapterTypeList adapterTypeList = new AdapterTypeList();

    @Override
    public void onStart() {
        super.onStart();
//        updateUI();
        reloadUI();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_messages, container, false);
        messagesViewModel =
                new ViewModelProvider(this, new MessagesViewModelFactory())
                        .get(MessagesViewModel.class);
        FloatingActionButton messageFab = view.findViewById(R.id.message_fab);
        messageFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Starting message...", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Intent intent = new Intent(getContext(), CreateMessageActivity.class);
                startActivity(intent);
            }
        });
        rView = view.findViewById(R.id.messages_recycler_view);
        layoutManager = new LinearLayoutManager(getActivity());
        reloadUI();
        return view;
    }

    private void reloadUI() {
        if(messagesViewModel.getConversations() == null && messagesViewModel.getUser() != null) {
            messagesViewModel.loadMessages();
            messagesViewModel.getMessagesResult().observe(getViewLifecycleOwner(), new Observer<MessagesResult>() {
                @Override
                public void onChanged(MessagesResult messagesResult) {
                    if (messagesResult == null) {
                        return;
                    }
                    if (messagesResult.getError() != null) {
                        showMessagesFailed(messagesResult.getError());
                    }
                    if (messagesResult.getSuccess() != null) {
                        updateUiWithMessages(messagesResult.getSuccess());
                    }
                }
            });
        } else
            updateUI(false);
    }

    private void updateUiWithMessages(MessagesView model) {
        // not using MessagesView model currently
        updateUI(false);
    }

    private void updateUI(Boolean reload) {
        messages_adapter = new MainAdapter(messagesViewModel.getConversations());
        messages_adapter.onAttachedToRecyclerView(rView);
        RecyclerView.ItemDecoration divider = new DividerItemDecoration(getActivity(), layoutManager.getOrientation());
        rView.addItemDecoration(divider);
        rView.setLayoutManager(layoutManager);
        rView.scrollToPosition(0);
        messages_adapter.notifyDataSetChanged();
        rView.setAdapter(messages_adapter);
        if(reload)
            reloadUI();
    }

    private void showMessagesFailed(Integer error) {
        Toast.makeText(getContext(), error, Toast.LENGTH_LONG).show();
    }
}