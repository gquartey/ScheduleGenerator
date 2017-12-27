package edu.swarthmore.cs71.starfruit.classes;

import edu.swarthmore.cs71.starfruit.classes.course.Course;
import edu.swarthmore.cs71.starfruit.classes.course.CourseCode;
import edu.swarthmore.cs71.starfruit.classes.departments.Department;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class Mapper {
    private Collection<CourseCode> courseCodes;
    private Collection<Course> courses;
    private HashMap<Department, Collection<Course>> catalog;
    DeptLib deptLib = DeptLib.getInstance();

    public Mapper(Collection<Course> courses, Collection<CourseCode> courseCodes, HashMap<Department, Collection<Course>> catalog) {
        this.catalog = catalog;
        this.courses = courses;
        this.courseCodes = courseCodes;
    }

    public Collection<Course> codesToCourses(){




        Sorter sorter = new Sorter (this.courseCodes);
        this.courseCodes = sorter.getClasses();

        Collection<Course> courses = new ArrayList<>();
        Collection<Integer> added = new ArrayList<>();
        if (!this.courseCodes.isEmpty() && !catalog.isEmpty()) {
            for (CourseCode i : this.courseCodes) {
                Department department = deptLib.getCourseMapping(i.getStringDepartment());

                Collection<Course> depClasses = catalog.get(department);
                for (Course j : depClasses) {
                    if (j.getCourseCode().equals(i.getCourseNumber()) && !added.contains(j.getCourseCode())) {
                        courses.add(j);
                        added.add(j.getCourseCode());
                        break;
                    }
                }
            }
        }

        return courses;
    }

    public Collection<CourseCode> coursesToCodes() {
        Collection<CourseCode> courseCodes = new ArrayList<>();

        if (!this.courses.isEmpty()) {

            for (Course i : this.courses) {
                CourseCode courseCode = new CourseCode(i.getDepartment().getFourLetter(), i.getCourseCode());
                courseCodes.add(courseCode);
            }
        }
        return courseCodes;
    }

    public Collection<CourseCode> getCourseCodes() {
        return courseCodes;
    }

    public Collection<Course> getCourses() {
        return courses;
    }
}
