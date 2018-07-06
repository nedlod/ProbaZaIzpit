package com.example.asus.probazaizpit;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {Cat.class}, version = 1)
public abstract class CatDatabase extends RoomDatabase {
    public abstract CatDao mCatsDao();

    private static CatDatabase INSTANCE;

    public CatDatabase() {
    }

    static CatDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (CatDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            CatDatabase.class, "demo_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
