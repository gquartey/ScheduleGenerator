package edu.swarthmore.cs71.starfruit.scraper;



/*This is our transcript parser
* This parser takes the text from a pdf, passed in as a string and parses it for the
* Courses that the user has completed by tokenize the strings and creating course objects
* from the tokens produced*/

import edu.swarthmore.cs71.starfruit.classes.course.Course;

import java.util.ArrayList;
import java.util.Collection;

public class TranscriptParser{
    private String text;
    private String[] splitString;
    private TokenBank tokenBank;
    private Collection<CourseToken> courseBank;
    private Collection<Course> courseObjects;

    public TranscriptParser(String text) {
        this.text = text;
        this.splitString = this.text.split("\\s");
        this.tokenBank = new TokenBank();
        this.courseBank = new ArrayList<>();
        this.courseObjects = new ArrayList<>();

    }

    public TokenType getLastToken() {
        return tokenBank.getLastToken();
    }

    public int tokenCount(){
        return tokenBank.size();
    }

    public String[] getSplitString() {
        return splitString;
    }

    public ArrayList<BuilderToken> getTokenList() {
        return tokenBank.getTokenList();
    }

    public void simpleLexer(String[] words) {
        ArrayList<String> STRING_TO_IGNORE = new ArrayList<>();
        STRING_TO_IGNORE.add("Total"); STRING_TO_IGNORE.add("Earned");
        STRING_TO_IGNORE.add("Credits"); STRING_TO_IGNORE.add("*");
        Boolean Classflag = false;
        for (int i = 0; i < words.length; i++) {
            if (!STRING_TO_IGNORE.contains(words[i])) {
                if (words[i].equals("Fall") || words[i].equals("Spring")) {
                    this.tokenBank.add(new BuilderToken.Season(words[i]));
                } else if (tokenBank.getLastToken() != null) {
                    if (tokenBank.getLastToken().equals(TokenType.Season)) {

                        this.tokenBank.add(new BuilderToken.Year(words[i]));

                    } else if (tokenBank.getLastToken().equals(TokenType.DEPT)) {
                        this.tokenBank.add(new BuilderToken.CourseNumber(words[i]));

                    } else if (tokenBank.getLastToken().equals(TokenType.Credits)) {
                        if(words[i] == "IN" && words[i+1] == "PROGRESS"){
                            this.tokenBank.add(new BuilderToken.Grade(words[i]+" "+words[i+1]));
                            i++;
                        }else {
                            this.tokenBank.add(new BuilderToken.Grade(words[i]));
                        }
                    } else if (tokenBank.getLastToken().equals(TokenType.Year) || tokenBank.getLastToken().equals(TokenType.Grade)) {
                        if (words[i].equals("IN") && words[i + 1].equals("PROGRESS") && words[i + 2].equals("WORK")) {
                            i = i + 2;
                        }
                        if (words[i].equals("END") && words[i + 1].equals("OF") && words[i + 2].equals("TRANSCRIPT")) {
                            tokenBank.done();
                            break;
                        }
                        this.tokenBank.add(new BuilderToken.Dept(words[i]));
                    } else {
                        try {
                            Float.parseFloat(words[i]);
                            this.tokenBank.add(new BuilderToken.Credits(words[i]));
                        } catch (NumberFormatException e) {
                            this.tokenBank.add(new BuilderToken.ClassNameComponent(words[i]));
                        }
                    }
                }
            }
        }

        }
    // takes a token list iterates through creating coursetokens from the token list
    public Collection<CourseToken> createClassTokensFromTokens(ArrayList<BuilderToken> tokenList){
        final ArrayList<BuilderToken> staticTokenList = tokenList;
        ArrayList<CourseToken> courseTokenList = new ArrayList<>();
        CourseToken token = new CourseToken();
        if(staticTokenList.size() == 0){
            return courseTokenList;
        }else{
            while(!token.getComplete() && tokenList.size() != 0){
                BuilderToken item = staticTokenList.remove(0);
                token.addToken(item);
            }
            ArrayList<CourseToken> tokenWrapper = new ArrayList<>();
            tokenWrapper.add(token);
            tokenWrapper.addAll(createClassTokensFromTokens(staticTokenList));
            return tokenWrapper;
        }
    }

    public void setCourseBank(Collection<CourseToken> courseBank) {
        this.courseBank = courseBank;
    }

    // create a course object from the list of course tokens
    public void courseTokenToCourseObject(){
        Collection<Course> constructedCourses = new ArrayList<>();
        for(CourseToken i: this.courseBank){
            if(i.getComplete()) {
                Course course = i.convertToCourse();
                if(course != null){
                constructedCourses.add(i.convertToCourse());
                }
            }
        }
        this.courseObjects = constructedCourses;
        //return constructedCourses;
    }
    // returns all the courses made from the parsed string
    public Collection<Course> getCourses(){
        return this.courseObjects;
        }
    }


