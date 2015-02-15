package com.koki.codeivate.Models;

import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by koki on 15/02/15.
 */
public class CodeivateLanguage implements Serializable {
    public String name;
    public Double level;
    public Integer points;

    public CodeivateLanguage(String name, Double level, Integer points) {
        this.name = name;
        this.level = level;
        this.points = points;
    }

    @Override
    public String toString() {
        return name;
    }

}
