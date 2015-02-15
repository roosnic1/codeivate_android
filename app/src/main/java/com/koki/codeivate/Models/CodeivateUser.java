package com.koki.codeivate.Models;

import java.io.Serializable;

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



}
