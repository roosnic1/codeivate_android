package com.koki.codeivate;

import android.app.SearchManager;
import android.content.Context;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.koki.codeivate.Models.CodeivateUser;


public class MainActivity extends ActionBarActivity implements IContentLoaderCallback {

    private static final String TAG = "MainActivity";

    private SearchView searchView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Call ContentLoader
        /*ContentLoader cl = new ContentLoader(this,this,"roosnic1");
        cl.loadJSON();*/
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);

        searchView = (SearchView) menu.findItem(R.id.search_action).getActionView();

        setupSearchView();



        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private void setupSearchView() {
        if(searchView != null) {
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String s) {
                    Log.i(TAG,"Query changed s: " + s);
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String s) {
                    return false;
                }
            });
        }
    }

    @Override
    public void contentLoaderSuccess(CodeivateUser user) {
        Log.i(TAG,"CodeivateUser: " + user.toString());
        Toast.makeText(this,"USer Ready: "+user.toString(),Toast.LENGTH_LONG).show();
    }

    @Override
    public void contentLoaderFail(String errorMessage) {
        Log.e(TAG,"ContentLoader failed with message: " + errorMessage);
    }


}
