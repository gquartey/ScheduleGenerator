package tests;

import edu.swarthmore.cs71.starfruit.classes.course.Course;
import edu.swarthmore.cs71.starfruit.scraper.CourseToken;
import edu.swarthmore.cs71.starfruit.scraper.TokenType;
import edu.swarthmore.cs71.starfruit.scraper.TranscriptParser;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collection;

public class ConfirmTranscriptParserFunctions {
    @Test
    public void emptyList() {
        int ExpectedOutput = 0;
        String string = "";
        TranscriptParser parser = new TranscriptParser(string);
        int ActualOutput = parser.tokenCount();
        Assert.assertEquals(ExpectedOutput,ActualOutput);
    }
    @Test
    public void inValidList(){
        int ExpectedOutput = 0;
        String string = "This wont' works because there isn't a season";
        TranscriptParser parser = new TranscriptParser(string);
        int ActualOutput = parser.tokenCount();
        Assert.assertEquals(ExpectedOutput,ActualOutput);
    }
    @Test
    public void identifySeason(){
        int ExpectedOutput = 1;
        String string = "Spring";
        TranscriptParser parser = new TranscriptParser(string);
        parser.simpleLexer(parser.getSplitString());
        int ActualOutput = parser.tokenCount();
        Assert.assertEquals(ExpectedOutput,ActualOutput);
    }
    @Test
    public void identifySeasonandYear(){
        int ExpectedOutput = 2;
        String string = "Spring 2017";
        TranscriptParser parser = new TranscriptParser(string);
        parser.simpleLexer(parser.getSplitString());
        int ActualOutput = parser.tokenCount();
        Assert.assertEquals(ExpectedOutput,ActualOutput);
    }
    @Test
    public void tokenCheck1(){
        TokenType ExpectedOutput = TokenType.Season;
        String string = "Spring";
        TranscriptParser parser = new TranscriptParser(string);
        parser.simpleLexer(parser.getSplitString());
        TokenType ActualOutput = parser.getLastToken();
        Assert.assertEquals(ExpectedOutput,ActualOutput);
    }
    @Test
    public void YearTokenCheck(){
        TokenType ExpectedOutput = TokenType.Year;
        String string = "Spring 2017";
        TranscriptParser parser = new TranscriptParser(string);
        parser.simpleLexer(parser.getSplitString());
        TokenType ActualOutput = parser.getLastToken();
        Assert.assertEquals(ExpectedOutput,ActualOutput);
    }
    @Test
    public void DeptTokenCheck(){
        TokenType ExpectedOutput = TokenType.DEPT;
        String string = "Spring 2017 COGS";
        TranscriptParser parser = new TranscriptParser(string);
        parser.simpleLexer(parser.getSplitString());
        TokenType ActualOutput = parser.getLastToken();
        Assert.assertEquals(ExpectedOutput,ActualOutput);
    }
    @Test
    public void CourseNumberCheck(){
        TokenType ExpectedOutput = TokenType.CourseNumber;
        String string = "Spring 2017 COGS 007";
        TranscriptParser parser = new TranscriptParser(string);
        parser.simpleLexer(parser.getSplitString());
        TokenType ActualOutput = parser.getLastToken();
        Assert.assertEquals(ExpectedOutput,ActualOutput);
    }
    @Test
    public void NameComponentCheck(){
        TokenType ExpectedOutput = TokenType.NameComponent;
        String string = "Spring 2017 COGS 007 litty city";
        TranscriptParser parser = new TranscriptParser(string);
        parser.simpleLexer(parser.getSplitString());
        TokenType ActualOutput = parser.getLastToken();
        Assert.assertEquals(ExpectedOutput,ActualOutput);
    }
    @Test
    public void NameComponentCheck2(){
        TokenType ExpectedOutput = TokenType.NameComponent;
        String string = "Spring 2017 COGS 007 litty city we're flowing";
        TranscriptParser parser = new TranscriptParser(string);
        parser.simpleLexer(parser.getSplitString());
        TokenType ActualOutput = parser.getLastToken();
        Assert.assertEquals(ExpectedOutput,ActualOutput);
    }
    @Test
    public void CreditTokenCheck(){
        TokenType ExpectedOutput = TokenType.Credits;
        String string = "Spring 2017 COGS 007 litty city we're flowing 1.0";
        TranscriptParser parser = new TranscriptParser(string);
        parser.simpleLexer(parser.getSplitString());
        TokenType ActualOutput = parser.getLastToken();
        Assert.assertEquals(ExpectedOutput,ActualOutput);
    }
    @Test
    public void NewClassTokenCheck(){
        TokenType ExpectedOutput = TokenType.DEPT;
        String string = "Spring 2017 COGS 007 litty city we're flowing 1.0 B+ BIOL";
        TranscriptParser parser = new TranscriptParser(string);
        parser.simpleLexer(parser.getSplitString());
        TokenType ActualOutput = parser.getLastToken();
        Assert.assertEquals(ExpectedOutput,ActualOutput);
    }
    // with the changes to make the parser an interface and remove the heavylifting fromt he constructor
    // these tests need to be modified to match the current structure
    @Test
    public void testCourseCreation(){
        int ExpectedOutput = 1;
        String string = "Spring 2017 COGS 007 litty city we're flowing 1.0 B+ BIOL";
        TranscriptParser parser = new TranscriptParser(string);
        parser.simpleLexer(parser.getSplitString());
        Collection<CourseToken> tokencourses = parser.createClassTokensFromTokens(parser.getTokenList());
        parser.setCourseBank(tokencourses);
        parser.courseTokenToCourseObject();
        Collection<Course> output = parser.getCourses();
        int ActualOutput = parser.getCourses().size();

        Assert.assertEquals(ExpectedOutput,ActualOutput);
    }

    @Test
    public void testCourseCreation2(){
        int ExpectedOutput = 2;
        String string = "Spring 2017 COGS 007 litty city we're flowing 1.0 B+ BIOL 009 test testing tesing 1.0 CR";
        TranscriptParser parser = new TranscriptParser(string);
        parser.simpleLexer(parser.getSplitString());
        Collection<CourseToken> tokencourses = parser.createClassTokensFromTokens(parser.getTokenList());
        parser.setCourseBank(tokencourses);
        parser.courseTokenToCourseObject();
        Collection<Course> output = parser.getCourses();
        int ActualOutput = parser.getCourses().size();
        Assert.assertEquals(ExpectedOutput,ActualOutput);
    }
    @Test
    public void testCourseCreation3(){
        int ExpectedOutput = 1;
        String string = "Spring 2017 COGS 007 litty city we're flowing 1.0 B+ BIOL 009 test testing tesing 1.0";
        TranscriptParser parser = new TranscriptParser(string);
        parser.simpleLexer(parser.getSplitString());
        Collection<CourseToken> tokencourses = parser.createClassTokensFromTokens(parser.getTokenList());
        parser.setCourseBank(tokencourses);
        parser.courseTokenToCourseObject();
        Collection<Course> output = parser.getCourses();
        int ActualOutput = parser.getCourses().size();
        Assert.assertEquals(ExpectedOutput,ActualOutput);
    }
}





