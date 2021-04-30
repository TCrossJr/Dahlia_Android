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
    public static Messages current_conversation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversation);
        conversationViewModel =
                new ViewModelProvider(this, new ConversationViewModelFactory()).get(ConversationViewModel.class);
        rView = findViewById(R.id.conversation_recycler);
        layoutManager = new LinearLayoutManager(this);

        Messages conversation = conversationViewModel.getConversation(0);
//        conversationViewModel.getCoversationResult<>
        conversation_adapter = new MainAdapter(conversation); //TODO: Move to ViewModel/DataSource/DataRepository
        conversation_adapter.notifyDataSetChanged();
        RecyclerView.ItemDecoration divider = new DividerItemDecoration(this, layoutManager.getOrientation());
        rView.addItemDecoration(divider);
        rView.setLayoutManager(layoutManager);
        rView.scrollToPosition(0);
        rView.setAdapter(conversation_adapter);
    }
}