package com.example.dahlia_android.util;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.dahlia_android.ui.user.User;

import org.jetbrains.annotations.NotNull;

public class UserNameSpinnerAdapter extends ArrayAdapter<User> {
    private Context context;
    private User[] values;

    public UserNameSpinnerAdapter(Context context, int textViewResourceId, User[] values){
        super(context, textViewResourceId, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public int getCount() {
        return values.length;
    }

    @Nullable
    @Override
    public User getItem(int position) {
        return values[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getDropDownView(int position, @Nullable @org.jetbrains.annotations.Nullable View convertView, @NonNull @NotNull ViewGroup parent) {
        TextView label = (TextView) super.getDropDownView(position, convertView, parent);
        label.setTextColor(Color.BLACK);
        label.setText(values[position].getUsername());

        return label;
    }

}
