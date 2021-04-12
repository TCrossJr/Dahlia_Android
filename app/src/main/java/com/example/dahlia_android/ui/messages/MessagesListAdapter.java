package com.example.dahlia_android.ui.messages;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dahlia_android.R;


public class MessagesListAdapter extends RecyclerView.Adapter<MessagesListAdapter.ViewHolder> {

    private static final String TAG = "MessagesAdapter";
    private static Messages _messages; // TODO: Put in MessagesViewModel and use LiveData + Repository

    public MessagesListAdapter(Messages messages) {
        _messages = messages;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_message, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d(TAG, "Message->" + position + "<-position");
        Message msg = _messages.getMessage(position);
        holder.setMessagesDisplayName(msg.getMessageDisplayName());
        holder.setMessageText(msg.getMessageText());
        holder.frameLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                 TODO: Handle when user clicks message... Message (New v  iew) Fragment???
                /*
                TownDetailsFragment.currentTown = new Town(town);
                Intent intent = new Intent(v.getContext(),TownDetailsFragment.class);
                ContextCompat.startActivity(v.getContext(),intent, null);
                 */
            }
        });
    }

    @Override
    public int getItemCount() {
        return _messages.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final FrameLayout frameLayout;
        private final ImageView messageProfileImageURL;
        private final TextView messageDisplayName;
        private final TextView messageText;
        private Button messageReply;
        private Button messageMoreOptions;

        public ViewHolder(@NonNull View view) {
            super(view);
            frameLayout = view.findViewById(R.id.message_frame);
            messageProfileImageURL = view.findViewById(R.id.message_profile_image);
            messageDisplayName = view.findViewById(R.id.message_user_from);
            messageText = view.findViewById(R.id.message_text);
            messageReply = view.findViewById(R.id.message_reply);
            messageMoreOptions = view.findViewById(R.id.message_more_options);
            messageReply.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO: Reply to a message button
                }
            });
            messageMoreOptions.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO: More options button
                }
            });
        }

        public void setMessagesDisplayName(String displayName) {
            this.messageDisplayName.setText(displayName);
        }

        public void setMessageText(String messageText) {
            this.messageText.setText(messageText);
        }
    }
}
