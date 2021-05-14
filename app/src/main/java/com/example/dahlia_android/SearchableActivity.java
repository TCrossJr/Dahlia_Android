package com.example.dahlia_android;

import android.app.SearchManager;
import android.content.AsyncQueryHandler;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.MenuItemCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import com.example.dahlia_android.ui.user.User;
import com.example.dahlia_android.ui.user.UserListViewModel;
import com.example.dahlia_android.ui.user.UserListViewModelFactory;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class SearchableActivity extends AppCompatActivity {
    private MyHandler myHandler;
    private TextView textView;
   // private UserListViewModel userListViewModel;
 //   private ListView listView;
  //  private User[] userListViewItems;
   // private ArrayList<User> userListViewUser;
  //  private ArrayAdapter<User> arrayAdapter;

    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchable);
      //  userListViewModel = new ViewModelProvider(
              //  (ViewModelStoreOwner) this, new UserListViewModelFactory())
              //          .get(UserListViewModel.class);
        //final ListView listView = findViewById(R.id.listView);

       // final ArrayList<User> userList = userListViewModel.getUserList();
        //final ArrayAdapter<User> arrayAdapter = new ArrayAdapter<User>(this,android.R.layout.simple_list_item_1, userList);
       // listView.setAdapter(arrayAdapter);

        textView = findViewById(R.id.textView);

        Intent intent = getIntent();
        if(Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            textView.setText("Searching by: "+query);
        } else if (Intent.ACTION_VIEW.equals(intent.getAction())) {
            myHandler = new MyHandler(this);
            myHandler.startQuery(0,null,intent.getData(),null,null,null,null);
        }


    }
/**
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        MenuItem searchMenu = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchMenu);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);

    }**/

    public void updateText(String text){
        textView.setText(text);
    }

    static class MyHandler extends AsyncQueryHandler {
        WeakReference<SearchableActivity> activity;

        public MyHandler(SearchableActivity searchableActivity) {
            super(searchableActivity.getContentResolver());
            activity = new WeakReference<>(searchableActivity);
        }

        @Override
        protected void onQueryComplete(int token, Object cookie, Cursor cursor) {
            super.onQueryComplete(token, cookie, cursor);
            if (cursor==null || cursor.getCount()==0) return;

            cursor.moveToFirst();

            long id = cursor.getLong(cursor.getColumnIndex(BaseColumns._ID));
            String text = cursor.getString(cursor.getColumnIndex(SearchManager.SUGGEST_COLUMN_TEXT_1));
            long dataId = cursor.getLong(cursor.getColumnIndex(SearchManager.SUGGEST_COLUMN_INTENT_DATA_ID));

            cursor.close();

            if(activity.get() != null){
                activity.get().updateText("onQueryComplete"+id+" / "+text+" / "+dataId);
            }
        }
    }
}