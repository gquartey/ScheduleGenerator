package edu.swarthmore.cs71.starfruit.classes.departments;

import edu.swarthmore.cs71.starfruit.classes.course.CourseCode;
import edu.swarthmore.cs71.starfruit.classes.requirements.DegreeRequirement;
import edu.swarthmore.cs71.starfruit.classes.Solution;

import java.util.Collection;

//TODO: Need to change Strings to Groups
//TODO: Access global dictionary (String, Department) and define requirements

public class BlackStudies implements Department {
    private DegreeRequirement major;
    private DegreeRequirement minor;
    public BlackStudies() {
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
        return "BLST";
    }
}