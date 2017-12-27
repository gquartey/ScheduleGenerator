package edu.swarthmore.cs71.starfruit.classes;

import edu.swarthmore.cs71.starfruit.classes.course.CourseCode;

import java.util.Set;

public class Solution {
    private Set<CourseCode> needToTake;

    public Solution(Set<CourseCode> needToTake) {
        this.needToTake = needToTake;
    }

    public Set<CourseCode> getCourses() {
        return needToTake;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Solution solution = (Solution) o;

        return needToTake.equals(solution.needToTake);
    }

    @Override
    public int hashCode() {
        return needToTake.hashCode();
    }

    public String toString(){
        String accumulator = "";
        for (CourseCode c: getCourses()){
            if(!accumulator.isEmpty()){
                accumulator += ",";
            }
            accumulator += c.toString();
        }
        return accumulator;
    }
}
