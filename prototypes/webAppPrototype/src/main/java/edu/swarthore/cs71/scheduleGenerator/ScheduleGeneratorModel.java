package edu.swarthore.cs71.scheduleGenerator;

import java.util.*;

import static java.lang.Math.max;

/*
* A dumb made up schedule generator model that makes decisions on graduation based
* off of class year and major.
* */
public class ScheduleGeneratorModel {
    private int classYear;
    private boolean canGraduate;
    private String major;
    private String minor;
    private int semestersLeft;
    private int currentYear = 2017;

    public ScheduleGeneratorModel(String classYear, String major, String minor){
        this.classYear = Integer.parseInt(classYear);
        this.major = major;
        this.minor = minor;
        semestersLeft = max(this.classYear-currentYear, 0) * 2;
        if(semestersLeft>0){
            canGraduate = true;
        }
        else{
            canGraduate = false;
        }
        if(this.major.equals("Engineering") && this.semestersLeft<6){
            canGraduate = false;
        }
    }

    public String getSemestersLeft(){
        return String.valueOf(this.semestersLeft);
    }

    public boolean getIfCanGraduate(){
        return canGraduate;
    }
}
