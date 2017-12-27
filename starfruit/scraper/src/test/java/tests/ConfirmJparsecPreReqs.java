package tests;

import edu.swarthmore.cs71.starfruit.scraper.TokenType;
import edu.swarthmore.cs71.starfruit.scraper.jparsec_work.DeptName;
import edu.swarthmore.cs71.starfruit.scraper.jparsec_work.MyToken;
import edu.swarthmore.cs71.starfruit.scraper.jparsec_work.Tokenizer;
import org.jparsec.Parser;
import org.jparsec.Parsers;
import org.jparsec.Token;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collection;
import java.util.Iterator;

import static edu.swarthmore.cs71.starfruit.scraper.jparsec_work.TokenUtils.deptNameParser;

public class ConfirmJparsecPreReqs {
    @Test
    public void RecognizeDept() throws Exception {
        String string = "BIOL";
        Tokenizer parser = new Tokenizer(string);
        TokenType ExpectedOutput = TokenType.DEPT;
        Collection<MyToken> Output = parser.stringToTokens();
        TokenType ActualOutput = Output.iterator().next().getTokenType();
        Assert.assertEquals(ExpectedOutput,ActualOutput);
    }
    @Test
    public void RecognizeCourseNum() throws Exception {
        String string = "099";
        Tokenizer parser = new Tokenizer(string);
        TokenType ExpectedOutput = TokenType.CourseNumber;
        Collection<MyToken> Output = parser.stringToTokens();
        TokenType ActualOutput = Output.iterator().next().getTokenType();
        Assert.assertEquals(ExpectedOutput,ActualOutput);
    }
    @Test
    public void RecognizeAnd(){
        String string = "and";
        Tokenizer parser = new Tokenizer(string);
        TokenType ExpectedOutput = TokenType.And;
        Collection<MyToken> Output = parser.stringToTokens();
        TokenType ActualOutput = Output.iterator().next().getTokenType();
        Assert.assertEquals(ExpectedOutput,ActualOutput);
    }
    @Test
    public void RecognizeOr(){
        String string = "or";
        Tokenizer parser = new Tokenizer(string);
        TokenType ExpectedOutput = TokenType.Or;
        Collection<MyToken> Output = parser.stringToTokens();
        TokenType ActualOutput = Output.iterator().next().getTokenType();
        Assert.assertEquals(ExpectedOutput,ActualOutput);
    }
    @Test
    public void CourseDeptandCoursenum() throws Exception {
        String string = "BIOL 099";
        Tokenizer parser = new Tokenizer(string);
        TokenType ExpectedOutput1 = TokenType.DEPT;
        Collection<MyToken> Output = parser.stringToTokens();
        Iterator<MyToken> iterator = Output.iterator();
        TokenType ActualOutput = iterator.next().getTokenType();
        Assert.assertEquals(ExpectedOutput1,ActualOutput);
        TokenType ExpectedOutput2 = TokenType.CourseNumber;
        ActualOutput = iterator.next().getTokenType();
        Assert.assertEquals(ExpectedOutput2,ActualOutput);
    }
    @Test
    public void RecognizeAllTokens(){
        String string = "BIOL 009 and BIOL 009";
        Tokenizer parser = new Tokenizer(string);
        int ExpectedOutput = 5;
        int ActualOuput = parser.stringToTokens().size();
        Assert.assertEquals(ExpectedOutput,ActualOuput);
    }
    @Test
    public void RecognizeOpenParen(){
        String string = "(BIOL";
        Tokenizer parser = new Tokenizer(string);
        TokenType ExpectedOutput = TokenType.OpenParen;
        Collection<MyToken> output= parser.stringToTokens();
        TokenType ActualOutput = output.iterator().next().getTokenType();
        Assert.assertEquals(ExpectedOutput,ActualOutput);
    }
    @Test
    public void RecognizeClosedParen(){
        String string = ")";
        Tokenizer parser = new Tokenizer(string);
        TokenType ExpectedOutput = TokenType.ClosedParen;
        Collection<MyToken> output= parser.stringToTokens();
        TokenType ActualOutput = output.iterator().next().getTokenType();
        Assert.assertEquals(ExpectedOutput,ActualOutput);
    }
    @Test
    public void RecognizeCommas(){
        String string = ",";
        Tokenizer parser = new Tokenizer(string);
        TokenType ExpectedOutput = TokenType.Comma;
        Collection<MyToken> output = parser.stringToTokens();
        TokenType ActualOutput = output.iterator().next().getTokenType();
        Assert.assertEquals(ExpectedOutput,ActualOutput);
    }
    @Test
    public void IgnoreSemiColon(){
        String string = "BIOL;";
        Tokenizer parser = new Tokenizer(string);
        TokenType ExpectedOutput = TokenType.DEPT;
        Collection<MyToken> output = parser.stringToTokens();
        TokenType ActualOutput = output.iterator().next().getTokenType();
        Assert.assertEquals(ExpectedOutput,ActualOutput);
        Assert.assertEquals("BIOL",output.iterator().next().getToken_string());
    }
    @Test
    public void RequirementsInParanthesis(){
        String string = " MATH 029 and MATH 027 or (BIOL 002 or BIOL 001 )";
        Tokenizer parser = new Tokenizer(string);
        Collection<MyToken> output = parser.stringToTokens();
        Iterator<MyToken> iterator = output.iterator();

        TokenType ExpectedOutput = TokenType.DEPT;
        TokenType ActualOutput = iterator.next().getTokenType();
        Assert.assertEquals(ExpectedOutput,ActualOutput);

        ExpectedOutput = TokenType.CourseNumber;
        ActualOutput = iterator.next().getTokenType();
        Assert.assertEquals(ExpectedOutput,ActualOutput);

        ExpectedOutput = TokenType.And;
        ActualOutput = iterator.next().getTokenType();
        Assert.assertEquals(ExpectedOutput,ActualOutput);

        ExpectedOutput = TokenType.DEPT;
        ActualOutput = iterator.next().getTokenType();
        Assert.assertEquals(ExpectedOutput,ActualOutput);

        ExpectedOutput = TokenType.CourseNumber;
        ActualOutput = iterator.next().getTokenType();
        Assert.assertEquals(ExpectedOutput,ActualOutput);

        ExpectedOutput = TokenType.Or;
        ActualOutput = iterator.next().getTokenType();
        Assert.assertEquals(ExpectedOutput,ActualOutput);

        ExpectedOutput = TokenType.OpenParen;
        ActualOutput = iterator.next().getTokenType();
        Assert.assertEquals(ExpectedOutput,ActualOutput);

        ExpectedOutput = TokenType.DEPT;
        ActualOutput = iterator.next().getTokenType();
        Assert.assertEquals(ExpectedOutput,ActualOutput);

        ExpectedOutput = TokenType.CourseNumber;
        ActualOutput = iterator.next().getTokenType();
        Assert.assertEquals(ExpectedOutput,ActualOutput);

        ExpectedOutput = TokenType.Or;
        ActualOutput = iterator.next().getTokenType();
        Assert.assertEquals(ExpectedOutput,ActualOutput);

        ExpectedOutput = TokenType.DEPT;
        ActualOutput = iterator.next().getTokenType();
        Assert.assertEquals(ExpectedOutput,ActualOutput);

        ExpectedOutput = TokenType.CourseNumber;
        ActualOutput = iterator.next().getTokenType();
        Assert.assertEquals(ExpectedOutput,ActualOutput);

        ExpectedOutput = TokenType.ClosedParen;
        ActualOutput = iterator.next().getTokenType();
        Assert.assertEquals(ExpectedOutput,ActualOutput);

    }
    @Test
    public void JTokenTest(){
        String string = "BIOL";
        Tokenizer parser = new Tokenizer(string);
        TokenType ExpectedOutput = TokenType.DEPT;
        Collection<Token> Output = parser.produceJTokens();
        TokenType ActualOuput = ((MyToken)(Output.iterator().next().value())).getTokenType();
        Assert.assertEquals(ExpectedOutput,ActualOuput);


       // Assert.assertEquals(ExpectedOutput,ActualOutput);
    }
    @Test
    public void deptNameParserTest(){
        String string = "BIOL";
        Tokenizer parser = new Tokenizer(string);
        TokenType ExpectedOutput = TokenType.DEPT;
        Collection<Token> Output = parser.produceJTokens();
        Parser<? extends Collection<Token>> fakeLexer = Parsers.constant(Output);

    }
}
