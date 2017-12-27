package edu.swarthmore.cs71.starfruit.classes;

import edu.swarthmore.cs71.starfruit.classes.course.Course;
import edu.swarthmore.cs71.starfruit.classes.departments.Department;
import edu.swarthmore.cs71.starfruit.classes.requirements.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class User {
    private float allCredits;
    private Integer gradYear;

    private HashMap<Department,Collection<Course>> catalog;
    private Collection<Department> studentPref;
    private History history;

    private Collection<Major> majors;
    private Major major1;
    private Major major2;
    private Minor minor1;
    private Minor minor2;
    private Collection<Minor> minors;

    private Collection<GradRequirement> reqs;

    //TODO: Set all info according to UserData (assumed to be completed)

    public User(UserData userData) {
        this.studentPref = userData.getAcademicInterests();
        this.allCredits = 0;

        DeptLib deptLib = DeptLib.getInstance();


        this.gradYear = userData.getClassYear();



        // initialize majors & minors
        this.majors = new ArrayList<Major>();
        this.minors = new ArrayList<Minor>();


        //have to have
        this.major1 = new Major(deptLib.getCourseMapping(userData.getMajor1()));
        this.majors.add(this.major1);

        // optional double
        if(userData.getDoubleMajor()){
            Department dept = deptLib.getCourseMapping(userData.getMajor2());
            this.major2 = new Major(dept);
            this.majors.add(this.major2);
        }
        else{
            // if not a double major you can minor
            if(userData.getMinor1() != null){
                //if first minor not null
                Department dept = deptLib.getCourseMapping(userData.getMinor1());
                this.minor1 = new Minor(dept);
                this.minors.add(this.minor1);
            }
            // add the second if your allowed
            if(userData.getDoubleMinor()){
                Department dept = deptLib.getCourseMapping(userData.getMinor2());
                this.minor2 = new Minor(dept);
                this.minors.add(this.minor2);
            }
        }

        //assign history from user data
        this.history = userData.getAcademicHistory();

        this.allCredits = this.history.getCredits();


        // Requirements list
        this.reqs = new ArrayList<GradRequirement>();
        this.reqs.add(new Humanity(this.history));
        this.reqs.add(new PE(this.history));
        this.reqs.add(new NaturalScience(this.history));
        this.reqs.add(new SocialScience(this.history));
        this.reqs.add(new Writing(this.history));

    }

    //TODO: Maybe need to think about history updates

    public SemesterSchedule generateSemester(){
        return null;
    }

    public void addCredits(int credits){
        this.allCredits += credits;
    }

    public int getGradYear() {
        return gradYear;
    }


    public float getAllCredits() {
        return allCredits;
    }

    public Collection<Department> getStudentPref() {
        return studentPref;
    }

    public Major getPrimeMajor() {
        return major1;
    }

    public Major getSecondMajor() {
        return major2;
    }

    public Minor getMinor1() {
        return minor1;
    }

    public Minor getMinor2() {
        return minor2;
    }

    //TODO: NOt sure if we need these
    public Collection<Major> getMajors() {
        return majors;
    }

    public Collection<Minor> getMinors() {
        return minors;
    }


    public void updateHistory(Collection<Course> newCourses){
        //ADD a bunch of courses to history . called by schedule generator model
        for(Course c: newCourses){
            this.history.addToHistory(c);
        }
    }

    public History getHistory() {
        return history;
    }

    // TODO: update how to find which requirements are yet to complete

    public boolean isGradReqsCompleted(){

        for(GradRequirement d: reqs){
            d.updateHistory(this.history);
            if(d.isCompleted() == false){
                return false;
            }
        }
        return true;
    }

    public Collection<GradRequirement> getReqsRemaining(){
        Collection<GradRequirement> gradReqs = new ArrayList<GradRequirement>();
        for(GradRequirement d: reqs){
            d.updateHistory(this.history);
            if(d.isCompleted()== false){
                gradReqs.add(d);
            }
        }
        return gradReqs;
    }

    public int getWritingLeft() {
       return 3 - this.history.getWrite();
    }

    public int getPELeft() {
        return 4 - this.history.getWrite();
    }

    public int getSSLeft() {
        return 3 - this.history.getWrite();
    }

    public int getNSLeft() {
        return 3 - this.history.getWrite();
    }

    public int getHULeft() {
        return 3 - this.history.getWrite();
    }

    public int getNumReqsleft() {
        return getHULeft() + getNSLeft() + getWritingLeft() + getSSLeft();
    }

}
