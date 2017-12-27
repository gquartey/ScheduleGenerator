package edu.swarthmore.cs71.starfruit.classes.requirements;

import edu.swarthmore.cs71.starfruit.classes.course.CourseCode;
import edu.swarthmore.cs71.starfruit.classes.Solution;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

public interface DegreeRequirement {

    //TODO pass in available courses
    Set<Solution> getSolutionSet(Collection<CourseCode> taken);

}
