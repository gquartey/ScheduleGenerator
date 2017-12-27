package edu.swarthmore.cs71.starfruit.classes;

import edu.swarthmore.cs71.starfruit.classes.course.Course;
import edu.swarthmore.cs71.starfruit.classes.course.CourseCode;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public class Sorter {

    private ArrayList<CourseCode> classes;
    private ArrayList<Integer> numbers;


    public Sorter(Collection<CourseCode> classes) {
        this.classes = new ArrayList<>(classes);
        this.numbers = new ArrayList<>();
        for (CourseCode i : classes) {
            numbers.add(i.getCourseNumber());
        }
        Collections.sort(numbers);
    }

    public Collection<CourseCode> getClasses() {
        ArrayList<CourseCode> sortedClasses = new ArrayList<>();
        for (int i=0; i<numbers.size(); i++) {
            for (CourseCode j : classes) {
                if (numbers.get(i) == j.getCourseNumber()) {
                    sortedClasses.add(j);
                }
            }
        }
        return sortedClasses;
    }

}
