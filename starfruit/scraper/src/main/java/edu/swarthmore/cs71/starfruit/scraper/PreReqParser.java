package edu.swarthmore.cs71.starfruit.scraper;

import com.google.common.base.CharMatcher;
import edu.swarthmore.cs71.starfruit.classes.course.Course;
import edu.swarthmore.cs71.starfruit.classes.requirements.AndRequirement;
import edu.swarthmore.cs71.starfruit.classes.requirements.CourseRequirement;
import edu.swarthmore.cs71.starfruit.classes.requirements.DegreeRequirement;
import edu.swarthmore.cs71.starfruit.scraper.jparsec_work.CourseCode;

import java.util.*;
/*
* This is the pre-req parser
* this parser takes a string the represents the college bulletin
* and iterates through extracting the relevant information to construct a hashmap
* that maps the department and course code, which represents a class
* to a collection of classes that need to be taken before the class
* that is the key to the hashmap
* Currently this does not accurately represent optional pre-reqs and treats them
* all as mandatory*/
public class PreReqParser {
    String[] splitString;
    TokenBank tokenBank;
    String end = "Directions to Swarthmore College";
    public PreReqParser(String PDF_TEXT) {
        this.splitString = PDF_TEXT.split("\\s");
        this.tokenBank = new TokenBank();

    }
    public int tokenBankSize(){
        return this.tokenBank.size();
    }

    public String[] getSplitString() {
        return splitString;
    }


