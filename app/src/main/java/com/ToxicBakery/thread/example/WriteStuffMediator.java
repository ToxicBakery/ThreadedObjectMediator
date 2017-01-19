package com.ToxicBakery.thread.example;

import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;

import java.util.concurrent.atomic.AtomicReference;

class WriteStuffMediator implements IWriteStuff {

    private static final String THREAD_NAME = "WriteStuffMediatorThread";

    private final Handler handler;
    private final AtomicReference<IWriteStuff> writeStuffRef;

    WriteStuffMediator() {
        writeStuffRef = new AtomicReference<IWriteStuff>(new WriteStuffNoOp());
        HandlerThread handlerThread = new HandlerThread(THREAD_NAME);
        handlerThread.start();
        handler = new Handler(handlerThread.getLooper());

        final ValidateThread validateThread = new ValidateThread();
        handler.post(new CreateWriteStuff(THREAD_NAME, validateThread, this));
    }

    void setWriteStuffImplementation(IWriteStuff writeStuff) {
        Log.d("WriteStuffMediator", "setWriteStuffImplementation");
        writeStuffRef.set(writeStuff);
    }

    @Override
    public void thingOne() {
        Log.d("WriteStuffMediator", "scheduling thingOne");
        handler.post(new PerformThingOne(writeStuffRef.get()));
    }

    @Override
    public void thingTwo() {
        Log.d("WriteStuffMediator", "scheduling thingTwo");
        handler.post(new PerformThingTwo(writeStuffRef.get()));
    }

    static class CreateWriteStuff implements Runnable {

        private final String threadName;
        private final ValidateThread validateThread;
        private final WriteStuffMediator writeStuffMediator;

        CreateWriteStuff(String threadName,
                         ValidateThread validateThread,
                         WriteStuffMediator writeStuffMediator) {

            this.threadName = threadName;
            this.validateThread = validateThread;
            this.writeStuffMediator = writeStuffMediator;
        }

        @Override
        public void run() {
            IWriteStuff writeStuff = new WriteStuff(threadName, validateThread);
            writeStuffMediator.setWriteStuffImplementation(writeStuff);
        }
    }

    static class PerformThingOne implements Runnable {

        private final IWriteStuff writeStuff;

        PerformThingOne(IWriteStuff writeStuff) {
            this.writeStuff = writeStuff;
        }

        @Override
        public void run() {
            writeStuff.thingOne();
        }

    }

    static class PerformThingTwo implements Runnable {

        private final IWriteStuff writeStuff;

        PerformThingTwo(IWriteStuff writeStuff) {
            this.writeStuff = writeStuff;
        }

        @Override
        public void run() {
            writeStuff.thingTwo();
        }

    }

}
