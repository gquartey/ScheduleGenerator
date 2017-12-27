package edu.swarthmore.cs71.starfruit.classes;

import java.io.Serializable;

public class TimeSection implements Serializable{
    private String start;
    private String end;
    private String day;

    //TODO: turn into java time objects once we know the parse representation of time slots
    //TODO use JODA time section thing?
    public TimeSection(String start, String end, String day){
        this.start = start;
        this.end = end;
        this.day = day;

    }
}