    // this method iterates through he string list until you hit a parsable section
    public void generateTokens(String[] body){
        for(int i = 0; i < body.length-1 ; i ++){
            if(body[i].length() == 4){
                if(body[i+1].length() >= 3){
                    try{
                        Integer.valueOf(body[i+1].substring(0,3));
                        this.tokenBank.add(new BuilderToken.Dept(body[i]));
                        this.tokenBank.add(new BuilderToken.CourseNumber(body[i+1].substring(0,3)));
                        if(!(i > body.length)) {
                            tokenizePreReqFlag(Arrays.copyOfRange(body, i + 1, body.length));
                            break;
                        }
                    }catch (NumberFormatException e){
                        //System.out.println("not a number");
                    }
                }
            }
        }
    }
    // iterates through to find the pre-req flag, if there is no flag then then it moves onto the next
    // class because that means the course doesn't contain pre-reqs
    private void tokenizePreReqFlag(String [] body) {
        for (int i = 0; i < body.length - 2; i++) {
            if (body[i].equals("prerequisite:")) {
                // call a function that now stores pre reqs
                this.tokenBank.add(new BuilderToken.PreReq(body[i].substring(0,body[i].length()-1)));
                tokenizePreReqs(Arrays.copyOfRange(body,i+1,body.length));
                break;
            } else if (body[i].length() == 4) {
                if (body[i + 1].length() >= 3) {
                    try {
                        Integer.valueOf(body[i + 1].substring(0, 3));
                        this.tokenBank.add(new BuilderToken.Dept(body[i]));
                        tokenizePreReqFlag(Arrays.copyOfRange(body, i + 2, body.length));
                        break;
                    } catch (NumberFormatException e) {
                    }
                }
            }
        }
    }
    // tokenizes all the pre reqs for a specific class
    // changes this to simply collect the entire sentence so i can run a classifier on the information
    private void tokenizePreReqs(String[] body){
        Boolean InsertParen = false;
        for(int i = 0; i< body.length-2; i++){
            if(body[i].length() >= 3){
                if(body[i].contains("(")){
                    tokenBank.add(new BuilderToken.OpenParen("("));
                    body[i] = body[i].substring(1,body[i].length());
                    tokenizePreReqs(Arrays.copyOfRange(body, i, body.length));
                }
                String tester = body[i].substring(0,3);
                try{
                    Integer.valueOf(tester);
                    tokenBank.add(new BuilderToken.CourseNumber(tester));
                    if(body[i].contains(".")){
                        tokenBank.add(new BuilderToken.PreReqEnd("END"));
                        generateTokens(Arrays.copyOfRange(body,i+1,body.length));
                        break;
                    }else{
                        tokenizePreReqs(Arrays.copyOfRange(body,i+1,body.length));
                        break;
                    }

                }catch(NumberFormatException e){
                    /* this conditional needs to be fixed so it's open to extension, right now it's terribly
                    * bad coding habit but works
                    * */
                    if(CharMatcher.JAVA_UPPER_CASE.matchesAllOf(body[i])) {
                        tokenBank.add(new BuilderToken.Dept(body[i]));
                        tokenizePreReqs(Arrays.copyOfRange(body, i + 1, body.length));
                        break;
                    }else if(body[i].contains(".")){
                        tokenBank.add(new BuilderToken.DescripComp(body[i].substring(0,body[i].length()-1)));
                        tokenBank.add(new BuilderToken.PreReqEnd("END"));
                        generateTokens(Arrays.copyOfRange(body,i+1,body.length));
                        break;
                    }else{
                        if(body[i].contains("or ")){
                            tokenBank.add(new BuilderToken.Or(body[i]));
                        }else {
                            tokenBank.add(new BuilderToken.DescripComp(body[i]));
                        }
                    }

                    }
                }else if(body[i].contains("or")){
                    tokenBank.add(new BuilderToken.Or("or"));
            }
            }
        }
    public Collection<String> checkDuplicates(Collection<String> ORINGIAL_LIST){
        Collection<String> listSansDuplicates = new ArrayList<>();
        HashMap<String, String> CHECKER = new HashMap<>();
        for(String i: ORINGIAL_LIST ){
            if(!CHECKER.containsKey(i)){
                listSansDuplicates.add(i);
                CHECKER.put(i,i);
            }
        }

        return listSansDuplicates;
    }
    private Collection<String> preReqListMaker(List<BuilderToken> tokens){
        Collection<String> courseList = new ArrayList<>();
        String newCourse = "";
        Boolean skip = false;
        for(BuilderToken i: tokens){
             if( i.returnType().equals(TokenType.DEPT) || i.returnType().equals(TokenType.CourseNumber)){
                newCourse += i.returnString();
                if(i.returnType().equals(TokenType.CourseNumber) && newCourse.length() == 7){
                    if(!skip) {
                        courseList.add(newCourse);
                    }else{
                        skip = false;
                    }
                    newCourse = "";
                }else if (newCourse.length() > 7 || newCourse.length() == 6){
                    newCourse = "";
                }
            }else if(i.returnType() == TokenType.Or){
                 skip = true;
             }else if(i.returnType() == TokenType.PreReqEnd){
                return checkDuplicates(courseList);
            }
        }
        return checkDuplicates(courseList);
    }
    public edu.swarthmore.cs71.starfruit.classes.course.CourseCode courseCodeMaker(String preReq){
        return new edu.swarthmore.cs71.starfruit.classes.course.CourseCode(preReq.substring(0,4),Integer.valueOf(preReq.substring(4)));

    }
    public HashMap<edu.swarthmore.cs71.starfruit.classes.course.CourseCode,DegreeRequirement> courseTokensToHashMap(){
        HashMap<String,Collection<String>> StrPreReqs = new HashMap<>();
        HashMap<edu.swarthmore.cs71.starfruit.classes.course.CourseCode,DegreeRequirement> RetrPreReqs = new HashMap<>();
        ArrayList<BuilderToken> builderTokens = this.tokenBank.getTokenList();
        for(int i = 0; i < builderTokens.size()-2; i++){

            if(builderTokens.get(i+2).returnType().equals(TokenType.PreReq)){
                String course = builderTokens.get(i).returnString() + builderTokens.get(i+1).returnString();
                List<BuilderToken> tokens = builderTokens.subList(i+3,builderTokens.size());
                StrPreReqs.put(course, preReqListMaker(tokens));
            }
        }
        for(String i: StrPreReqs.keySet()){
            if( StrPreReqs.get(i).size() > 1){
                RetrPreReqs.put(courseCodeMaker(i),new CourseRequirement(courseCodeMaker(StrPreReqs.get(i).iterator().next())));
            }else{
                Collection<DegreeRequirement> courseCodeCollection = new ArrayList<>();
                for(String j: StrPreReqs.get(i)){
                    courseCodeCollection.add(new CourseRequirement(courseCodeMaker(j)));
                }
                RetrPreReqs.put(courseCodeMaker(i),new AndRequirement(courseCodeCollection));
            }
        }
        return RetrPreReqs;
    }

}
