package com.ToxicBakery.thread.example;

import android.support.annotation.NonNull;
import android.util.Log;

class WriteStuff implements IWriteStuff {

    private final String expectedThreadName;
    private final ValidateThread validateThread;

    WriteStuff(@NonNull String expectedThreadName,
                      @NonNull ValidateThread validateThread) {

        this.expectedThreadName = expectedThreadName;
        this.validateThread = validateThread;

        validateThread.is(expectedThreadName);
    }

    @Override
    public void thingOne() {
        validateThread.is(expectedThreadName);
        Log.d("WriteStuff", "Did thing one");
    }

    @Override
    public void thingTwo() {
        validateThread.is(expectedThreadName);
        Log.d("WriteStuff", "Did thing two");
    }

}
