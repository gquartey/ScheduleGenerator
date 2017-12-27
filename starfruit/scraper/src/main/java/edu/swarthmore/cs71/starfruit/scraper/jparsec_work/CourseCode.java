package edu.swarthmore.cs71.starfruit.scraper.jparsec_work;

public class CourseCode {
    private DeptName deptName;
    private CourseNumber courseCode;

    public CourseCode(DeptName deptName, CourseNumber courseCode) {
        this.deptName = deptName;
        this.courseCode = courseCode;
    }

    public DeptName getDeptName() {
        return deptName;
    }

    public CourseNumber getCourseCode() {
        return courseCode;
    }
}
