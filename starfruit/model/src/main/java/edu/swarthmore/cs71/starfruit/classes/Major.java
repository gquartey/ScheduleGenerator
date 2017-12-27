package edu.swarthmore.cs71.starfruit.classes;

import edu.swarthmore.cs71.starfruit.classes.course.CourseCode;
import edu.swarthmore.cs71.starfruit.classes.departments.Department;

import java.util.Collection;

public class Major {
    private Department department;
    private boolean done;


    public Major(Department department) {
        this.department = department;
        this.done = false;

    }

    //TODO: how to check if these are completed (in Department instead)
    public void completedMajor(){

    }


    public boolean isDone(){
        return this.done;
    }


    public Department getDepartment () {
        return this.department;
    }


    public Collection<Solution> getClassesNeeded(Collection<CourseCode> taken){
        return this.department.getSolutionsMajor(taken);
    }


    public int getNumClassesNeeded(Collection<CourseCode> taken) {
        int num = 0;
        for (Solution i : this.department.getSolutionsMajor(taken)) {
            num = i.getCourses().size();
        }
        return num;
    }


}
