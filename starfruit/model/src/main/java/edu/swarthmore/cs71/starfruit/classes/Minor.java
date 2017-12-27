package edu.swarthmore.cs71.starfruit.classes;

import edu.swarthmore.cs71.starfruit.classes.course.CourseCode;
import edu.swarthmore.cs71.starfruit.classes.departments.Department;

import java.util.Collection;

public class Minor {
    private Department department;
    private boolean done;

    public Minor(Department department) {
        this.department = department;
        this.done = false;
    }

    public void completedMinor(){

    }

    public boolean isDone(){
        return this.done;
    }

    public Collection<Solution> getClassesNeeded(Collection<CourseCode> taken){
        return this.department.getSolutionsMinor(taken);
    }
}
