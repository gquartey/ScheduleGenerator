package edu.swarthmore.cs71.starfruit.wrapper;

import edu.swarthmore.cs71.starfruit.classes.course.Course;
import edu.swarthmore.cs71.starfruit.classes.departments.Department;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class SemesterCatalog {
    HashMap<Department,Collection<Course>> AllSemesterCourses;
    public SemesterCatalog(Collection<Course> semesterCourses){
        this.AllSemesterCourses = new HashMap<>();
        for(Course i: semesterCourses){
            if(AllSemesterCourses.containsKey(i.getDepartment())){
                Collection<Course> placeHolder = AllSemesterCourses.get(i.getDepartment());
                placeHolder.add(i);
                AllSemesterCourses.put(i.getDepartment(),placeHolder);
            }else{
                Collection<Course> courses = new ArrayList<>();
                courses.add(i);
                AllSemesterCourses.put(i.getDepartment(),courses);
            }
        }
    }
    public Collection<Course> getDepartmentCourses(Department department){
        if(this.AllSemesterCourses.containsKey(department)){
            return AllSemesterCourses.get(department);
        }else{
            return new ArrayList<Course>();
        }
    }

    public HashMap<Department, Collection<Course>> getAllSemesterCourses() {
        return AllSemesterCourses;
    }
}
