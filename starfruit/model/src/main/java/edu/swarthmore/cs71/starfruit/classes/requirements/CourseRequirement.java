package edu.swarthmore.cs71.starfruit.classes.requirements;

import edu.swarthmore.cs71.starfruit.classes.Solution;
import edu.swarthmore.cs71.starfruit.classes.course.CourseCode;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class CourseRequirement implements DegreeRequirement {
    private CourseCode course;

    public CourseRequirement(CourseCode courseCode) {
        this.course = courseCode;
    }

    @Override
    public Set<Solution> getSolutionSet(Collection<CourseCode> taken) {
        //solution set has one array which has just a single course = the required course

        Set<Solution> solutionSet = new HashSet<>();
        Set<CourseCode> solutionArray = new HashSet<>();

//        Collection<Solution> solutionSet = new HashSet<>();
//        Collection<CourseCode> solutionArray = new HashSet<>();

        if(!taken.contains(this.course)){
            solutionArray.add(course);
            Solution solution = new Solution(solutionArray);
            solutionSet.add(solution);
        }
        return solutionSet;
    }


}
