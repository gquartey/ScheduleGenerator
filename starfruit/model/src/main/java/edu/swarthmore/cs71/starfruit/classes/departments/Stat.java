package edu.swarthmore.cs71.starfruit.classes.departments;



import edu.swarthmore.cs71.starfruit.classes.course.CourseCode;
import edu.swarthmore.cs71.starfruit.classes.requirements.DegreeRequirement;
import edu.swarthmore.cs71.starfruit.classes.Solution;

import java.util.ArrayList;
import java.util.Collection;

public class Stat implements Department {
    private DegreeRequirement major;
    private DegreeRequirement minor;

    //TODO: look up stat -> this is just a template so we can make global dictionary

    public Stat() {
        Collection<Integer> stat = new ArrayList<>();
        stat.add(11);
        stat.add(21);
        stat.add(29);
        //cores

    }


    @Override
    public Collection<Solution> getSolutionsMajor(Collection<CourseCode> taken) {
        return null;
    }

    @Override
    public Collection<Solution> getSolutionsMinor(Collection<CourseCode> taken) {
        return null;
    }

    @Override
    public String getFourLetter() {
        return "STAT";
    }
}

