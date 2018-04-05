package com.caavo.assignment;

import android.app.Application;

import com.caavo.assignment.database.BaseDatabase;

/**
 * Created by santo on 2/4/18.
 */

public class ApplicationLoader extends Application {

    private BaseDatabase baseDatabase;
    @Override
    public void onCreate() {
        super.onCreate();
        setUpDatabase();
    }

    private void setUpDatabase() {
        baseDatabase = new BaseDatabase(this);
    }

    public BaseDatabase getDB() {
        if(baseDatabase != null) {
            return baseDatabase;
        } else {
            baseDatabase = new BaseDatabase(this);
            return baseDatabase;
        }
    }
}
