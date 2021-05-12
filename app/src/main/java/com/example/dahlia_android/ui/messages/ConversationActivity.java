package com.example.dahlia_android.ui.messages;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dahlia_android.R;
import com.example.dahlia_android.ui.recyclerview.MainAdapter;

public class ConversationActivity extends AppCompatActivity {

    private ConversationViewModel conversationViewModel;
    protected MainAdapter conversation_adapter;
    protected LinearLayoutManager layoutManager;
    private RecyclerView rView;

    @Override
    protected void onStart() {
        super.onStart();
        updateUI();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversation);
        conversationViewModel =
                new ViewModelProvider(this, new ConversationViewModelFactory()).get(ConversationViewModel.class);
        rView = findViewById(R.id.conversation_recycler);
        layoutManager = new LinearLayoutManager(this);
        updateUI();
    }

    private void updateUI() {
        Messages conversation = conversationViewModel.getConversation(0);
        conversation_adapter = new MainAdapter(conversation);
        conversation_adapter.notifyDataSetChanged();
        RecyclerView.ItemDecoration divider = new DividerItemDecoration(this, layoutManager.getOrientation());
        rView.addItemDecoration(divider);
        rView.setLayoutManager(layoutManager);
        rView.scrollToPosition(conversation.size()-1);
        rView.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                rView.scrollToPosition(conversation.size()-1);
            }
        });
        rView.setAdapter(conversation_adapter);
    }
}