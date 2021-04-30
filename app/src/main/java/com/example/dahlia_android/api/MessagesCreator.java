package com.example.dahlia_android.api;

import com.example.dahlia_android.ui.messages.Messages;
import com.google.gson.InstanceCreator;

import java.lang.reflect.Type;

public class MessagesCreator implements InstanceCreator {
    @Override
    public Object createInstance(Type type) {
        return new Messages();
    }
}
