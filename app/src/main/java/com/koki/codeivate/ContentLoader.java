package com.koki.codeivate;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.koki.codeivate.Models.CodeivateLanguage;
import com.koki.codeivate.Models.CodeivatePlatform;
import com.koki.codeivate.Models.CodeivateUser;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

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
        new GetJsonTask().execute(jsonUrl);
        //new LoadJsonTask().execute(jsonUrl);
    }


    private void saveJSON(String jsonString) {
        try {
            Log.i(TAG,"JSON String: " + jsonString);
            if(jsonString.startsWith("?(") && jsonString.endsWith(");")) {
                jsonString = jsonString.substring(2,jsonString.length()-2);
            }
            JSONObject jo = new JSONObject(jsonString);
            String name = jo.getString("name");
            Log.i(TAG,"NAME: " + name);
            Log.i(TAG,"JSON Object: " + jo.toString());

            //Get all the Platforms
            ArrayList<CodeivatePlatform> platforms = new ArrayList<>();
            JSONObject joPlatforms = jo.optJSONObject("platforms");
            if(joPlatforms != null) {
                Iterator<String> keys = joPlatforms.keys();
                while(keys.hasNext()) {
                    String key = keys.next();
                    JSONObject joPlatform = joPlatforms.optJSONObject(key);
                    if(joPlatform != null) {
                        CodeivatePlatform platform = new CodeivatePlatform(key,joPlatform.optDouble("percent_work"),joPlatform.optInt("points"),joPlatform.optInt("time"));
                        platforms.add(platform);
                    }
                }


            }

            //Get all the Languages
            ArrayList<CodeivateLanguage> languages = new ArrayList<>();
            JSONObject joLanguages = jo.optJSONObject("languages");
            if(joLanguages != null) {
                Iterator<String> keys = joLanguages.keys();
                while (keys.hasNext()) {
                    String key = keys.next();
                    JSONObject joLanguage = joLanguages.optJSONObject(key);
                    if(joLanguage != null) {
                        CodeivateLanguage language = new CodeivateLanguage(key,joLanguage.optDouble("level"),joLanguage.optInt("points"));
                        languages.add(language);
                    }
                }
            }

            //Create Codeivate User
            CodeivateUser user = new CodeivateUser(jo.optString("name"),jo.optDouble("level"),jo.optDouble("focus_level"),jo.optInt("focus_point"),jo.optInt("max_streak"),jo.optInt("total_days_coded"),jo.optInt("total_flow_states"),jo.optInt("time_spent"),jo.optBoolean("programming_now"),jo.optString("current_language"),jo.optBoolean("streaking_now"),platforms,languages);


            //Log.i(TAG,"Platforms: " + platforms.toString());
            //Log.i(TAG,"Languages: " + languages.toString());
            mCallback.contentLoaderSuccess(user);
        } catch(JSONException e) {
            e.printStackTrace();
            mCallback.contentLoaderFail(mContext.getString(R.string.errConvertJson));
        }
    }


    private class GetJsonTask extends AsyncTask<String,Void,String> {
        @Override
        protected String doInBackground(String... params) {

            try {
                OkHttpClient client = new OkHttpClient();

                Request request = new Request.Builder().url(params[0]).build();
                Response response = client.newCall(request).execute();

                return response.body().string();
            } catch (IOException e) {
                e.printStackTrace();
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
