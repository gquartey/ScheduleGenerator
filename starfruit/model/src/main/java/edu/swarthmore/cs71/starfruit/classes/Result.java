package edu.swarthmore.cs71.starfruit.classes;

import java.util.ArrayList;
import java.util.Collection;

public class Result {
    private Collection<SemesterSchedule> finished;

    public Result() {
        this.finished = new ArrayList<SemesterSchedule>();
    }

    public void addSemesterSchedule(SemesterSchedule s){

        this.finished.add(s);
    }

    public void removeSemesterSchedule(SemesterSchedule s){

        if(this.finished.contains(s)) {
            this.finished.remove(s);
        }
    }
    // Should be dependent on class year
    public boolean canAddSchedule(int gradYear){
        int size = this.finished.size();
        if(size <= 7){
            return true;
        }
        return false;
    }
}
