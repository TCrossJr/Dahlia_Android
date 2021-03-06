package com.example.dahlia_android.ui.messages;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.dahlia_android.R;
import com.example.dahlia_android.api.APIClient;
import com.example.dahlia_android.api.APIServiceInterface;
import com.example.dahlia_android.data.DataRepository;
import com.example.dahlia_android.data.DataSource;
import com.example.dahlia_android.ui.friends.FriendsViewModel;
import com.example.dahlia_android.ui.friends.FriendsViewModelFactory;
import com.example.dahlia_android.ui.user.User;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CreateMessageActivity extends AppCompatActivity {
    private static final String TAG = "CreateMessageActivity";
    private FriendsViewModel friendsViewModel;
    private MessagesViewModel messagesViewModel;
    private APIServiceInterface apiInterface;
    private User messageTo;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_message);
        // TODO: Finish Create Message
        friendsViewModel =
                new ViewModelProvider(this, new FriendsViewModelFactory())
                        .get(FriendsViewModel.class);
        messagesViewModel =
                new ViewModelProvider(this, new MessagesViewModelFactory())
                .get(MessagesViewModel.class);

        final Spinner messageToSpinner = findViewById(R.id.spinner);
        //final EditText messageUserTo = findViewById(R.id.name_group);
        final EditText messageText = findViewById(R.id.text_message);
        final ImageView messageMedia = findViewById(R.id.media_image);
        final Button messageAddMedia = findViewById(R.id.media_add);
        final Button messageSend = findViewById(R.id.create_group);

        final ArrayList<User> friendList = friendsViewModel.getFriends();

        final ArrayAdapter<User> friendsDataAdapter = new ArrayAdapter<User>(
                this,android.R.layout.simple_spinner_item, friendList){};

        friendsDataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        messageToSpinner.setAdapter(friendsDataAdapter);
        messageToSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                messageTo = (User) parent.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        messageAddMedia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: Implement add media
            }
        });
        messageSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: Fix
                int friendID = messageTo.getUserID();
               /** int friendID = getIntent().getIntExtra("friendID",-1);
                if(friendID==-1){
                    friendID  = Integer.parseInt(messageUserTo.getText().toString());
                }**/
                String message  = messageText.getText().toString();
                String media = "testUrl/img.jpg"; // TODO: Implement

                try {
                    apiInterface = APIClient.getClient().create(APIServiceInterface.class);
                    DataRepository data = DataRepository.getInstance(new DataSource());
                    Call<Message> sendCall = apiInterface.sendMessage(data.getTokenString(),
                            data.getUser().getUserID(), friendID, message);
                    sendCall.enqueue(new Callback<Message>() {
                        @Override
                        public void onResponse(Call<Message> call, Response<Message> response) {
                            if(response.isSuccessful()) {
                                Toast.makeText(getBaseContext(), R.string.prompt_message_sent, Toast.LENGTH_LONG).show();
                                Message msg = response.body();
                                Log.d(TAG, "sendMessage: " + response.message());
                                setResult(Activity.RESULT_OK);
                                finish();
                            } else {
                                Toast.makeText(getBaseContext(), R.string.prompt_message_error_send, Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<Message> call, Throwable t) {
                            Toast.makeText(getBaseContext(), R.string.prompt_message_failed_send, Toast.LENGTH_LONG).show();
                            Log.d(TAG, "sendMessage: " + t.getMessage() );
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
