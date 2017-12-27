package edu.swarthmore.cs71.starfruit.classes;

import edu.swarthmore.cs71.starfruit.classes.course.Course;
import edu.swarthmore.cs71.starfruit.classes.departments.Department;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class History {
    private Collection<Course> courseTaken;

    private int HU;
    private int Write;
    private int Pe;
    private int SS;
    private int NS;
    private float creds;


    //TODO: now history has a dictionary of classes taken by Dep and courseCode
    private HashMap<Department,Collection<Course>> reqCheck;


    public History(Collection<Course> courseTaken) {
        this.courseTaken = courseTaken;
        this.HU = 0;
        this.Write = 0;
        this.Pe = 0;
        this.SS = 0;
        this.NS = 0;
        this.creds = 0;

        this.reqCheck = new HashMap<>();

        for(Course c:courseTaken){

            if(!reqCheck.containsKey(c.getDepartment())){
                Collection<Course> taken = new ArrayList<>();
                taken.add(c);
                reqCheck.put(c.getDepartment(),taken);
            }
            else{
                Collection<Course> taken = reqCheck.get(c.getDepartment());
                taken.add(c);
                reqCheck.put(c.getDepartment(),taken);
            }

            this.creds += c.getCredits();

            if(c.isWritingCourse()) {
                this.Write++;
            }

            if(c.isPECourse()){
                this.Pe++;
            }

            if(c.isHUCourse()){
                this.HU++;
            }
            if(c.isSSCourse()){
                this.SS++;
            }
            if(c.isNSCourse()){
                this.NS++;
            }

        }
    }


    //may not need since we have HashMap now

    public Collection<Course> getCourseTaken() {
        return courseTaken;
    }

    public float getCredits(){
        return creds;
    }

    public void addToHistory(Course c) {

        if(!reqCheck.containsKey(c.getDepartment())){
            Collection<Course> taken = new ArrayList<>();
            taken.add(c);
            reqCheck.put(c.getDepartment(),taken);
        }
        else{
            Collection<Course> taken = reqCheck.get(c.getDepartment());
            taken.add(c);
            reqCheck.put(c.getDepartment(),taken);
        }

        if (!this.courseTaken.contains(c)) {
            this.courseTaken.add(c);
            if(c.isWritingCourse()) {
                this.Write++;
            }

            if(c.isPECourse()){
                this.Pe++;
            }

            if(c.isHUCourse()){
                this.HU++;
            }
            if(c.isSSCourse()){
                this.SS++;
            }
            if(c.isNSCourse()){
                this.NS++;
            }

        }

    }

    public int getHU() {
        return HU;
    }

    public int getWrite() {
        return Write;
    }

    public int getPe() {
        return Pe;
    }

    public int getSS() {
        return SS;
    }

    public int getNS() {
        return NS;
    }

    public HashMap<Department, Collection<Course>> getReqCheck() {
        return reqCheck;
    }
}

