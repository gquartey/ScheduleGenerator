package edu.swarthmore.cs71.starfruit.classes;

import edu.swarthmore.cs71.starfruit.classes.course.Course;
import edu.swarthmore.cs71.starfruit.classes.departments.Department;

public class ScheduledCourse {
    private Course course;
    private TimeSection time;
    private boolean isScheduled;

    //TODO:
    //

    public ScheduledCourse(Course course) {
        this.course = course;
        this.isScheduled = false;
        this.time = null;

    }

    public void setTime(TimeSection time) {
        this.time = time;
        this.isScheduled = true;
    }

    public boolean isScheduled() {
        return isScheduled;
    }

    public TimeSection getTime(){
        return this.time;
    }

    public String getCourseName() { return this.course.getCourseName();}

    public Integer getCourseNum(){return this.course.getCourseCode();}

    public Department getSchedDept(){return this.course.getDepartment();}

}
