package edu.swarthmore.cs71.starfruit.scraper;

import edu.swarthmore.cs71.starfruit.classes.course.Course;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

public class Main {
    public static void main(String[] args) throws IOException {
        File file = new File("/Users/gabrielquartey/SwatGithub/group-starfruit/starfruit/scraper/201802CSV.xlsx");
        ExcelParser parser = new ExcelParser(file);
        Collection<Course> allCourses = parser.ProduceCourses();
        System.out.println("Number of courses = "+ allCourses.size());


    }
}
