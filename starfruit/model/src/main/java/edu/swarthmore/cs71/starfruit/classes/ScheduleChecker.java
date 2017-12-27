package edu.swarthmore.cs71.starfruit.classes;

import java.util.ArrayList;
import java.util.Collection;

public class ScheduleChecker {
    private User student;
    private SemesterSchedule semesterSchedule;

    public ScheduleChecker(User student, SemesterSchedule semesterSchedule) {
        this.student = student;
        this.semesterSchedule = semesterSchedule;
    }


    //use this to check later to make sure our courses don't overlap
    public boolean checkMajors() {

        return true;
    }
}
