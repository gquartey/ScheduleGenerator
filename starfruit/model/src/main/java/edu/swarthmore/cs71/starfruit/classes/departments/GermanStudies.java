package edu.swarthmore.cs71.starfruit.classes.departments;

//TODO: Need to change Strings to Groups
//TODO: Access global dictionary (String, Department) and define requirements

import edu.swarthmore.cs71.starfruit.classes.course.CourseCode;
import edu.swarthmore.cs71.starfruit.classes.requirements.DegreeRequirement;
import edu.swarthmore.cs71.starfruit.classes.Solution;

import java.util.Collection;

public class GermanStudies implements Department {
    private DegreeRequirement major;
    private DegreeRequirement minor;

    public GermanStudies() {
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
        return "GMST";
    }
}

