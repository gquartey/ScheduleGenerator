package edu.swarthmore.cs71.starfruit.classes.course;

import java.util.Comparator;

public class CourseCodeComparator implements Comparator {
    @Override
    public int compare(Object o1, Object o2) {
        CourseCode c1 = (CourseCode) o1;
        CourseCode c2 = (CourseCode) o2;

        if(c1.getStringDepartment().equals(c2.getStringDepartment())) {
            return c1.getCourseNumber() - c2.getCourseNumber();
      }
        return 1;
    }

    public CourseCodeComparator() {
    }
}
