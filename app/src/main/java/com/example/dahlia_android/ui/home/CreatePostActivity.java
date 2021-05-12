package com.example.dahlia_android.ui.home;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.dahlia_android.R;
import com.example.dahlia_android.api.APIServiceInterface;
import com.example.dahlia_android.ui.recyclerview.MainAdapter;

//import static com.example.dahlia_android.data.DataSource.TOKEN;
//import static com.example.dahlia_android.data.DataSource.USER_ID;

public class CreatePostActivity extends AppCompatActivity {
    private static final String TAG = "CreateMessageActivity";
    private HomeFeedViewModel homeFeedViewModel;
    private APIServiceInterface apiInterface;
    private Button postAddMedia;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_post);
        homeFeedViewModel =
                new ViewModelProvider(this, new HomeFeedViewModelFactory())
                .get(HomeFeedViewModel.class);

        final EditText postText = findViewById(R.id.text_post);
        final ImageView mediaImage = findViewById(R.id.media_image_post);
        postAddMedia = findViewById(R.id.media_add_post);
        final Button postSend = findViewById(R.id.send_post);

/*        final FriendsList friends = friendsViewModel.getFriends();
        final ArrayAdapter<User> userToAdapter = new ArrayAdapter<User>(
                this, android.R.layout.simple_spinner_item,
                friends != null ? new ArrayList<User>() : null ) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                TextView initial = (TextView)super.getView(position, convertView, parent);
                if(initial.getText().toString().isEmpty()) {
                    initial.setTextColor(Color.GRAY);
                }
                return initial;
            }

            @Override
            public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                TextView initial = (TextView)super.getDropDownView(position, convertView, parent);
                if(initial.getText().toString().isEmpty()) {
                    initial.setVisibility(View.GONE);
                } else {
                    initial.setVisibility(View.VISIBLE);
                }
                return initial;
            }
        };*/
        postAddMedia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: Implement add media
            }
        });
        postSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String post  = postText.getText().toString();
                String media = ""; // TODO: Implement

                homeFeedViewModel.createPost(post);
                final Observer<PostResult> postObserver = new Observer<PostResult>() {
                    @Override
                    public void onChanged(PostResult postResult) {
                        if (postResult == null) {
                            return;
                        }
                        if (postResult.getError() != null) {
                            showPostFailed(postResult.getError());
                        }
                        if (postResult.getSuccess() != null) {
                            updateUiWithPost(postResult.getSuccess());
                        }
                        setResult(Activity.RESULT_OK);
                        finish();
                    }
                };
                homeFeedViewModel.getCreatePostResult().observe(CreatePostActivity.this, postObserver);
/*                homeFeedViewModel.getCreatePostResult().observe(this, new Observer<PostResult>() {
                    @Override
                    public void onChanged(PostResult postResult) {
                        if (postResult == null) {
                            return;
                        }
                        if (postResult.getError() != null) {
                            showPostFailed(postResult.getError());
                        }
                        if (postResult.getSuccess() != null) {
                            updateUiWithPost(postResult.getSuccess());
                        }
                    }
                });*/
            }
        });
    }

    private void updateUiWithPost(PostView success) {
//        success.getPost();
    }

    private void showPostFailed(Integer error) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show();
    }
}
