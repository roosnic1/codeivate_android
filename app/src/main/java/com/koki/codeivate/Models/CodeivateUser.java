package com.koki.codeivate.Models;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by koki on 15/02/15.
 */
public class CodeivateUser implements Serializable {

    public String name;
    public Double level;
    public Double focus_level;
    public Integer focus_points;
    public Integer max_streak;
    public Integer total_days_coded;
    public Integer total_flow_states;
    public Integer time_spent;
    public Boolean programming_now;
    public String current_language;
    public Boolean streaking_now;
    public ArrayList<CodeivatePlatform> platforms;
    public ArrayList<CodeivateLanguage> languages;

    public CodeivateUser(String name,Double level,Double focus_level, Integer focus_points, Integer max_streak, Integer total_days_coded, Integer total_flow_states, Integer time_spent, Boolean programming_now, String current_language, Boolean streaking_now, ArrayList<CodeivatePlatform> platforms, ArrayList<CodeivateLanguage> languages) {
        this.name = name;
        this.level = level;
        this.focus_level = focus_level;
        this.focus_points = focus_points;
        this.max_streak = max_streak;
        this.total_days_coded = total_days_coded;
        this.total_flow_states = total_flow_states;
        this.time_spent = time_spent;
        this.programming_now = programming_now;
        this.current_language = current_language;
        this.streaking_now = streaking_now;
        this.platforms = platforms;
        this.languages = languages;

    }

    @Override
    public String toString() {
        return name;
    }



}
