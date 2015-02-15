package com.koki.codeivate.Models;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Created by koki on 15/02/15.
 */
public class CodeivateUser implements Serializable {

    public String name;
    public Float level;
    public Float focus_level;
    public Float focus_points;
    public Integer max_streak;
    public Integer total_days_coded;
    public Integer total_flow_states;
    public Integer time_spent;
    public Boolean programming_now;
    public String current_language;
    public Boolean streaking_now;
    public HashMap<String,CodeivatePlatform> platforms;
    public HashMap<String,CodeivateLanguage> languages;

    public CodeivateUser(String name,Float level,Float focus_level, Float focus_points, Integer max_streak, Integer total_days_coded, Integer total_flow_states, Integer time_spent, Boolean programming_now, String current_language, Boolean streaking_now, HashMap<String,CodeivatePlatform> platforms, HashMap<String,CodeivateLanguage> languages) {
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
