package tests;

import edu.swarthmore.cs71.starfruit.classes.*;
import edu.swarthmore.cs71.starfruit.classes.course.Course;
import edu.swarthmore.cs71.starfruit.scraper.CourseCatalogParser;
import org.junit.Assert;
import org.junit.Test;


import java.util.ArrayList;

public class ConfirmParserFunctions {

    @Test
    public void testEmpty() {
        ArrayList<String> ExpectedOutput = new ArrayList<>();
        CourseCatalogParser parser = new CourseCatalogParser("");
        ArrayList<String> ActualOutput = parser.produceBasicStrings();
        Assert.assertEquals(ExpectedOutput, ActualOutput);
    }

    @Test
    public void testInvalid() {
        ArrayList<String> ExpectedOutput = new ArrayList<>();
        CourseCatalogParser parser = new CourseCatalogParser("This is is an invalid string");
        ArrayList<String> ActualOutput = parser.produceBasicStrings();
        Assert.assertEquals(ExpectedOutput, ActualOutput);
    }

    @Test
    public void testInvalid2() {
        ArrayList<String> ExpectedOutput = new ArrayList<>();
        CourseCatalogParser parser = new CourseCatalogParser(" 4   5    6    1   ");
        ArrayList<String> ActualOutput = parser.produceBasicStrings();
        Assert.assertEquals(ExpectedOutput, ActualOutput);
    }

    @Test
    public void InvalidCourseCode() {
        ArrayList<String> ExpectedOutput = new ArrayList<>();
        CourseCatalogParser parser = new CourseCatalogParser(" 4   5    6    1   2");
        ArrayList<String> ActualOutput = parser.produceBasicStrings();
        Assert.assertEquals(ExpectedOutput, ActualOutput);
    }

    @Test
    public void ValidCourseCode() {
        ArrayList<String> ExpectedOutput = new ArrayList<>();
        ExpectedOutput.add("45612");
        CourseCatalogParser parser = new CourseCatalogParser(" 45612");
        ArrayList<String> ActualOutput = parser.produceBasicStrings();
        Assert.assertEquals(ExpectedOutput, ActualOutput);
    }

    @Test
    public void InvalidDept() {
        ArrayList<String> ExpectedOutput = new ArrayList<>();
        ExpectedOutput.add("45612");
        CourseCatalogParser parser = new CourseCatalogParser(" 45612 BIOL99");
        ArrayList<String> ActualOutput = parser.produceBasicStrings();
        Assert.assertEquals(ExpectedOutput, ActualOutput);
    }

    @Test
    public void ValidDept() {
        ArrayList<String> ExpectedOutput = new ArrayList<>();
        ExpectedOutput.add("45612");
        ExpectedOutput.add("BIOL099");

        CourseCatalogParser parser = new CourseCatalogParser(" 45612 BIOL099");
        ArrayList<String> ActualOutput = parser.produceBasicStrings();
        Assert.assertEquals(ExpectedOutput, ActualOutput);
    }

    @Test
    public void InvalidOrder() {
        ArrayList<String> ExpectedOutput = new ArrayList<>();
        ExpectedOutput.add("45612");
        CourseCatalogParser parser = new CourseCatalogParser("BIOL099  45612 ");
        ArrayList<String> ActualOutput = parser.produceBasicStrings();
        Assert.assertEquals(ExpectedOutput, ActualOutput);
    }

    @Test
    public void ValidCourseName() {
        ArrayList<String> ExpectedOutput = new ArrayList<>();
        ExpectedOutput.add("45612");
        ExpectedOutput.add("BIOL099");
        ExpectedOutput.add("this is for testing purposes only");
        CourseCatalogParser parser = new CourseCatalogParser(" 45612 BIOL099  this is for testing purposes only 1");
        ArrayList<String> ActualOutput = parser.produceBasicStrings();
        Assert.assertEquals(ExpectedOutput, ActualOutput);
    }

    @Test
    public void EraseSGMSections(){
        ArrayList<String> ExpectedOutput = new ArrayList<>();
        CourseCatalogParser parser = new CourseCatalogParser(" 45612 BIOLSGM  this is for testing purposes only 1");
        ArrayList<String> ActualOutput = parser.produceBasicStrings();
        Assert.assertEquals(ExpectedOutput, ActualOutput);
    }

    @Test
    public void ValidCourseCreate(){
        DeptLib deptlib = DeptLib.getInstance();
        String test_string = "45612 BIOL099  Testing 1";
        ArrayList<Course> ExpectedOutput = new ArrayList<>();
        ExpectedOutput.add(new Course("Testing", deptlib.getCourseMapping("BIOL"),99,false,false,false, new ArrayList<>()));
        CourseCatalogParser parser = new CourseCatalogParser(test_string);
        ArrayList<Course> ActualOutput = parser.produceCourses(parser.produceBasicStrings());
        Assert.assertEquals(ExpectedOutput.get(0).getCourseName(), ActualOutput.get(0).getCourseName());
        Assert.assertEquals(ExpectedOutput.get(0).isWritingCourse(), ActualOutput.get(0).isWritingCourse());
//        Assert.assertEquals(ExpectedOutput.get(0).getCredits(), ActualOutput.get(0).getCredits());
    }

    @Test
    public void InValidCourseCreate() throws Exception {
        String test_string = "BIOL099  Testing 1";
        ArrayList<Course> ExpectedOutput = new ArrayList<>();
        CourseCatalogParser parser = new CourseCatalogParser(test_string);
        ArrayList<Course> ActualOutput = parser.produceCourses(parser.produceBasicStrings());
        Assert.assertEquals(ExpectedOutput, ActualOutput);
    }
    @Test
    public void InValidCourseCreate2() throws Exception {
        boolean thrown = false;
        String test_string = "BIOL099  Testing 45612 1";
        try {
            CourseCatalogParser parser = new CourseCatalogParser(test_string);
            ArrayList<Course> ActualOutput = parser.produceCourses(parser.produceBasicStrings());
        } catch (RuntimeException e) {
            thrown = true;
        }
        Assert.assertTrue(thrown);
    }



}
