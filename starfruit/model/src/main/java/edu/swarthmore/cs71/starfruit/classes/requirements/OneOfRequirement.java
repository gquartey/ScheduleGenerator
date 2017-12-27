package edu.swarthmore.cs71.starfruit.classes.requirements;

import edu.swarthmore.cs71.starfruit.classes.course.CourseCode;
import edu.swarthmore.cs71.starfruit.classes.Solution;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class OneOfRequirement implements DegreeRequirement {
    private Collection<CourseCode> courses;

    public OneOfRequirement(Collection<CourseCode> courses) {
        this.courses = courses;
    }

    @Override
    public Set<Solution> getSolutionSet(Collection<CourseCode> taken) {

        Set<Solution> possibles = new HashSet<>();

//        Collection<Solution> possibles = new HashSet<>();

        for(CourseCode i: this.courses){

            Set<CourseCode> couldTake = new HashSet<>();

//            Collection<CourseCode> couldTake = new HashSet<>();

            if(!taken.contains(couldTake)){
                couldTake.add(i);
                Solution possibility = new Solution(couldTake);
                possibles.add(possibility);
            }
        }
        return possibles;
    }


}
