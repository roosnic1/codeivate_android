package com.koki.codeivate.Models;

/**
 * Created by koki on 15/02/15.
 */
public class CodeivatePlatform {
    public String name;
    public Float percent_work;
    public Integer points;
    public Integer time;

    public CodeivatePlatform(String name, Float percent_work, Integer points, Integer time) {
        this.name = name;
        this.percent_work = percent_work;
        this.points = points;
        this.time = time;
    }

    @Override
    public String toString() {
        return name;
    }
}
