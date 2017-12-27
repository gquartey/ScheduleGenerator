package edu.swarthmore.cs71.starfruit.classes.requirements;

import edu.swarthmore.cs71.starfruit.classes.course.CourseCode;
import edu.swarthmore.cs71.starfruit.classes.course.CourseCodeComparator;
import edu.swarthmore.cs71.starfruit.classes.Solution;

import java.util.*;

//TODO: Make getSolutions for all reqs and initialize empty reqs for all depts


public class AndRequirement implements DegreeRequirement {
    private Collection<DegreeRequirement> reqs;
    private Map<DegreeRequirement,ArrayList<ArrayList<CourseCode>>> solutionSets;
    private ArrayList<DegreeRequirement> keyList;

    public AndRequirement(Collection<DegreeRequirement> reqs) {
        this.reqs = reqs;
        solutionSets = new HashMap<>();
    }


    public Set<Solution> getSolutionSet(Collection<CourseCode> taken){
        //TODO: New way need to implement
        // Create single empty solution
        Set<Solution> accumulator = new HashSet<>(Arrays.asList(new Solution(new HashSet<>())));

        // Accumulate
        for (DegreeRequirement requirement : reqs) {
            Set<Solution> requirementSolutions = requirement.getSolutionSet(taken);
            Set<Solution> newAccumulator = disjointCartesianProduct(requirementSolutions,accumulator);
            accumulator = newAccumulator;
        }
        return accumulator;
    }

    private Set<Solution> disjointCartesianProduct(Set<Solution> a, Set<Solution> b) {

        Set<Solution> result = new HashSet<>();
        for (Solution solutionR : b) {
            for (Solution solutionA : a) {
                Solution newSolution = disjointUnion(solutionR, solutionA);
                if (newSolution != null) {
                    result.add(newSolution);
                }

            }
        }
        return result;
    }

    private Solution disjointUnion(Solution a, Solution b) {

        //TODO: new way to implement set

        Set<CourseCode> intersection = new HashSet<>();
        Set<CourseCode> check = new HashSet<>();
        check.addAll(a.getCourses());
        intersection.addAll(b.getCourses());
        intersection.retainAll(check);
        boolean disjoint = intersection.isEmpty();
        if(!disjoint){
            return null;
        }
        Set<CourseCode> a1 = new HashSet<>(a.getCourses());
        Set<CourseCode> b1 = b.getCourses();
        a1.addAll(b1);


        Solution newSolution = new Solution(a1);
        return newSolution ;
    }
}
