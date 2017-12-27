package edu.swarthmore.cs71.starfruit.scraper.jparsec_work;

import com.google.common.base.CharMatcher;
import edu.swarthmore.cs71.starfruit.classes.requirements.DegreeRequirement;
import edu.swarthmore.cs71.starfruit.scraper.TokenType;
import org.jparsec.Parser;
import org.jparsec.Parsers;
import org.jparsec.Token;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

//TODO: change current tokens to be wrapped by jparsec toknes
//TODO: make simple classes for deptname and course number and so on
public class Tokenizer {
    private String CollegeBulletin;

    public Tokenizer(String collegeBulletin) {
        this.CollegeBulletin = collegeBulletin;
    }

    public Collection<MyToken> stringToTokens() {
        Collection<MyToken> tokens = new ArrayList<>();
        String[] preReqWords = this.CollegeBulletin.split("\\s");
        for (String i : preReqWords) {
            boolean containsParen = false;
            if (i.contains("(")) {
                tokens.add(new MyToken("(", TokenType.OpenParen));
                i = i.substring(1);
            }
            if(i.contains(";")){
                i = i.substring(0,i.length()-1);
            }
            if (i.length() == 4) {
                if(CharMatcher.JAVA_UPPER_CASE.matchesAllOf(i)){
                    tokens.add(new MyToken(i.substring(0,4),TokenType.DEPT));
                }
            }
            else{
                if(i.contains(")")){
                    containsParen = true;
                }
                if(i.contains(";")){
                    i.substring(0,i.length()-1);
                }
                if(i.equals("and")){
                    tokens.add(new MyToken("and",TokenType.And));
                }
                else if(i.equals("or")){
                    tokens.add(new MyToken("or", TokenType.Or));
                }
                else{
                    try{
                        Integer.valueOf(i.substring(0,3));
                        tokens.add(new MyToken(i.substring(0,3),TokenType.CourseNumber));
                        i = i.substring(3);
                    }catch(NumberFormatException e){
                        System.out.println(e);
                    }catch(IndexOutOfBoundsException e){
                        System.out.println(e);
                    }
                }
            }
            if(i.contains(",")){
                tokens.add(new MyToken(",",TokenType.Comma));
            }
            if(containsParen){
                tokens.add(new MyToken(")",TokenType.ClosedParen));
            }



        }
        return tokens;
    }
    public List<Token> produceJTokens(){
        List<Token> JTokens = new ArrayList<>();
        Collection<MyToken> tokens = stringToTokens();
        for(MyToken i: tokens){
            JTokens.add(new Token(0,i.getToken_string().length(),i));
        }
        return JTokens;
    }
    public DegreeRequirement parseRequirements(List<Token> JparsecTokens){
        DegreeRequirement requirement = null;
        Parser<? extends Collection<Token>> fakeLexer = Parsers.constant(JparsecTokens);
         

        return requirement;
    }
}
