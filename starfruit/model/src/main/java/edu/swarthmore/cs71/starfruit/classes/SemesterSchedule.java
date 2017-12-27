package edu.swarthmore.cs71.starfruit.classes;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.Collection;

public class SemesterSchedule {
    private Collection<ScheduledCourse> schedule;
    boolean detailed;
    private String date;

    //TODO: Need to be able to see if a given course can be added to a schedule (Time and PreReq Check)
    // only if detailed

    public SemesterSchedule(String string) {
        this.schedule = new ArrayList<ScheduledCourse>();
        this.detailed = false; //has time section
        this.date = date;
    }

    //TODO: Make sure it aligns with the date
    public void addCourse(ScheduledCourse course){
        if(!this.schedule.contains(course) && this.schedule.size() < 4) {
            this.schedule.add(course);
        }
    }

    public int getSemSize() {
        return this.schedule.size();
    }

    public Collection<ScheduledCourse> getSchedule() {
        return schedule;
    }
}
