package com.ss.aris.open.widget;

import android.content.Context;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import com.ss.aris.open.console.Console;

public abstract class ArisWidget {

    protected boolean hasPaused = false;
    protected boolean hasDestroyed = false;

    private IResource resource = null;
    protected Context context;
    protected Console console;
    private Runnable task;
    private int interval = 10000;

    public void onCreate(Context context, Console console){
        this.context = context;
        this.console = console;
        hasDestroyed = false;
    }

    public void setResource(IResource res){
        this.resource = res;
    }

    public void onDestroy(){
        hasDestroyed = true;
        mHandler.removeCallbacks(runnable);
    }

    public void onResume() {
        hasPaused = false;
        mHandler.post(runnable);
    }

    public void onPause() {
        hasPaused = true;
    }

    protected IResource getResource(){
        return resource;
    }

    private Handler mHandler = new Handler();

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (!hasPaused) {
                if (task != null) task.run();
            }

            if (!hasDestroyed) mHandler.postDelayed(this, interval);
        }
    };

    protected void registerIntervalTask(Runnable task, int interval){
        this.task = task;
        this.interval = interval;

        mHandler.post(task);
    }

    public abstract View getView(ViewGroup parent, String value);

}