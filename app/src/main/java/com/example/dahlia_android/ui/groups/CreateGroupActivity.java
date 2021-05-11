package com.example.dahlia_android.ui.groups;

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

import com.example.dahlia_android.R;
import com.example.dahlia_android.api.APIClient;
import com.example.dahlia_android.api.APIServiceInterface;
import com.example.dahlia_android.ui.home.HomeFeedViewModel;
import com.example.dahlia_android.ui.home.HomeFeedViewModelFactory;
import com.example.dahlia_android.ui.home.Post;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.dahlia_android.data.DataSource.TOKEN;
import static com.example.dahlia_android.data.DataSource.USER_ID;

public class CreateGroupActivity extends AppCompatActivity {
    private static final String TAG = "CreateGroupActivity";
    private GroupsViewModel groupsViewModel;
    private APIServiceInterface apiInterface;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_group);
        groupsViewModel =
                new ViewModelProvider(this, new GroupsViewModelFactory())
                        .get(GroupsViewModel.class);

        final EditText groupName = findViewById(R.id.name_group);
        final Button groupCreate = findViewById(R.id.create_group);

        groupCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name  = groupName.getText().toString();

                try {
                    apiInterface = APIClient.getClient().create(APIServiceInterface.class);
                    Call<Group> createCall = apiInterface.createGroup(TOKEN, USER_ID, name); // TODO: Hardcoded
                    createCall.enqueue(new Callback<Group>() {
                        @Override
                        public void onResponse(Call<Group> call, Response<Group> response) {
                            if(response.isSuccessful()) {
                                Toast.makeText(getBaseContext(), R.string.prompt_groups_failed, Toast.LENGTH_LONG).show();
                                Group g = response.body();
                                Log.d(TAG, "createGroup: " + response.message());
                                setResult(Activity.RESULT_OK);
                                finish();
                            } else {
                                Toast.makeText(getBaseContext(), R.string.prompt_group_error_create, Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<Group> call, Throwable t) {
                            Toast.makeText(getBaseContext(), R.string.prompt_group_failed_create, Toast.LENGTH_LONG).show();
                            Log.d(TAG, "createGroup: " + t.getMessage() );

                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
