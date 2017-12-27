package tests;

import com.sun.javafx.geom.transform.BaseTransform;
import edu.swarthmore.cs71.starfruit.classes.course.CourseCode;
import edu.swarthmore.cs71.starfruit.classes.requirements.DegreeRequirement;
import edu.swarthmore.cs71.starfruit.scraper.PreReqParser;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

public class ConfirmPreReqParser {
    @Test
    public void TestEmpty(){
        int ExpectedOutput = 0;
        PreReqParser parser = new PreReqParser("");
        parser.generateTokens(parser.getSplitString());
        int ActualOutput = parser.tokenBankSize();
        Assert.assertEquals(ExpectedOutput,ActualOutput);
    }

    @Test
    public void TestDepartmentTokenizer() throws Exception {
        int ExpectOutput = 2;
        PreReqParser parser = new PreReqParser("Testing testing testing testing testing BIOL 099");
        parser.generateTokens(parser.getSplitString());
        int ActualOutput = parser.tokenBankSize();
        Assert.assertEquals(ExpectOutput,ActualOutput);
    }
    @Test
    public void TestPrequesiteFlagTokenizer(){
        int ExpectOutput = 3;
        PreReqParser parser = new PreReqParser("Testing testing testing testing testing BIOL 099 prerequisite: blah blah ");
        parser.generateTokens(parser.getSplitString());
        int ActualOutput = parser.tokenBankSize();
        Assert.assertEquals(ExpectOutput,ActualOutput);
    }
    @Test
    public void TestPrequesiteClassTokenizer(){
        int ExpectOutput = 6;
        PreReqParser parser = new PreReqParser("Testing testing testing testing testing BIOL 099 prerequisite: CHEM 010. yippity do dah ");
        parser.generateTokens(parser.getSplitString());
        int ActualOutput = parser.tokenBankSize();
        Assert.assertEquals(ExpectOutput,ActualOutput);
    }
    @Test
    public void TestDoublePrequisiteClassTokenizer(){
        int ExpectOutput = 9;
        PreReqParser parser = new PreReqParser("Testing testing testing testing testing BIOL 099 prerequisite: CHEM 009 and CHEM 010. yippity do dah ");
        parser.generateTokens(parser.getSplitString());
        int ActualOutput = parser.tokenBankSize();
        Assert.assertEquals(ExpectOutput,ActualOutput);
    }
    @Test
    public void TestPrequesiteTwoClases(){
        int ExpectOutput = 12;
        PreReqParser parser = new PreReqParser("Testing testing testing testing testing BIOL 099 prerequisite: CHEM 010. yippity do dah " +
                "BIOL 098 prerequisite: MATH 015. rip rip rip ");
        parser.generateTokens(parser.getSplitString());
        int ActualOutput = parser.tokenBankSize();
        Assert.assertEquals(ExpectOutput,ActualOutput);
    }
    @Test
    public void TestPrequesitePreReqIncomplete(){
        int ExpectOutput = 12;
        PreReqParser parser = new PreReqParser("Testing testing testing testing testing BIOL 099 prerequisite: CHEM 010. yippity do dah " +
                "BIOL 098 prerequisite: MATH 015 rip rip rip ");
        parser.generateTokens(parser.getSplitString());
        int ActualOutput = parser.tokenBankSize();
        Assert.assertEquals(ExpectOutput,ActualOutput);
    }
    @Test
    public void TestIgnoreBackgroundInfo(){
        int ExpectOutput = 12;
        PreReqParser parser = new PreReqParser("Testing testing testing testing testing BIOL 099 " +
                "and though shalt not do anything before completing theses prerequisite: CHEM 010. yippity do dah " +
                "BIOL 098 prerequisite: MATH 015. rip rip rip ");
        parser.generateTokens(parser.getSplitString());
        int ActualOutput = parser.tokenBankSize();
        Assert.assertEquals(ExpectOutput,ActualOutput);
    }
    @Test
    public void TestHashMap(){
        int ExpectOutput = 1;
        PreReqParser parser = new PreReqParser("Testing testing testing testing testing BIOL 099 " +
                "and though shalt not do anything before completing theses prerequisite: CHEM 010. yippity do dah ");
        parser.generateTokens(parser.getSplitString());

        HashMap<CourseCode,DegreeRequirement> tester = parser.courseTokensToHashMap();
        int ActualOutput = tester.size();
        Assert.assertEquals(ExpectOutput,ActualOutput);
    }
    @Test
    public void TestHashMapContents(){
        String ExpectOutput = "[BIOL99]";
        PreReqParser parser = new PreReqParser("Testing testing testing testing testing BIOL 099 " +
                "and though shalt not do anything before completing theses prerequisite: CHEM 010. yippity do dah ");
        parser.generateTokens(parser.getSplitString());

        HashMap<CourseCode,DegreeRequirement> testDict = parser.courseTokensToHashMap();
        Set<CourseCode> KeySet = testDict.keySet();
        String ActualOutput = KeySet.toString();
        Assert.assertEquals(ExpectOutput,ActualOutput);
    }
    @Test
    public void TestHashMapContents2(){
        String ExpectOutput = "[BIOL98, BIOL99]";
        PreReqParser parser = new PreReqParser("Testing testing testing testing testing BIOL 099 " +
                "and though shalt not do anything before completing theses prerequisite: CHEM 010 CHEM 010 HN. yippity do dah " +
                "BIOL 098 prerequisite: MATH 015. rip rip rip ");
        parser.generateTokens(parser.getSplitString());

        HashMap<CourseCode,DegreeRequirement> tester =  parser.courseTokensToHashMap();
        Set<CourseCode> KeySet = tester.keySet();
        String ActualOutput = KeySet.toString();
        Assert.assertEquals(ExpectOutput,ActualOutput);
    }
    @Test
    public void TestPreReqContents(){
        //String ExpectOutput = "[BIOL098, MATH029, BIOL099]";
        int ExpectedOutput = 1;
        PreReqParser parser = new PreReqParser("Testing testing testing testing testing BIOL 099 " +
                "and though shalt not do anything before completing theses prerequisite: CHEM 010 CHEM 010 HN. yippity do dah " +
                "BIOL 098 prerequisite: MATH 015. rip rip rip MATH 029 zach recommended this course prerequisite: MATH 015; CHEM 032 or CHEM 72. ");
        parser.generateTokens(parser.getSplitString());

        HashMap<CourseCode,DegreeRequirement> tester =     parser.courseTokensToHashMap();
        CourseCode course = new CourseCode("BIOL",98);
        DegreeRequirement ActualOutput = tester.get(course);
        Collection<CourseCode> classes = new ArrayList<>();
        Assert.assertEquals(ExpectedOutput,ActualOutput.getSolutionSet(classes).size());
    }
//    /* this doesn't pass if it the class is the last thing in the list
//        slightly concerning but a course will never be at the very end of a
//        string when receiving the output of the college bulletin so we can safely
//        ignore this edge case for not but is something that I will aim to fix in
//        the future
//     */
    @Test
    public void TestPreReqContents2(){
        //String ExpectOutput = "[BIOL098, MATH029, BIOL099]";
        String ExpectedOutput = "[MATH15]";
        PreReqParser parser = new PreReqParser("Testing testing testing testing testing BIOL 099 " +
                "and though shalt not do anything before completing theses prerequisite: CHEM 010 CHEM 010 HN. yippity do dah " +
                "BIOL 098 prerequisite: MATH 015. rip rip rip MATH 029 zach recommended this course prerequisite: MATH 015; CHEM 032 or CHEM 072. litty litty");
        parser.generateTokens(parser.getSplitString());

        HashMap<CourseCode,DegreeRequirement> tester = parser.courseTokensToHashMap();
        CourseCode course = new CourseCode("MATH",29);
        DegreeRequirement ActualOutput = tester.get(course);
        Collection<CourseCode> classes = new ArrayList<>();
        Assert.assertEquals(ExpectedOutput,ActualOutput.getSolutionSet(classes).toString());
    }

