package edu.swarthmore.cs71.starfruit.classes.course;

public class CourseCode{
    private String department;
    private Integer courseNumber;

    public CourseCode(String department, Integer courseNumber) {
        this.department = department;
        this.courseNumber = courseNumber;
    }

    public String getStringDepartment() {
        return department;
    }

    public Integer getCourseNumber() {
        return courseNumber;
    }
    public String getStringCourseCode(){
        String stringCourseCode = department + Integer.toString(courseNumber);
        return stringCourseCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CourseCode that = (CourseCode) o;

        if (!department.equals(that.department)) return false;
        return courseNumber.equals(that.courseNumber);
    }

    @Override
    public int hashCode() {
        int result = department.hashCode();
        result = 31 * result + courseNumber.hashCode();
        return result;
    }

    public String toString(){
        return getStringCourseCode();
    }
}
