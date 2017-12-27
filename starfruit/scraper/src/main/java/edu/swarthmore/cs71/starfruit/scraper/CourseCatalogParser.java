package edu.swarthmore.cs71.starfruit.scraper;


//import edu.swarthmore.cs71.starfruit.classes.course.Course;
//import edu.swarthmore.cs71.starfruit.classes.departments.Department;
//import org.junit.internal.Classes;

import edu.swarthmore.cs71.starfruit.classes.course.Course;
import edu.swarthmore.cs71.starfruit.classes.departments.Department;
import edu.swarthmore.cs71.starfruit.classes.DeptLib;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;

/*This is our course catalog parser
* This parser receives a string that represents a course catalog
* and creates course objects
* This parser works uniquely for strings produced from the Swarthmore
* College course catalog
* */
public class CourseCatalogParser {
    private String text;
    public CourseCatalogParser(String initial_string) {
        this.text = initial_string;
    }

    
    private ArrayList<String> getCourseCode(char[] chars){
        ArrayList<String> returnList = new ArrayList<>();
        if(chars.length == 0){
            return returnList ;
        }else{
            String Course_code = new String();
            for(int i = 0; i < chars.length; i++){
                if(Character.isDigit(chars[i])){
                    Course_code = Course_code + chars[i];
                }else{
                    Course_code = "";
                }if(Course_code.length() == 5){
                    returnList.add(Course_code);
                    char[] EndOfArray = Arrays.copyOfRange(chars,i+1,chars.length);
                    returnList.addAll(getDepartment(EndOfArray));
                    return returnList;

                }
            }
        }
        return returnList;
    }
    /* this contains the logic to get the department for course
    *  once it finds a department then it will call the getCourseName
    *  method, unless it matches the criteria such as SGM that means
    *  that it shouldn't be in the list of courses which then mean it will
    *  call the getCourseCode method */
    private ArrayList<String> getDepartment(char[] chars){
        ArrayList<String> returnList = new ArrayList<>();
        if(chars.length == 0){
            return returnList;
        }else{
            int courseNumberCheck = 0;
            String courseDepartment = "";
            for(int i = 0; i < chars.length; i++){
                if(Character.isDigit(chars[i])){
                    courseNumberCheck ++;
                    courseDepartment += chars[i];
                }else{
                    courseDepartment += chars[i];
                }
                if(courseDepartment.contains("SGM")){
                    returnList.add("DELETE");
                    char[] endOfArray = Arrays.copyOfRange(chars,i,chars.length);
                    returnList.addAll(getCourseCode(endOfArray));
                    return returnList;
                }
                if(courseNumberCheck == 3){
                    returnList.add(courseDepartment);
                    if(i + 2 < chars.length) {
                        char[] endOfArray = Arrays.copyOfRange(chars, i + 2, chars.length);
                        returnList.addAll(getCourseName(endOfArray));
                    }
                    return returnList;
                }
            }
        }
        return returnList;
    }
    /* This will iterate through the character list until
    *  it has the complete courseName, then it will call the
    *  getCourseCode method and look for the next class
    *  */
    private ArrayList<String> getCourseName(char[] chars){
        ArrayList<String> returnList = new ArrayList<>();
        if(chars.length == 0 ){
            return returnList;
        }else{
            String CourseName = "";
            for(int i = 0; i < chars.length; i++){
                if(Character.isDigit(chars[i]) && i > 3){
                    returnList.add(CourseName);
                    char[] endOfArray = Arrays.copyOfRange(chars,i+1,chars.length);
                    returnList.addAll(getCourseCode(endOfArray));
                    return returnList;
                }else{
                    CourseName += chars[i];
                }
            }
        }
        return returnList;
    }
    private ArrayList<String> cleanStringList(ArrayList<String> dirtyList){
        if(dirtyList.size() == 0){
            return dirtyList;
        }
        for(int i = 0; i < dirtyList.size(); i++){
            dirtyList.set(i, dirtyList.get(i).trim());
        }
        ArrayList<String> cleanedList = new ArrayList<>();
        for(int i = 0; i < dirtyList.size() -1; i++){
            if(!dirtyList.get(i).equals("DELETE") && !dirtyList.get(i + 1).equals("DELETE")){
                cleanedList.add(dirtyList.get(i));
            }
        }
        if(!dirtyList.get(dirtyList.size()-1).equals("DELETE") && !cleanedList.contains(dirtyList.get(dirtyList.size()-1))){
            cleanedList.add(dirtyList.get(dirtyList.size()-1));
        }
        return cleanedList;
    }

    // this produces strings that can be used to make courses from the character list
    // that is received from the pdf
    public ArrayList<String> produceBasicStrings(){
        char[] CharList = this.text.toCharArray();
        ArrayList<String> basicStrings = getCourseCode(CharList);
        basicStrings = cleanStringList(basicStrings);
        return basicStrings;
    }
    // produces a list of classes
    public ArrayList<Course> produceCourses(ArrayList<String> list){
        ArrayList<Course> ClassList = new ArrayList<>();
        ArrayList<String> StringList = list;
        DeptLib DEPT_TRANSLATOR = DeptLib.getInstance();
        if(StringList.size()%3 != 0){
            throw new RuntimeException("InvalidParsedList");
        }
        for(int i = 0; i < StringList.size(); i = i + 3){
            String classNum = StringList.get(i+2);
            //ClassList.add(new Course(Class,Department));
            String departmentAndCourse = StringList.get(i+1).replaceAll("\\s","");
            Department department = DEPT_TRANSLATOR.getCourseMapping(departmentAndCourse.substring(0,4));
            String courseCode = departmentAndCourse.substring(4);
            if(StringList.get(i+2).contains("Lab")) {
                Course course = new Course(classNum,department,Integer.valueOf(courseCode), true,false, false,null);
                course.setCredits(0);
                ClassList.add(course);
            }else if(StringList.get(i+2).contains("(W)")){
                ClassList.add(new Course(classNum, department, Integer.valueOf(courseCode),false,true,false,null));
            }else if(department.equals("PHED")){
                ClassList.add(new Course(classNum, department,Integer.valueOf(courseCode),false, false,true,null));
            }else{
                ClassList.add(new Course(classNum, department,Integer.valueOf(courseCode),false, false,false,null));
            }

        }
        return ClassList;
    }

    public ArrayList<Course> setPreReqs(ArrayList<Course> courseList, HashMap<String,Collection<String>> preReqs){
        for(Course i: courseList){
            if(preReqs.containsKey(i.getDepartment() +i.getCourseName())){
                i.setPreReqs(preReqs.get(preReqs.containsKey(i.getDepartment()+i.getCourseName())));
            }
        }
        return courseList;
    }
}
