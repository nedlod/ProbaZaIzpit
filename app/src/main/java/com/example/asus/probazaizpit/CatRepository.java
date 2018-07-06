package com.example.asus.probazaizpit;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class CatRepository {
    private CatDao mCatsDao;
    private LiveData<List<Cat>> mData;

    CatRepository(Application application)
    {
        CatDatabase db = CatDatabase.getDatabase(application);
        mCatsDao = db.mCatsDao();
        mData = mCatsDao.getAllCats();
    }

    LiveData<List<Cat>> getAllCats()
    {
        return mData;
    }

    public void insert(Cat cat)
    {
        new InsertAsyncTask(mCatsDao).execute(cat);
    }

    public void deleteAll()
    {
        new DeleteAsyncTask(mCatsDao).execute();
    }

    private static class InsertAsyncTask extends AsyncTask<Cat, Void, Void>
    {

        private CatDao mAsyncTaskDao;

        InsertAsyncTask(CatDao dao)
        {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Cat... params)
        {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    private static class DeleteAsyncTask extends AsyncTask<Void, Void, Void>
    {
        private CatDao mAsyncTaskDao;

        DeleteAsyncTask(CatDao catDao)
        {
            this.mAsyncTaskDao = catDao;
        }

        @Override
        protected Void doInBackground(Void... voids)
        {
            mAsyncTaskDao.deleteAll();
            return null;
        }
    }
}
