package edu.swarthmore.cs71.starfruit.classes.requirements;

import edu.swarthmore.cs71.starfruit.classes.course.CourseCode;
import edu.swarthmore.cs71.starfruit.classes.Solution;
import java.util.*;

public class MinimumRequirement implements DegreeRequirement {
        private int num;
        private DegreeRequirement and;

    public MinimumRequirement(int num, DegreeRequirement and) {
        this.num = num;
        this.and = and;
    }

    @Override
    public Set<Solution> getSolutionSet(Collection<CourseCode> taken) {
        Set<Solution> result = new HashSet<>();
        Set<Solution> allResults = and.getSolutionSet(taken);
        for (Solution s: allResults){
            if (s.getCourses().size() == num){
                result.add(s);
            }
        }
        return result;
    }
}
