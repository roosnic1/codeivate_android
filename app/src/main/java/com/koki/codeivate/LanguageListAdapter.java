package com.koki.codeivate;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.koki.codeivate.Models.CodeivateLanguage;

import java.util.ArrayList;

/**
 * Created by koki on 15/03/15.
 */
public class LanguageListAdapter extends ArrayAdapter<CodeivateLanguage> {

    private final Context context;
    private final ArrayList<CodeivateLanguage> languages;
    private final int listviewLength;


    public LanguageListAdapter(Context context,ArrayList<CodeivateLanguage> languages,int listviewLength) {
        super(context,R.layout.listitem_language,languages);
        this.languages = languages;
        this.context = context;
        this.listviewLength = listviewLength;

    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if(convertView == null) {
            convertView =  inflater.inflate(R.layout.listitem_language,null);
        }

        CodeivateLanguage lang = languages.get(position);

        TextView language = (TextView) convertView.findViewById(R.id.tvLanguageName);
        TextView levelStart = (TextView) convertView.findViewById(R.id.tvLevelStart);
        TextView levelEnd = (TextView) convertView.findViewById(R.id.tvLevelEnd);
        RelativeLayout levelBar = (RelativeLayout) convertView.findViewById(R.id.rlLevelBar);

        int level = (int) Math.round(lang.level - 0.5);

        levelStart.setText(""+level);
        levelEnd.setText(""+(level+1));

        language.setText(languages.get(position).name);

        int length = (int) Math.round((listviewLength - (listviewLength * 0.2)) * (lang.level - level));


        levelBar.getLayoutParams().width = length;

        return convertView;
    }
}
