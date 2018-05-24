package com.pet.wiseapp.utils;

import android.os.AsyncTask;

public class SplashScreenLoader extends AsyncTask<Void, Integer, Void>{

    public interface SplashScreenLoaderCallback{
        void onProgressUpdate();
        void onLoadingCompleted();
    }

    private SplashScreenLoaderCallback callback;

    public SplashScreenLoader(SplashScreenLoaderCallback callback){
        this.callback = callback;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            for (int i = 0; i < Constants.SPLASH_LOADING_UPDATE_TIMES; i++){
                    publishProgress();
                    Thread.sleep(Constants.SPLASH_LOADING_THRESHOLD);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        if (callback != null)
            callback.onProgressUpdate();
    }

    @Override
    protected void onPostExecute(Void result) {
        super.onPostExecute(result);
        if (callback != null)
            callback.onLoadingCompleted();
    }
}
