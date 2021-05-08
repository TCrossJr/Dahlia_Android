package com.example.dahlia_android.ui.home;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dahlia_android.R;
import com.example.dahlia_android.api.APIClient;
import com.example.dahlia_android.api.APIServiceInterface;
import com.example.dahlia_android.ui.friends.FriendsViewModel;
import com.example.dahlia_android.ui.friends.FriendsViewModelFactory;
import com.example.dahlia_android.ui.messages.MessagesViewModel;
import com.example.dahlia_android.ui.messages.MessagesViewModelFactory;
import com.example.dahlia_android.ui.messages.RawMessage;
import com.example.dahlia_android.ui.recyclerview.MainAdapter;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.dahlia_android.data.DataSource.TOKEN;
import static com.example.dahlia_android.data.DataSource.USER_ID;

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

                try {
                    apiInterface = APIClient.getClient().create(APIServiceInterface.class);
                    Call<Post> sendCall = apiInterface.createPost(TOKEN, USER_ID, post); // TODO: Hardcoded
                    sendCall.enqueue(new Callback<Post>() {
                        @Override
                        public void onResponse(Call<Post> call, Response<Post> response) {
                            if(response.isSuccessful()) {
                                Toast.makeText(getBaseContext(), R.string.prompt_post_sent, Toast.LENGTH_LONG).show();
                                Post p = response.body();
                                Log.d(TAG, "createPost: " + response.message());
                                setResult(Activity.RESULT_OK);
                                finish();
                            } else {
                                Toast.makeText(getBaseContext(), R.string.prompt_post_error_send, Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<Post> call, Throwable t) {
                            Toast.makeText(getBaseContext(), R.string.prompt_post_failed_send, Toast.LENGTH_LONG).show();
                            Log.d(TAG, "createPost: " + t.getMessage() );

                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
