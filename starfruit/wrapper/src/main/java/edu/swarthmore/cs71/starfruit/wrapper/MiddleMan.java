package edu.swarthmore.cs71.starfruit.wrapper;

import edu.swarthmore.cs71.starfruit.classes.*;
import edu.swarthmore.cs71.starfruit.classes.course.Course;
import edu.swarthmore.cs71.starfruit.classes.departments.Department;
import edu.swarthmore.cs71.starfruit.scraper.CourseCatalogParserImpl;
import edu.swarthmore.cs71.starfruit.scraper.TranscriptParserImpl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class MiddleMan {

    private User student;
    private UserData studentData;
    private ScheduleGeneratorModel generator;
    public MiddleMan(Questionnaire data) throws IOException {
        //construct studentData with data, make user
        studentData = new UserData();
        System.out.println("NOT FAILED YET1");

        History history = new History(new ArrayList<>());
        //TODO: make sure the middleman gets a course catalog file to be parsed
        //HashMap<Department,Collection<Course>> semCat = new SemesterCatalog(new CourseCatalogParserImpl(catalog)).getAllSemesterCourses();
        System.out.println("NOT FAILED YET2");

        studentData.setAcademicHistory(history);
        System.out.println("NOT FAILED YET3");

        studentData.setAcademicInterests(data.getAcademicInterests());
        studentData.setClassYear(data.getClassYear());
//        studentData.setDoubleMajor(data.getDoubleMajor());
//        studentData.setDoubleMinor(data.getDoubleMinor());
        studentData.setMajor1(data.getMajor1());
        System.out.println("NOT FAILED YET4");

//        if(studentData.getDoubleMajor()){
//            studentData.setMajor2(data.getMajor2());
//        }
//        else if(!data.getMinor1().equals(null)){
//            studentData.setMinor1(data.getMinor1());
//        }
//        if(data.getDoubleMinor()){
//            studentData.setMinor2(data.getMinor2());
//        }
        System.out.println("NOT FAILED YET 4.5 ");

        //TODO: make sure the middleman gets a course catalog file to be parsed
        SemesterCatalog thatBigCatalogSpring = catalogMakerSpring();
        HashMap<Department, Collection<Course>> allThemCourses = thatBigCatalogSpring.getAllSemesterCourses();
        SemesterCatalog thatBigCatalogFall = catalogMakerFall();
        HashMap<Department, Collection<Course>> allThemCourses2 = thatBigCatalogFall.getAllSemesterCourses();
        System.out.println("NOT FAILED YET 5");


        generator = new ScheduleGeneratorModel(studentData, allThemCourses, allThemCourses2);
        System.out.println("Not failed yet 6");
    }
    public Collection<SemesterSchedule> getSchedule(){
        return generator.getFullPlan();
    }

    public SemesterCatalog catalogMakerSpring() throws IOException {

//        ExcelParser allCourse = new ExcelParser(new File("/home/ywang7/cd71/group-starfruit/starfruit/scraper/201802CSV.xlsx"));
//        Collection<Course> courses = allCourse.ProduceCourses();
//        SemesterCatalog semesterCatalog = new SemesterCatalog(courses);
        CourseCatalogParserImpl allCourse = new CourseCatalogParserImpl(new File("/Users/jonahTM/cs71/group-starfruit/starfruit/scraper/course_schedule_spring.pdf"));
        Collection<Course> courses = allCourse.parsePDF();
        SemesterCatalog semesterCatalog = new SemesterCatalog(courses);

        return semesterCatalog;
    }

    public SemesterCatalog catalogMakerFall() throws IOException {

//        ExcelParser allCourse = new ExcelParser(new File("/home/ywang7/cd71/group-starfruit/starfruit/scraper/201704CSV-1.xlsx"));
//        Collection<Course> courses = allCourse.ProduceCourses();
//        SemesterCatalog semesterCatalog = new SemesterCatalog(courses);
        CourseCatalogParserImpl allCourse = new CourseCatalogParserImpl(new File("/Users/jonahTM/cs71/group-starfruit/starfruit/scraper/schedule_course_fall.pdf"));
        Collection<Course> courses = allCourse.parsePDF();

        SemesterCatalog semesterCatalog = new SemesterCatalog(courses);

        return semesterCatalog;
    }

}
