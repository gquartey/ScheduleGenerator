package edu.swarthmore.cs71.starfruit.scraper;

import edu.swarthmore.cs71.starfruit.classes.course.Course;
import edu.swarthmore.cs71.starfruit.classes.DeptLib;

import java.util.ArrayList;

public class CourseToken {
    private String dept;
    private String courseNum;
    private String courseName;
    private String credits;
    private String grade;
    private Boolean complete;
    private String courseTime;
    private String courseDays;

    public CourseToken() {
        this.dept = null;
        this.courseNum = null;
        this.credits = null;
        this.grade = null;
        this.courseName = new String();
        this.complete = false;
        this.courseTime = null;
        this.courseDays = null;
    }

    public String getDept() {
        return dept;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getCourseNum() {
        return courseNum;
    }

    // adds a token to the correct field, handles all of he organization
    public void addToken(BuilderToken token) {
        if (token != null) {
            if(token.returnType().equals(TokenType.Time)) {
                this.courseTime = token.returnString();
            }
            if(token.returnType().equals(TokenType.MeetingDays)){
                this.courseDays = token.returnString();
                this.complete = true;
            }
            if (token.returnType().equals(TokenType.DEPT)) {
                this.dept = token.returnString();

            }
            if (token.returnType().equals(TokenType.CourseNumber)) {
                String check = token.returnString();
                if(check.length() > 3){
                    check = check.substring(0,2);
                }
                this.courseNum = check;
            }
            if (token.returnType().equals(TokenType.Credits)) {
                this.credits = token.returnString();
            }
            if (token.returnType().equals(TokenType.Grade)) {
                this.grade = token.returnString();
                this.complete = true;

            }
            if (token.returnType().equals(TokenType.NameComponent)) {
                this.courseName += token.returnString() + " ";

            }
        }
    }
    // returns the course object equivalent of the courestoken

    public Course convertToCourse() {
        DeptLib deptLib = DeptLib.getInstance();
        try {
            if (!this.complete) {
                System.out.println("invalid course and returning null within convert to course method");
                return null;
            }
            if (dept.contains("PHED")) {

                Course course = new Course(courseName.trim(), deptLib.getCourseMapping(dept), Integer.parseInt(courseNum.substring(0, 2)), false, false, true, new ArrayList<>());
                course.setCredits(Float.valueOf(this.credits));
                course = setData(course,this);
                return course;
            }
            if (courseName.contains("(W)")) {

                Course course = new Course(courseName.trim(), deptLib.getCourseMapping(dept), Integer.parseInt(courseNum.substring(0, 2)), false, true, false,new ArrayList<>());
                course.setCredits(Float.valueOf(this.credits));
                course = setData(course,this);
                return course;
            }
           if (courseName.contains("Lab")){
               Course course = new Course(courseName.trim(), deptLib.getCourseMapping(dept), Integer.parseInt(courseNum.substring(0, 2)), true, false, false,new ArrayList<>());
               course.setCredits(Float.valueOf(this.credits));
               course = setData(course,this);
               return course;
           }
            Course course =  new Course(courseName.trim(), deptLib.getCourseMapping(dept), Integer.parseInt(courseNum.substring(0, 2)), false, false, false, new ArrayList<>());

            course.setCredits(Float.valueOf(this.credits));
            course = setData(course,this);
            return course;
        }catch(NumberFormatException e){
            System.out.println("The parser is mismatching something " + this.courseName);
            return null;
        }catch(StringIndexOutOfBoundsException e){
            System.out.println("The parser is doing some shenanigans with strings");
            return null;
        }
    }
    // tells us if all of the field within the course object have been filled
    // ensures we have the neccesary information to make a course
    public Boolean getComplete() {
        return this.complete;
    }
    public Course setData(Course course, CourseToken token){
        if(token.courseTime != null){
            course.setCourseTime(this.courseTime);
        }
        if(token.courseDays != null){
            course.setDays(this.courseDays);
        }
        return course;
    }
}
