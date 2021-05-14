package com.example.dahlia_android.search;

import android.app.SearchManager;
import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.provider.BaseColumns;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import com.example.dahlia_android.ui.user.User;
import com.example.dahlia_android.ui.user.UserListViewModel;
import com.example.dahlia_android.ui.user.UserListViewModelFactory;

import java.util.ArrayList;



public class UserSuggestionProvider extends ContentProvider {

    private UserListViewModel userListViewModel;
    private ArrayList<User> users;
    final String AUTHORITY = "com.example.dahlia_android.search.UserSuggestionProvider";

    private static final int TYPE_ALL_SUGGESTIONS = 1;
    private static final int TYPE_SINGLE_SUGGESTION = 2;

    private UriMatcher uriMatcher;
    @Override
    public boolean onCreate() {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY, "/#", TYPE_SINGLE_SUGGESTION);
        uriMatcher.addURI(AUTHORITY, "search_suggest_query/*", TYPE_ALL_SUGGESTIONS);
        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        userListViewModel = new ViewModelProvider((ViewModelStoreOwner) this, new UserListViewModelFactory()).get(UserListViewModel.class);
        users = userListViewModel.getUserList();

        MatrixCursor cursor = new MatrixCursor(
            new String[] {
                    BaseColumns._ID,
                    SearchManager.SUGGEST_COLUMN_TEXT_1,
                    SearchManager.SUGGEST_COLUMN_INTENT_DATA_ID
            }
        );

        if (uriMatcher.match(uri) == TYPE_ALL_SUGGESTIONS) {
            if(users != null) {
                String query = uri.getLastPathSegment().toUpperCase();
                int limit = Integer.parseInt(uri.getQueryParameter(SearchManager.SUGGEST_PARAMETER_LIMIT));

                int lenght = users.size();
                for(int i = 0; i<lenght && cursor.getCount() < limit; i++){
                    User user = users.get(i);
                    Log.i("userlist","User: "+user.getUsername());
                    if (user.getUsername().toUpperCase().contains(query)) {
                        cursor.addRow(new Object[]{i, user, i});
                    }
                }
            }
        } else if (uriMatcher.match(uri) == TYPE_SINGLE_SUGGESTION) {
            int position = Integer.parseInt(uri.getLastPathSegment());
            User user = users.get(position);
            cursor.addRow(new Object[]{position, user, position});
        }
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
