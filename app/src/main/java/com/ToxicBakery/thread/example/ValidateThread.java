package com.ToxicBakery.thread.example;

import android.support.annotation.NonNull;

import java.util.Locale;

class ValidateThread {

    public void is(@NonNull String name) {
        String currentThreadName = Thread.currentThread().getName();
        if (!name.equals(currentThreadName)) {
            throw new RuntimeException(
                    String.format(Locale.ENGLISH, "Expected thread %s but on thread %s", name, currentThreadName));
        }
    }

}
