package com.koki.codeivate;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

/**
 * Created by koki on 15/02/15.
 */
public class ContentLoader {

    private static final String TAG = "ContentLoader";
    private static final String URL = "http://codeivate.com/users/";

    private Context mContext;
    private IContentLoaderCallback mCallback;
    private String mUsername;

    public ContentLoader(Context context, IContentLoaderCallback callback, String username) {
        mContext = context;
        mCallback = callback;
        mUsername = username;
    }

    public void loadJSON() {
        String jsonUrl = String.format(URL + "%s.json?callback=?",mUsername);
        Log.i(TAG,"URL to be called: " + jsonUrl);
        new LoadJsonTask().execute(jsonUrl);
    }


    private void saveJSON(String jsonString) {
        try {
            Log.i(TAG,"JSON String: " + jsonString);
            JSONObject jo = new JSONObject(jsonString);
        } catch(JSONException e) {
            e.printStackTrace();
            mCallback.contentLoaderFail(mContext.getString(R.string.errConvertJson));
        }
    }



    private class LoadJsonTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            StringBuilder stringBuilder = new StringBuilder();
            try {
                DefaultHttpClient httpClient = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost(params[0]);
                HttpResponse httpResponse = httpClient.execute(httpPost);
                HttpEntity httpEntity = httpResponse.getEntity();
                InputStream inputStream = httpEntity.getContent();

                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                String line;
                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line);
                }
                inputStream.close();
                return stringBuilder.toString();
            } catch(UnsupportedEncodingException | ClientProtocolException e) {
                e.printStackTrace();
                mCallback.contentLoaderFail(mContext.getString(R.string.errLoadHttp));
            } catch(IOException e) {
                e.printStackTrace();
                mCallback.contentLoaderFail(mContext.getString(R.string.errLoadIO));
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if(s != null) {
                saveJSON(s);
            }
        }
    }

}
