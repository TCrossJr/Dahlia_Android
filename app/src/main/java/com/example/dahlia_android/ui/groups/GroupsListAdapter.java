package com.example.dahlia_android.ui.groups;

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


public class GroupsListAdapter extends RecyclerView.Adapter<GroupsListAdapter.ViewHolder> {

    private static final String TAG = "GroupsAdapter";
    private static Groups groups_list; // TODO: Put in GroupsViewModel and use LiveData + Repository

    public GroupsListAdapter(Groups groups) {
        groups_list = groups;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_group, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d(TAG, "Group->" + position + "<-position");
        Group group = groups_list.getGroup(position);
        holder.setGroupName(group.getGroupName());
        holder.setGroupDescription(group.getGroupDescription());
//        holder.frameLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                 TODO: Handle when user clicks post... Post(View)Fragment???
//                /*
//                TownDetailsFragment.currentTown = new Town(town);
//                Intent intent = new Intent(v.getContext(),TownDetailsFragment.class);
//                ContextCompat.startActivity(v.getContext(),intent, null);
//                 */
//
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return groups_list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final FrameLayout frameLayout;
        private final ImageView groupProfileImageURL;
        private final TextView groupName;
        private final TextView groupDescription;
        private final Button groupMessage;
        private final Button groupMoreOptions;

        public ViewHolder(@NonNull View view) {
            super(view);
            frameLayout = view.findViewById(R.id.group_frame);
            groupProfileImageURL = view.findViewById(R.id.group_profile_image);
            groupName = view.findViewById(R.id.group_name);
            groupDescription = view.findViewById(R.id.group_description);
            groupMessage = view.findViewById(R.id.group_message);
            groupMoreOptions = view.findViewById(R.id.group_more_options);
            groupMessage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO: Implement send group chat message
                }
            });
            groupMoreOptions.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO: Implement group more options
                }
            });
        }

        public void setGroupThumbnail(String imageURL) {
            // TODO: Implement setting profile thumbnail of group. Might need to change param
        }

        public void setGroupName(String groupName) {
            this.groupName.setText(groupName);
        }

        public void setGroupDescription(String groupDescription) {
            this.groupDescription.setText(groupDescription);
        }
    }
}
