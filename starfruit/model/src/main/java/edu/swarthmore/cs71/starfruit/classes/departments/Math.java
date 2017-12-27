package edu.swarthmore.cs71.starfruit.classes.departments;

import edu.swarthmore.cs71.starfruit.classes.course.CourseCode;
import edu.swarthmore.cs71.starfruit.classes.requirements.DegreeRequirement;
import edu.swarthmore.cs71.starfruit.classes.Solution;

import java.util.ArrayList;
import java.util.Collection;

public class Math implements Department {
    private DegreeRequirement major;
    private DegreeRequirement minor;

    public Math() {
        Collection<Integer> math = new ArrayList<>();
        math.add(15);
        math.add(25);
        math.add(33);
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
        return "MATH";
    }
}
