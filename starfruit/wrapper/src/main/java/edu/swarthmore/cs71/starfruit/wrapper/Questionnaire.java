package edu.swarthmore.cs71.starfruit.wrapper;

import edu.swarthmore.cs71.starfruit.classes.departments.Department;

import java.io.File;
import java.util.Collection;

public class Questionnaire {
    private String major1;
    private String major2;
    private String minor1;
    private String minor2;
    private String abroadSemester;
    private Boolean goingAbroad;
    private Integer classYear;
    private Boolean doubleMajor;
    private Boolean doubleMinor;
    //Would it be possible to make academic Interests a list of departments?
    //Make like a checklist in the questionnnaire for the user to fill out, the ones that are checked get put into the collection of interests
    private Collection<Department> academicInterests;
    private Collection academicHistory;
    private File transcript;


    public Questionnaire() {

    }

    public Boolean isLegal(){
        if(academicHistory == null || academicInterests == null || classYear == null){
            return false;
        }
        else if(doubleMinor && doubleMajor){
            return false;
        }
        else if(doubleMajor){
            if(major1 == null || major2 == null || minor1 != null){
                return false;
            }
        }
        else if(doubleMinor){
            if(minor1 == null || minor2 == null){
                return false;
            }
        }
        else if(transcript.equals(null)){
            return false;
        }
        else if(goingAbroad && abroadSemester.equals(null)){
            return false;
        }
        else if(!abroadSemester.equals(null) && !goingAbroad){
            return false;
        }
        return true;
    }

    public void setAbroadSemester(String abroadSemester) {
        this.abroadSemester = abroadSemester;
    }

    public void setGoingAbroad(Boolean goingAbroad) {
        this.goingAbroad = goingAbroad;
    }

    public String getAbroadSemester() {
        return abroadSemester;
    }

    public Boolean getGoingAbroad() {
        return goingAbroad;
    }

    public void setMajor1(String major1) {
        this.major1 = major1;
    }

    public void setMajor2(String major2) {
        this.major2 = major2;
    }

    public void setMinor1(String minor1) {
        this.minor1 = minor1;
    }

    public void setMinor2(String minor2) {
        this.minor2 = minor2;
    }

    public void setClassYear(Integer classYear) {
        this.classYear = classYear;
    }

    public void setDoubleMajor(Boolean doubleMajor) {
        this.doubleMajor = doubleMajor;
    }

    public void setDoubleMinor(Boolean doubleMinor) {
        this.doubleMinor = doubleMinor;
    }

    public void setAcademicInterests(Collection<Department> academicInterests) {
        this.academicInterests = academicInterests;
    }

    public void setAcademicHistory(Collection academicHistory) {
        this.academicHistory = academicHistory;
    }

    public void setTranscript(File transcript) {
        this.transcript = transcript;
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

    public Integer getClassYear() {
        return classYear;
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

    public Collection getAcademicHistory() {
        return academicHistory;
    }

    public File getTranscript() {
        return transcript;
    }
}
