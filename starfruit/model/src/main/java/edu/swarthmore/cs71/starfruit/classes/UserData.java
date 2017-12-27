package edu.swarthmore.cs71.starfruit.classes;//package edu.swarthmore.cs71.starfruit.classes;

import edu.swarthmore.cs71.starfruit.classes.course.Course;
import edu.swarthmore.cs71.starfruit.classes.departments.Department;

import java.util.ArrayList;
import java.util.Collection;

public class UserData {
    private String major1;
    private String major2;
    private String minor1;
    private String minor2;
    private Integer classYear;
    private Boolean doubleMajor;
    private Boolean doubleMinor;

    //Make academic interests a list from which the student can select (a list of departments and the student picks their favorite)
    private Collection<Department> academicInterests;
    private History academicHistory;

    public UserData() {
        this.doubleMajor = false;
        this.doubleMinor = false;
        this.major1 = null;
        this.major2 = null;
        this.minor1 = null;
        this.minor1 = null;
        this.classYear = null;
        this.academicInterests = null;
        this.academicHistory = new History(new ArrayList<Course>());
    }

    public void setClassYear(Integer classYear) {
        this.classYear = classYear;
    }

    public Integer getClassYear() {

        return classYear;
    }

    public String getMajor1() {
        return major1;
    }

    public String getMajor2() {
        return major2;
    }

    public String getMinor1() {
        return minor1;
    }

    public String getMinor2() {
        return minor2;
    }

    public Boolean getDoubleMajor() {
        return doubleMajor;
    }

    public Boolean getDoubleMinor() {
        return doubleMinor;
    }

    public Collection<Department> getAcademicInterests() {
        return academicInterests;
    }

    public History getAcademicHistory() {
        return academicHistory;
    }

    public void setMajor1(String major1) {
        this.major1 = major1;
    }

    public void setMajor2(String major2) {
        this.major2 = major2;
        this.doubleMajor = true;
    }

    public void setAcademicInterests(Collection<Department> fav) {
        this.academicInterests = fav;
    }


    public void setMinor1(String minor1) {
        this.minor1 = minor1;
    }

    public void setMinor2(String minor2) {
        this.minor2 = minor2;
        this.doubleMinor = true;
    }

    public void setDoubleMajor(Boolean doubleMajor) {
        this.doubleMajor = doubleMajor;
    }

    public void setDoubleMinor(Boolean doubleMinor) {
        this.doubleMinor = doubleMinor;
    }

    public void setAcademicHistory(History academicHistory) {
        this.academicHistory = academicHistory;
    }
}
