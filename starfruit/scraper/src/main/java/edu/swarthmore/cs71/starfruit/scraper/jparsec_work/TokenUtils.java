package edu.swarthmore.cs71.starfruit.scraper.jparsec_work;

import edu.swarthmore.cs71.starfruit.scraper.TokenType;
import org.jparsec.*;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertThat;

public class TokenUtils {
    public static Parser<DeptName> deptNameParser() {
        return Parsers.token(
                (Token token) ->
                {
                    MyToken realToken = (MyToken) token.value();
                    if (realToken.getTokenType() == TokenType.DEPT) {
                        return new DeptName(realToken.getToken_string());
                    } else {
                        return null;
                    }
                });
    }
    public static Parser<CourseNumber> courseNumberParser() {
        return Parsers.token(
                (Token token) ->
                {
                    MyToken realToken = (MyToken)(token.value());
                    if (realToken.getTokenType() == TokenType.CourseNumber) {
                        return new CourseNumber(realToken.getToken_string());
                    } else {
                        return null;
                    }
                });
    }
    public static Parser<CourseCode> courseCodeParser() {
        return Parsers.sequence(
                deptNameParser(),
                courseNumberParser(),
                ((deptName, courseNumber) ->
                        new CourseCode(deptName, courseNumber)
                ));
    }
    public static Parser<String> commaParser(){
        return Parsers.token(
                (Token token) ->
                {
                   MyToken realToken = (MyToken)(token.value());
                   if(realToken.getTokenType() == TokenType.Comma){
                       return realToken.getToken_string();
                   }else{
                       return null;
                   }
                }
        );
    }
    public static Parser<String> andParser(){
        return Parsers.token(
                (Token token) -> {
                    MyToken realToken = (MyToken)(token.value());
                    if(realToken.getTokenType() == TokenType.And){
                        return realToken.getToken_string();
                    }else{
                        return null;
                    }
                }
        );
    }
    public static Parser<String> orParser(){
        return Parsers.token(
                (Token token) -> {
                    MyToken realToken = (MyToken)(token.value());
                    if(realToken.getTokenType() == TokenType.Or){
                        return realToken.getToken_string();
                    }else{
                        return null;
                    }
                }
        );
    }
    public static Parser<List<CourseCode>> courseCodeListParser(){
        return courseCodeParser().many();
    }
    public static Parser<List<CourseCode>> courseCodeCommaListParser(){
        return Parsers.sequence(commaParser(), courseCodeParser().many());
    }

}