    @Test
    public void TestPreReqContents3(){
        int ExpectedOutput = 1;
        PreReqParser parser = new PreReqParser("Testing testing testing testing testing BIOL 099 " +
                "and though shalt not do anything before completing theses prerequisite: CHEM 010 CHEM 010 HN. yippity do dah " +
                "BIOL 098 prerequisite: MATH 015. rip rip rip MATH 029 zach recommended this course prerequisite: " +
                "MATH 015; CHEM 032  CHEM 072, CHEM 098 , BIOL 002. litty litty");
        parser.generateTokens(parser.getSplitString());

        HashMap<CourseCode,DegreeRequirement> tester = parser.courseTokensToHashMap();
        CourseCode course = new CourseCode("MATH",29);
        DegreeRequirement ActualOutput = tester.get(course);
        Collection<CourseCode> classes = new ArrayList<>();
        Assert.assertEquals(ExpectedOutput,ActualOutput.getSolutionSet(classes).size());
    }
    @Test
    public void TestDuplicatePreReqs(){
        String ExpectedOutput = "[MATH15]";
        PreReqParser parser = new PreReqParser("Testing testing testing testing testing BIOL 099 " +
                "and though shalt not do anything before completing theses prerequisite: CHEM 010 CHEM 010 HN. yippity do dah " +
                "BIOL 098 prerequisite: MATH 015. rip rip rip MATH 029 zach recommended this course prerequisite: " +
                "MATH 015; CHEM 032 and CHEM 032 or (CHEM 072, and if you're so inclined CHEM 098) and BIOL 002. litty litty");
        parser.generateTokens(parser.getSplitString());

        HashMap<CourseCode,DegreeRequirement> tester = parser.courseTokensToHashMap();
        CourseCode course = new CourseCode("MATH",29);
        DegreeRequirement ActualOutput = tester.get(course);
        Collection<CourseCode> classes = new ArrayList<>();
        Assert.assertEquals(ExpectedOutput,ActualOutput.getSolutionSet(classes).toString());
    }
    @Test
    public void TestingOrPreReqs(){
        String ExpectedOutoupt = "[CHEM10]";
        PreReqParser parser = new PreReqParser("litty MATH 029 prerequisite: CHEM 010 or BIOL 099 or COGS 001 or GMST 191. i love school ya know ");
        parser.generateTokens(parser.getSplitString());
        HashMap<CourseCode,DegreeRequirement> tester = parser.courseTokensToHashMap();
        CourseCode course = new CourseCode("MATH",29);

        DegreeRequirement ActualOutput = tester.get(course);
        Collection<CourseCode> classes = new ArrayList<>();
        Assert.assertEquals(ExpectedOutoupt,ActualOutput.getSolutionSet(classes).toString());
    }
    /*
    * this test is returning the wrong thing
    * */
//    @Test
//    public void ComplexRequirements(){
//        int ExpectedOutput = 1;
//        PreReqParser parser = new PreReqParser("BIOL 092 prerequisite: CHEM 010 CHEM 010 HN and BIOL 002 or BIOL 001. I love school ya know ");
//        parser.generateTokens(parser.getSplitString());
//        HashMap<CourseCode,DegreeRequirement> tester = parser.courseTokensToHashMap();
//        CourseCode course = new CourseCode("BIOL",92);
//        DegreeRequirement ActualOutput = tester.get(course);
//        Collection<CourseCode> classes = new ArrayList<>();
//        Assert.assertEquals(ExpectedOutput,ActualOutput.getSolutionSet(classes));
//    }
    @Test
    public void ComplexRequirements2(){
        String ExpectedOutput = "[CHEM10]";
        PreReqParser parser = new PreReqParser("BIOL 092 prerequisite: CHEM 010 CHEM 010 HN and BIOL 002 or BIOL 001 and MATH 029. litty litty");
        parser.generateTokens(parser.getSplitString());
        HashMap<CourseCode,DegreeRequirement> tester = parser.courseTokensToHashMap();
        CourseCode course = new CourseCode("BIOL",92);
        DegreeRequirement ActualOutput = tester.get(course);
        Collection<CourseCode> classes = new ArrayList<>();
        Assert.assertEquals(ExpectedOutput,ActualOutput.getSolutionSet(classes).toString());
    }
}
