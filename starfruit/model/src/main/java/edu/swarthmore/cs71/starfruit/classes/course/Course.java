package edu.swarthmore.cs71.starfruit.classes.course;

/* import these with maven
import java.io.*;
import java.util.*;
*/

import edu.swarthmore.cs71.starfruit.classes.DistributionMapping;
import edu.swarthmore.cs71.starfruit.classes.departments.Department;
import edu.swarthmore.cs71.starfruit.classes.requirements.Distribution;

import java.io.Serializable;
import java.util.Collection;

//makes sures course implements serializable java.io
public class Course implements Serializable {
    //private ArrayList<Course> preReqs;
    private float credits;
    private String courseName;
    private Department department;
    private boolean isLab;
    private boolean writing;
    private boolean PE;
    private Collection<String> preReqs;
    private CourseCode courseCode;
    private String courseTime;
    private String days;


    public Course(String courseName, Department department, Integer courseCode, boolean isLab, boolean writing, boolean PE, Collection<String> preReqs) {
        this.courseName = courseName;
        this.department = department;
        this.credits = 1;
        this.courseCode = new CourseCode(department.getFourLetter(), courseCode);
        this.isLab = isLab;
        this.writing = writing;
        this.PE = PE;
        this.preReqs = preReqs;
        this.courseTime = null;
        this.days = null;

        //once parser works, set time
    }

    public void setCourseTime(String courseTime) {
        this.courseTime = courseTime;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public String getCourseTime() { return courseTime; }

    public String getDays() { return days; }

    public float getCredits() {
        return credits;
    }
    
    public CourseCode getCourseCodeObject(){return courseCode;}

    public Integer getCourseCode(){return courseCode.getCourseNumber();}

    public void setCredits(float value){ this.credits = value;}

    public Department getDepartment() {
        return department;
    }

    public boolean isWritingCourse(){return this.writing;}

    public boolean isPECourse(){return this.PE;}

    public String getCourseName(){return this.courseName;}

    public Collection<String> getPreReqs() {
        return preReqs;
    }

    public void setPreReqs(Collection<String> preReqs) {
        this.preReqs = preReqs;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Course){
            if(((Course) obj).courseName.equals(this.courseName)){
                return true;
            }
        }
        return false;
        //return super.equals(obj);
    }

    public boolean isNSCourse() {
        DistributionMapping distributionMapping = DistributionMapping.getInstance();
        return Distribution.NS.equals(distributionMapping.getDepartmentToDistribution().get(this.department));
    }

    public boolean isSSCourse() {
        DistributionMapping distributionMapping = DistributionMapping.getInstance();
        return Distribution.SS.equals(distributionMapping.getDepartmentToDistribution().get(this.department));
    }

    public boolean isHUCourse() {
        DistributionMapping distributionMapping = DistributionMapping.getInstance();
        return Distribution.HU.equals(distributionMapping.getDepartmentToDistribution().get(this.department));
    }

}
