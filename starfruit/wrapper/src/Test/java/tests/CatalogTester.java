package tests;

import edu.swarthmore.cs71.starfruit.classes.*;
import edu.swarthmore.cs71.starfruit.classes.course.Course;
import edu.swarthmore.cs71.starfruit.classes.departments.Department;
import edu.swarthmore.cs71.starfruit.scraper.CourseCatalogParserImpl;
import edu.swarthmore.cs71.starfruit.wrapper.SemesterCatalog;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class CatalogTester {

    public SemesterCatalog catalogMakerSpring() throws IOException {

//        ExcelParser allCourse = new ExcelParser(new File("/home/ywang7/cd71/group-starfruit/starfruit/scraper/201802CSV.xlsx"));
//        Collection<Course> courses = allCourse.ProduceCourses();
//        SemesterCatalog semesterCatalog = new SemesterCatalog(courses);
        CourseCatalogParserImpl allCourse = new CourseCatalogParserImpl(new File("/Users/gabrielquartey/SwatGithub/group-starfruit/starfruit/scraper/course_schedule_spring.pdf"));
        Collection<Course> courses = allCourse.parsePDF();
        SemesterCatalog semesterCatalog = new SemesterCatalog(courses);

        return semesterCatalog;
    }

    public SemesterCatalog catalogMakerFall() throws IOException {

//        ExcelParser allCourse = new ExcelParser(new File("/home/ywang7/cd71/group-starfruit/starfruit/scraper/201704CSV-1.xlsx"));
//        Collection<Course> courses = allCourse.ProduceCourses();
//        SemesterCatalog semesterCatalog = new SemesterCatalog(courses);
        CourseCatalogParserImpl allCourse = new CourseCatalogParserImpl(new File("/Users/gabrielquartey/SwatGithub/group-starfruit/starfruit/scraper/schedule_course_fall.pdf"));
        Collection<Course> courses = allCourse.parsePDF();

        SemesterCatalog semesterCatalog = new SemesterCatalog(courses);

        return semesterCatalog;
    }


 //   @Test

//    public void catalogMakerTester() throws IOException {
//        //SemesterCatalog semesterCatalog = new SemesterCatalog();
//        File file = new File("course_schedule_spring.pdf");
//        CourseCatalogParserImpl parser = new CourseCatalogParserImpl(file);
//        SemesterCatalog semesterCatalog = new SemesterCatalog(parser.parsePDF());
//
//    }

    @Test

    public void scheduleGeneratorTests() throws IOException{
        //construct studentData with data, make user

        UserData studentData = new UserData();

        //History history = new History(new ArrayList<>());

        //studentData.setAcademicHistory(history);
        DeptLib deptLib = DeptLib.getInstance();

        Collection<Department> interests = new ArrayList<>();
        interests.add(deptLib.getCourseMapping("SPAN"));
       // interests.add(deptLib.getCourseMapping("ENGL"));
        studentData.setAcademicInterests(interests);
        studentData.setClassYear(2021);
        studentData.setMajor1("ECON");
        studentData.setMajor2("CHEM");
       // studentData.setMajor1("CHEM");

        //studentData.setMinor1("");


        //TODO: make sure the middleman gets a course catalog file to be parsed
        SemesterCatalog thatBigCatalogSpring = catalogMakerSpring();
        HashMap<Department, Collection<Course>> allThemCourses = thatBigCatalogSpring.getAllSemesterCourses();
        SemesterCatalog thatBigCatalogFall = catalogMakerFall();
        HashMap<Department, Collection<Course>> allThemCourses2 = thatBigCatalogFall.getAllSemesterCourses();


        ScheduleGeneratorModel generator = new ScheduleGeneratorModel(studentData, allThemCourses, allThemCourses2);


        Collection<SemesterSchedule> plan = generator.getFullPlan();
        for(SemesterSchedule semSched: plan){
            for (ScheduledCourse j : semSched.getSchedule()) {
                System.out.println(j.getCourseName());
            }
            System.out.println("***************New Semester*******************");
        }

    }

}
